package code.controller;

import code.domain.*;
import code.exception.DateOutOfSprintDateBoundsException;
import code.exception.TaskNotConfirmedException;
import code.service.EmployeeService;
import code.service.ProjectService;
import code.service.SprintService;
import code.service.TaskService;
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
@SessionAttributes("SprintController")
public class SprintController {
    private static final String DATE_PATTERN = "yyyy-mm-dd";
    public static final Logger log = Logger.getLogger(EmployeeController.class);
    @Autowired(required = true)
    private SprintService sprintService;
    @Autowired(required = true)
    private ProjectService projectService;
    @Autowired(required = true)
    private TaskService taskService;
    @Autowired(required = true)
    private EmployeeService employeeService;

    public SprintController() {
    }


    @RequestMapping(value = "/showAllSprintsByProjectManagerIdPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String showAllSprintsByProjectManagerIdPage(@RequestParam("managerId") Long managerId,
                                      Model model) {
        log.info("/showAllSprintsByProjectManagerIdPage code.controller.SprintController");
        Employee manager = employeeService.getEmployeeById(managerId);
        model.addAttribute("listSprints", sprintService.getSprintsByProjectManager(manager));
        return "projectManagerDashboardSprint";
    }


    @RequestMapping(value = "/deleteSprintPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String deleteSprintPage(@RequestParam("sprintId") Long sprintId,
                                   Model model) {

        Sprint sprint = sprintService.getSprintById(sprintId);
        model.addAttribute("listSprints", sprintService.getSprintsByProjectManager(sprint.getProject().getProjectManager()));
        sprintService.deleteSprint(sprintId);
        return "projectManagerDashboardSprint";
    }

    @RequestMapping(value = "/editSprintPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String editSprintPage(@RequestParam("sprintId") Long sprintId,
                                   Model model) {

//        Sprint sprint = sprintService.getSprintById(sprintId);
//        model.addAttribute("listSprints", sprintService.getSprintsByProjectManager(sprint.getProject().getProjectManager()));
//        sprintService.deleteSprint(sprintId);
        return "projectManagerDashboardSprint";
    }



    @RequestMapping(value = "/createSprintPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String createSprintPage(@RequestParam("projectId") Long projectId,
                                   Model model) {
        log.info("/createSprintPage code.controller.SprintController");
        model.addAttribute("project", projectService.getProjectById(projectId));
        return "createSprint";
    }


    @RequestMapping(value = "/createSprint", method = {RequestMethod.POST})
    public String createSprint(@RequestParam("name") String name,
                               @RequestParam("projectId") Long projectId,
                               @RequestParam("sprint") Long sprintId,
                               @RequestParam("finishDate") String finishDate,
                               Model model) throws ParseException{
        log.info("/createSprint code.controller.SprintController");

        Project project = projectService.getProjectById(projectId);
        if(project == null){
            throw new NullPointerException("There is not Project with id = " + projectId);
        }

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(DATE_PATTERN);
        Date finishDateConverted = format.parse(finishDate);
        if(finishDateConverted.after(project.getProjectFinishDate())){
            throw new IllegalArgumentException("Finish date of sprint after finish date of project");

        }
        if(sprintId == -1 && project.getSprints() != null ){
            throw new IllegalArgumentException("Previous sprint has not been chosen");

        }
        if(sprintId == -1 && project.getSprints() == null ){
            Long newSprintId = sprintService.createSprint(name, project, null, finishDateConverted);
            Sprint sprint = sprintService.getSprintById(newSprintId);
            project.addSprint(sprint);
            projectService.updateProject(project);
        }

        Sprint previousSprint = sprintService.getSprintById(sprintId);
        if (finishDateConverted.before(previousSprint.getSprintFinishDate())){
            throw new IllegalArgumentException("Finish date of sprint before finish date of previous sprint");
        }
        Long newSprintId = sprintService.createSprint(name, project, previousSprint, finishDateConverted);
        Sprint sprint = sprintService.getSprintById(newSprintId);
        project.addSprint(sprint);
        projectService.updateProject(project);

        model.addAttribute("listSprints", sprintService.getSprintsByProjectManager(project.getProjectManager()));
        return "projectManagerDashboardSprint";
    }

    @RequestMapping(value = "/addTaskToSprintEditPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String addTaskToSprintPage(@RequestParam("taskId") Long taskId,
                                      @RequestParam("sprintId") Long sprintId,
                                      @RequestParam("startDate") String startDate,
                                      @RequestParam("finishDate") String finishDate,
                                     Model model) throws ParseException{
        log.info("/addTaskToSprintPage code.controller.SprintController");

        Task task = taskService.getTaskById(taskId);
        Sprint sprint = sprintService.getSprintById(sprintId);
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(DATE_PATTERN);
        Date startDateConverted = format.parse(startDate);
        Date finishDateConverted = format.parse(finishDate);
        task.setStartDate(startDateConverted);
        task.setExpectedCompletionDate(finishDateConverted);
        task.setSprint(sprint);
        taskService.updateTask(task);
        sprint.addTask(task);
        sprintService.updateSprint(sprint);


        model.addAttribute("listSprints", sprintService.getSprintsByProjectManager(sprint.getProject().getProjectManager()));
        return "projectManagerDashboardSprint";
    }

    @RequestMapping(value = "/addTaskToSprint", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String addInfluencingTask(@RequestParam("taskId") Long taskId,
                                     @RequestParam("Id") Long sprintId,
                                     Model model) throws TaskNotConfirmedException, DateOutOfSprintDateBoundsException {
        log.info("/addTaskToSprint code.controller.SprintController");
        Task task = taskService.getTaskById(taskId);
        Sprint sprint = sprintService.getSprintById(sprintId);
        if(Status.Confirmed.equals(task.getStatus())){
            if(task.getInfluencingTasks() == null){
                sprint.addTask(task);
                sprintService.updateSprint(sprint);
                task.setSprint(sprint);
                taskService.updateTask(task);
            }else {
                for(Task influencingTask: task.getInfluencingTasks()){
                    Date expectedCompletionDate = influencingTask.getExpectedCompletionDate();
                    if(expectedCompletionDate != null){
                        if(expectedCompletionDate.after(sprint.getSprintStartDate())){
                            throw new DateOutOfSprintDateBoundsException("Influencing task <"
                                    + influencingTask.getTaskName() + "> will be completed from "
                                    + influencingTask.getStartDate() + " to "
                                    + influencingTask.getExpectedCompletionDate()
                                    + "Therefore, this task can not be added to the Sprint");
                        } else{
                            model.addAttribute("task", task);
                            model.addAttribute("sprint", sprint);
                            return "editStartDateTask";
                        }
                    } else {
                        model.addAttribute("task", influencingTask);
                        model.addAttribute("sprint", sprint);
                        return "question";
                    }
                }
            }
        } else {
            throw new TaskNotConfirmedException("Task <" + task.getTaskName() + "> not confirmed! Can not be added.");
        }


        model.addAttribute("listSprints", sprintService.getSprintsByProjectManager(sprint.getProject().getProjectManager()));
        return "projectManagerDashboardSprint";
    }
}
