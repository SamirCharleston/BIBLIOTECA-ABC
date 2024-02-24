package com.sismanut.sismanut.services;

import com.sismanut.sismanut.config.messageHandling.successMessages.SuccessMessages;
import com.sismanut.sismanut.dto.ordensDeServico.entrada.*;
import com.sismanut.sismanut.dto.ordensDeServico.saida.OrdemDeServicoCompletaDTOOut;
import com.sismanut.sismanut.entities.OrdemDeServicoEntity;
import com.sismanut.sismanut.repositories.OrdensDeServicoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrdemDeServicoService {
    @Autowired
    OrdensDeServicoRepository repository;
    @Autowired
    ModelMapper modelMapper;
    public OrdemDeServicoCompletaDTOOut buscarOrdemDeServico(OrdemDeServicoNumeroDTOIn ordemDeServicoNumero) throws EntityNotFoundException {
        OrdemDeServicoEntity oSDoBanco = repository.encontrarPorNumero(ordemDeServicoNumero.getNumeroDaOS());

        if(oSDoBanco == null){
            throw new EntityNotFoundException("Ordem de servico nao encontrada.");
        }

        return modelMapper.map(oSDoBanco, OrdemDeServicoCompletaDTOOut.class);
    }
    public List<OrdemDeServicoCompletaDTOOut> buscarOrdensDeServico(QuantidadeDeOrdemDeServicoDTOIn parametros)
            throws DataIntegrityViolationException{

        if(parametros.getQuantidade() > 100){
            throw new DataIntegrityViolationException("Solicitacao maxima de 100 ordens de servico.");
        }

        List<OrdemDeServicoEntity> oSDoBanco = repository
                .buscarPorQuantidadeEOrdenacao(parametros.getQuantidade(),
                        parametros.isOrdemDecrescente());
        if(oSDoBanco.isEmpty()){
            throw new DataIntegrityViolationException("Nao ha registro de ordens de servico.");
        }

        List<OrdemDeServicoCompletaDTOOut> listaOS = new ArrayList<>();

        oSDoBanco.forEach(chamado -> {
            listaOS.add(modelMapper.map(chamado, OrdemDeServicoCompletaDTOOut.class));
        });

        return listaOS;
    }
    public String salvarOS(OrdemDeServicoCadastroDTOIn ordemDeServico) throws DataIntegrityViolationException {
        if(repository.verificaSeExistePorOS(ordemDeServico.getNumeroDaOS())){
            throw new DataIntegrityViolationException("Esta ordem de servico ja esta registrada.");
        } else if(repository.verificaSeExistePorGlpi(ordemDeServico.getGlpiNumero())){
            throw new DataIntegrityViolationException("Este GLPI ja esta registrado.");
        }

        OrdemDeServicoEntity ordemDeServicoEntity = modelMapper
                .map(ordemDeServico, OrdemDeServicoEntity.class);
        repository.save(ordemDeServicoEntity);
        return SuccessMessages.SAVED;
    }
    public String atualizarOS(OSComNovoNumeroDTOIn ordemDeServico)
            throws DataIntegrityViolationException{

        OrdemDeServicoEntity ordemDeServicoDoBanco = repository
                .encontrarPorNumero(ordemDeServico.getNumeroDaOS());

        if(ordemDeServicoDoBanco == null){
            throw new DataIntegrityViolationException("Ordem de servico nao encontrada.");
        }

        OrdemDeServicoEntity ordemDeServicoAtualizada = modelMapper
                .map(ordemDeServico, OrdemDeServicoEntity.class);
        ordemDeServicoAtualizada.setId(ordemDeServicoDoBanco.getId());

        if(ordemDeServico.getNovoNumeroOS() != null
                && repository.verificaSeExistePorOS(ordemDeServico.getNovoNumeroOS())){
            throw new DataIntegrityViolationException("A ordem de serviço ja existe.");
        }

        if(!(repository.verificaSeExistePorGlpi(ordemDeServico.getGlpiNumero()) &&
                ordemDeServico.getGlpiNumero().equals(ordemDeServicoDoBanco.getGlpiNumero()))
        && repository.verificaSeExistePorGlpi(ordemDeServico.getGlpiNumero())){
            throw new DataIntegrityViolationException("O GLPI ja existe.");
        }

        if(ordemDeServico.getNovoNumeroOS() != null){
            ordemDeServicoAtualizada.setNumeroDaOS(ordemDeServico.getNovoNumeroOS());
        }

        repository.save(ordemDeServicoAtualizada);
        return SuccessMessages.UPDATED;
    }
    public String conclusaoDaOS(OrdemDeServicoConclusaoDTOIn ordemDeServico){
        OrdemDeServicoEntity ordemDeServicoDoBanco = repository
                .encontrarPorNumero(ordemDeServico.getNumeroDaOS());

        if(ordemDeServicoDoBanco == null){
            throw new DataIntegrityViolationException("Ordem de serviço não encontrada.");
        } else if (ordemDeServico.isConcluido() && ordemDeServico.getDataDeEntregaDaExecucao() == null) {
            throw new DateTimeException("Quando concluído deve conter a data de conclusão.");
        } else if(ordemDeServico.getDataDeEntregaDaExecucao().isAfter(LocalDate.now())){
            throw new DateTimeException("A data de conclusão não pode ser depois da data atual.");
        }

        ordemDeServicoDoBanco.setPagoIntegralmente(ordemDeServico.isPagoIntegralmente());
        ordemDeServicoDoBanco.setConcluido(ordemDeServico.isConcluido());

        if(ordemDeServico.isConcluido()
                && ordemDeServico.getDataDeEntregaDaExecucao()
                .isBefore(ordemDeServicoDoBanco.getDataDeTermino())
                || ordemDeServico.getDataDeEntregaDaExecucao()
                .isEqual(ordemDeServicoDoBanco.getDataDeTermino())){

            ordemDeServicoDoBanco.setDataDeEntregaDaExecucao(ordemDeServico.getDataDeEntregaDaExecucao());
            ordemDeServicoDoBanco.setConcluidaNoPrazo(true);
            repository.save(ordemDeServicoDoBanco);

            return SuccessMessages.FINISHED;
        } else {
            ordemDeServicoDoBanco.setConcluidaNoPrazo(false);
            repository.save(ordemDeServicoDoBanco);

            return SuccessMessages.UPDATED;
        }
    }
    public String excluirOS(OrdemDeServicoNumeroDTOIn numeroOSDTOIn){
        OrdemDeServicoEntity osDoBanco = repository.encontrarPorNumero(numeroOSDTOIn.getNumeroDaOS());
        if(osDoBanco == null){
            throw new EntityNotFoundException("Ordem de servico nao encontrada.");
        }
//        if(!chamadoDoBanco.isStatus()){
//            throw new DataIntegrityViolationException("Este chamado ja foi excluido.");
//        }
//        chamadoDoBanco.setStatus(false);
        repository.deleteById(osDoBanco.getId());
        return SuccessMessages.DELETED;
    }
}