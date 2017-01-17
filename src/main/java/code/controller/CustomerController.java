package code.controller;

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
                                 @RequestParam("password") String password) throws EntityAlreadyExistException {
        log.info("/createCustomer code.controller.CustomerController");
//        try {
        customerService.createCustomer(name, surname, login, password, Role.Customer.name());
//        } catch (EntityAlreadyExistException employeeAlreadyExistsExeption) {
//            employeeAlreadyExistsExeption.printStackTrace();
//        }


        //model.addAttribute("listQualifications", createDomainsService.getAllQualifications());
        return "createCustomer";
    }
}
