package code.dao;

import code.domain.Project;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Napha on 11.01.2017.
 */
@Repository
@Transactional
public class ProjectDaoImpl implements ProjectDao{
    @Autowired(required = true)
    private SessionFactory factory;

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

    public void delete(Project project) {
        factory.getCurrentSession().delete(project);
    }
}
