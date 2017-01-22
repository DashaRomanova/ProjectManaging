package code.controller;

import code.domain.Role;
import code.service.QualificationService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Napha on 20.01.2017.
 */
@Controller
@SessionAttributes("EmployeeController")
public class Test {
    public static final Logger log = Logger.getLogger(EmployeeController.class);
    @Autowired(required = true)
    private QualificationService qualificationService;

    public Test() {
    }

    @RequestMapping(value = "/go", method = RequestMethod.GET)
    public @ResponseBody
    List<String> getPages() {
        log.info("/getPages code.controller.Test");

//        ObjectMapper mapper = new ObjectMapper();
//        ModelAndView model = new ModelAndView("new");
//
//        String jsonRolesList = "";
//        String jsonQualificationsList = "";
//        try {
//            jsonRolesList = mapper.writeValueAsString(Arrays.asList(Role.Employee.name(), Role.ProjectManager.name()));
//            jsonQualificationsList = mapper.writeValueAsString(qualificationService.getAllQualifications());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        model.addObject("listRoles", jsonRolesList);
//        //model.addObject("listQualifications", jsonQualificationsList);


        return Arrays.asList(Role.Employee.name(), Role.ProjectManager.name());
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String getPage() {
        log.info("/ code.controller.Test");
        return "new";
    }
}
