package code.controller;

import code.domain.Employee;
import code.domain.Project;
import code.domain.ProjectStatisticReport;
import code.domain.Sprint;
import code.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Napha on 29.01.2017.
 */
@Controller
@SessionAttributes("ReportController")
public class ReportController {
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final Logger log = Logger.getLogger(EmployeeController.class);

    @Autowired(required = true)
    private ProjectService projectService;
    @Autowired(required = true)
    private EmployeeService employeeService;
    @Autowired(required = true)
    private SprintService sprintService;
    @Autowired(required = true)
    private ReportService reportService;

    public ReportController() {
    }

    @RequestMapping(value = "/projectManager/chooseProjectForOvertimeReportPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String chooseProjectForOvertimeReportPage(@RequestParam("managerId") Long managerId,
                                                     Model model) {
        log.info("/projectManager/chooseSprintForOvertimeReportPage code.controller.ReportController");
        Employee employee = employeeService.getEmployeeById(managerId);
        model.addAttribute("listProjects", projectService.getProjectsByManager(employee));
        model.addAttribute("reference", "projectManager/chooseSprintForOvertimeReportPage");
        return "chooseProject";
    }

    @RequestMapping(value = "/projectManager/chooseProjectForReportPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String chooseProjectForReportPage(@RequestParam("managerId") Long managerId,
                                                     Model model) {
        log.info("/projectManager/chooseProjectForReportPage code.controller.ReportController");
        Employee employee = employeeService.getEmployeeById(managerId);
        model.addAttribute("listProjects", projectService.getProjectsByManager(employee));
        model.addAttribute("reference", "projectManager/showReportPage");
        return "chooseProject";
    }

    @RequestMapping(value = "/projectManager/showReportPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String showReportPage(@RequestParam("projectId") Long projectId,
                                             Model model) {
        log.info("/projectManager/showReportPage code.controller.ReportController");
        model.addAttribute("projectStatisticReport", reportService.getProjectStatisticReport(projectId));
        Project project = projectService.getProjectById(projectId);
        model.addAttribute("manager", project.getProjectManager());
        return "projectManagerDashboardProjectReport";
    }

    @RequestMapping(value = "/projectManager/chooseSprintForOvertimeReportPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String chooseSprintForOvertimeReportPage(@RequestParam("projectId") Long projectId,
                                                    Model model) {
        log.info("/projectManager/chooseSprintForOvertimeReportPage code.controller.ReportController");
        Project project = projectService.getProjectById(projectId);
        model.addAttribute("project", project);
        model.addAttribute("reference", "projectManager/showOvertimeReportPage");
        return "chooseSprint";
    }

    @RequestMapping(value = "/projectManager/showOvertimeReportPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String showOvertimeReportPage(@RequestParam("projectId") Long projectId,
                                         @RequestParam("sprintId") Long sprintId,
                                         Model model) {
        log.info("/projectManager/showOvertimeReportPage code.controller.ReportController");
        Project project = projectService.getProjectById(projectId);
        Sprint sprint = sprintService.getSprintById(sprintId);
        model.addAttribute("projectName", project.getProjectName());
        model.addAttribute("manager", project.getProjectManager());
        model.addAttribute("sprintName", sprint.getSprintName());
        model.addAttribute("employees", reportService.findEmployeesWhoHasOvertime(sprint));
        return "projectManagerDashboardOvertimeReport";
    }

    @RequestMapping(value = "/projectManager/chooseEmployeeForReportPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String chooseEmployeeForReportPage(@RequestParam("managerId") Long managerId,
                                             Model model) {
        log.info("/projectManager/chooseProjectForReportPage code.controller.ReportController");
        Employee manager = employeeService.getEmployeeById(managerId);
        model.addAttribute("listEmployees", employeeService.getAllEmployeesByProjectManager(managerId));
        model.addAttribute("manager", manager);
        return "chooseEmployeeForReport";
    }

    @RequestMapping(value = "/projectManager/chooseEmployeeForTaskReportPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String chooseEmployeeForTaskReportPage(@RequestParam("managerId") Long managerId,
                                              Model model) {
        log.info("/projectManager/chooseEmployeeForTaskReportPage code.controller.ReportController");
        Employee manager = employeeService.getEmployeeById(managerId);
        model.addAttribute("listEmployees", employeeService.getAllEmployeesByProjectManager(managerId));
        model.addAttribute("manager", manager);
        return "chooseEmployeeForReport2";
    }

    @RequestMapping(value = "/projectManager/chooseEmployeeForTaskReport", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String chooseEmployeeForTaskReport(@RequestParam("employeeId") Long employeeId,
                                              @RequestParam("managerId") Long managerId,
                                              Model model) {
        log.info("/projectManager/chooseEmployeeForTaskReport code.controller.ReportController");

        Employee employee = employeeService.getEmployeeById(employeeId);
        model.addAttribute("listTasks", reportService.findTasksWithDelayByEmployee(employee));
        model.addAttribute("employee", employee);
        model.addAttribute("manager", employeeService.getEmployeeById(managerId));
        return "projectManagerDashboardTaskTimeDeviation";
    }

    @RequestMapping(value = "/projectManager/chooseEmployeeForReport", method = {RequestMethod.POST, RequestMethod.HEAD})
    public String chooseEmployeeForReport(@RequestParam("managerId") Long managerId,
                                          @RequestParam("employeeId") Long employeeId,
                                          @RequestParam("startDate") String startDate,
                                          @RequestParam("endDate") String endDate,
                                          Model model) {
        log.info("/projectManager/chooseEmployeeForReport code.controller.ReportController");

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(DATE_PATTERN);
        Date finishDateConverted = null;
        Date startDateConverted = null;
        try {
            startDateConverted = format.parse(startDate);
            finishDateConverted = format.parse(endDate);
        } catch (ParseException e) {
            model.addAttribute("errorMassage", "Date is incorrect format. Should be " + DATE_PATTERN);
            model.addAttribute("reference", "/projectManager/chooseEmployeeForReportPage?managerId=" + managerId);
            log.warn(e.getMessage(), e);
            return "errorPage";
        }
        Employee employee = employeeService.getEmployeeById(employeeId);
        model.addAttribute("listTaskReportStatistic", reportService.getTaskStatisticBetweenDatesByEmployee(employeeId, startDateConverted, finishDateConverted));
        model.addAttribute("employee", employee);
        model.addAttribute("manager", employeeService.getEmployeeById(managerId));
        return "projectManagerDashboardEmployeeWorkStatistic";
    }
}
