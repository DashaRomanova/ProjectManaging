package code.service;

import code.domain.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Napha on 29.01.2017.
 */
public interface ReportService {
    List<ViewEmployees> findEmployeesWhoHasOvertime(Sprint sprint);
    ProjectStatisticReport getProjectStatisticReport(Long projectId);
    List<TaskReportView> getTaskStatisticBetweenDatesByEmployee(Long employeeId, Date start, Date end);
    List<Task> findTasksWithDelayByEmployee(Employee employee);
}
