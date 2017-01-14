package code.dao;

import code.domain.SubTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Napha on 14.01.2017.
 */
@Repository
@Transactional
public class SubTaskDaoImpl implements SubTaskDao {
    @Autowired(required = true)
    private SessionFactory factory;

    public SubTaskDaoImpl() {
    }

    public Long create(SubTask subTask) {
        return (Long)factory.getCurrentSession().save(subTask);
    }

    public SubTask read(Long id) {
        return factory.getCurrentSession().get(SubTask.class, id);
    }

    public void update(SubTask subTask) {
        factory.getCurrentSession().update(subTask);
    }

    public void delete(SubTask subTask) {
        factory.getCurrentSession().delete(subTask);
    }

    public SubTask findTaskByName(String name) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from SubTask t where t.taskName =:taskName");
        query.setParameter("taskName", name);
        return (SubTask)query.uniqueResult();
    }
}
