package code.service;

import code.dao.*;
import code.domain.*;
import code.exception.EntityAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Napha on 14.01.2017.
 */
@Service
@Transactional
public class CreateDomainsServiceImpl implements CreateDomainsService {
    @Autowired(required = true)
    private EmployeeDao employeeDao;
    @Autowired(required = true)
    private CustomerDao customerDao;
    @Autowired(required = true)
    private TaskDao taskDao;
    @Autowired(required = true)
    private SubTaskDao subTaskDao;
    @Autowired(required = true)
    private  StatusDao statusDao;
    @Autowired(required = true)
    private RoleDao roleDao;
    @Autowired(required = true)
    private QualificationDao qualificationDao;

    public Long createEmployee(String name, String surname, String login, String password, Long qualificationId) throws EntityAlreadyExistException {
        Employee employee = employeeDao.findEmployeeByLogin(login);
        if(employee != null) throw new EntityAlreadyExistException("Employee with such login already exists!");
        String roleName = "Employee";
        Role role = getRoleByName(roleName);
        Qualification qualification = getQualificationById(qualificationId);
        Long employeeId = employeeDao.create(new Employee(name, surname, login, password, qualification, role));
        return employeeId;
    }

    public Long createCustomer(String name, String surname, String login, String password) throws EntityAlreadyExistException{
        Customer customer = customerDao.findCustomerByLogin(login);
        if(customer != null) throw new EntityAlreadyExistException("Customer with such login already exists!");
        String roleName = "Customer";
        Role role = getRoleByName(roleName);
        Long customerId = customerDao.create(new Customer(name, surname, login, password));
        return customerId;
    }

    public Long createTask(String taskName, int estimate, Date expirationDate, String taskDescription, Long qualificationId, Long employeeId) throws EntityAlreadyExistException {
        Task task = taskDao.findTaskByName(taskName);
        if(task != null) throw new EntityAlreadyExistException("Task with such name already exists!");
        String statusName = "Created";
        Status status = getStatusByName(statusName);
        Qualification qualification = getQualificationById(qualificationId);
        Task newTask;
        if(employeeId != null){
            Employee employee = getEmployeeById(employeeId);
            newTask = new Task(taskName, taskDescription, estimate, expirationDate, status, employee, qualification);
        }else {
            newTask = new Task(taskName, estimate, expirationDate, status, taskDescription, qualification);
        }
        Long taskId = taskDao.create(newTask);
        return taskId;
    }


    public List getAllQualifications() {
        return qualificationDao.getAllQualifications();
    }

    private Qualification getQualificationById(Long id){
        return qualificationDao.read(id);
    }

    private Employee getEmployeeById(Long id){
        return employeeDao.read(id);
    }

    private Status getStatusByName(String name){
        return statusDao.getStatusByName(name);
    }
    private Role getRoleByName(String name){
        return roleDao.getRoleByName(name);
    }
}
