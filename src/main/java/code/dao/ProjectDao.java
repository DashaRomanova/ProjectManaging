package code.dao;

import code.domain.Employee;
import code.domain.Project;

import java.util.List;

/**
 * Created by Napha on 11.01.2017.
 */
public interface ProjectDao {
    Long create(Project project);
    Project read(Long id);
    void update(Project project);
    void delete(Long id);
    Project findProjectByName(String name);
    List<Project> findProjectsByManager(Employee manager);
    List<Project> findAllProjects();
}
