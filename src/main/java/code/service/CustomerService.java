package code.service;

import code.domain.Company;
import code.domain.Customer;
import code.domain.Role;
import code.exception.EntityAlreadyExistException;

import java.util.List;

/**
 * Created by Napha on 16.01.2017.
 */
public interface CustomerService {
    Long createCustomer(String name, String surname, String login, String password, Role role) throws EntityAlreadyExistException;
    void updateCustomer (Customer customer);
    void deleteCustomer (Long id);
    List<Customer> getAllCustomersByCompanyId(Company company);
    Customer getCustomerById(Long id);
    List<Customer> getAllCustomers();

}
