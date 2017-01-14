package code.dao;

import code.domain.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Napha on 14.01.2017.
 */
@Repository
@Transactional
public class TaskDaoImpl implements TaskDao{
    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory factory;

    public TaskDaoImpl() {
    }

    public Long create(Task task) {
        return (Long)factory.getCurrentSession().save(task);
    }

    public Task read(Long id) {
        return factory.getCurrentSession().get(Task.class, id);
    }

    public void update(Task task) {
        factory.getCurrentSession().update(task);
    }

    public void delete(Task task) {
        factory.getCurrentSession().delete(task);
    }

    public Task findTaskByName(String name) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Task t where t.taskName =:taskName");
        query.setParameter("taskName", name);
        return (Task)query.uniqueResult();
    }
}
