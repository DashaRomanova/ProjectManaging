package code.service;

import code.domain.*;
import code.exception.EntityAlreadyExistException;

import java.util.Date;
import java.util.List;

/**
 * Created by Napha on 16.01.2017.
 */
public interface TaskService {
    Long createTask(String taskName, int estimate, String taskDescription, Qualification qualification, Project project) throws EntityAlreadyExistException;
    List<Task>  getTasksByProject(Project project);
    List<Task> getTasksByProjectWhereSprintIsNull(Project project);
    List<Task> getTasksByProjectManagerWhereStatusIsChangeRequest(Employee projectManager);
    List<Task>  getTasksByProjectManager(Employee manager);
    List<Task> getAnotherTasksByProject(Project project, Long taskId);
    List<Task> getTasksByEmployeeWhereStatusIsCompleted(Employee employee);//Status = Completed
    List<Task> getTasksByEmployeeWhereStatusIsInProgress(Employee employee);//Status = InProgress
    List<Task> getTasksByEmployeeWhichIsInSprint(Employee employee);//Status = Confirmed, sprint != null
    List<Task> getTasksByEmployeeWhereStatusIsAssigned(Employee employee);//Status = Assigned, requestedEstimate = null
    List<Task> getTasksByEmployeeWhichRefused(Employee employee);//Status = Assigned, requestedEstimate != null
    List<Task> getTasksByEmployeeWhereStatusIsChangeRequest(Employee employee);//Status = ChangeRequest, requestedEstimate != null

    Task getTaskById(Long id);
    void updateTask (Task task);
    void deleteTask (Long id);
}
