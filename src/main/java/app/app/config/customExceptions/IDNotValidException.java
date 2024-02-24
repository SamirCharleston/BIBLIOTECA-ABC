package app.app.config.customExceptions;

public class IDNotValidException extends RuntimeException{
    public IDNotValidException(String message){
        super(message);
    }
}
