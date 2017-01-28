package code.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Napha on 27.01.2017.
 */
@ResponseStatus(value= HttpStatus.NOT_ACCEPTABLE, reason="Date is out of Sprint date bounds")
public class DateOutOfSprintDateBoundsException extends Exception {
    public DateOutOfSprintDateBoundsException(String message) {
        super(message);
    }
}