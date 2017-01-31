package code.service.impl;

import code.dao.TaskDao;
import code.domain.*;
import code.exception.EntityAlreadyExistException;
import code.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Napha on 16.01.2017.
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    @Autowired(required = true)
    private TaskDao taskDao;

    public Long createTask(String taskName, int estimate, String taskDescription, Qualification qualification, Project project) throws EntityAlreadyExistException {
        Task task = taskDao.findTaskByName(taskName);
        if(task != null) throw new EntityAlreadyExistException("Task with such name already exists!");
        Long taskId = taskDao.create(new Task(taskName, taskDescription, estimate, Status.Created, qualification, project));
        return taskId;
    }

    public List<Task> getTasksByProject(Project project) {
        return taskDao.findTasksByProject(project);
    }

    public List<Task> getTasksByProjectWhereSprintIsNull(Project project) {
        return taskDao.findTasksByProjectWhereSprintIsNull(project);
    }

    public List<Task> getTasksByProjectManagerWhereStatusIsChangeRequest(Employee projectManager) {
        return taskDao.findTasksByProjectManagerWhereStatusIsChangeRequest(projectManager);
    }

    public List<Task> getTasksByProjectManager(Employee manager) {
        return taskDao.findTasksByProjectManager(manager);
    }

    public List<Task> getAnotherTasksByProject(Project project, Long taskId) {
        return taskDao.findAnotherTasksByProject(project, taskId);
    }

    public List<Task> getTasksByEmployeeWhereStatusIsCompleted(Employee employee) {
        return taskDao.findTasksByEmployeeWhereStatusIsCompleted(employee);
    }

    public List<Task> getTasksByEmployeeWhereStatusIsInProgress(Employee employee) {
        return taskDao.findTasksByEmployeeWhereStatusIsInProgress(employee);
    }

    public List<Task> getTasksByEmployeeWhichIsInSprint(Employee employee) {
        return taskDao.findTasksByEmployeeWhichIsInSprint(employee);
    }

    public List<Task> getTasksByEmployeeWhereStatusIsAssigned(Employee employee) {
        return taskDao.findTasksByEmployeeWhereStatusIsAssigned(employee);
    }

    public List<Task> getTasksByEmployeeWhichRefused(Employee employee) {
        return taskDao.findTasksByEmployeeWhichRefused(employee);
    }

    public List<Task> getTasksByEmployeeWhereStatusIsChangeRequest(Employee employee) {
        return taskDao.findTasksByEmployeeWhereStatusIsChangeRequest(employee);
    }

    public Task getTaskById(Long id) {
        return taskDao.read(id);
    }

    public void updateTask(Task task) {
        taskDao.update(task);
    }

    public void deleteTask(Long id) {
        taskDao.delete(id);
    }
}
