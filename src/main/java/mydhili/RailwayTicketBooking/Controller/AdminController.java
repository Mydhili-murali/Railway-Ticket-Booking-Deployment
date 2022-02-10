package mydhili.RailwayTicketBooking.Controller;
import mydhili.RailwayTicketBooking.Service.AdminService;
import mydhili.RailwayTicketBooking.Service.BookedSeatsService;
import mydhili.RailwayTicketBooking.Service.PassengersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;

@Transactional
@Controller
public class AdminController {
    @Autowired
    private AdminService service;

    @Autowired
    private PassengersService passengersService;

    @Autowired
    private BookedSeatsService bookedSeatsService;

    //viewing registered user accounts
    @RequestMapping("/{userName}/viewUsersAccount")
    public String viewUsersAccount(@PathVariable String userName, Model model){
        model.addAttribute("userName",userName);
        model.addAttribute("passengers",passengersService.listAllPassengers());
        return "viewAccounts";
    }

    //deleting user account
    @RequestMapping("/{userName}/deleteAccount/{id}")
    public String deleteAccount(@PathVariable String userName,@PathVariable String id,Model model){
        model.addAttribute("userName",userName);
        if(bookedSeatsService.existsByPassengersUserName(id)){
            bookedSeatsService.deleteByPassengerId(id);
        }
        passengersService.deletePassengerById(id);

        return "redirect:/"+userName+"/viewUsersAccount";
    }
}
