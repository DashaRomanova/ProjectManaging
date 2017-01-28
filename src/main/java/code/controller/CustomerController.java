package code.controller;

import code.domain.Customer;
import code.domain.Role;
import code.exception.EntityAlreadyExistException;
import code.service.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created by Napha on 16.01.2017.
 */
@Controller
@SessionAttributes("CustomerController")
public class CustomerController {
    public static final Logger log = Logger.getLogger(CustomerController.class);
    @Autowired(required = true)
    private CustomerService customerService;


    public CustomerController() {
    }

    @RequestMapping(value = "/createCustomerPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String createCustomerPage(Model model) {
        log.info("/createCustomerPage code.controller.CustomerController");
        return "createCustomer";
    }

    @RequestMapping(value = "/createCustomer", method = {RequestMethod.POST})
    public String createCustomer(@RequestParam("name") String name,
                                 @RequestParam("surname") String surname,
                                 @RequestParam("login") String login,
                                 @RequestParam("password") String password,
                                 Model model) throws EntityAlreadyExistException {
        log.info("/createCustomer code.controller.CustomerController");
        customerService.createCustomer(name, surname, login, password, Role.Customer);

        model.addAttribute("listCustomers", customerService.getAllCustomers());
        return "adminDashboardCustomer";
    }

    @RequestMapping(value = "/chooseCustomerForProjectPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String chooseCustomerForProjectPage(Model model) {
        log.info("/chooseCustomerForProjectPage code.controller.ProjectController");
        model.addAttribute("listCustomers", customerService.getAllCustomers());
        return "chooseCustomer";
    }

//    @RequestMapping(value = "/showCustomersPage", method = {RequestMethod.GET, RequestMethod.HEAD})
//    public String showCustomersPage(Model model) {
//        log.info("/showCustomersPage code.controller.CustomerController");
//
//        model.addAttribute("listCustomers", customerService.getAllCustomersByCompanyId(companyDao.read(new Long(1))));
//        return "showCustomers";
//    }

    @RequestMapping(value = "/showAllCustomersPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String showAllCustomersPage(Model model) {
        log.info("/showAllCustomersPage code.controller.CustomerController");

        model.addAttribute("listCustomers", customerService.getAllCustomers());
        return "adminDashboardCustomer";
    }

    @RequestMapping(value = "/deleteCustomerPage", method = {RequestMethod.GET})
    public String deleteCustomerPage(@RequestParam("customerId") Long customerId, Model model) {
        log.info("/deleteCustomerPage code.controller.CustomerController");
        customerService.deleteCustomer(customerId);
        model.addAttribute("listCustomers", customerService.getAllCustomers());
        return "adminDashboardCustomer";
    }

    @RequestMapping(value = "/editCustomerPage", method = {RequestMethod.GET})
    public String editCustomerPage(@RequestParam("customerId") Long customerId,
                                   Model model) {
        log.info("/editCustomerPage code.controller.CustomerController");
        model.addAttribute("customer", customerService.getCustomerById(customerId));
        return "editCustomer";
    }

    @RequestMapping(value = "/editCustomer", method = {RequestMethod.POST})
    public String editCustomer(@RequestParam("customerId") Long customerId,
                               @RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               @RequestParam("login") String login,
                               @RequestParam("password") String password,
                               Model model){
        log.info("/editCustomer code.controller.CustomerController");

        Customer customer = customerService.getCustomerById(customerId);
        customer.setName(name);
        customer.setSurname(surname);
        customer.setLogin(login);
        customer.setPassword(password);

        customerService.updateCustomer(customer);

        model.addAttribute("listCustomers", customerService.getAllCustomers());
        return "adminDashboardCustomer";
    }
}
