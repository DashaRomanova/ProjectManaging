package code.service.impl;

import code.dao.SprintDao;
import code.domain.Employee;
import code.domain.Project;
import code.domain.Sprint;
import code.service.SprintService;
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
public class SprintServiceImpl implements SprintService {
    @Autowired(required = true)
    private SprintDao sprintDao;
    public Long createSprint(String name, Project project, Sprint previousSprint, Date sprintFinishDate) {
        Sprint sprint;
        if(previousSprint == null){
            sprint = new Sprint(name, project, previousSprint, project.getProjectStartDate(), sprintFinishDate);
        } else {
            sprint = new Sprint(name, project, previousSprint, previousSprint.getSprintFinishDate(), sprintFinishDate);
        }
        return sprintDao.create(sprint);
    }

    public Sprint getSprintById(Long id) {
        return sprintDao.read(id);
    }

    public List<Sprint> getSprintsByProject(Project project) {
        return sprintDao.findSprintsByProject(project);
    }

    public List<Sprint> getSprintsByProjectManager(Employee manager) {
        return sprintDao.findSprintsByProjectManager(manager);
    }

    public void updateSprint(Sprint sprint) {
        sprintDao.update(sprint);
    }

    public void deleteSprint(Long id) {
        sprintDao.delete(id);
    }
}
