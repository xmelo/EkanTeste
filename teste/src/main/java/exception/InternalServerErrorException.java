package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends Exception {

    @Serial
    private static final long serialVersionUID = 1478496339453015130L;

    public InternalServerErrorException(String message){
        super(message);
    }
}