package mydhili.RailwayTicketBooking.Controller;

import mydhili.RailwayTicketBooking.Entity.TrainSchedule;
import mydhili.RailwayTicketBooking.Entity.Trains;
import mydhili.RailwayTicketBooking.Service.TrainScheduleService;
import mydhili.RailwayTicketBooking.Service.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.Date;
import java.sql.Time;

@Controller
public class TrainsController {

    @Autowired
    private TrainsService service;

    @Autowired
    private TrainScheduleService trainScheduleService;

    //adding train details
    @RequestMapping("/addTrain")
    public String addTrain(Principal principal, Model model) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        return "addTrain";
    }


    @PostMapping("/addTrainDetails")
    public String addTrain(Principal principal, HttpServletRequest req, Model model) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }

        String trainNum = req.getParameter("trainNumber");
        String trainName = req.getParameter("trainName");
        String routingFrom = req.getParameter("routingFrom");
        String routingTo = req.getParameter("routingTo");
        Trains trains = new Trains(trainNum, trainName, routingFrom, routingTo);
        service.saveTrain(trains);
        model.addAttribute("message", "Successfully added !!!");
        return "addTrainSchedule";

    }

    @RequestMapping("/addTrainSchedule")
    public String addTrainSchedule(Principal principal, Model model) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }

        return "addTrainSchedule";
    }

    @PostMapping("/addTrainSchedule")
    public String addTrainScheduleDetails(Principal principal, HttpServletRequest req, Model model) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        String trainNumber = req.getParameter("trainNumber");
        Date date = Date.valueOf(req.getParameter("date"));
        Time departingTime = Time.valueOf(req.getParameter("departingTime") + ":00");
        Time arrivalTime = Time.valueOf(req.getParameter("arrivalTime") + ":00");
        if(service.existsById(trainNumber)){
            TrainSchedule trainSchedule = new TrainSchedule(date, departingTime, arrivalTime);
            trainSchedule.setTrains(service.getByTrainNumber(trainNumber));
            trainScheduleService.saveTrainSchedule(trainSchedule);
            model.addAttribute("message", "Successfully added");
        }
        else{
            model.addAttribute("message", "Train number is invalid...Please enter the Valid train number...");
        }

        return "addTrainSchedule";


    }


    //view for train details by passenger
    @RequestMapping("/viewTrains")
    public String viewTrains(Principal principal, Model model) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }

        model.addAttribute("trains", service.listAllTrains());
        return "viewTrains";
    }

    //view for train details by admin
    @RequestMapping("/adminViewTrains")
    public String adminViewTrains(Principal principal, Model model) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }

        model.addAttribute("trains", service.listAllTrains());
        return "adminViewTrains";
    }

}
