package mydhili.RailwayTicketBooking.Controller;

import mydhili.RailwayTicketBooking.Entity.TrainSchedule;
import mydhili.RailwayTicketBooking.Entity.Trains;
import mydhili.RailwayTicketBooking.Service.TrainScheduleService;
import mydhili.RailwayTicketBooking.Service.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.Date;
import java.sql.Time;

@Controller
public class TrainScheduleController {
    @Autowired
    private TrainsService trainsService;

    @Autowired
    private TrainScheduleService trainScheduleService;

    //for user to view train schedule
    @RequestMapping("/trainSchedule/{trainNumber}")
    public String trainSchedule(Principal principal, @PathVariable String trainNumber, Model model) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        model.addAttribute("schedules", trainScheduleService.trainScheduleDetails(trainNumber));
        Trains trains = trainsService.getByTrainNumber(trainNumber);
        model.addAttribute("trainName", trains.getTrainName());
        return "trainSchedule";
    }

    //for admin to view train schedule
    @RequestMapping("/adminViewTrainSchedule/{trainNumber}")
    public String adminViewTrainSchedule(Principal principal, @PathVariable String trainNumber, Model model) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        model.addAttribute("schedules", trainScheduleService.trainScheduleDetails(trainNumber));
        Trains trains = trainsService.getByTrainNumber(trainNumber);
        model.addAttribute("trainName", trains.getTrainName());
        return "adminViewTrainSchedule";
    }

    //admin updates train schedule
    @RequestMapping("/updateTrainSchedule/{id}")
    public String updateTrainScheduleForm(Principal principal, @PathVariable Long id, Model model) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        model.addAttribute("trainSchedule", trainScheduleService.getById(id));
        return "updateTrainSchedule";
    }

    @PostMapping("/updateTrainSchedule/{id}")
    public String updateTrainSchedule(Principal principal, @PathVariable Long id, Model model, HttpServletRequest req) {
        TrainSchedule trainSchedule = trainScheduleService.getById(id);
        // trainSchedule.getTrains();
        Date date = Date.valueOf(req.getParameter("date"));
        System.out.println(req.getParameter("departingTime"));
        System.out.println(req.getParameter("arrivalTime"));
        String dTime = req.getParameter("departingTime");
        String aTime = req.getParameter("arrivalTime");
        if (dTime.length() == 5) dTime += ":00";
        if (aTime.length() == 5) aTime += ":00";
        Time departingTime = Time.valueOf(dTime);
        Time arrivalTime = Time.valueOf(aTime);
        trainSchedule.setDate(date);
        trainSchedule.setDepartingTime(departingTime);
        trainSchedule.setArrivalTime(arrivalTime);
        trainScheduleService.saveTrainSchedule(trainSchedule);
        return "redirect:/adminViewTrainSchedule/" + trainSchedule.getTrains().getTrainNumber();

    }


}
