package code.dao;

import code.domain.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Napha on 14.01.2017.
 */
public interface TaskDao {
    Long create(Task task);
    Task read(Long id);
    void update(Task task);
    void delete(Long id);

    Task findTaskByName(String name);

    List<Task> findTasksByProjectManager(Employee manager);
    List<Task> findTasksByProject(Project project);
    List<Task> findAnotherTasksByProject(Project project, Long taskId);
    List<Task> findTasksByProjectWhereSprintIsNull(Project project);
    List<Task> findTasksByProjectManagerWhereStatusIsChangeRequest(Employee projectManager);
    List<Task> findTasksByEmployeeWhereStatusIsCompleted(Employee employee);//Status = Completed
    List<Task> findTasksByEmployeeWhereStatusIsInProgress(Employee employee);//Status = InProgress
    List<Task> findTasksByEmployeeWhichIsInSprint(Employee employee);//Status = Confirmed, sprint != null
    List<Task> findTasksByEmployeeWhereStatusIsAssigned(Employee employee);//Status = Assigned, requestedEstimate = null
    List<Task> findTasksByEmployeeWhichRefused(Employee employee);//Status = Assigned, requestedEstimate != null
    List<Task> findTasksByEmployeeWhereStatusIsChangeRequest(Employee employee);//Status = ChangeRequest, requestedEstimate != null

    //SprintStatisticReport getTaskStatisticBySprint(Long sprintId);

    List<TaskReportView> getTaskStatisticBetweenDatesByEmployee(Long employeeId, Date start, Date end);

    List<Task> findTasksWithDelayByEmployee(Employee employee);
}
