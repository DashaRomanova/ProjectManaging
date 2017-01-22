package code.service.impl;

import code.dao.ProjectDao;
import code.domain.Employee;
import code.domain.Project;
import code.exception.EntityAlreadyExistException;
import code.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Napha on 22.01.2017.
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService{
    @Autowired(required = true)
    private ProjectDao projectDao;

    public Long createProject(String projectName, Date projectStartDate, Date projectFinishDate) throws EntityAlreadyExistException{
        Project project = projectDao.findProjectByName(projectName);
        if(project != null) throw new EntityAlreadyExistException("Project with such name already exists!");
        Long projectId = projectDao.create(new Project(projectName, projectStartDate, projectFinishDate));
        return projectId;
    }

    public Project getProjectByName(String name){
        return projectDao.findProjectByName(name);
    }

    public Project getProjectById(Long id){
        return projectDao.read(id);
    }

    public void updateProject(Project project) {
        projectDao.update(project);
    }

    public List<Project> getProjectsByManager(Employee manager) {
        return projectDao.findProjectsByManager(manager);
    }
}
