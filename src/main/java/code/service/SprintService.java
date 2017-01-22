package code.service;

import code.domain.Project;
import code.domain.Sprint;

import java.util.Date;
import java.util.List;

/**
 * Created by Napha on 22.01.2017.
 */
public interface SprintService {
    Long createSprint(Sprint previousSprint, Date sprintStartDate, Date sprintFinishDate);
    Sprint getSprintById(Long id);
    List<Sprint> getSprintsByProject(Project project);
    void updateSprint (Sprint sprint);
}
