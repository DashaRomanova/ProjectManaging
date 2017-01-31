package code.dao.impl;

import code.dao.CustomerDao;
import code.domain.Company;
import code.domain.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Napha on 11.01.2017.
 */
@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {


    @Autowired(required = true)
    private SessionFactory factory;

    public CustomerDaoImpl() {
    }

    public Long create(Customer customer) {
        return (Long)factory.getCurrentSession().save(customer);
    }

    public Customer read(Long id) {
        return (Customer) factory.getCurrentSession().get(Customer.class, id);
    }

    public void update(Customer customer) {
        factory.getCurrentSession().update(customer);
    }

    public void delete(Long id) {
//        Session session = factory.getCurrentSession();
//        Customer parentCustomer = session.load(Customer.class, id);
//        session.delete(parentCustomer);
    }

    public Customer findCustomerByLogin(String login) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Customer c where c.username =:cusLogin");
        query.setParameter("cusLogin", login);
        return (Customer)query.uniqueResult();
    }

    public List<Customer> getAllCustomersByCompanyId(Company company) {
        Integer maxResultsSize = 5;
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Customer c where c.company =:company");
        query.setParameter("company", company);
        query.setMaxResults(maxResultsSize);
        return query.list();
    }

    public List<Customer> getAllCustomers() {
        Integer maxResultsSize = 5;
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Customer");
        query.setMaxResults(maxResultsSize);
        return query.list();
    }
}
