package com.sismanut.sismanut.services.controleDeEstoque.materiais.composicaoDoMaterial;

import com.sismanut.sismanut.coreClasses.genericCrudSuperClasses.CrudGenericService;
import com.sismanut.sismanut.dto.controleDeEstoque.entrada.material.composicaoDoMaterial.MarcaDoMaterialDTOIn;
import com.sismanut.sismanut.dto.controleDeEstoque.saida.material.composicaoDoMaterial.MarcaDoMaterialDTOOut;
import com.sismanut.sismanut.entities.controleDeEstoque.materiais.classesDeComposicao.caracteristicas.MarcaDoMaterialEntity;
import com.sismanut.sismanut.repositories.controleDeEstoque.materiais.composicaoDoMaterialRepository.caracteristicas.MarcaDoMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MarcaDoMaterialService extends CrudGenericService<
        MarcaDoMaterialEntity,
        MarcaDoMaterialRepository,
        MarcaDoMaterialDTOIn,
        MarcaDoMaterialDTOOut> {
    @Autowired
    MarcaDoMaterialRepository repository;
    @Override
    public String register(MarcaDoMaterialDTOIn objectIn) throws Exception {
        return this.genericRegister(
                objectIn,
                MarcaDoMaterialEntity.class
        );
    }

    @Override
    public List<MarcaDoMaterialDTOOut> find(Long quantity, String order) throws Exception {
        return this.genericFind(
                quantity,
                order,
                MarcaDoMaterialDTOOut.class
        );
    }

    @Override
    public MarcaDoMaterialDTOOut findById(UUID id) throws Exception {
        return this.genericFindById(
                id,
                MarcaDoMaterialDTOOut.class
        );
    }

    @Override
    public String update(MarcaDoMaterialDTOIn objectIn) throws Exception {
        return this.genericUpdate(
                objectIn,
                MarcaDoMaterialEntity.class
        );
    }

    @Override
    public String delete(UUID uuid) throws Exception {
        return this.genericDelete(
                uuid
        );
    }

    @Override
    public UUID findByAttribute(MarcaDoMaterialDTOIn objectIn) {

        if(repository.verificaSeExistePorAtributo(objectIn.getNome()))
            return repository.buscarIdPorNome(objectIn.getNome());

        return null;
    }

    @Override
    public MarcaDoMaterialEntity handleRelatedEntities(MarcaDoMaterialEntity entityToPersist) {
        return null;
    }

    @Override
    public MarcaDoMaterialEntity applyBusinessRules(MarcaDoMaterialEntity entityToPersist) {
        return null;
    }
}
