package code.dao.impl;

import code.dao.TaskDao;
import code.domain.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Created by Napha on 14.01.2017.
 */
@Repository
@Transactional
public class TaskDaoImpl implements TaskDao {
    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory factory;

    private final Integer MAX_RESULT_SIZE = 5;

    public TaskDaoImpl() {
    }

    public Long create(Task task) {
        return (Long)factory.getCurrentSession().save(task);
    }

    public Task read(Long id) {
        return factory.getCurrentSession().get(Task.class, id);
    }

    public void update(Task task) {
        factory.getCurrentSession().update(task);
    }

    public void delete(Long id) {
        Session session = factory.getCurrentSession();

        Task parentTask = session.load(Task.class, id);
        session.delete(parentTask);
        for (Task task : parentTask.getInfluencingTasks()) {
            task.setParentInfluencingTask(null);
        }
        session.flush();
    }

    public Task findTaskByName(String name) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Task t where t.taskName =:taskName");
        query.setParameter("taskName", name);
        return (Task)query.uniqueResult();
    }

    public List<Task> findTasksByStatus(Status status) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Task t where t.status =:status");
        query.setParameter("status", status.name());
        query.setMaxResults(MAX_RESULT_SIZE);
        return query.list();
    }

//    public SprintStatisticReport getTaskStatisticBySprint(Long sprintId) {
//        Session session = factory.getCurrentSession();
//        NativeQuery query = session.createNativeQuery(
//                "SELECT SUM(t.ESTIMATE) sum_estimate, SUM(t.Actual_estimate) sum_actual_estimate" +
//                " FROM Task t" +
//                " WHERE t.SPRINT_SPRINT_ID = :sprintId", "SprintMapping");
//        query.setParameter("sprintId", sprintId);
//        Object result =  query.getSingleResult();
//        return null;
//    }

    public List<TaskReportView> getTaskStatisticBetweenDatesByEmployee(Long employeeId, Date start, Date end) {
        Session session = factory.getCurrentSession();
        NativeQuery query = session.createNativeQuery(
               "SELECT t.NAME Task_Name, s.NAME Sprint_Name, p.NAME Project_Name " +
               "FROM Task t " +
               "INNER JOIN Sprint s ON t.SPRINT_SPRINT_ID = s.SPRINT_ID " +
               "INNER JOIN Project p ON t.PROJECT_PROJECT_ID = p.PROJECT_ID " +
               "WHERE t.EMPLOYEE = :employeeId AND t.START_DATE between :start AND (:finish) " +
               "GROUP BY p.NAME, s.NAME, t.NAME", "TaskMapping");
        query.setParameter("employeeId", employeeId);
        query.setParameter("start", start, TemporalType.DATE);
        query.setParameter("finish", end, TemporalType.DATE);
        return query.getResultList();
    }

    public List<Task> findTasksWithDelayByEmployee(Employee employee) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Task t where t.employee =:employee and t.actualEstimate > t.estimate");
        query.setParameter("employee", employee);
        query.setMaxResults(MAX_RESULT_SIZE);
        return query.list();
    }

    public List<Task> findTasksByProjectManager(Employee manager) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Task t where t.project.projectManager =:manager");
        query.setParameter("manager", manager);
        query.setMaxResults(MAX_RESULT_SIZE);
        return query.list();
    }

    public List<Task> findAnotherTasksByProject(Project project, Long taskId) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Task t where t.taskId != :taskId and t.project =:project");
        query.setParameter("project", project);
        query.setParameter("taskId", taskId);
        query.setMaxResults(MAX_RESULT_SIZE);
        return query.list();
    }

    public List<Task> findTasksByProject(Project project) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Task t where t.project =:project");
        query.setParameter("project", project);
        query.setMaxResults(MAX_RESULT_SIZE);
        return query.list();
    }

    public List<Task> findTasksByProjectWhereSprintIsNull(Project project) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Task t where t.project =:project and t.sprint is null");
        query.setParameter("project", project);
        query.setMaxResults(MAX_RESULT_SIZE);
        return query.list();
    }

    public List<Task> findTasksByProjectManagerWhereStatusIsChangeRequest(Employee projectManager) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Task t where t.project.projectManager =:projectManager and t.status =:status");
        query.setParameter("projectManager", projectManager);
        query.setParameter("status", Status.ChangeRequest.name());
        query.setMaxResults(MAX_RESULT_SIZE);
        return query.list();
    }

    public List<Task> findTasksByEmployeeWhereStatusIsCompleted(Employee employee) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Task t where t.employee =:employee and t.status =:status");
        query.setParameter("employee", employee);
        query.setParameter("status", Status.Completed.name());
        query.setMaxResults(MAX_RESULT_SIZE);
        return query.list();
    }

    public List<Task> findTasksByEmployeeWhereStatusIsInProgress(Employee employee) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Task t where t.employee =:employee and t.status =:status");
        query.setParameter("employee", employee);
        query.setParameter("status", Status.InProgress.name());
        query.setMaxResults(MAX_RESULT_SIZE);
        return query.list();
    }

    public List<Task> findTasksByEmployeeWhichIsInSprint(Employee employee) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Task t where t.employee =:employee and t.status =:status and t.sprint is not null");
        query.setParameter("employee", employee);
        query.setParameter("status", Status.Confirmed.name());
        query.setMaxResults(MAX_RESULT_SIZE);
        return query.list();
    }

    public List<Task> findTasksByEmployeeWhereStatusIsAssigned(Employee employee) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Task t where t.employee =:employee and t.status =:status and t.requestedEstimate is null");
        query.setParameter("employee", employee);
        query.setParameter("status", Status.Assigned.name());
        query.setMaxResults(MAX_RESULT_SIZE);
        return query.list();
    }

    public List<Task> findTasksByEmployeeWhichRefused(Employee employee) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Task t where t.employee =:employee and t.status =:status and t.requestedEstimate is not null");
        query.setParameter("employee", employee);
        query.setParameter("status", Status.Assigned.name());
        query.setMaxResults(MAX_RESULT_SIZE);
        return query.list();
    }

    public List<Task> findTasksByEmployeeWhereStatusIsChangeRequest(Employee employee) {
        Session session = factory.getCurrentSession();
        Query query = session.createQuery("from Task t where t.employee =:employee and t.status =:status and t.requestedEstimate is not null");
        query.setParameter("employee", employee);
        query.setParameter("status", Status.ChangeRequest.name());
        query.setMaxResults(MAX_RESULT_SIZE);
        return query.list();
    }
}
