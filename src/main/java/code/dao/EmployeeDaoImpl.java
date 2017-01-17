package code.dao;

import code.domain.Company;
import code.domain.Employee;
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
public class EmployeeDaoImpl implements EmployeeDao {
    @Autowired(required = true)
    private SessionFactory factory;

    public EmployeeDaoImpl() {
    }

    public Long create(Employee employee) {
        return (Long)factory.getCurrentSession().save(employee);
    }

    public Employee read(Long id) {
        return (Employee) factory.getCurrentSession().get(Employee.class, id);
    }

    public void update(Employee employee) {
        factory.getCurrentSession().update(employee);
    }

    public void delete(Employee employee) {
        factory.getCurrentSession().delete(employee);
    }

    public Employee findEmployeeByLogin(String login) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Employee e where e.login =:empLogin");
        query.setParameter("empLogin", login);
        return (Employee)query.uniqueResult();
    }

    public List<Employee> getAllEmployeesByCompanyId(Company company) {
        Integer maxResultsSize = 5;
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Employee e where e.company =:company");
        query.setParameter("company", company);
        query.setMaxResults(maxResultsSize);
        return query.list();
    }
}
