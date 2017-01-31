package code.dao.impl;

import code.dao.CompanyDao;
import code.domain.Company;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Napha on 11.01.2017.
 */
@Repository
@Transactional
public class CompanyDaoImpl implements CompanyDao {
    @Autowired(required = true)
    private SessionFactory factory;

    public CompanyDaoImpl() {
    }

    public Long create(Company company) {
        return (Long)factory.getCurrentSession().save(company);
    }

    public Company read(Long id) {
        return (Company) factory.getCurrentSession().get(Company.class, id);
    }

    public void update(Company company) {
        factory.getCurrentSession().update(company);
    }

    public void delete(Company company) {
        factory.getCurrentSession().delete(company);
    }
}
