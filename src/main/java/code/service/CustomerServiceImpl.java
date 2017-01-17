package code.service;

import code.dao.CustomerDao;
import code.domain.Customer;
import code.exception.EntityAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Napha on 16.01.2017.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired(required = true)
    private CustomerDao customerDao;

    public Long createCustomer(String name, String surname, String login, String password, String role) throws EntityAlreadyExistException {
        Customer customer = customerDao.findCustomerByLogin(login);
        if(customer != null) throw new EntityAlreadyExistException("Customer with such login already exists!");
        Long customerId = customerDao.create(new Customer(name, surname, login, password, role));
        return customerId;
    }
}
