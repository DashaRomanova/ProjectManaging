package code.service;

import code.dao.TaskDao;
import code.domain.Employee;
import code.domain.Qualification;
import code.domain.Status;
import code.domain.Task;
import code.exception.EntityAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Napha on 16.01.2017.
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService{
    @Autowired(required = true)
    private TaskDao taskDao;
    @Autowired(required = true)
    private QualificationService qualificationService;
    @Autowired(required = true)
    private EmployeeService employeeService;

    public Long createTask(String taskName, int estimate, Date expirationDate, String taskDescription, Long qualificationId, Long employeeId) throws EntityAlreadyExistException {
        Task task = taskDao.findTaskByName(taskName);
        if(task != null) throw new EntityAlreadyExistException("Task with such name already exists!");
        Qualification qualification = qualificationService.getQualificationById(qualificationId);
        Task newTask;
        if(employeeId != null){
            Employee employee = employeeService.getEmployeeById(employeeId);
            if(employee == null) throw new NullPointerException("There isn't any employee with such id");
            newTask = new Task(taskName, taskDescription, estimate, expirationDate, Status.Assigned.name(), employee, qualification);
            employee.addTask(newTask);
            employeeService.updateEmployee(employee);
        }else {
            newTask = new Task(taskName, taskDescription, estimate, expirationDate, Status.Created.name(), qualification);
        }
        Long taskId = taskDao.create(newTask);
        return taskId;
    }
}
