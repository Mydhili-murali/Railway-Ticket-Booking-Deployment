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

    @RequestMapping("/{userName}/seatMap/{id}")
    public String bookMyTrain(@PathVariable String userName, @PathVariable Long id, Model model){
        model.addAttribute("userName",userName);
        model.addAttribute("train",id);
        return "seatMap";
    }

    //seat booking

    @RequestMapping("/{username}/bookedStatus/{id}")
    public String seatBooking(@PathVariable String userName,@PathVariable Long id,Model model){
        model.addAttribute("userName",userName);
        model.addAttribute("train",id);
        return "bookedStatus";
    }

    @PostMapping("/{userName}/bookedStatus/{id}")
    public String bookedSeats(@PathVariable String userName, @PathVariable Long id, HttpServletRequest request, Model model){
        model.addAttribute("train",id);
        String bookSeats=request.getParameter("bookedSeats");
        String[] bookedSeat=bookSeats.split(",");
        for(String seat:bookedSeat){
            BookedSeats bookedSeats=bookedSeatsService.getBySeatsAndTrainScheduleId(seat,id);
            if(!(Objects.isNull(bookedSeats))){
                model.addAttribute("message1", " Seats are already booked...Please select another seats to continue booking !!!");
                return "redirect:/"+userName+"/seatMap/"+id;
            }
        }
        for(String seat:bookedSeat){
            BookedSeats bookedSeats=bookedSeatsService.getBySeatsAndTrainScheduleId(seat,id);
            Seats seats=seatsService.getBySeats(seat);
            BookedSeats bookedSeats1=new BookedSeats(seats.getSeats(),seats.getPrice());
            bookedSeats1.setTrainSchedule(trainScheduleService.getById(id));
            bookedSeats1.setPassenger(passengersService.findById(userName));
            bookedSeatsService.saveSeats(bookedSeats1);
            model.addAttribute("message","Seats booked successfully!!!");

        }




        return "bookedStatus";
    }

    //details of previous bookings

    @RequestMapping("/{userName}/myBookings")
    public String myBookings(@PathVariable String userName, Model model ){
        model.addAttribute("userName",userName);
        model.addAttribute("name",bookedSeatsService.getByPassengerUserName(userName));
        return "myBookings";
    }
    @RequestMapping("/{userName}/cancelBookings/{id}")
    public String cancelBooking(@PathVariable String userName,@PathVariable Long id,Model model){
        model.addAttribute("userName",userName);
        model.addAttribute("id",id);
        if(bookedSeatsService.existById(id)){
            bookedSeatsService.deleteById(id);
        }
        return "redirect:/"+userName+"/myBookings";
    }
}
