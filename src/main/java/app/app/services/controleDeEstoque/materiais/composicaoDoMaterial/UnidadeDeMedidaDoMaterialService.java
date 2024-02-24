package com.sismanut.sismanut.services.controleDeEstoque.materiais.composicaoDoMaterial;

import com.sismanut.sismanut.coreClasses.genericCrudSuperClasses.CrudGenericService;
import com.sismanut.sismanut.dto.controleDeEstoque.entrada.material.composicaoDoMaterial.UnidadeDeMedidaDoMaterialDTOIn;
import com.sismanut.sismanut.dto.controleDeEstoque.saida.material.composicaoDoMaterial.UnidadeDeMedidaDoMaterialDTOOut;
import com.sismanut.sismanut.entities.controleDeEstoque.materiais.classesDeComposicao.UnidadeDeMedidaDoMaterialEntity;
import com.sismanut.sismanut.repositories.controleDeEstoque.materiais.composicaoDoMaterialRepository.UnidadeDeMedidaDoMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UnidadeDeMedidaDoMaterialService extends CrudGenericService<
        UnidadeDeMedidaDoMaterialEntity,
        UnidadeDeMedidaDoMaterialRepository,
        UnidadeDeMedidaDoMaterialDTOIn,
        UnidadeDeMedidaDoMaterialDTOOut> {
    @Autowired
    UnidadeDeMedidaDoMaterialRepository repository;

    @Override
    public String register(UnidadeDeMedidaDoMaterialDTOIn objectIn) throws Exception {
        return genericRegister(
                objectIn,
                UnidadeDeMedidaDoMaterialEntity.class
        );
    }

    @Override
    public List<UnidadeDeMedidaDoMaterialDTOOut> find(Long quantity, String order) throws Exception {
        return this.genericFind(
                quantity,
                order,
                UnidadeDeMedidaDoMaterialDTOOut.class
        );
    }

    @Override
    public UnidadeDeMedidaDoMaterialDTOOut findById(UUID id) throws Exception {
        return this.genericFindById(
                id,
                UnidadeDeMedidaDoMaterialDTOOut.class
        );
    }

    @Override
    public String update(UnidadeDeMedidaDoMaterialDTOIn objectIn) throws Exception {
        return genericUpdate(
                objectIn,
                UnidadeDeMedidaDoMaterialEntity.class
        );
    }

    @Override
    public String delete(UUID uuid) throws Exception {
        return genericDelete(uuid);
    }

    @Override
    public UUID findByAttribute(UnidadeDeMedidaDoMaterialDTOIn objectIn) {

        if(this.repository.verificaSeExistePorAtributo(objectIn.getUnidadeDeMedida()))
            return this.repository.buscarIdPorNome(objectIn.getUnidadeDeMedida());

        return null;
    }

    @Override
    public UnidadeDeMedidaDoMaterialEntity handleRelatedEntities(UnidadeDeMedidaDoMaterialEntity entityToPersist) {
        return null;
    }

    @Override
    public UnidadeDeMedidaDoMaterialEntity applyBusinessRules(UnidadeDeMedidaDoMaterialEntity entityToPersist) {
        return null;
    }
}
