package TableTennis.Exception;

public class DataBaseException extends RuntimeException{
    public DataBaseException(String message){
        super(message);
    }
    public DataBaseException(String message,Throwable e){
        super(message,e);
    }
}
