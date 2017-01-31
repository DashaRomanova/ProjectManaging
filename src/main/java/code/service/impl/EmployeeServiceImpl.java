package code.service.impl;

import code.dao.EmployeeDao;
import code.domain.*;
import code.exception.EntityAlreadyExistException;
import code.service.DateWorkerService;
import code.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Napha on 16.01.2017.
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired(required = true)
    private EmployeeDao employeeDao;

    public Long createEmployee(String name, String surname, String login, String password, Qualification qualification, Role role) throws EntityAlreadyExistException {
        Employee employee = employeeDao.findEmployeeByLogin(login);
        if(employee != null) throw new EntityAlreadyExistException("Employee with such login already exists!");
        Long employeeId = employeeDao.create(new Employee(name, surname, login, password, qualification, role));
        return employeeId;
    }

    public Employee getEmployeeById(Long id) {
        return employeeDao.read(id);
    }

    public void updateEmployee(Employee employee) {
        employeeDao.update(employee);
    }

    public void deleteEmployee(Long id) {
        employeeDao.delete(id);
    }

    public Employee findEmployeeByLogin(String login) {
        return employeeDao.findEmployeeByLogin(login);
    }

    public ViewEmployee findEmployeeIfHasOvertime(Sprint sprint, Long employeeId){
        int workingHours = DateWorkerService.getWorkingHoursBetweenTwoDates(sprint.getSprintStartDate(), sprint.getSprintFinishDate());
        return employeeDao.findEmployeeIfHasOvertime(workingHours, sprint.getSprintId(), employeeId);
    }

    public List<Employee> getAllEmployeesByCompanyId(Company company) {
        return employeeDao.getAllEmployeesByCompanyId(company);
    }

    public List<Employee> getAllEmployeesByQualificationAndRole(Qualification qualification, Role role) {
        return employeeDao.getAllEmployeesByQualificationAndRole(qualification, role);
    }

    public List<Employee> getAllEmployeesByRole(Role role) {
        return employeeDao.getAllEmployeesByRole(role);
    }

    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    public List<ViewEmployee> getAllEmployeesByProjectManager(Long managerId) {
        return employeeDao.getAllEmployeesByProjectManager(managerId);
    }

//    public List<Employee> getAllEmployeesByProjectManager(Employee manager) {
//        return employeeDao.getAllEmployeesByProjectManager(manager);
//    }


}
