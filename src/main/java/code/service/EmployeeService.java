package code.service;

import code.domain.Company;
import code.domain.Employee;
import code.exception.EntityAlreadyExistException;

import java.util.List;

/**
 * Created by Napha on 16.01.2017.
 */
public interface EmployeeService {
    Long createEmployee(String name, String surname, String login, String password, Long qualificationId, String role) throws EntityAlreadyExistException;
    Employee getEmployeeById(Long id);
    void updateEmployee (Employee employee);

    List<Employee> getAllEmployeesByCompanyId(Company company);
}
