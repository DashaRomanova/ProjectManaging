package code.dao;

import code.domain.Qualification;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Napha on 11.01.2017.
 */
@Repository
public class QualificationDaoImpl implements QualificationDao {
    @Autowired(required = true)
    private SessionFactory factory;

    public QualificationDaoImpl() {
    }

    public Long create(Qualification qualification) {
        return (Long)factory.getCurrentSession().save(qualification);
    }

    public Qualification read(Long id) {
        return factory.getCurrentSession().get(Qualification.class, id);
    }

    public void update(Qualification qualification) {
        factory.getCurrentSession().update(qualification);
    }

    public void delete(Qualification qualification) {
        factory.getCurrentSession().delete(qualification);
    }

    @Transactional
    public List getAllQualifications() {
        Integer maxResultsSize = 5;
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Qualification");
        query.setMaxResults(maxResultsSize);
        return query.list();
    }
}
