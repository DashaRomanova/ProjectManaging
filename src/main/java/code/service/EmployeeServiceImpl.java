package code.service;

import code.dao.EmployeeDao;
import code.domain.Company;
import code.domain.Employee;
import code.domain.Qualification;
import code.exception.EntityAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Napha on 16.01.2017.
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired(required = true)
    private EmployeeDao employeeDao;
    @Autowired(required = true)
    private QualificationService qualificationService;

    public Long createEmployee(String name, String surname, String login, String password, Long qualificationId, String role) throws EntityAlreadyExistException {
        Employee employee = employeeDao.findEmployeeByLogin(login);
        if(employee != null) throw new EntityAlreadyExistException("Employee with such login already exists!");
        Qualification qualification = qualificationService.getQualificationById(qualificationId);
        Long employeeId = employeeDao.create(new Employee(name, surname, login, password, qualification, role));
        return employeeId;
    }

    public Employee getEmployeeById(Long id) {
        return employeeDao.read(id);
    }

    public void updateEmployee(Employee employee) {
        employeeDao.update(employee);
    }

    public List<Employee> getAllEmployeesByCompanyId(Company company) {
        return employeeDao.getAllEmployeesByCompanyId(company);
    }
}
