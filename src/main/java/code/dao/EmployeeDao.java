package code.dao;

import code.domain.Company;
import code.domain.Employee;

import java.util.List;

/**
 * Created by Napha on 11.01.2017.
 */
public interface EmployeeDao {
    Long create(Employee employee);
    Employee read(Long id);
    void update(Employee employee);
    void delete(Employee employee);

    Employee findEmployeeByLogin(String login);

    List<Employee> getAllEmployeesByCompanyId(Company company);
}
