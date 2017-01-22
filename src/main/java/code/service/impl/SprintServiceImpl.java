package code.service.impl;

import code.dao.SprintDao;
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
    public Long createSprint(Sprint previousSprint, Date sprintStartDate, Date sprintFinishDate) {
        Long sprintId = sprintDao.create(new Sprint(previousSprint, sprintStartDate, sprintFinishDate));
        return sprintId;
    }

    public Sprint getSprintById(Long id) {
        return sprintDao.read(id);
    }

    public List<Sprint> getSprintsByProject(Project project) {
        return sprintDao.findSprintsByProject(project);
    }

    public void updateSprint(Sprint sprint) {
        sprintDao.update(sprint);
    }
}
