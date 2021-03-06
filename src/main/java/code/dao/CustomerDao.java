package code.dao;

import code.domain.Company;
import code.domain.Customer;

import java.util.List;

/**
 * Created by Napha on 11.01.2017.
 */
public interface CustomerDao {
    Long create(Customer customer);
    Customer read(Long id);
    void update(Customer customer);
    void delete(Long id);

    Customer findCustomerByLogin(String login);
    List<Customer> getAllCustomersByCompanyId(Company company);
    List<Customer> getAllCustomers();
}
