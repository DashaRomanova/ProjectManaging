package code.dao;

import code.domain.Customer;

/**
 * Created by Napha on 11.01.2017.
 */
public interface CustomerDao {
    Long create(Customer customer);
    Customer read(Long id);
    void update(Customer customer);
    void delete(Customer customer);

    Customer findCustomerByLogin(String login);
}
