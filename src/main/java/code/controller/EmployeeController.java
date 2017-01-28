package code.controller;

import code.domain.*;
import code.exception.EntityAlreadyExistException;
import code.exception.TaskAlreadyCompletedException;
import code.service.EmployeeService;
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

/**
 * Created by Napha on 16.01.2017.
 */
@Controller
@SessionAttributes("EmployeeController")
public class EmployeeController {
    public static final Logger log = Logger.getLogger(EmployeeController.class);
    @Autowired(required = true)
    private EmployeeService employeeService;
    @Autowired(required = true)
    private TaskService taskService;

    public EmployeeController() {
    }

    @RequestMapping(value = "/createEmployeePage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String createEmployeePage(Model model) {
        log.info("/createEmployeePage code.controller.EmployeeController");
        model.addAttribute("listRoles", Arrays.asList(Role.Employee.name(), Role.ProjectManager.name()));
        model.addAttribute("listQualifications", Arrays.asList(Qualification.values()));
        return "createEmployee";
    }

    @RequestMapping(value = "/createEmployee", method = {RequestMethod.POST})
    public String createEmployee(@RequestParam("name") String name,
                                 @RequestParam("surname") String surname,
                                 @RequestParam("login") String login,
                                 @RequestParam("password") String password,
                                 @RequestParam("role") String role,
                                 @RequestParam("qualification") String qualification) throws EntityAlreadyExistException {
        log.info("/createEmployee code.controller.EmployeeController");
//        try {
        employeeService.createEmployee(name, surname, login, password, Qualification.valueOf(qualification),Role.valueOf(role));
//        } catch (EntityAlreadyExistException employeeAlreadyExistsExeption) {
//            employeeAlreadyExistsExeption.printStackTrace();
//        }


        //model.addAttribute("listQualifications", createDomainsService.getAllQualifications());
        return "createEmployee";
    }

//    @RequestMapping(value = "/showEmployeesPage", method = {RequestMethod.GET, RequestMethod.HEAD})
//    public String showEmployeesPage(Model model) {
//        log.info("/showEmployeesPage code.controller.EmployeeController");
//
//        model.addAttribute("listEmployees", employeeService.getAllEmployeesByCompanyId(companyDao.read(new Long(1))));
//        return "showEmployees";
//    }

    @RequestMapping(value = "/showAllEmployeesPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String showAllEmployeesPage(Model model) {
        log.info("/showAllEmployeesPage code.controller.EmployeeController");

        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "adminDashboardEmployees";
    }

    @RequestMapping(value = "/editEmployeePage", method = {RequestMethod.GET})
    public String editEmployeePage(@RequestParam("empId") Long empId, Model model) {
        log.info("/editEmployeePage code.controller.EmployeeController");
        model.addAttribute("employee", employeeService.getEmployeeById(empId));
        model.addAttribute("listRoles", Arrays.asList(Role.Employee.name(), Role.ProjectManager.name()));
        model.addAttribute("listQualifications", Arrays.asList(Qualification.values()));
        return "editEmployee";
    }


    @RequestMapping(value = "/deleteEmployeePage", method = {RequestMethod.GET})
    public String deleteEmployeePage(@RequestParam("empId") Long empId, Model model) {
        log.info("/deleteEmployeePage code.controller.EmployeeController");
        employeeService.deleteEmployee(empId);
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "adminDashboardEmployees";
    }

    @RequestMapping(value = "/editEmployee", method = {RequestMethod.POST})
    public String editEmployee(@RequestParam("employeeId") Long employeeId,
                               @RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               @RequestParam("login") String login,
                               @RequestParam("password") String password,
                               @RequestParam("role") String role,
                               @RequestParam("qualification") String qualification) {
        log.info("/editEmployee code.controller.EmployeeController");

        Employee employee = employeeService.getEmployeeById(employeeId);
        employee.setName(name);
        employee.setSurname(surname);
        employee.setLogin(login);
        employee.setPassword(password);
        employee.setRole(Role.valueOf(role));
        employee.setQualification(Qualification.valueOf(qualification));
        employeeService.updateEmployee(employee);
        return "createEmployee";
    }

    @RequestMapping(value = "/chooseEmployeeForTask", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String chooseProjectForTaskPage(@RequestParam("taskId") Long taskId,
                                           @RequestParam("taskQualification") String taskQualification,
                                           Model model) throws TaskAlreadyCompletedException{
        log.info("/createTaskPage code.controller.TaskController");
        Task task = taskService.getTaskById(taskId);
        if (Status.Completed.equals(task.getStatus())) {
            throw new TaskAlreadyCompletedException("Task <" + task.getTaskName() + "> already completed! Can not be edit.");
        }
        model.addAttribute("task", taskService.getTaskById(taskId));
        model.addAttribute("listEmployees", employeeService.getAllEmployeesByQualificationAndRole(Qualification.valueOf(taskQualification), Role.Employee));
        return "chooseEmployee";
    }
}
