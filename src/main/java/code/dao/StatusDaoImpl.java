package code.dao;

import code.domain.Status;
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
public class StatusDaoImpl implements StatusDao{
    @Autowired(required = true)
    private SessionFactory factory;

    public StatusDaoImpl() {
    }

    public Long create(Status status) {
        return (Long)factory.getCurrentSession().save(status);
    }

    public Status read(Long id) {
        return (Status) factory.getCurrentSession().get(Status.class, id);
    }

    public void update(Status status) {
        factory.getCurrentSession().update(status);
    }

    public void delete(Status status) {
        factory.getCurrentSession().delete(status);
    }

    @Transactional
    public Status getStatusByName(String name) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Status s where s.statusName =:statusName");
        query.setParameter("statusName", name);
        return (Status)query.uniqueResult();
    }
}
