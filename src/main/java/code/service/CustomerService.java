package code.service;

import code.exception.EntityAlreadyExistException;

/**
 * Created by Napha on 16.01.2017.
 */
public interface CustomerService {
    Long createCustomer(String name, String surname, String login, String password, String role) throws EntityAlreadyExistException;
}
