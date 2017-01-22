package code.controller;

import code.dao.CompanyDao;
import code.domain.Employee;
import code.domain.Role;
import code.exception.EntityAlreadyExistException;
import code.service.EmployeeService;
import code.service.QualificationService;
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
    private QualificationService qualificationService;

    @Autowired(required = true)
    private CompanyDao companyDao;

    public EmployeeController() {
    }

    @RequestMapping(value = "/createEmployeePage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String createEmployeePage(Model model) {
        log.info("/createEmployeePage code.controller.EmployeeController");
        model.addAttribute("listRoles", Arrays.asList(Role.Employee.name(), Role.ProjectManager.name()));
        model.addAttribute("listQualifications", qualificationService.getAllQualifications());
        return "createEmployee";
    }

    @RequestMapping(value = "/createEmployee", method = {RequestMethod.POST})
    public String createEmployee(@RequestParam("name") String name,
                                 @RequestParam("surname") String surname,
                                 @RequestParam("login") String login,
                                 @RequestParam("password") String password,
                                 @RequestParam("role") String role,
                                 @RequestParam("qualification") Long qualificationId) throws EntityAlreadyExistException {
        log.info("/createEmployee code.controller.EmployeeController");
//        try {
        employeeService.createEmployee(name, surname, login, password, qualificationId, role);
//        } catch (EntityAlreadyExistException employeeAlreadyExistsExeption) {
//            employeeAlreadyExistsExeption.printStackTrace();
//        }


        //model.addAttribute("listQualifications", createDomainsService.getAllQualifications());
        return "createEmployee";
    }

    @RequestMapping(value = "/showEmployeesPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String showEmployeesPage(Model model) {
        log.info("/showEmployeesPage code.controller.EmployeeController");

        model.addAttribute("listEmployees", employeeService.getAllEmployeesByCompanyId(companyDao.read(new Long(1))));
        return "showEmployees";
    }

    @RequestMapping(value = "/editEmployeePage", method = {RequestMethod.GET})
    public String editEmployeePage(@RequestParam("empId") Long empId, Model model) {
        log.info("/editEmployeePage code.controller.EmployeeController");
        model.addAttribute("employee", employeeService.getEmployeeById(empId));
        model.addAttribute("listRoles", Arrays.asList(Role.Employee.name(), Role.ProjectManager.name()));
        model.addAttribute("listQualifications", qualificationService.getAllQualifications());
        return "editEmployee";
    }

    @RequestMapping(value = "/editEmployee", method = {RequestMethod.POST})
    public String editEmployee(@RequestParam("employeeId") Long employeeId,
                               @RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               @RequestParam("login") String login,
                               @RequestParam("password") String password,
                               @RequestParam("role") String role,
                               @RequestParam("qualification") Long qualificationId) {
        log.info("/editEmployee code.controller.EmployeeController");

        Employee employee = employeeService.getEmployeeById(employeeId);
        employee.setName(name);
        employee.setSurname(surname);
        employee.setLogin(login);
        employee.setPassword(password);
        employee.setRole(role);
        employee.setQualification(qualificationService.getQualificationById(qualificationId));
        employeeService.updateEmployee(employee);
        return "createEmployee";
    }
}
