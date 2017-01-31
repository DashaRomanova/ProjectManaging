package code.service.impl;

import code.dao.CustomerDao;
import code.domain.Company;
import code.domain.Customer;
import code.domain.Role;
import code.exception.EntityAlreadyExistException;
import code.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Napha on 16.01.2017.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired(required = true)
    private CustomerDao customerDao;

    public Long createCustomer(String name, String surname, String login, String password, Role role) throws EntityAlreadyExistException {
        Customer customer = customerDao.findCustomerByLogin(login);
        if(customer != null) throw new EntityAlreadyExistException("Customer with such login already exists!");
        Long customerId = customerDao.create(new Customer(name, surname, login, password, role));
        return customerId;
    }

    public void updateCustomer(Customer customer) {
        customerDao.update(customer);
    }

    public void deleteCustomer(Long id) {
        customerDao.delete(id);
    }

    public List<Customer> getAllCustomersByCompanyId(Company company) {
        return customerDao.getAllCustomersByCompanyId(company);
    }

    public Customer getCustomerById(Long id) {
        return customerDao.read(id);
    }

    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }
}
