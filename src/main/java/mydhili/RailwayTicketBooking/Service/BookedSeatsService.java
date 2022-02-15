package mydhili.RailwayTicketBooking.Service;

import mydhili.RailwayTicketBooking.Entity.BookedSeats;
import mydhili.RailwayTicketBooking.Entity.Passengers;
import mydhili.RailwayTicketBooking.Repository.BookedSeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookedSeatsService {
    @Autowired
    private BookedSeatsRepository repo;

    public BookedSeats getBySeatsAndTrainScheduleId(String seat, Long id) {
        return repo.getBySeatsAndTrainScheduleId(seat, id);
    }

    public void saveSeats(BookedSeats bookedSeats1) {
        repo.save(bookedSeats1);
    }

    public List<BookedSeats> getByPassengerUserName(String userName) {
        return repo.getByPassengerUserName(userName);
    }

    public boolean existsByPassengersUserName(String id) {
        return repo.existsByPassengerUserName(id);
    }

    public void deleteByPassengerId(String id) {
        repo.deleteByPassengerUserName(id);
    }

    public boolean existById(Long id) {
        return repo.existsById(id);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public List<BookedSeats> getByTrainScheduleId(Long id) {
        return repo.getByTrainScheduleId(id);
    }
}
