package mydhili.RailwayTicketBooking.Repository;

import mydhili.RailwayTicketBooking.Entity.BookedSeats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookedSeatsRepository extends JpaRepository<BookedSeats, Long> {


    public BookedSeats getBySeatsAndTrainScheduleId(String seat, Long id);

    public List<BookedSeats> getByPassengerUserName(String userName);

    public boolean existsByPassengerUserName(String id);

    public void deleteByPassengerUserName(String id);


    public List<BookedSeats> getByTrainScheduleId(Long id);
}
