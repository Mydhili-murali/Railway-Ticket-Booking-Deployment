package mydhili.RailwayTicketBooking.Controller;
import mydhili.RailwayTicketBooking.Service.BookedSeatsService;
import mydhili.RailwayTicketBooking.Service.PassengersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.security.Principal;

@Transactional
@Controller
public class AdminController {

    @Autowired
    private PassengersService passengersService;

    @Autowired
    private BookedSeatsService bookedSeatsService;

    //viewing registered user accounts
    @RequestMapping("/viewUsersAccount")
    public String viewUsersAccount(Principal principal, Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }

        model.addAttribute("passengers",passengersService.listAllPassengers());
        return "viewAccounts";
    }

    //deleting user account
    @RequestMapping("/deleteAccount/{id}")
    public String deleteAccount(Principal principal,@PathVariable String id,Model model){
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }

        if(bookedSeatsService.existsByPassengersUserName(id)){
            bookedSeatsService.deleteByPassengerId(id);
        }
        passengersService.deletePassengerById(id);

        return "redirect:/viewUsersAccount";
    }
}
