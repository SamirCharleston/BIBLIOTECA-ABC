package com.sismanut.sismanut.services;

import com.sismanut.sismanut.config.messageHandling.successMessages.SuccessMessages;
import com.sismanut.sismanut.dto.chamados.entrada.*;
import com.sismanut.sismanut.dto.chamados.saida.ChamadoCompletoDTOOut;
import com.sismanut.sismanut.entities.ChamadoDeLogisticaEntity;
import com.sismanut.sismanut.repositories.ChamadoDeLogisticaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ChamadoDeLogisticaService {
    @Autowired
    private ChamadoDeLogisticaRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    public ChamadoCompletoDTOOut buscarChamado(ChamadoGlpiDTOIn chamadoGlpiDTOIn) throws EntityNotFoundException{
        ChamadoDeLogisticaEntity chamadoDoBanco = repository.encontrarPorGlpi(chamadoGlpiDTOIn.getIdGlpi());

        if(chamadoDoBanco == null){
            throw new EntityNotFoundException("GLPI nao encontrado.");
        }

        return modelMapper.map(chamadoDoBanco, ChamadoCompletoDTOOut.class);
    }
    public String salvarChamado(ChamadoCompletoDTOIn chamadoCompletoDTOIn) throws DataIntegrityViolationException {
        if(repository.verificaSeExistePorGlpi(chamadoCompletoDTOIn.getIdGlpi())){
            throw new DataIntegrityViolationException("Este GLPI ja esta registrado.");
        }
        ChamadoDeLogisticaEntity chamadoDeLogisticaEntity = modelMapper.map(chamadoCompletoDTOIn, ChamadoDeLogisticaEntity.class);
        repository.save(chamadoDeLogisticaEntity);
        return SuccessMessages.SAVED;
    }
    public List<ChamadoCompletoDTOOut> buscarUltimosChamados(QuantidadeChamadosDTOIn quantidadeChamadosDTOIn)
            throws DataIntegrityViolationException {
        if(quantidadeChamadosDTOIn.getQuantidade() > 100){
            throw new DataIntegrityViolationException("Solicitacao maxima de 100 chamados.");
        }

        List<ChamadoDeLogisticaEntity> chamadosDoBanco = repository
                .buscarPorQuantidadeEOrdenacao(quantidadeChamadosDTOIn.getQuantidade(),
                quantidadeChamadosDTOIn.isOrdemDecrescente());
        if(chamadosDoBanco.isEmpty()){
            throw new DataIntegrityViolationException("Nao ha registro de chamados.");
        }

        List<ChamadoCompletoDTOOut> listaChamados = new ArrayList<>();

        chamadosDoBanco.forEach(chamado -> {
            listaChamados.add(modelMapper.map(chamado, ChamadoCompletoDTOOut.class));
        });

        return listaChamados;
    }
    public String atualizarChamado(ChamadoComNovoGlpiDTOIn chamadoCompletoDTOIn)
            throws DataIntegrityViolationException{

        ChamadoDeLogisticaEntity chamadoDoBanco = repository
                .encontrarPorGlpi(chamadoCompletoDTOIn.getIdGlpi());

        if(chamadoDoBanco == null){
            throw new DataIntegrityViolationException("Glpi nao encontrado.");
        }

        ChamadoDeLogisticaEntity chamadoAtualizado = modelMapper
                .map(chamadoCompletoDTOIn, ChamadoDeLogisticaEntity.class);
        chamadoAtualizado.setId(chamadoDoBanco.getId());

        if(chamadoCompletoDTOIn.getNovoIdGlpi() != null
                && repository.verificaSeExistePorGlpi(chamadoCompletoDTOIn.getNovoIdGlpi())){
            throw new DataIntegrityViolationException("O novo Glpi ja existe.");
        }

        if(chamadoCompletoDTOIn.getNovoIdGlpi() != null){
            chamadoAtualizado.setIdGlpi(chamadoCompletoDTOIn.getNovoIdGlpi());
        }

        repository.save(chamadoAtualizado);
        return SuccessMessages.UPDATED;
    }

    public String concluirChamado (ChamadoConcluidoDTOIn chamadoConcluido){
        ChamadoDeLogisticaEntity chamadoDoBanco = repository.encontrarPorGlpi(chamadoConcluido.getIdGlpi());
        if(chamadoDoBanco == null){
            throw new EntityNotFoundException("Glpi nao encontrado.");
        }

        if(chamadoDoBanco.isFoiConcluido() == chamadoConcluido.isFoiConcluido()){
            throw new DataIntegrityViolationException("Este chamado ja possui este status de conclusao.");
        }

        chamadoDoBanco.setFoiConcluido(chamadoConcluido.isFoiConcluido());
        repository.save(chamadoDoBanco);
        return SuccessMessages.UPDATED;
    }
    public String excluirChamado(ChamadoAExcluirDTOIn chamado){
        ChamadoDeLogisticaEntity chamadoDoBanco = repository.encontrarPorGlpi(chamado.getIdGlpi());
        if(chamadoDoBanco == null){
            throw new EntityNotFoundException("Glpi nao encontrado.");
        }
//        if(!chamadoDoBanco.isStatus()){
//            throw new DataIntegrityViolationException("Este chamado ja foi excluido.");
//        }
//        chamadoDoBanco.setStatus(false);
        repository.deleteById(chamadoDoBanco.getId());
        return SuccessMessages.DELETED;
    }
}
