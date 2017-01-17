package code.service;

import code.exception.EntityAlreadyExistException;

import java.util.Date;

/**
 * Created by Napha on 16.01.2017.
 */
public interface TaskService {
    Long createTask(String taskName, int estimate, Date expirationDate, String taskDescription, Long qualificationId, Long employeeId) throws EntityAlreadyExistException;
}
