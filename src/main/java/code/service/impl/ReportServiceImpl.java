package code.service.impl;

import code.dao.EmployeeDao;
import code.dao.ProjectDao;
import code.dao.TaskDao;
import code.domain.*;
import code.service.DateWorkerService;
import code.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Napha on 29.01.2017.
 */
@Service
@Transactional
public class ReportServiceImpl implements ReportService {

    @Autowired(required = true)
    private TaskDao taskDao;
    @Autowired(required = true)
    private EmployeeDao employeeDao;
    @Autowired(required = true)
    private ProjectDao projectDao;

    public ProjectStatisticReport getProjectStatisticReport(Long projectId) {

        Project project = projectDao.read(projectId);
        ProjectStatisticReport projectStatisticReport = null;
        if(project != null){
            projectStatisticReport = new ProjectStatisticReport(project.getProjectName(),
                    project.getProjectStartDate(),
                    project.getProjectFinishDate(),
                    project.getProjectManager(),
                    project.getCustomer());
            int sumEstimate;
            int sumActualEstimate;
            List<Sprint> sprints = project.getSprints();
            if(sprints != null){
                for(Sprint sprint: sprints){
                    SprintStatisticReport sprintStatisticReport = new SprintStatisticReport(sprint.getSprintName(), sprint.getSprintStartDate(), sprint.getSprintFinishDate());
                    sumEstimate = 0;
                    sumActualEstimate = 0;
                    List<Task> tasks = sprint.getTasks();
                    if(tasks != null){
                        for(Task task: tasks){
                            sprintStatisticReport.addTaskStatisticReport(new TaskStatisticReport(task.getTaskName(),
                                    task.getStartDate(),
                                    task.getExpectedCompletionDate(),
                                    task.getActualCompletionDate(),
                                    task.getEstimate(),
                                    task.getActualEstimate()));
                            sumEstimate += task.getEstimate();
                            if(task.getActualEstimate() != null){
                                sumActualEstimate +=  task.getActualEstimate();
                            }
                        }
                        sprintStatisticReport.setSumEstimate(sumEstimate);
                        sprintStatisticReport.setSumActualEstimate(sumActualEstimate);
                    }
                    projectStatisticReport.addSprintStatisticReport(sprintStatisticReport);
                }
            }
        }

        return projectStatisticReport;
    }

    public List<TaskReportView> getTaskStatisticBetweenDatesByEmployee(Long employeeId, Date start, Date end) {
        return taskDao.getTaskStatisticBetweenDatesByEmployee(employeeId, start, end);
    }

    public List<Task> findTasksWithDelayByEmployee(Employee employee) {
        return taskDao.findTasksWithDelayByEmployee(employee);
    }

    public List<ViewEmployee> findEmployeesWhoHasOvertime(Sprint sprint) {
        int workingHours = DateWorkerService.getWorkingHoursBetweenTwoDates(sprint.getSprintStartDate(), sprint.getSprintFinishDate());
        return employeeDao.findEmployeesWhoHasOvertime(workingHours, sprint.getSprintId());
    }
}
