package code.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Napha on 27.01.2017.
 */
@ResponseStatus(value= HttpStatus.NOT_ACCEPTABLE, reason="Task already completed")
public class TaskAlreadyCompletedException extends Exception {
    public TaskAlreadyCompletedException(String message) {
        super(message);
    }
}