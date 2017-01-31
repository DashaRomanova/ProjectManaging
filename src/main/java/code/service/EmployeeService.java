package code.service;

import code.domain.*;
import code.exception.EntityAlreadyExistException;

import java.util.List;

/**
 * Created by Napha on 16.01.2017.
 */
public interface EmployeeService {
    Long createEmployee(String name, String surname, String login, String password, Qualification qualification, Role role) throws EntityAlreadyExistException;
    Employee getEmployeeById(Long id);
    void updateEmployee (Employee employee);
    void deleteEmployee (Long id);

    Employee findEmployeeByLogin(String login);

    ViewEmployees findEmployeeIfHasOvertime(Sprint sprint, Long employeeId);
    List<Employee> getAllEmployeesByQualificationAndRole(Qualification qualification, Role role);
    List<Employee> getAllEmployeesByRole(Role role);
    List<Employee> getAllEmployees();

    //List<Employee> getAllEmployeesByProjectManager(Employee manager);
    List<ViewEmployees> getAllEmployeesByProjectManager(Long managerId);
}
