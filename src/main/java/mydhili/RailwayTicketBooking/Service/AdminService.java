package mydhili.RailwayTicketBooking.Service;

import mydhili.RailwayTicketBooking.Entity.Admin;
import mydhili.RailwayTicketBooking.Entity.Passengers;
import mydhili.RailwayTicketBooking.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository repo;

    public boolean existsById(String userName) {
        return repo.existsById(userName);
    }

    public Admin findById(String userName) {
        return repo.findById(userName).get();
    }


}
