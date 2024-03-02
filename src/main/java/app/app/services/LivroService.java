package app.app.services;

import app.app.coreClasses.genericCrudSuperClasses.CrudGenericService;
import app.app.dto.entrada.LivroDTOIn;
import app.app.dto.saida.LivroDTOOut;
import app.app.entities.LivroEntity;
import app.app.repositories.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class LivroService extends CrudGenericService<
        LivroEntity,
        LivroRepository,
        LivroDTOIn,
        LivroDTOOut> {
    @Override
    public String register(LivroDTOIn objectIn) throws Exception {
        return genericRegister(objectIn, LivroEntity.class);
    }

    @Override
    public List<LivroDTOOut> find(Long quantity, String order) throws Exception {
        return genericFind(quantity, order, LivroDTOOut.class);
    }

    @Override
    public LivroDTOOut findById(UUID id) throws Exception {
        return genericFindById(id, LivroDTOOut.class);
    }

    @Override
    public String update(LivroDTOIn objectIn) throws Exception {
        return genericUpdate(objectIn, LivroEntity.class);
    }

    @Override
    public String delete(UUID uuid) throws Exception {
        return genericDelete(uuid);
    }

    @Override
    public UUID findByAttribute(LivroDTOIn objectIn) {
        return null;
    }

    @Override
    public LivroEntity handleRelatedEntities(LivroEntity entityToPersist) {
        return entityToPersist;
    }

    @Override
    public LivroEntity applyBusinessRules(LivroEntity entityToPersist) {
        return entityToPersist;
    }
}
