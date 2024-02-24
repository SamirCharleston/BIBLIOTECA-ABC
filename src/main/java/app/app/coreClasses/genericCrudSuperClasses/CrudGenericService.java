package app.app.coreClasses.genericCrudSuperClasses;

import app.app.config.customExceptions.EntityAlrealdyRegisteredException;
import app.app.config.customExceptions.EntityNotFoundException;
import app.app.config.customExceptions.EntityNullException;
import app.app.config.customExceptions.IDNotValidException;
import app.app.config.messageHandling.errorMessages.ExceptionMessages;
import app.app.config.messageHandling.successMessages.SuccessMessages;

import java.util.ArrayList;
import java.util.List;

public abstract class CrudGenericService<Entity extends CrudGenericEntity>{

    private List<Entity> entities = new ArrayList<>();
    public String save(Entity entity)
            throws EntityAlrealdyRegisteredException,
            EntityNullException{

        if(entity == null)
            throw new EntityNullException("Entity cannot be null");

        if(entity.getId() == null)
            throw new IDNotValidException("The Id must have a value");

        this.entities.forEach(e ->{
            if(e.getId().equals(entity.getId()))
                throw new EntityAlrealdyRegisteredException(ExceptionMessages.ENTITY_ALREADY_EXISTS);
        });

        this.entities.add(entity);

        return SuccessMessages.CREATED;
    }
    public Entity findById(Long id)
            throws EntityNotFoundException {

        if(id == null)
            throw new IDNotValidException("Id cannot be null");

        for(Entity e : this.entities){
            if(e.getId().equals(id))
                return e;
        }

        throw new EntityNotFoundException(ExceptionMessages.ENTITY_NOT_FOUND);
    }
    public List<Entity> listAll()
            throws EntityNotFoundException {

        if(this.entities.isEmpty())
            throw new EntityNotFoundException("There arent any saved entities.");

        return this.entities;
    }
    public String update(Entity entity)
            throws EntityNotFoundException,
            EntityNullException,
            IDNotValidException{

        if(entity == null)
            throw new EntityNullException("Entity cannot be null");

        if(entity.getId() == null)
            throw new IDNotValidException("The Id must have a value");

        for(Entity e : this.entities){
            if(e.getId().equals(entity.getId())){
                this.entities.remove(e);
                this.entities.add(entity);
                return SuccessMessages.UPDATED;
            }
        }

        throw new EntityNotFoundException(ExceptionMessages.ENTITY_NOT_FOUND);
    }
    public String delete(Long id)
            throws EntityNotFoundException {

        if(id == null)
            throw new IDNotValidException("The Id must have a value");

        for(Entity e : this.entities){
            if(e.getId().equals(id)){
                this.entities.remove(e);
                return SuccessMessages.DELETED;
            }
        }

        throw new EntityNotFoundException(ExceptionMessages.ENTITY_NOT_FOUND);
    }
}
