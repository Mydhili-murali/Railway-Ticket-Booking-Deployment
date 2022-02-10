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
import java.sql.Date;
import java.sql.Time;

@Controller
public class TrainScheduleController {
    @Autowired
    private TrainsService trainsService;

    @Autowired
    private TrainScheduleService trainScheduleService;

    //for user to view train schedule
    @RequestMapping("/{userName}/trainSchedule/{trainNumber}")
    public String trainSchedule(@PathVariable String userName, @PathVariable String trainNumber, Model model) {
        model.addAttribute("schedules", trainScheduleService.trainScheduleDetails(trainNumber));
        model.addAttribute("userName", userName);
        Trains trains = trainsService.getByTrainNumber(trainNumber);
        model.addAttribute("trainName", trains.getTrainName());
        return "trainSchedule";
    }

    //for admin to view train schedule
    @RequestMapping("/{userName}/adminViewTrainSchedule/{trainNumber}")
    public String adminViewTrainSchedule(@PathVariable String userName, @PathVariable String trainNumber, Model model) {
        model.addAttribute("schedules", trainScheduleService.trainScheduleDetails(trainNumber));
        model.addAttribute("userName", userName);
        Trains trains = trainsService.getByTrainNumber(trainNumber);
        model.addAttribute("trainName", trains.getTrainName());
        return "adminViewTrainSchedule";
    }

    //admin updates train schedule
    @RequestMapping("/{userName}/updateTrainSchedule/{id}")
    public String updateTrainScheduleForm(@PathVariable String userName, @PathVariable Long id, Model model) {
        model.addAttribute("userName", userName);
        model.addAttribute("trainSchedule", trainScheduleService.getById(id));
        return "updateTrainSchedule";
    }
    @PostMapping("/{userName}/updateTrainSchedule/{id}")
    public String updateTrainSchedule(@PathVariable String userName, @PathVariable Long id, Model model, HttpServletRequest req) {
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
        return "redirect:/" + userName + "/adminViewTrainSchedule/" + trainSchedule.getTrains().getTrainNumber();

    }


}
