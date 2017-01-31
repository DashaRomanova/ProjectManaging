package code.dao.impl;

import code.dao.ProjectDao;
import code.domain.Employee;
import code.domain.Project;
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
public class ProjectDaoImpl implements ProjectDao {
    @Autowired(required = true)
    private SessionFactory factory;

    private final Integer MAX_RESULT_SIZE = 5;

    public ProjectDaoImpl() {
    }

    public Long create(Project project) {
        return (Long)factory.getCurrentSession().save(project);
    }

    public Project read(Long id) {
        return (Project) factory.getCurrentSession().get(Project.class, id);
    }

    public void update(Project project) {
        factory.getCurrentSession().update(project);
    }

    public void delete(Long id) {
        Session session = factory.getCurrentSession();
        Project project = session.get(Project.class, id);
        session.delete(project);
    }

    public Project findProjectByName(String name) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Project p where p.projectName =:projectName");
        query.setParameter("projectName", name);
        return (Project)query.uniqueResult();
    }

    public List<Project> findProjectsByManager(Employee manager) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Project p where p.projectManager =:projectManager");
        query.setParameter("projectManager", manager);
        query.setMaxResults(MAX_RESULT_SIZE);
        return query.list();
    }

    public List<Project> findAllProjects() {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Project ");
        query.setMaxResults(MAX_RESULT_SIZE);
        return query.list();
    }
}
