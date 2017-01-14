package code.dao;

import code.domain.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    public void delete(Customer customer) {
        factory.getCurrentSession().delete(customer);
    }

    public Customer findCustomerByLogin(String login) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Customer c where c.login =:cusLogin");
        query.setParameter("cusLogin", login);
        return (Customer)query.uniqueResult();
    }
}
