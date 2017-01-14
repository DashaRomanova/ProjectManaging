package code.dao;

import code.domain.Sprint;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Napha on 11.01.2017.
 */
@Repository
@Transactional
public class SprintDaoImpl implements SprintDao{
    @Autowired(required = true)
    private SessionFactory factory;

    public SprintDaoImpl() {
    }

    public Long create(Sprint sprint) {
        return (Long)factory.getCurrentSession().save(sprint);
    }

    public Sprint read(Long id) {
        return (Sprint) factory.getCurrentSession().get(Sprint.class, id);
    }

    public void update(Sprint sprint) {
        factory.getCurrentSession().update(sprint);
    }

    public void delete(Sprint sprint) {
        factory.getCurrentSession().delete(sprint);
    }
}
