package code.controller;

import code.domain.*;
import code.exception.EntityAlreadyExistException;
import code.service.CustomerService;
import code.service.EmployeeService;
import code.service.ProjectService;
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
 * Created by Napha on 22.01.2017.
 */
@Controller
@SessionAttributes("ProjectController")
public class ProjectController {
    private static final String DATE_PATTERN = "yyyy-mm-dd";
    public static final Logger log = Logger.getLogger(EmployeeController.class);
    @Autowired(required = true)
    private ProjectService projectService;
    @Autowired(required = true)
    private EmployeeService employeeService;
    @Autowired(required = true)
    private CustomerService customerService;

    public ProjectController() {
    }

    @RequestMapping(value = "/createProjectPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String createProjectPage(@RequestParam("customerId") Long customerId, Model model) {
        log.info("/createProjectPage code.controller.ProjectController");
        model.addAttribute("listManagers", employeeService.getAllEmployeesByRole(Role.ProjectManager));
        model.addAttribute("customer", customerService.getCustomerById(customerId));
        return "createProject";
    }

    @RequestMapping(value = "/createProject", method = {RequestMethod.POST})
    public String createProject(@RequestParam("customerId") Long customerId,
                                @RequestParam("name") String name,
                                @RequestParam("startDate") String startDate,
                                @RequestParam("finishDate") String finishDate,
                                @RequestParam("manager") Long userId,
                                Model model) throws EntityAlreadyExistException {
        log.info("/createProject code.controller.ProjectController");

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(DATE_PATTERN);
        Date startDateConverted = null;
        Date finishDateConverted = null;
        try {
            startDateConverted =  format.parse(startDate);
            finishDateConverted = format.parse(finishDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Customer customer = customerService.getCustomerById(customerId);
        Employee manager = null;
        if(userId != -1){
            manager = employeeService.getEmployeeById(userId);
        }
        projectService.createProject(name, startDateConverted, finishDateConverted, customer, manager);
        model.addAttribute("listProjects", projectService.getAllProjects());
        return "adminDashboardProjects";
    }

//    @RequestMapping(value = "/showProjectsByManagerPage", method = {RequestMethod.GET, RequestMethod.HEAD})
//    public String showProjectsByManagerPage(Model model) {
//        log.info("/showProjectsByManagerPage code.controller.ProjectController");
//
//        Long employeeId = new Long(2);
//        Employee manager = employeeService.getEmployeeById(employeeId);
//        model.addAttribute("listProjects", projectService.getProjectsByManager(manager));
//        return "showProjects";
//    }

    @RequestMapping(value = "/showAllProjectsPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String showAllProjectsPage(Model model) {
        log.info("/showAllProjectsPage code.controller.ProjectController");

        model.addAttribute("listProjects", projectService.getAllProjects());
        return "adminDashboardProjects";
    }

    @RequestMapping(value = "/deleteProjectPage", method = {RequestMethod.GET})
    public String deleteProjectPage(@RequestParam("projectId") Long projectId, Model model) {
        log.info("/deleteProjectPage code.controller.ProjectController");
        projectService.deleteProject(projectId);
        model.addAttribute("listProjects", projectService.getAllProjects());
        return "adminDashboardProjects";
    }

    @RequestMapping(value = "/editProjectPage", method = {RequestMethod.GET})
    public String editProjectPage(@RequestParam("projectId") Long projectId, Model model) {
        log.info("/editProjectPage code.controller.ProjectController");
        model.addAttribute("project", projectService.getProjectById(projectId));
        model.addAttribute("listManagers", employeeService.getAllEmployeesByRole(Role.ProjectManager));
        return "editProject";
    }

    @RequestMapping(value = "/editProject", method = {RequestMethod.POST})
    public String editEmployee(@RequestParam("projectId") Long projectId,
                               @RequestParam("name") String name,
                               @RequestParam("startDate") String startDate,
                               @RequestParam("finishDate") String finishDate,
                               @RequestParam("manager") Long userId,
                               Model model) {
        log.info("/editProject code.controller.ProjectController");

        Project project = projectService.getProjectById(projectId);
        project.setProjectName(name);
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(DATE_PATTERN);
        Date startDateConverted = null;
        Date finishDateConverted = null;
        try {
            startDateConverted =  format.parse(startDate);
            finishDateConverted = format.parse(finishDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        project.setProjectStartDate(startDateConverted);
        project.setProjectFinishDate(finishDateConverted);
        if(userId == -1){
            project.setProjectManager(null);
        } else {
            Employee manager = employeeService.getEmployeeById(userId);
            project.setProjectManager(manager);
        }

        projectService.updateProject(project);

        model.addAttribute("listProjects", projectService.getAllProjects());
        return "adminDashboardProjects";
    }

    @RequestMapping(value = "/chooseProjectPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String chooseProjectForTaskPage(@RequestParam("managerId") Long managerId,
                                           @RequestParam("entityName") String entityName,
                                           Model model) {
        log.info("/createTaskPage code.controller.TaskController");
        Employee employee = employeeService.getEmployeeById(managerId);
        model.addAttribute("listProjects", projectService.getProjectsByManager(employee));
        if(entityName == "Task"){
            model.addAttribute("reference", "createTaskPage");
        }
        if(entityName == "Sprint"){
            model.addAttribute("reference", "createSprintPage");
        }
        return "chooseProject";
    }
}
