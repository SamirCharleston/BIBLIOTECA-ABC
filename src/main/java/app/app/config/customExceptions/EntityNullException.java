package app.app.config.customExceptions;

public class EntityNullException extends RuntimeException{
    public EntityNullException(String message){
        super(message);
    }
}
