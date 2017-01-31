package code.controller;

import code.dao.EmployeeDao;
import code.dao.TaskDao;
import code.domain.Employee;
import code.domain.User;
import code.domain.ViewEmployee;
import code.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.SQLDataException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Napha on 28.01.2017.
 */
@Controller
public class LoginController {
    @Autowired(required = true)
    private EmployeeService employeeService;


    @RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
    public ModelAndView defaultPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("hello");
        return model;

    }
    @RequestMapping(value = { "/contact"}, method = RequestMethod.GET)
    public ModelAndView contactPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("contact");
        return model;

    }

    @RequestMapping(value = { "/admin/welcomePage" }, method = RequestMethod.GET)
    public ModelAndView welcomePageForAdmin(Principal principal) {
        ModelAndView model = new ModelAndView();
        if(principal != null){
            String username = principal.getName(); //get logged in username
            Employee admin = employeeService.findEmployeeByLogin(username);
            model.addObject("admin", admin);
            model.setViewName("adminDashboardWelcome");
        }
        return model;
    }

    @RequestMapping(value = { "/projectManager/welcomePage" }, method = RequestMethod.GET)
    public ModelAndView welcomePageForProjectManager(Principal principal) {
        ModelAndView model = new ModelAndView();
        if(principal != null){
            String username = principal.getName(); //get logged in username
            Employee projectManager = employeeService.findEmployeeByLogin(username);
            model.addObject("manager", projectManager);
            model.setViewName("projectManagerDashboardWelcome");
        }
        return model;
    }

    @RequestMapping(value = { "/employee/welcomePage" }, method = RequestMethod.GET)
    public ModelAndView welcomePageForEmployee(Principal principal) {
        ModelAndView model = new ModelAndView();
        if(principal != null){
            String username = principal.getName(); //get logged in username
            Employee employee = employeeService.findEmployeeByLogin(username);
            model.addObject("employee", employee);
            model.setViewName("employeeDashboardWelcome");
        }
        return model;
    }

//    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
//    public ModelAndView adminPage() {
//
//        ModelAndView model = new ModelAndView();
//        model.addObject("title", "Spring Security Login Form - Database Authentication");
//        model.addObject("message", "This page is for ROLE_ADMIN only!");
//        model.setViewName("admin");
//        return model;
//
//    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }

    //for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("403");
        return model;

    }
//    private static final String DATE_PATTERN = "dd.MM.yyyy";
//    @Autowired
//    private EmployeeDao employeeDao;
//    @Autowired
//    private TaskDao taskDao;
//
//    @RequestMapping(value="/main", method = RequestMethod.GET)
//    public String printWelcome(ModelMap model, Principal principal ) {
//
//        String name = principal.getName();
//        model.addAttribute("username", name);
//        return "main_page";
//
//    }
//
//    @RequestMapping(value="/login", method = RequestMethod.GET)
//    public String login(ModelMap model) {
//
//        return "login_page";
//
//    }
//
//    @RequestMapping(value="/", method = RequestMethod.GET)
//    public String login1(ModelMap model) {
//        List list = employeeDao.findEmployeesWhoHasOvertime(5, new Long(2));
//
//        SimpleDateFormat format = new SimpleDateFormat();
//        SimpleDateFormat format2 = new SimpleDateFormat();
//        format.applyPattern(DATE_PATTERN);
//        format2.applyPattern(DATE_PATTERN);
//        Date start = null;
//        Date end = null;
//        try {
//            start = format.parse("21.01.2017");
//            end = format2.parse("26.02.2017");
//        } catch (ParseException e) {
//
//        }
//
//        List l = taskDao.getTaskStatisticBetweenDatesByEmployee(new Long(1), start, end);
//            //ViewEmployee viewEmployee = employeeDao.findIfEmployeeHasOvertime(5, new Long(2), new Long(1));
//
//        return "main_page";
//
//    }
//
//    @RequestMapping(value="/loginError", method = RequestMethod.GET)
//    public String loginError(ModelMap model) {
//        model.addAttribute("error", "true");
//        return "login_page";
//
//    }

}