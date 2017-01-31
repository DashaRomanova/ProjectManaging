package code.controller;

import code.domain.*;
import code.exception.EntityAlreadyExistException;
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

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Napha on 16.01.2017.
 */
@Controller
@SessionAttributes("TaskController")
public class TaskController {
    public static final Logger log = Logger.getLogger(TaskController.class);
    @Autowired(required = true)
    private TaskService taskService;
    @Autowired(required = true)
    private ProjectService projectService;
    @Autowired(required = true)
    private EmployeeService employeeService;
    @Autowired(required = true)
    private SprintService sprintService;

    public TaskController() {

    }

    @RequestMapping(value = "/employee/showAllRequestsByEmployeeIdPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String showAllRequestsByEmployeeIdPage(@RequestParam("employeeId") Long employeeId,
                                                  Model model) {
        log.info("/showAllRequestsByEmployeeIdPage code.controller.TaskController");
        Employee employee = employeeService.getEmployeeById(employeeId);
        model.addAttribute("employee", employee);
        model.addAttribute("listTasks", taskService.getTasksByEmployeeWhereStatusIsChangeRequest(employee));
        return "employeeDashboardChangeRequestTask";
    }

    @RequestMapping(value = "/employee/showAllRefusedTasksByEmployeeIdPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String showAllRefusedTasksByEmployeeIdPage(@RequestParam("employeeId") Long employeeId,
                                                      Model model) {
        log.info("/showAllRefusedTasksByEmployeeIdPage code.controller.TaskController");
        Employee employee = employeeService.getEmployeeById(employeeId);
        model.addAttribute("employee", employee);
        model.addAttribute("listTasks", taskService.getTasksByEmployeeWhichRefused(employee));
        return "employeeDashboardRefusedTask";
    }

    @RequestMapping(value = "/employee/showAllReadyToRunTasksByEmployeeIdPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String showAllReadyToRunTasksByEmployeeIdPage(@RequestParam("employeeId") Long employeeId,
                                                         Model model) {
        log.info("/showAllReadyToRunTasksByEmployeeIdPage code.controller.TaskController");
        Employee employee = employeeService.getEmployeeById(employeeId);
        model.addAttribute("employee", employee);
        model.addAttribute("listTasks", taskService.getTasksByEmployeeWhichIsInSprint(employee));
        return "employeeDashboardReadyToRunTask";
    }

    @RequestMapping(value = "/employee/beginTask", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String beginTask(@RequestParam("taskId") Long taskId,
                            Model model) {
        log.info("/beginTask code.controller.TaskController");
        Task task = taskService.getTaskById(taskId);
        task.setStatus(Status.InProgress);
        taskService.updateTask(task);
        Employee employee = task.getEmployee();
        model.addAttribute("employee", employee);
        model.addAttribute("listTasks", taskService.getTasksByEmployeeWhichIsInSprint(employee));
        return "employeeDashboardReadyToRunTask";
    }

    @RequestMapping(value = "/employee/showAllAssignedTasksByEmployeeIdPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String showAllAssignedTasksByEmployeeIdPage(@RequestParam("employeeId") Long employeeId,
                                                         Model model) {
        log.info("/showAllAssignedTasksByEmployeeIdPage code.controller.TaskController");
        Employee employee = employeeService.getEmployeeById(employeeId);
        model.addAttribute("employee", employee);
        model.addAttribute("listTasks", taskService.getTasksByEmployeeWhereStatusIsAssigned(employee));
        return "employeeDashboardAssignedTask";
    }

    @RequestMapping(value = "/employee/requestEstimateTaskPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String requestEstimateTaskPage(@RequestParam("taskId") Long taskId,
                               Model model) {
        log.info("/requestEstimateTaskPage code.controller.TaskController");
        model.addAttribute("task", taskService.getTaskById(taskId));
        return "requestEstimateTask";
    }

    @RequestMapping(value = "/employee/requestEstimateTask", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String requestEstimateTask(@RequestParam("taskId") Long taskId,
                                      @RequestParam("requestEstimate") Integer requestEstimate,
                                          Model model) {
        log.info("/requestEstimateTask code.controller.TaskController");
        Task task = taskService.getTaskById(taskId);
        task.setRequestedEstimate(requestEstimate);
        task.setStatus(Status.ChangeRequest);
        taskService.updateTask(task);
        Employee employee = task.getEmployee();
        model.addAttribute("employee", employee);
        model.addAttribute("listTasks", taskService.getTasksByEmployeeWhereStatusIsAssigned(employee));
        return "employeeDashboardAssignedTask";
    }

    @RequestMapping(value = "/employee/confirmTask", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String confirmTask(@RequestParam("taskId") Long taskId,
                              Model model) {
        log.info("/confirmTask code.controller.TaskController");
        Task task = taskService.getTaskById(taskId);
        task.setStatus(Status.Confirmed);
        taskService.updateTask(task);
        Employee employee = task.getEmployee();
        model.addAttribute("employee", employee);
        model.addAttribute("listTasks", taskService.getTasksByEmployeeWhereStatusIsAssigned(employee));
        return "employeeDashboardAssignedTask";
    }

    @RequestMapping(value = "/employee/showAllCompletedTasksByEmployeeIdPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String showAllCompletedTasksByEmployeeIdPage(@RequestParam("employeeId") Long employeeId,
                                                     Model model) {
        log.info("/showAllCompletedTasksByEmployeeIdPage code.controller.TaskController");
        Employee employee = employeeService.getEmployeeById(employeeId);
        model.addAttribute("employee", employee);
        model.addAttribute("listTasks", taskService.getTasksByEmployeeWhereStatusIsCompleted(employee));
        return "employeeDashboardCompletedTask";
    }

    @RequestMapping(value = "/employee/showAllInProgressTasksByEmployeeIdPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String showAllInProgressTasksByEmployeeIdPage(@RequestParam("employeeId") Long employeeId,
                                                        Model model) {
        log.info("/showAllInProgressTasksByEmployeeIdPage code.controller.TaskController");
        Employee employee = employeeService.getEmployeeById(employeeId);
        model.addAttribute("employee", employee);
        model.addAttribute("listTasks", taskService.getTasksByEmployeeWhereStatusIsInProgress(employee));
        return "employeeDashboardInProgressTask";
    }

    @RequestMapping(value = "/employee/completeTask", method = {RequestMethod.POST, RequestMethod.HEAD})
    public String completeTask(@RequestParam("taskId") Long taskId,
                               @RequestParam("actualEstimate") Integer actualEstimate,
                               Model model) {
        log.info("/completeTask code.controller.TaskController");
        Task task = taskService.getTaskById(taskId);
        task.setStatus(Status.Completed);
        task.setActualCompletionDate(new Date());
        task.setActualEstimate(actualEstimate);
        taskService.updateTask(task);
        Employee employee = task.getEmployee();
        model.addAttribute("employee", employee);
        model.addAttribute("listTasks", taskService.getTasksByEmployeeWhereStatusIsInProgress(employee));
        return "employeeDashboardInProgressTask";
    }

    @RequestMapping(value = "/projectManager/showAllTasksByProjectManagerIdPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String showAllTasksByProjectManagerIdPage(@RequestParam("managerId") Long managerId,
                                                     Model model) {
        log.info("/showAllTasksByProjectManagerIdPage code.controller.TaskController");
        Employee manager = employeeService.getEmployeeById(managerId);
        model.addAttribute("manager", manager);
        model.addAttribute("listTasks", taskService.getTasksByProjectManager(manager));
        return "projectManagerDashboardTask";
    }

    @RequestMapping(value = "/projectManager/showAllRequestsByProjectManagerIdPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String showAllRequestsByProjectManagerIdPage(@RequestParam("managerId") Long managerId,
                                                        Model model) {
        log.info("/showAllRequestsByProjectManagerIdPage code.controller.TaskController");
        Employee manager = employeeService.getEmployeeById(managerId);
        model.addAttribute("manager", manager);
        model.addAttribute("listTasks", taskService.getTasksByProjectManagerWhereStatusIsChangeRequest(manager));
        return "projectManagerDashboardRequest";
    }

    @RequestMapping(value = "/projectManager/confirmRequestPage", method = {RequestMethod.GET, RequestMethod.HEAD})
     public String confirmRequestPage(@RequestParam("taskId") Long taskId,
                                      Model model) {
        log.info("/confirmRequestPage code.controller.TaskController");
        Task task = taskService.getTaskById(taskId);
        task.setEstimate(task.getRequestedEstimate());
        task.setRequestedEstimate(null);
        task.setStatus(Status.Assigned);
        taskService.updateTask(task);
        Employee manager = task.getProject().getProjectManager();
        model.addAttribute("manager", manager);
        model.addAttribute("listTasks", taskService.getTasksByProjectManagerWhereStatusIsChangeRequest(manager));
        return "projectManagerDashboardRequest";
    }

    @RequestMapping(value = "/projectManager/refuseRequestPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String refuseRequestPage(@RequestParam("taskId") Long taskId,
                                     Model model) {
        log.info("/refuseRequestPage code.controller.TaskController");
        Task task = taskService.getTaskById(taskId);
        task.setStatus(Status.Assigned);
        taskService.updateTask(task);
        Employee manager = task.getProject().getProjectManager();
        model.addAttribute("manager", manager);
        model.addAttribute("listTasks", taskService.getTasksByProjectManagerWhereStatusIsChangeRequest(manager));
        return "projectManagerDashboardRequest";
    }



    @RequestMapping(value = "/projectManager/chooseTask", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String chooseInfluencingTask(@RequestParam("Id") Long id,
                                        @RequestParam("entityName") String entityName,
                                        Model model){
        log.info("/chooseTask code.controller.TaskController");
        if(entityName.equals("Task")){
            Task task = taskService.getTaskById(id);
            if(Status.Completed.equals(task.getStatus())){
                String errorMassage = "Task <" + task.getTaskName() + "> already completed! Can not be edit.";
                model.addAttribute("errorMassage", errorMassage);
                model.addAttribute("reference", "/projectManager/showAllSprintsByProjectManagerIdPage?managerId=" + task.getProject().getProjectManager().getUserId());
                log.warn(errorMassage);
                return "errorPage";
            }
            model.addAttribute("reference", "projectManager/addInfluencingTask");
            List<Task> tasks = taskService.getAnotherTasksByProject(task.getProject(), task.getTaskId());
            model.addAttribute("listTasks", tasks);
        }
        if(entityName.equals("Sprint")){
            Sprint sprint = sprintService.getSprintById(id);
            model.addAttribute("reference", "projectManager/addTaskToSprint");
            model.addAttribute("listTasks", taskService.getTasksByProjectWhereSprintIsNull(sprint.getProject()));
        }
        model.addAttribute("Id", id);
        return "chooseTask";
    }

    @RequestMapping(value = "/projectManager/addInfluencingTask", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String addInfluencingTask(@RequestParam("taskId") Long taskId,
                                     @RequestParam("Id") Long parentTaskId,
                                     Model model) {
        log.info("/addInfluencingTask code.controller.TaskController");
        Task task = taskService.getTaskById(taskId);
        Task parentTask = taskService.getTaskById(parentTaskId);
        parentTask.addInfluencingTask(task);
        task.setParentInfluencingTask(parentTask);
        taskService.updateTask(parentTask);
        taskService.updateTask(task);
        Employee manager = parentTask.getProject().getProjectManager();
        model.addAttribute("manager", manager);
        model.addAttribute("listTasks", taskService.getTasksByProjectManager(manager));
        return "projectManagerDashboardTask";
    }

    @RequestMapping(value = "/projectManager/addEmployeeForTask", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String addEmployeeForTask(@RequestParam("taskId") Long taskId,
                                     @RequestParam("empId") Long empId,
                                     Model model) {
        log.info("/addEmployeeForTask code.controller.TaskController");
        Task task = taskService.getTaskById(taskId);
        Employee employee = employeeService.getEmployeeById(empId);
        task.setEmployee(employee);
        task.setStatus(Status.Assigned);
        taskService.updateTask(task);
        employee.addTask(task);
        employeeService.updateEmployee(employee);

        Employee manager = task.getProject().getProjectManager();
        model.addAttribute("manager", manager);
        model.addAttribute("listTasks", taskService.getTasksByProjectManager(manager));
        return "projectManagerDashboardTask";
    }


    @RequestMapping(value = "/projectManager/createTaskPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String createTaskPage(@RequestParam("projectId") Long projectId,
                                 Model model) {
        log.info("/createTaskPage code.controller.TaskController");
        model.addAttribute("listQualifications", Arrays.asList(Qualification.values()));
        model.addAttribute("project", projectService.getProjectById(projectId));
        return "createTask";
    }

    @RequestMapping(value = "/projectManager/createTask", method = {RequestMethod.POST})
    public String createTask(@RequestParam("name") String name,
                             @RequestParam("qualifications") String qualification,
                             @RequestParam("estimate") Integer estimate,
                             @RequestParam("description") String description,
                             @RequestParam("projectId") Long projectId,
                             Model model){
        log.info("/createTask code.controller.TaskController");
        Project project = projectService.getProjectById(projectId);
        Long taskId = null;
        try {
            taskId = taskService.createTask(name, estimate, description, Qualification.valueOf(qualification), project);
        } catch (EntityAlreadyExistException e) {
            model.addAttribute("errorMassage", e.getMessage());
            model.addAttribute("reference", "/projectManager/createTaskPage?projectId=" + projectId);
            log.warn(e.getMessage(), e);
            return "errorPage";
        }
        project.addTask(taskService.getTaskById(taskId));
        projectService.updateProject(project);
        Employee manager = project.getProjectManager();
        model.addAttribute("manager", manager);
        model.addAttribute("listTasks", taskService.getTasksByProjectManager(manager));
        return "projectManagerDashboardTask";
    }


//    @RequestMapping(value = "/showTasksByStatusPage", method = {RequestMethod.GET, RequestMethod.HEAD})
//    public String showTasksByStatusPage(Model model) {
//        log.info("/showTasksByStatusPage code.controller.TaskController");
//
//        model.addAttribute("listTasks", taskService.getTasksByStatus(Status.valueOf("Assigned")));
//        return "showTasks";
//    }

    @RequestMapping(value = "/projectManager/editTaskPage", method = {RequestMethod.GET})
    public String editTaskPage(@RequestParam("taskId") Long taskId,
                               Model model){
        log.info("/editTaskPage code.controller.TaskController");
        Task task = taskService.getTaskById(taskId);
        if(Status.Completed.equals(task.getStatus())){
            String errorMassage = "Task <" + task.getTaskName() + "> already completed! Can not be edit.";
            model.addAttribute("errorMassage", errorMassage);
            model.addAttribute("reference", "/projectManager/showAllTasksByProjectManagerIdPage?managerId=" + task.getProject().getProjectManager().getUserId());
            log.warn(errorMassage);
            return "errorPage";
        }
        model.addAttribute("task", task);
        return "editTask";
    }

    @RequestMapping(value = "/projectManager/editTask", method = {RequestMethod.POST})
    public String editTask(@RequestParam("taskId") Long taskId,
                           @RequestParam("name") String name,
                           @RequestParam("description") String description,
                           Model model) {
        log.info("/editEmployee code.controller.EmployeeController");

        Task task = taskService.getTaskById(taskId);
        task.setTaskName(name);
        task.setTaskDescription(description);
        taskService.updateTask(task);
        Employee manager = task.getProject().getProjectManager();
        model.addAttribute("manager", manager);

        model.addAttribute("listTasks", taskService.getTasksByProjectManager(manager));
        return "projectManagerDashboardTask";
    }

    @RequestMapping(value = "/projectManager/deleteTaskPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String deleteTaskPage(@RequestParam("taskId") Long taskId,
                                 Model model) {
        log.info("/deleteTaskPage code.controller.TaskController");
        Task task = taskService.getTaskById(taskId);
        Employee manager = task.getProject().getProjectManager();
        model.addAttribute("manager", manager);
        model.addAttribute("listTasks", taskService.getTasksByProjectManager(manager));
        taskService.deleteTask(taskId);
        return "projectManagerDashboardTask";
    }
}
