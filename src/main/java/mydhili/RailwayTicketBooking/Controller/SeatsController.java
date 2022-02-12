package mydhili.RailwayTicketBooking.Controller;
import mydhili.RailwayTicketBooking.Entity.BookedSeats;
import mydhili.RailwayTicketBooking.Entity.Passengers;
import mydhili.RailwayTicketBooking.Entity.Seats;
import mydhili.RailwayTicketBooking.Service.BookedSeatsService;
import mydhili.RailwayTicketBooking.Service.PassengersService;
import mydhili.RailwayTicketBooking.Service.SeatsService;
import mydhili.RailwayTicketBooking.Service.TrainScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Objects;

@Controller
public class SeatsController {

    @Autowired
    private BookedSeatsService bookedSeatsService;

    @Autowired
    private SeatsService seatsService;

    @Autowired
    private TrainScheduleService trainScheduleService;

    @Autowired
    private PassengersService passengersService;

    @RequestMapping("/seatMap/{id}")
    public String bookMyTrain(Principal principal, @PathVariable Long id, Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }

        model.addAttribute("train",id);
        return "seatMap";
    }

    //seat booking

    @RequestMapping("/bookedStatus/{id}")
    public String seatBooking(Principal principal, @PathVariable Long id,Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }

        model.addAttribute("train",id);
        return "bookedStatus";
    }

    @PostMapping("/bookedStatus/{id}")
    public String bookedSeats(Principal principal, @PathVariable Long id, HttpServletRequest request, Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        model.addAttribute("train",id);
        String bookSeats=request.getParameter("bookedSeats");
        String[] bookedSeat=bookSeats.split(",");
        for(String seat:bookedSeat){
            BookedSeats bookedSeats=bookedSeatsService.getBySeatsAndTrainScheduleId(seat,id);
            if(!(Objects.isNull(bookedSeats))){
                model.addAttribute("message1", " Seats are already booked...Please select another seats to continue booking !!!");
                return "seatMap";
            }
        }
        for(String seat:bookedSeat){
            BookedSeats bookedSeats=bookedSeatsService.getBySeatsAndTrainScheduleId(seat,id);
            Seats seats=seatsService.getBySeats(seat);
            BookedSeats bookedSeats1=new BookedSeats(seats.getSeats(),seats.getPrice());
            bookedSeats1.setTrainSchedule(trainScheduleService.getById(id));
            bookedSeats1.setPassenger(passengersService.findById(principal.getName()));
            bookedSeatsService.saveSeats(bookedSeats1);
            model.addAttribute("message","Seats booked successfully... You can see your bookings here...");

        }




        return "bookedStatus";
    }

    //details of previous bookings

    @RequestMapping("/myBookings")
    public String myBookings(Principal principal, Model model ){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        if((bookedSeatsService.getByPassengerUserName(principal.getName()).isEmpty())){
            model.addAttribute("message1","There is no more previous bookings...please book your train..");
            return "redirect:/viewTrains";
        }
        else{
            model.addAttribute("name",bookedSeatsService.getByPassengerUserName(principal.getName()));
        }
        return "myBookings";


    }
    @RequestMapping("/cancelBookings/{id}")
    public String cancelBooking(Principal principal, @PathVariable Long id,Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }

        model.addAttribute("id",id);
        if(bookedSeatsService.existById(id)){
            bookedSeatsService.deleteById(id);
        }
        return "redirect:/myBookings";
    }
}
