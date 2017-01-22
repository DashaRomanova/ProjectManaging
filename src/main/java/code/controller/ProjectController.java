package code.controller;

import code.domain.Employee;
import code.domain.Project;
import code.exception.EntityAlreadyExistException;
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

    public ProjectController() {
    }

    @RequestMapping(value = "/createProjectPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String createProjectPage(Model model) {
        log.info("/createProjectPage code.controller.ProjectController");
        return "createProject";
    }

    @RequestMapping(value = "/createProject", method = {RequestMethod.POST})
    public String createProject(@RequestParam("name") String name,
                                 @RequestParam("startDate") String startDate,
                                 @RequestParam("finishDate") String finishDate) throws EntityAlreadyExistException {
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
        projectService.createProject(name, startDateConverted, finishDateConverted);
        return "showProjects";
    }

    @RequestMapping(value = "/showProjectsByManagerPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String showProjectsByManagerPage(Model model) {
        log.info("/showProjectsByManagerPage code.controller.ProjectController");

        Long employeeId = new Long(2);
        Employee manager = employeeService.getEmployeeById(employeeId);
        model.addAttribute("listProjects", projectService.getProjectsByManager(manager));
        return "showProjects";
    }

    @RequestMapping(value = "/editProjectPage", method = {RequestMethod.GET})
    public String editProjectPage(@RequestParam("projectId") Long projectId, Model model) {
        log.info("/editProjectPage code.controller.ProjectController");
        model.addAttribute("project", projectService.getProjectById(projectId));
        return "editProject";
    }

    @RequestMapping(value = "/editProject", method = {RequestMethod.POST})
    public String editEmployee(@RequestParam("projectId") Long projectId,
                               @RequestParam("name") String name,
                               @RequestParam("startDate") String startDate,
                               @RequestParam("finishDate") String finishDate) {
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
        projectService.updateProject(project);

        return "showProjects";
    }
}
