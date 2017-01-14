package code.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Napha on 14.01.2017.
 */
@ResponseStatus(value= HttpStatus.NOT_ACCEPTABLE, reason="Id Not Found")
public class EntityAlreadyExistException extends Exception {
    public EntityAlreadyExistException(String message) {
        super(message);
    }
}
