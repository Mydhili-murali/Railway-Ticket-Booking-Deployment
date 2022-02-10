package mydhili.RailwayTicketBooking.Controller;
import mydhili.RailwayTicketBooking.Entity.Passengers;
import mydhili.RailwayTicketBooking.Service.AdminService;
import mydhili.RailwayTicketBooking.Service.PassengersService;
import mydhili.RailwayTicketBooking.Service.TrainsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class PassengersController {
    @Autowired
    private PassengersService service;

    @Autowired
    private AdminService adminService;

    @Autowired
    private TrainsService trainsService;

    @RequestMapping("/")
    public String index() {
        return "home";

    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(HttpServletRequest req, Model model) {
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String emailId = req.getParameter("emailId");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        Long phoneNumber = Long.parseLong(req.getParameter("phNo"));
        Passengers passenger = new Passengers(name, userName, password, address, emailId, phoneNumber,"ROLE_USER");
        service.savePassenger(passenger);
        model.addAttribute("message", "Successfully registered!!!!!!");
        return "login";


    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/success")
    public String login(Principal principal){
        String userName = principal.getName();
        if (userName.equals("admin")){
            return "redirect:/addTrain";
            //return "redirect:/"+userName+"/addTrain";
        }
        return "redirect:/viewTrains";
        //return "redirect:/"+userName+"/viewTrains";
    }


    //view for passenger profile details
    @RequestMapping("/viewProfile")
    public String viewProfile(Model model,Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        Passengers passenger = service.findById(principal.getName());

        model.addAttribute("name", passenger.getPassengerName());
        model.addAttribute("userName", passenger.getUserName());
        model.addAttribute("address", passenger.getAddress());
        model.addAttribute("email", passenger.getEmail());
        model.addAttribute("phoneNumber", passenger.getPhoneNumber());

        return "viewProfile";
    }

    //updating passenger profile details
    @RequestMapping("/updateProfile")
    public String updateProfile(Principal principal,Model model) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        model.addAttribute("passenger", service.findById(principal.getName()));
        return "updateProfile";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(Principal principal, HttpServletRequest req, Model model) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("userName", username);
        }
        Passengers passenger = service.findById(principal.getName());
        String name=req.getParameter("name");
        String address=req.getParameter("address");
        String email=req.getParameter("emailId");
        String password=req.getParameter("password");
        Long phoneNumber=Long.parseLong(req.getParameter("phoneNumber"));
        //passenger.getUserName();
        passenger.setPassengerName(name);
        passenger.setAddress(address);
        passenger.setEmail(email);
        passenger.setPassword(password);
        passenger.setPhoneNumber(phoneNumber);
        service.savePassenger(passenger);
        return "redirect:/viewProfile";
    }



}
