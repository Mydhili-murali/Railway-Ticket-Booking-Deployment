package mydhili.RailwayTicketBooking.Repository;

import mydhili.RailwayTicketBooking.Entity.Seats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatsRepository extends JpaRepository<Seats,Long> {
   public Seats getBySeats(String seat);
}
