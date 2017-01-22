package code.service;

import code.domain.Employee;
import code.domain.Project;
import code.exception.EntityAlreadyExistException;

import java.util.Date;
import java.util.List;

/**
 * Created by Napha on 22.01.2017.
 */
public interface ProjectService {
    Long createProject(String projectName, Date projectStartDate, Date projectFinishDate) throws EntityAlreadyExistException;

    List<Project> getProjectsByManager(Employee manager);

    Project getProjectByName(String name);

    Project getProjectById(Long id);

    void updateProject(Project project);
}
