package code.service;

import code.domain.Employee;
import code.domain.Project;
import code.domain.Sprint;
import code.domain.SprintStatisticReport;

import java.util.Date;
import java.util.List;

/**
 * Created by Napha on 22.01.2017.
 */
public interface SprintService {
    Long createSprint(String name, Project project, Sprint previousSprint, Date sprintFinishDate);
    Sprint getSprintById(Long id);
    List<Sprint> getSprintsByProjectManager(Employee manager);

    void updateSprint (Sprint sprint);
    void deleteSprint (Long id);
}
