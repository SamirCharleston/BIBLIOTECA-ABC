package app.app.services;

import app.app.coreClasses.genericCrudSuperClasses.CrudGenericService;
import app.app.dto.entrada.AutorDTOIn;
import app.app.dto.saida.AutorDTOOut;
import app.app.entities.AutorEntity;
import app.app.repositories.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class AutorService extends CrudGenericService<AutorEntity, AutorRepository, AutorDTOIn, AutorDTOOut> {
    @Override
    public String register(AutorDTOIn objectIn) throws Exception {
        return genericRegister(objectIn, AutorEntity.class);
    }

    @Override
    public List<AutorDTOOut> find(Long quantity, String order) throws Exception {
        return genericFind(quantity, order, AutorDTOOut.class);
    }

    @Override
    public AutorDTOOut findById(UUID id) throws Exception {
        return genericFindById(id, AutorDTOOut.class);
    }

    @Override
    public String update(AutorDTOIn objectIn) throws Exception {
        return genericUpdate(objectIn, AutorEntity.class);
    }

    @Override
    public String delete(UUID uuid) throws Exception {
        return genericDelete(uuid);
    }

    @Override
    public UUID findByAttribute(AutorDTOIn objectIn) {
        return null;
    }

    @Override
    public AutorEntity handleRelatedEntities(AutorEntity entityToPersist) {
        return entityToPersist;
    }

    @Override
    public AutorEntity applyBusinessRules(AutorEntity entityToPersist) {
        return entityToPersist;
    }
}
