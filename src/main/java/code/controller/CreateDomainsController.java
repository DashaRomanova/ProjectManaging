package code.controller;

import code.exception.EntityAlreadyExistException;
import code.service.CreateDomainsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Date;

/**
 * Created by Napha on 12.01.2017.
 */
@Controller
@SessionAttributes("CreateDomainsController")
public class CreateDomainsController {
    public static final Logger log = Logger.getLogger(CreateDomainsController.class);
    @Autowired(required = true)
    private CreateDomainsService createDomainsService;

    public CreateDomainsController() {
    }


    @RequestMapping(value = "/createCustomerPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String createCustomerPage(Model model) {
        log.info("/createCustomerPage code.controller.CreateDomainsController");
        return "createCustomer";
    }

    @RequestMapping(value = "/createCustomer", method = {RequestMethod.POST})
    public String createCustomer(@RequestParam("name") String name,
                                 @RequestParam("surname") String surname,
                                 @RequestParam("login") String login,
                                 @RequestParam("password") String password) throws EntityAlreadyExistException {
        log.info("/createCustomer code.controller.CreateDomainsController");
//        try {
        createDomainsService.createCustomer(name, surname, login, password);
//        } catch (EntityAlreadyExistException employeeAlreadyExistsExeption) {
//            employeeAlreadyExistsExeption.printStackTrace();
//        }


        //model.addAttribute("listQualifications", createDomainsService.getAllQualifications());
        return "createCustomer";
    }

    @RequestMapping(value = "/createTaskPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String createTaskPage(Model model) {
        log.info("/createTaskPage code.controller.CreateDomainsController");
        model.addAttribute("listQualifications", createDomainsService.getAllQualifications());
        return "createTask";
    }

    @RequestMapping(value = "/createTask", method = {RequestMethod.POST})
    public String createTask(@RequestParam("name") String name,
                             @RequestParam("employeeId") Long employeeId,
                             @RequestParam("qualifications") Long qualificationId,
                             @RequestParam("estimate") Integer estimate,
                             @RequestParam("finishDate") Date finishDate,
                             @RequestParam("description") String description) throws EntityAlreadyExistException {
        log.info("/createTask code.controller.CreateDomainsController");
//        try {
        createDomainsService.createTask(name, estimate, finishDate, description, qualificationId, employeeId);
//        } catch (EntityAlreadyExistException employeeAlreadyExistsExeption) {
//            employeeAlreadyExistsExeption.printStackTrace();
//        }


        //model.addAttribute("listQualifications", createDomainsService.getAllQualifications());
        return "createTask";
    }

    @RequestMapping(value = "/createEmployeePage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String createEmployeePage(Model model) {
        log.info("/createEmployeePage code.controller.CreateDomainsController");
        model.addAttribute("listQualifications", createDomainsService.getAllQualifications());
        return "createEmployee";
    }

    @RequestMapping(value = "/createEmployee", method = {RequestMethod.POST})
    public String createEmployee(@RequestParam("name") String name,
                                 @RequestParam("surname") String surname,
                                 @RequestParam("login") String login,
                                 @RequestParam("password") String password,
                                 @RequestParam("qualifications") Long qualificationId) throws EntityAlreadyExistException {
        log.info("/createEmployee code.controller.CreateDomainsController");
//        try {
            createDomainsService.createEmployee(name, surname, login, password, qualificationId);
//        } catch (EntityAlreadyExistException employeeAlreadyExistsExeption) {
//            employeeAlreadyExistsExeption.printStackTrace();
//        }


        //model.addAttribute("listQualifications", createDomainsService.getAllQualifications());
        return "createEmployee";
    }
}
