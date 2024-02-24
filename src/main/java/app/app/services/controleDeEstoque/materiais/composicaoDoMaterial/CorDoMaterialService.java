package com.sismanut.sismanut.services.controleDeEstoque.materiais.composicaoDoMaterial;

import com.sismanut.sismanut.coreClasses.genericCrudSuperClasses.CrudGenericService;
import com.sismanut.sismanut.dto.controleDeEstoque.entrada.material.composicaoDoMaterial.CorDoMaterialDTOIn;
import com.sismanut.sismanut.dto.controleDeEstoque.saida.material.composicaoDoMaterial.CorDoMaterialDTOOut;
import com.sismanut.sismanut.entities.controleDeEstoque.materiais.classesDeComposicao.caracteristicas.CorDoMaterialEntity;
import com.sismanut.sismanut.repositories.controleDeEstoque.materiais.composicaoDoMaterialRepository.caracteristicas.CorDoMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CorDoMaterialService extends CrudGenericService<
        CorDoMaterialEntity,
        CorDoMaterialRepository,
        CorDoMaterialDTOIn,
        CorDoMaterialDTOOut>{
    @Autowired
    CorDoMaterialRepository repository;
    @Override
    public String register(CorDoMaterialDTOIn objectIn) throws Exception {
        return
                this.genericRegister(
                        objectIn,
                        CorDoMaterialEntity.class
                );
    }

    @Override
    public List<CorDoMaterialDTOOut> find(Long quantity, String order) throws Exception {
        return
                this.genericFind(
                        quantity,
                        order,
                        CorDoMaterialDTOOut.class
                );
    }

    @Override
    public CorDoMaterialDTOOut findById(UUID id) throws Exception {
        return this.genericFindById(
                id,
                CorDoMaterialDTOOut.class
        );
    }

    @Override
    public String update(CorDoMaterialDTOIn objectIn) throws Exception {
        return
                this.genericUpdate(
                        objectIn,
                        CorDoMaterialEntity.class
                );
    }

    @Override
    public String delete(UUID uuid) throws Exception {
        return this.genericDelete(uuid);
    }

    @Override
    public UUID findByAttribute(CorDoMaterialDTOIn objectIn) {

        if(repository.verificaSeExistePorNome(objectIn.getNome()))
            return repository.buscarPorNome(objectIn.getNome());

        return null;
    }

    @Override
    public CorDoMaterialEntity handleRelatedEntities(CorDoMaterialEntity entityToPersist) {
        return entityToPersist;
    }

    @Override
    public CorDoMaterialEntity applyBusinessRules(CorDoMaterialEntity entityToPersist) {
        return entityToPersist;
    }
}
