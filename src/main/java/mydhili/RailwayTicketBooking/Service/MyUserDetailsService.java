package mydhili.RailwayTicketBooking.Service;

import mydhili.RailwayTicketBooking.Entity.MyUserDetails;
import mydhili.RailwayTicketBooking.Entity.Passengers;
import mydhili.RailwayTicketBooking.Repository.PassengersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    public PassengersService passengersService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        Passengers user=passengersService.findById(username);
        System.out.println(user.getUserName());
        return new MyUserDetails(user);
    }
}
