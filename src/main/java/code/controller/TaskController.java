package code.controller;

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
                             @RequestParam("employeeId") Long employeeId,
                             @RequestParam("qualifications") Long qualificationId,
                             @RequestParam("estimate") Integer estimate,
                             @RequestParam("finishDate") String finishDate,
                             @RequestParam("description") String description) throws EntityAlreadyExistException {
        log.info("/createTask code.controller.TaskController");
//        try {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-mm-dd");
        Date finishDateConverted = null;
        try {
            finishDateConverted = format.parse(finishDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        taskService.createTask(name, estimate, finishDateConverted, description, qualificationId, employeeId);
//        } catch (EntityAlreadyExistException employeeAlreadyExistsExeption) {
//            employeeAlreadyExistsExeption.printStackTrace();
//        }


        //model.addAttribute("listQualifications", createDomainsService.getAllQualifications());
        return "createTask";
    }
}
