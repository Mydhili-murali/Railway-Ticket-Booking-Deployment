package mydhili.RailwayTicketBooking.Service;

import mydhili.RailwayTicketBooking.Entity.Seats;
import mydhili.RailwayTicketBooking.Repository.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatsService {
    @Autowired
    private SeatsRepository repo;

    public Seats getBySeats(String seat) {
        return repo.getBySeats(seat);
    }
}
