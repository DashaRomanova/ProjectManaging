package code.dao;

import code.domain.*;

import java.util.List;

/**
 * Created by Napha on 11.01.2017.
 */
public interface EmployeeDao {
    Long create(Employee employee);
    Employee read(Long id);
    void update(Employee employee);
    void delete(Long id);

    Employee findEmployeeByLogin(String login);

    List<Employee> getAllEmployeesByCompanyId(Company company);
    List<Employee> getAllEmployeesByQualificationAndRole(Qualification qualification, Role role);
    List<Employee> getAllEmployeesByRole(Role role);
    List<Employee> getAllEmployees();

    //List<Employee> getAllEmployeesByProjectManager(Employee manager);
    List<ViewEmployee> getAllEmployeesByProjectManager(Long managerId);

    List<ViewEmployee> findEmployeesWhoHasOvertime(Integer sprintDuration, Long sprintId);
    ViewEmployee findEmployeeIfHasOvertime(Integer sprintDuration, Long sprintId, Long employeeId);
}
