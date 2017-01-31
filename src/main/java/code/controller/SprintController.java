package code.controller;

import code.domain.*;
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
    private static final String DATE_PATTERN = "yyyy-MM-dd";
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


    @RequestMapping(value = "/projectManager/showAllSprintsByProjectManagerIdPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String showAllSprintsByProjectManagerIdPage(@RequestParam("managerId") Long managerId,
                                      Model model) {
        log.info("/showAllSprintsByProjectManagerIdPage code.controller.SprintController");
        Employee manager = employeeService.getEmployeeById(managerId);
        model.addAttribute("manager", manager);
        model.addAttribute("listSprints", sprintService.getSprintsByProjectManager(manager));
        return "projectManagerDashboardSprint";
    }


    @RequestMapping(value = "/projectManager/deleteSprintPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String deleteSprintPage(@RequestParam("sprintId") Long sprintId,
                                   Model model) {

        Sprint sprint = sprintService.getSprintById(sprintId);
        Employee manager = sprint.getProject().getProjectManager();
        model.addAttribute("manager", manager);
        model.addAttribute("listSprints", sprintService.getSprintsByProjectManager(manager));
        sprintService.deleteSprint(sprintId);
        return "projectManagerDashboardSprint";
    }

    @RequestMapping(value = "/projectManager/editSprintPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String editSprintPage(@RequestParam("sprintId") Long sprintId,
                                   Model model) {

//        Sprint sprint = sprintService.getSprintById(sprintId);
//        model.addAttribute("listSprints", sprintService.getSprintsByProjectManager(sprint.getProject().getProjectManager()));
//        sprintService.deleteSprint(sprintId);
        return "projectManagerDashboardSprint";
    }



    @RequestMapping(value = "/projectManager/createSprintPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String createSprintPage(@RequestParam("projectId") Long projectId,
                                   Model model) {
        log.info("/createSprintPage code.controller.SprintController");
        model.addAttribute("project", projectService.getProjectById(projectId));
        return "createSprint";
    }

    //ѕроверка того, что фазы выполн€ютс€ последовательно
    //фазы и проект будут выполнены в срок (или с задержкой)
    @RequestMapping(value = "/projectManager/createSprint", method = {RequestMethod.POST})
    public String createSprint(@RequestParam("name") String name,
                               @RequestParam("projectId") Long projectId,
                               @RequestParam("sprint") Long sprintId,
                               @RequestParam("finishDate") String finishDate,
                               Model model){
        log.info("/createSprint code.controller.SprintController");

        Project project = projectService.getProjectById(projectId);
        if(project == null){
            String errorMassage = "There is not Project with id = " + projectId;
            model.addAttribute("errorMassage", errorMassage);
            model.addAttribute("reference", "/projectManager/createSprintPage?projectId=" + projectId);
            log.warn(errorMassage);
            return "errorPage";
        }

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(DATE_PATTERN);
        Date finishDateConverted = null;
        try {
            finishDateConverted = format.parse(finishDate);
        } catch (ParseException e) {
            model.addAttribute("errorMassage", "Date is incorrect format. Should be " + DATE_PATTERN);
            model.addAttribute("reference", "/projectManager/createSprintPage?projectId=" + projectId);
            log.warn(e.getMessage(), e);
            return "errorPage";
        }
        if(finishDateConverted.after(project.getProjectFinishDate())){
            String errorMassage = "Finish date of sprint after finish date of project";
            model.addAttribute("errorMassage", errorMassage);
            model.addAttribute("reference", "/projectManager/createSprintPage?projectId=" + projectId);
            log.warn(errorMassage);
            return "errorPage";
        }
        if(sprintId == -1 && project.getSprints() != null ){
            String errorMassage = "Previous sprint has not been chosen";
            model.addAttribute("errorMassage", errorMassage);
            model.addAttribute("reference", "/projectManager/createSprintPage?projectId=" + projectId);
            log.warn(errorMassage);
            return "errorPage";
        }
        if(sprintId == -1 && project.getSprints() == null ){
            Long newSprintId = sprintService.createSprint(name, project, null, finishDateConverted);
            Sprint sprint = sprintService.getSprintById(newSprintId);
            project.addSprint(sprint);
            projectService.updateProject(project);
        }

        Sprint previousSprint = sprintService.getSprintById(sprintId);
        if (finishDateConverted.before(previousSprint.getSprintFinishDate())){
            String errorMassage = "Finish date of sprint before finish date of previous sprint";
            model.addAttribute("errorMassage", errorMassage);
            model.addAttribute("reference", "/projectManager/createSprintPage?projectId=" + projectId);
            log.warn(errorMassage);
            return "errorPage";
        }
        Long newSprintId = sprintService.createSprint(name, project, previousSprint, finishDateConverted);
        Sprint sprint = sprintService.getSprintById(newSprintId);
        project.addSprint(sprint);
        projectService.updateProject(project);

        Employee manager = project.getProjectManager();
        model.addAttribute("manager", manager);
        model.addAttribute("listSprints", sprintService.getSprintsByProjectManager(manager));
        return "projectManagerDashboardSprint";
    }

    @RequestMapping(value = "/projectManager/addTaskToSprintEditPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String addTaskToSprintPage(@RequestParam("taskId") Long taskId,
                                      @RequestParam("sprintId") Long sprintId,
                                      @RequestParam("startDate") String startDate,
                                      @RequestParam("finishDate") String finishDate,
                                     Model model){
        log.info("/addTaskToSprintPage code.controller.SprintController");

        Task task = taskService.getTaskById(taskId);
        Sprint sprint = sprintService.getSprintById(sprintId);
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(DATE_PATTERN);
        Date startDateConverted = null;
        Date finishDateConverted = null;
        try {
            startDateConverted = format.parse(startDate);
            finishDateConverted = format.parse(finishDate);
        } catch (ParseException e) {
            model.addAttribute("errorMassage", "Date is incorrect format. Should be " + DATE_PATTERN);
            model.addAttribute("reference", "/projectManager/addTaskToSprintRedirect?taskId=" + taskId + "&sprintId=" + sprintId);
            log.warn(e.getMessage(), e);
            return "errorPage";
        }
        task.setStartDate(startDateConverted);
        task.setExpectedCompletionDate(finishDateConverted);
        task.setSprint(sprint);
        taskService.updateTask(task);
        sprint.addTask(task);
        sprintService.updateSprint(sprint);

        Employee manager = sprint.getProject().getProjectManager();
        model.addAttribute("manager", manager);
        model.addAttribute("listSprints", sprintService.getSprintsByProjectManager(manager));
        return "projectManagerDashboardSprint";
    }

    @RequestMapping(value = "/projectManager/addTaskToSprintRedirect", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String addTaskToSprintRedirect(@RequestParam("taskId") Long taskId,
                                      @RequestParam("sprintId") Long sprintId,
                                      Model model){
        log.info("/addTaskToSprintRedirect code.controller.SprintController");

        model.addAttribute("task", taskService.getTaskById(taskId));
        model.addAttribute("sprint", sprintService.getSprintById(sprintId));
        return "editStartDateTask";
    }

   // ѕроверка того, что зависимые задачи выполн€ютс€ в правильной последовательности
   // ѕроверка того, что у сотрудников нет овертаймов
    @RequestMapping(value = "/projectManager/addTaskToSprint", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String addInfluencingTask(@RequestParam("taskId") Long taskId,
                                     @RequestParam("Id") Long sprintId,
                                     Model model){
        log.info("/addTaskToSprint code.controller.SprintController");
        Task task = taskService.getTaskById(taskId);
        Sprint sprint = sprintService.getSprintById(sprintId);
        Employee manager = sprint.getProject().getProjectManager();
        if(Status.Confirmed.equals(task.getStatus())){
            if(task.getInfluencingTasks() == null || task.getInfluencingTasks().isEmpty()){
                model.addAttribute("task", task);
                model.addAttribute("sprint", sprint);
                return "editStartDateTask";
            }else {
                for(Task influencingTask: task.getInfluencingTasks()){
                    Date expectedCompletionDate = influencingTask.getExpectedCompletionDate();
                    if(expectedCompletionDate != null){
                        if(expectedCompletionDate.after(sprint.getSprintStartDate())){
                            String errorMassage = "Influencing task <"
                                    + influencingTask.getTaskName() + "> will be completed from "
                                    + influencingTask.getStartDate() + " to "
                                    + influencingTask.getExpectedCompletionDate()
                                    + "Therefore, this task can not be added to the Sprint";
                            model.addAttribute("errorMassage", errorMassage);
                            model.addAttribute("reference", "/projectManager/showAllSprintsByProjectManagerIdPage?managerId=" + manager.getUserId());
                            log.warn(errorMassage);
                            return "errorPage";
                        } else{
                            ViewEmployee employee = employeeService.findEmployeeIfHasOvertime(sprint, task.getEmployee().getUserId());
                            if(employee != null){
                                String question = "Employee " + employee.getSurname() + " " + employee.getName() + " has overtime " + employee.getOvertime() + " hour(s). Would you like to add this task anyway?";
                                String referenceYes = "/projectManager/addTaskToSprintRedirect?taskId=" + influencingTask.getTaskId() + "&Id=" + sprint.getSprintId();
                                String referenceNo = "/projectManager/showAllSprintsByProjectManagerIdPage?managerId=" + manager.getUserId();
                                model.addAttribute("question", question);
                                model.addAttribute("referenceYes", referenceYes);
                                model.addAttribute("referenceNo", referenceNo);
                                return "question";
                            } else{
                                model.addAttribute("task", task);
                                model.addAttribute("sprint", sprint);
                                return "editStartDateTask";
                            }
                        }
                    } else {
                        String question = "Influencing task <" + influencingTask.getTaskName() + "> is not added to any Sprints. Would you like to add this Task to this Sprint?";
                        String referenceYes = "/projectManager/addTaskToSprint?taskId=" + influencingTask.getTaskId() + "&Id=" + sprint.getSprintId();
                        String referenceNo = "/projectManager/showAllSprintsByProjectManagerIdPage?managerId=" + manager.getUserId();
                        model.addAttribute("question", question);
                        model.addAttribute("referenceYes", referenceYes);
                        model.addAttribute("referenceNo", referenceNo);
                        return "question";
                    }
                }
            }
        } else {
            String errorMassage = "Task <" + task.getTaskName() + "> not confirmed! Can not be added.";
            model.addAttribute("errorMassage", errorMassage);
            model.addAttribute("reference", "/projectManager/showAllSprintsByProjectManagerIdPage?managerId=" + manager.getUserId());
            log.warn(errorMassage);
            return "errorPage";
        }



        model.addAttribute("manager", manager);
        model.addAttribute("listSprints", sprintService.getSprintsByProjectManager(manager));
        return "projectManagerDashboardSprint";
    }
}
