package code.controller;

import code.domain.Qualification;
import code.domain.Status;
import code.domain.Task;
import code.exception.EntityAlreadyExistException;
import code.service.QualificationService;
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
 * Created by Napha on 16.01.2017.
 */
@Controller
@SessionAttributes("TaskController")
public class TaskController {
    private static final String DATE_PATTERN = "yyyy-mm-dd";
    public static final Logger log = Logger.getLogger(TaskController.class);
    @Autowired(required = true)
    private TaskService taskService;
    @Autowired(required = true)
    private QualificationService qualificationService;

    public TaskController() {

    }

    @RequestMapping(value = "/createTaskPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String createTaskPage(Model model) {
        log.info("/createTaskPage code.controller.TaskController");
        model.addAttribute("listQualifications", qualificationService.getAllQualifications());
        return "createTask";
    }

    @RequestMapping(value = "/createTask", method = {RequestMethod.POST})
    public String createTask(@RequestParam("name") String name,
                             @RequestParam("qualifications") Long qualificationId,
                             @RequestParam("estimate") Integer estimate,
                             @RequestParam("finishDate") String finishDate,
                             @RequestParam("description") String description) throws EntityAlreadyExistException {
        log.info("/createTask code.controller.TaskController");
//        try {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(DATE_PATTERN);
        Date finishDateConverted = null;
        try {
            finishDateConverted = format.parse(finishDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Qualification qualification = qualificationService.getQualificationById(qualificationId);
        taskService.createTask(name, estimate, finishDateConverted, description, qualification);
//        } catch (EntityAlreadyExistException employeeAlreadyExistsExeption) {
//            employeeAlreadyExistsExeption.printStackTrace();
//        }


        //model.addAttribute("listQualifications", createDomainsService.getAllQualifications());
        return "createTask";
    }


    @RequestMapping(value = "/showTasksByStatusPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String showTasksByStatusPage(Model model) {
        log.info("/showTasksByStatusPage code.controller.TaskController");

        model.addAttribute("listTasks", taskService.getTasksByStatus(Status.valueOf("Assigned")));
        return "showTasks";
    }

    @RequestMapping(value = "/editTaskPage", method = {RequestMethod.GET})
    public String editTaskPage(@RequestParam("taskId") Long taskId, Model model) {
        log.info("/editTaskPage code.controller.TaskController");
        model.addAttribute("task", taskService.getTaskById(taskId));
        model.addAttribute("listQualifications", qualificationService.getAllQualifications());
        return "editTask";
    }

    @RequestMapping(value = "/editTask", method = {RequestMethod.POST})
    public String editTask(@RequestParam("taskId") Long taskId,
                           @RequestParam("name") String name,
                           @RequestParam("qualification") Long qualificationId,
                           @RequestParam("estimate") Integer estimate,
                           @RequestParam("finishDate") String finishDate,
                           @RequestParam("description") String description){
        log.info("/editEmployee code.controller.EmployeeController");

        Task task = taskService.getTaskById(taskId);
        task.setTaskName(name);
        task.setTaskQualification(qualificationService.getQualificationById(qualificationId));
        task.setEstimate(estimate);
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(DATE_PATTERN);
        Date finishDateConverted = null;
        try {
            finishDateConverted = format.parse(finishDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        task.setExpirationDate(finishDateConverted);
        task.setTaskDescription(description);
        taskService.updateTask(task);

        return "showTasks";
    }
}
