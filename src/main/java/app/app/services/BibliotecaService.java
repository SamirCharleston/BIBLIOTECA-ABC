package app.app.services;

import app.app.coreClasses.genericCrudSuperClasses.CrudGenericService;
import app.app.dto.entrada.BibliotecaDTOIn;
import app.app.dto.saida.BibliotecaDTOOut;
import app.app.entities.BibliotecaEntity;
import app.app.repositories.BibliotecaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class BibliotecaService extends CrudGenericService<
        BibliotecaEntity,
        BibliotecaRepository,
        BibliotecaDTOIn,
        BibliotecaDTOOut
        > {
    @Override
    public String register(BibliotecaDTOIn objectIn) throws Exception {
        return genericRegister(objectIn, BibliotecaEntity.class);
    }

    @Override
    public List<BibliotecaDTOOut> find(Long quantity, String order) throws Exception {
        return genericFind(quantity, order, BibliotecaDTOOut.class);
    }

    @Override
    public BibliotecaDTOOut findById(UUID id) throws Exception {
        return genericFindById(id, BibliotecaDTOOut.class);
    }

    @Override
    public String update(BibliotecaDTOIn objectIn) throws Exception {
        return genericUpdate(objectIn, BibliotecaEntity.class);
    }

    @Override
    public String delete(UUID uuid) throws Exception {
        return genericDelete(uuid);
    }

    @Override
    public UUID findByAttribute(BibliotecaDTOIn objectIn) {
        return null;
    }

    @Override
    public BibliotecaEntity handleRelatedEntities(BibliotecaEntity entityToPersist) {
        return entityToPersist;
    }

    @Override
    public BibliotecaEntity applyBusinessRules(BibliotecaEntity entityToPersist) {
        return entityToPersist;
    }
}
