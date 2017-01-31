package code.dao.impl;

import code.dao.SprintDao;
import code.domain.Employee;
import code.domain.Project;
import code.domain.Sprint;
import code.domain.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
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
public class SprintDaoImpl implements SprintDao {
    @Autowired(required = true)
    private SessionFactory factory;

    private final Integer MAX_RESULT_SIZE = 5;

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

    public void delete(Long id) {
        Session session = factory.getCurrentSession();

        Sprint parentSprint  = session.load(Sprint.class, id);
        session.delete(parentSprint);
        for (Task task : parentSprint.getTasks()) {
            task.setSprint(null);
        }
        session.flush();
    }

    public List<Sprint> findSprintsByProject(Project project) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Sprint s where s.project =:project");
        query.setParameter("project", project);
        query.setMaxResults(MAX_RESULT_SIZE);
        return query.list();
    }

    public List<Sprint> findSprintsByProjectManager(Employee manager) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Sprint s where s.project.projectManager =:manager");
        query.setParameter("manager", manager);
        query.setMaxResults(MAX_RESULT_SIZE);
        return query.list();
    }
}
