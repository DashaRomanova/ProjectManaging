package code.service;

import code.domain.Customer;
import code.domain.Employee;
import code.domain.Project;
import code.exception.EntityAlreadyExistException;

import java.util.Date;
import java.util.List;

/**
 * Created by Napha on 22.01.2017.
 */
public interface ProjectService {
    Long createProject(String projectName, Date projectStartDate, Date projectFinishDate, Customer customer, Employee manager) throws EntityAlreadyExistException;

    List<Project> getProjectsByManager(Employee manager);

    List<Project> getAllProjects();

    Project getProjectByName(String name);

    Project getProjectById(Long id);

    void updateProject(Project project);
    void deleteProject(Long id);
}
