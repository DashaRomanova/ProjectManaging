package code.service;

import code.exception.EntityAlreadyExistException;

import java.util.Date;
import java.util.List;

/**
 * Created by Napha on 14.01.2017.
 */
public interface CreateDomainsService {
    Long createEmployee(String name, String surname, String login, String password, Long qualificationId) throws EntityAlreadyExistException;
    Long createCustomer(String name, String surname, String login, String password) throws EntityAlreadyExistException;
    Long createTask(String taskName, int estimate, Date expirationDate, String taskDescription, Long qualificationId, Long employeeId) throws EntityAlreadyExistException;
    List getAllQualifications();
}
