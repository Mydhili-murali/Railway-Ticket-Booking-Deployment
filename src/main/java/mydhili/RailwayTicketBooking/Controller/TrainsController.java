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
public class TrainsController {

    @Autowired
    private TrainsService service;

    @Autowired
    private TrainScheduleService trainScheduleService;

    //adding train details
    @RequestMapping("/{userName}/addTrain")
    public String addTrain(@PathVariable String userName, Model model) {
        model.addAttribute("userName", userName);
        return "addTrain";
    }

    @RequestMapping("/addTrain")
    public String addTrain() {
        return "addTrain";
    }

    @PostMapping("/{userName}/addTrainDetails")
    public String addTrain(@PathVariable String userName, HttpServletRequest req, Model model) {

        String trainNum = req.getParameter("trainNumber");
        String trainName = req.getParameter("trainName");
        String routingFrom = req.getParameter("routingFrom");
        String routingTo = req.getParameter("routingTo");
        Trains trains = new Trains(trainNum, trainName, routingFrom, routingTo);
        service.saveTrain(trains);
        model.addAttribute("message", "Successfully added !!!");
        model.addAttribute("userName", userName);
        return "addTrainSchedule";


    }

    @RequestMapping("/{userName}/addTrainSchedule")
    public String addTrainSchedule(@PathVariable String userName, Model model) {

        model.addAttribute("userName", userName);
        return "addTrainSchedule";
    }

    @PostMapping("/{userName}/addTrainSchedule")
    public String addTrainScheduleDetails(@PathVariable String userName, HttpServletRequest req, Model model) {
        String trainNumber = req.getParameter("trainNumber");
        Date date = Date.valueOf(req.getParameter("date"));
        Time departingTime = Time.valueOf(req.getParameter("departingTime") + ":00");
        Time arrivalTime = Time.valueOf(req.getParameter("arrivalTime") + ":00");
        TrainSchedule trainSchedule = new TrainSchedule(date, departingTime, arrivalTime);
        trainSchedule.setTrains(service.getByTrainNumber(trainNumber));
        trainScheduleService.saveTrainSchedule(trainSchedule);
        model.addAttribute("message", "Successfully added");
        model.addAttribute("userName", userName);
        return "addTrainSchedule";


    }

//    @RequestMapping("/trainsListPage")
//    public String trainList(Model model) {
//        model.addAttribute("trains", service.listAllTrains());
//        return "trainsListPage";
//    }

    //view for train details by passenger
    @RequestMapping("/{userName}/viewTrains")
    public String viewTrains(@PathVariable String userName, Model model) {
        model.addAttribute("userName", userName);
        model.addAttribute("trains", service.listAllTrains());
        return "viewTrains";
    }

    //view for train details by admin
    @RequestMapping("/{userName}/adminViewTrains")
    public String adminViewTrains(@PathVariable String userName, Model model) {
        model.addAttribute("userName", userName);
        model.addAttribute("trains", service.listAllTrains());
        return "adminViewTrains";
    }

}
