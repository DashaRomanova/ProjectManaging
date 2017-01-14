package code.dao;

import code.domain.Employee;

/**
 * Created by Napha on 11.01.2017.
 */
public interface EmployeeDao {
    Long create(Employee employee);
    Employee read(Long id);
    void update(Employee employee);
    void delete(Employee employee);

    Employee findEmployeeByLogin(String login);
}
