package app.app.services;

import app.app.coreClasses.genericCrudSuperClasses.CrudGenericService;
import app.app.dto.entrada.EditoraDTOIn;
import app.app.dto.saida.EditoraDTOOut;
import app.app.entities.EditoraEntity;
import app.app.repositories.EditoraRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class EditoraService extends CrudGenericService<
        EditoraEntity,
        EditoraRepository,
        EditoraDTOIn,
        EditoraDTOOut> {
    @Override
    public String register(EditoraDTOIn objectIn) throws Exception {
        return genericRegister(objectIn, EditoraEntity.class);
    }

    @Override
    public List<EditoraDTOOut> find(Long quantity, String order) throws Exception {
        return genericFind(quantity, order, EditoraDTOOut.class);
    }

    @Override
    public EditoraDTOOut findById(UUID id) throws Exception {
        return genericFindById(id, EditoraDTOOut.class);
    }

    @Override
    public String update(EditoraDTOIn objectIn) throws Exception {
        return genericUpdate(objectIn, EditoraEntity.class);
    }

    @Override
    public String delete(UUID uuid) throws Exception {
        return genericDelete(uuid);
    }

    @Override
    public UUID findByAttribute(EditoraDTOIn objectIn) {
        return null;
    }

    @Override
    public EditoraEntity handleRelatedEntities(EditoraEntity entityToPersist) {
        return entityToPersist;
    }

    @Override
    public EditoraEntity applyBusinessRules(EditoraEntity entityToPersist) {
        return entityToPersist;
    }
}
