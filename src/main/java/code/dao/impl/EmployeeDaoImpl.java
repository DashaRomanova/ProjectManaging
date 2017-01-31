package code.dao.impl;

import code.dao.EmployeeDao;
import code.domain.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by Napha on 11.01.2017.
 */
@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {
    @Autowired(required = true)
    private SessionFactory factory;

    private final Integer MAX_RESULT_SIZE = 5;

    public EmployeeDaoImpl() {
    }



    public Long create(Employee employee) {
        return (Long)factory.getCurrentSession().save(employee);
    }

    public Employee read(Long id) {
        return (Employee) factory.getCurrentSession().get(Employee.class, id);
    }

    public void update(Employee employee) {
        factory.getCurrentSession().update(employee);
    }

    public void delete(Long id) {

        Session session = factory.getCurrentSession();

        Employee parentEmployee = session.load(Employee.class, id);
        session.delete(parentEmployee);
        for (Project project : parentEmployee.getProjects()) {
            project.setProjectManager(null);
        }

        for (Task task : parentEmployee.getTasks()) {
            task.setEmployee(null);
        }
        session.flush();
    }

    public Employee findEmployeeByLogin(String login) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Employee e where e.username =:empLogin");
        query.setParameter("empLogin", login);
        return (Employee)query.uniqueResult();
    }

    public List<Employee> getAllEmployeesByCompanyId(Company company) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Employee e where e.company =:company");
        query.setParameter("company", company);
        query.setMaxResults(MAX_RESULT_SIZE);
        return query.list();
    }

    public List<Employee> getAllEmployeesByQualificationAndRole(Qualification qualification, Role role) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Employee e where e.role =:role and e.qualification =:qualification");
        query.setParameter("role", role.name());
        query.setParameter("qualification", qualification.name());
        query.setMaxResults(MAX_RESULT_SIZE);
        return query.list();
    }

    public List<Employee> getAllEmployeesByRole(Role role) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Employee e where e.role =:role");
        query.setParameter("role", role.name());
        query.setMaxResults(MAX_RESULT_SIZE);
        return query.list();
    }

//    public List<Employee> getAllEmployeesByProjectManager(Employee manager) {
//        Session session = factory.getCurrentSession();
//        Query query = session.createQuery("from Employee e INNER JOIN Task t INNER JOIN Project p WHERE p.projectManager =:manager");
//        query.setParameter("manager", manager);
//        query.setMaxResults(MAX_RESULT_SIZE);
//        List<Employee> l = query.list();
//        return l;
//    }

    public List<ViewEmployee> getAllEmployeesByProjectManager(Long managerId) {
        Session session = factory.getCurrentSession();

        NativeQuery query = session.createNativeQuery(
                "SELECT DISTINCT e.USER_ID, e.SURNAME, e.NAME " +
                "FROM Employee e " +
                    "INNER JOIN TASK t ON e.USER_ID = t.EMPLOYEE " +
                    "INNER JOIN PROJECT p ON t.PROJECT_PROJECT_ID = p.PROJECT_ID " +
                "WHERE p.PROJECT_MANAGER = :managerId", "EmployeeMapping2");
        query.setParameter("managerId", managerId);
        return query.list();
    }

    public List<Employee> getAllEmployees() {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Employee ");
        query.setMaxResults(MAX_RESULT_SIZE);
        return query.list();
    }

    public List<ViewEmployee> findEmployeesWhoHasOvertime(Integer sprintDuration, Long sprintId) {
        Session session = factory.getCurrentSession();


        NativeQuery query = session.createNativeQuery(
                "SELECT e.USER_ID, e.SURNAME, e.NAME, emp.o - :sprintDuration as overtime " +
                "FROM Employee e " +
                    "INNER JOIN " +
                        "(SELECT employee, sum(estimate)as o " +
                        "FROM Task t " +
                        "WHERE t.SPRINT_SPRINT_ID = :sprintId " +
                        "GROUP BY employee " +
                        "HAVING sum(estimate) > :sprintDuration) emp " +
                    "ON e.User_id = emp.employee"
        );
        query.setParameter("sprintDuration", sprintDuration);
        query.setParameter("sprintId", sprintId);
        return query.list();
    }

    public ViewEmployee findEmployeeIfHasOvertime(Integer sprintDuration, Long sprintId, Long employeeId){
        Session session = factory.getCurrentSession();

        NativeQuery query = session.createNativeQuery(
                "SELECT e.USER_ID, e.SURNAME, e.NAME, emp.o - :sprintDuration as overtime " +
                "FROM Employee e " +
                    "INNER JOIN " +
                        "(SELECT employee, sum(estimate)as o " +
                        "FROM Task t " +
                        "WHERE t.SPRINT_SPRINT_ID = :sprintId " +
                        "GROUP BY employee " +
                        "HAVING employee = :employeeId and sum(estimate) > :sprintDuration) emp " +
                    "ON e.User_id = emp.employee", "EmployeeMapping"
        );
        query.setParameter("sprintDuration", sprintDuration);
        query.setParameter("sprintId", sprintId);
        query.setParameter("employeeId", employeeId);

        return (ViewEmployee)query.getSingleResult();
    }
}
