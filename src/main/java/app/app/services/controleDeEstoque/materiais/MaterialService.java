package com.sismanut.sismanut.services.controleDeEstoque.materiais;

import com.sismanut.sismanut.coreClasses.genericCrudSuperClasses.CrudGenericService;
import com.sismanut.sismanut.dto.controleDeEstoque.entrada.material.MaterialDTOIn;
import com.sismanut.sismanut.dto.controleDeEstoque.saida.material.MaterialDTOOut;
import com.sismanut.sismanut.entities.controleDeEstoque.materiais.MaterialEntity;
import com.sismanut.sismanut.entities.controleDeEstoque.materiais.classesDeComposicao.VariacaoDoMaterialEntity;
import com.sismanut.sismanut.entities.controleDeEstoque.materiais.classesDeComposicao.caracteristicas.CorDoMaterialEntity;
import com.sismanut.sismanut.repositories.controleDeEstoque.materiais.MaterialRepository;
import com.sismanut.sismanut.repositories.controleDeEstoque.materiais.composicaoDoMaterialRepository.*;
import com.sismanut.sismanut.repositories.controleDeEstoque.materiais.composicaoDoMaterialRepository.caracteristicas.CorDoMaterialRepository;
import com.sismanut.sismanut.repositories.controleDeEstoque.materiais.composicaoDoMaterialRepository.caracteristicas.MarcaDoMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class MaterialService extends CrudGenericService<
        MaterialEntity,
        MaterialRepository,
        MaterialDTOIn,
        MaterialDTOOut> {
    @Autowired
    MaterialRepository repository;
    @Autowired
    VariacaoDoMaterialRepository variacaoRepository;
    @Autowired
    CorDoMaterialRepository corRepository;
    @Autowired
    MarcaDoMaterialRepository marcaRepository;
    @Autowired
    UnidadeDeMedidaDoMaterialRepository unidMedRepository;
    @Autowired
    LinkDeReferenciaRepository linkRepository;
    @Autowired
    OpcaoDoMaterialRepository opcaoRepository;
    @Override
    public String register(MaterialDTOIn objectIn) throws Exception {
        return genericRegister(
                objectIn,
                MaterialEntity.class
        );
    }

    @Override
    public List<MaterialDTOOut> find(Long quantity, String order) throws Exception {
        return genericFind(
                quantity,
                order,
                MaterialDTOOut.class
        );
    }

    @Override
    public MaterialDTOOut findById(UUID id) throws Exception {
        return genericFindById(
                id,
                MaterialDTOOut.class
        );
    }

    @Override
    public String update(MaterialDTOIn objectIn) throws Exception {
        return genericUpdate(
                objectIn,
                MaterialEntity.class
        );
    }

    @Override
    public String delete(UUID uuid) throws Exception {
        return genericDelete(uuid);
    }

    @Override
    public UUID findByAttribute(MaterialDTOIn objectIn) {
        return null;
    }

    @Override
    public MaterialEntity handleRelatedEntities(MaterialEntity entityToPersist) {

        List<VariacaoDoMaterialEntity> variacoesPersistir = new ArrayList<>();

        /*
        * Salva as cores e marcas caso nao estejam no banco de dados
        * */
        if(entityToPersist.getVariacoes() != null &&
                !entityToPersist.getVariacoes().isEmpty()){
            entityToPersist.getVariacoes().forEach(v -> {
                boolean adicionarVariacao = false;

                if(v.getCor() != null &&
                        !corRepository.verificaSeExistePorNome(v.getCor().getNome())){
                    v.getCor().setId(UUID.randomUUID());
                    corRepository.save(v.getCor());
                    v.getCor().setStatus(true);
                    adicionarVariacao = true;
                } else if(v.getCor() != null &&
                        v.getCor().getId() != null &&
                        !corRepository.existsById(v.getCor().getId())){
                    /*
                    TODO: IMPLEMENTANDO ISSO!!!
                     */
                }

                if(v.getMarca() != null
                        && !marcaRepository.verificaSeExistePorAtributo(v.getMarca().getNome())){
                    v.getMarca().setId(UUID.randomUUID());
                    marcaRepository.save((v.getMarca()));
                    v.getMarca().setStatus(true);
                    adicionarVariacao = true;
                }

                if(adicionarVariacao){
                    v.setId(UUID.randomUUID());
                    variacaoRepository.save(v);
                    variacoesPersistir.add(v);
                }
            });
        }

        entityToPersist.setVariacoes(variacoesPersistir);

        /*
         * Salva as unidades de medida caso nao estejam no banco de dados
         * */
//        entityToPersist.getUnidadesDeMedida().forEach(u -> {
//            if(!unidMedRepository.verificaSeExistePorAtributo(u.getNome()))
//                unidMedRepository.save();
//        });

        return entityToPersist;
    }

    @Override
    public MaterialEntity applyBusinessRules(MaterialEntity entityToPersist) {
        return entityToPersist;
    }
}
