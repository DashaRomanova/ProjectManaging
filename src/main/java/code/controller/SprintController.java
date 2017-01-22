package code.controller;

import code.service.SprintService;
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
 * Created by Napha on 22.01.2017.
 */
@Controller
@SessionAttributes("SprintController")
public class SprintController {
    private static final String DATE_PATTERN = "yyyy-mm-dd";
    public static final Logger log = Logger.getLogger(EmployeeController.class);
    @Autowired(required = true)
    private SprintService sprintService;

    public SprintController() {
    }

    @RequestMapping(value = "/createSprintPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String createSprintPage(Model model) {
        log.info("/createSprintPage code.controller.SprintController");
        return "createSprint";
    }

//    @RequestMapping(value = "/createSprint", method = {RequestMethod.POST})
//    public String createSprint(@RequestParam("name") String name,
//                                @RequestParam("startDate") String startDate,
//                                @RequestParam("finishDate") String finishDate) {
//        log.info("/createSprint code.controller.SprintController");
//
//        SimpleDateFormat format = new SimpleDateFormat();
//        format.applyPattern(DATE_PATTERN);
//        Date startDateConverted = null;
//        Date finishDateConverted = null;
//        try {
//            startDateConverted =  format.parse(startDate);
//            finishDateConverted = format.parse(finishDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        projectService.createProject(name, startDateConverted, finishDateConverted);
//        return "showSprints";
//    }
}
