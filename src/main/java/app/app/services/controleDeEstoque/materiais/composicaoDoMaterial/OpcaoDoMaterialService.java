package com.sismanut.sismanut.services.controleDeEstoque.materiais.composicaoDoMaterial;

import com.sismanut.sismanut.coreClasses.genericCrudSuperClasses.CrudGenericService;
import com.sismanut.sismanut.dto.controleDeEstoque.entrada.material.composicaoDoMaterial.OpcaoDoMaterialDTOIn;
import com.sismanut.sismanut.dto.controleDeEstoque.saida.material.composicaoDoMaterial.OpcaoDoMaterialDTOOut;
import com.sismanut.sismanut.entities.controleDeEstoque.materiais.classesDeComposicao.OpcaoDoMaterialEntity;
import com.sismanut.sismanut.repositories.controleDeEstoque.materiais.composicaoDoMaterialRepository.OpcaoDoMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OpcaoDoMaterialService extends CrudGenericService<
        OpcaoDoMaterialEntity,
        OpcaoDoMaterialRepository,
        OpcaoDoMaterialDTOIn,
        OpcaoDoMaterialDTOOut> {
    @Autowired
    OpcaoDoMaterialRepository repository;

    @Override
    public String register(OpcaoDoMaterialDTOIn objectIn) throws Exception {
        return this.genericRegister(
                objectIn,
                OpcaoDoMaterialEntity.class
        );
    }

    @Override
    public List<OpcaoDoMaterialDTOOut> find(Long quantity, String order) throws Exception {
        return this.genericFind(
                quantity,
                order,
                OpcaoDoMaterialDTOOut.class
        );
    }

    @Override
    public OpcaoDoMaterialDTOOut findById(UUID id) throws Exception {
        return this.genericFindById(
                id,
                OpcaoDoMaterialDTOOut.class
        );
    }

    @Override
    public String update(OpcaoDoMaterialDTOIn objectIn) throws Exception {
        return this.genericUpdate(
                objectIn,
                OpcaoDoMaterialEntity.class
        );
    }

    @Override
    public String delete(UUID uuid) throws Exception {
        return genericDelete(uuid);
    }

    @Override
    public UUID findByAttribute(OpcaoDoMaterialDTOIn objectIn) {

        if(repository.verificaSeExistePorNome(objectIn.getNome()))
            return repository.buscarIdPorNome(objectIn.getNome());

        return null;
    }

    @Override
    public OpcaoDoMaterialEntity handleRelatedEntities(OpcaoDoMaterialEntity entityToPersist) {
        return null;
    }

    @Override
    public OpcaoDoMaterialEntity applyBusinessRules(OpcaoDoMaterialEntity entityToPersist) {
        return null;
    }
}
