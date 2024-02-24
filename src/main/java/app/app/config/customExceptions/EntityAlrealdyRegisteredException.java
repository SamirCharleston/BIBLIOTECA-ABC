package app.app.config.customExceptions;

public class EntityAlrealdyRegisteredException extends RuntimeException{
    public EntityAlrealdyRegisteredException(String message){
        super(message);
    }
}
