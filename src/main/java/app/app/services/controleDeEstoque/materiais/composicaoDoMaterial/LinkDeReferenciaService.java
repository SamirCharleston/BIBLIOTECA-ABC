package com.sismanut.sismanut.services.controleDeEstoque.materiais.composicaoDoMaterial;

import com.sismanut.sismanut.coreClasses.genericCrudSuperClasses.CrudGenericService;
import com.sismanut.sismanut.dto.controleDeEstoque.entrada.material.composicaoDoMaterial.LinkDeReferenciaDTOIn;
import com.sismanut.sismanut.dto.controleDeEstoque.saida.material.composicaoDoMaterial.LinkDeReferenciaDTOOut;
import com.sismanut.sismanut.entities.controleDeEstoque.materiais.classesDeComposicao.LinkDeReferenciaEntity;
import com.sismanut.sismanut.repositories.controleDeEstoque.materiais.composicaoDoMaterialRepository.LinkDeReferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LinkDeReferenciaService extends CrudGenericService<
        LinkDeReferenciaEntity,
        LinkDeReferenciaRepository,
        LinkDeReferenciaDTOIn,
        LinkDeReferenciaDTOOut> {
    @Autowired
    LinkDeReferenciaRepository repository;
    @Override
    public String register(LinkDeReferenciaDTOIn objectIn) throws Exception {
        return
                this.genericRegister(
                        objectIn,
                        LinkDeReferenciaEntity.class
                );
    }

    @Override
    public List<LinkDeReferenciaDTOOut> find(Long quantity, String order) throws Exception {
        return
                this.genericFind(
                        quantity,
                        order,
                        LinkDeReferenciaDTOOut.class
                );
    }

    @Override
    public LinkDeReferenciaDTOOut findById(UUID id) throws Exception {
        return this.genericFindById(
                id,
                LinkDeReferenciaDTOOut.class
        );
    }

    @Override
    public String update(LinkDeReferenciaDTOIn objectIn) throws Exception {
        return genericUpdate(
                objectIn,
                LinkDeReferenciaEntity.class
        );
    }

    @Override
    public String delete(UUID uuid) throws Exception {
        return genericDelete(uuid);
    }

    @Override
    public UUID findByAttribute(LinkDeReferenciaDTOIn objectIn) {

        if(repository.verificaSeExistePorNome(objectIn.getLink()))
            return repository.buscarPorNome(objectIn.getLink());

        return null;
    }

    @Override
    public LinkDeReferenciaEntity handleRelatedEntities(LinkDeReferenciaEntity entityToPersist) {
        return null;
    }

    @Override
    public LinkDeReferenciaEntity applyBusinessRules(LinkDeReferenciaEntity entityToPersist) {
        return null;
    }
}