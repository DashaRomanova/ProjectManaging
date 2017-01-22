package code.service;

import code.domain.Qualification;
import code.domain.Status;
import code.domain.Task;
import code.exception.EntityAlreadyExistException;

import java.util.Date;
import java.util.List;

/**
 * Created by Napha on 16.01.2017.
 */
public interface TaskService {
    Long createTask(String taskName, int estimate, Date expirationDate, String taskDescription, Qualification qualification) throws EntityAlreadyExistException;
    List<Task>  getTasksByStatus(Status status);
    Task getTaskById(Long id);
    void updateTask (Task task);
}
