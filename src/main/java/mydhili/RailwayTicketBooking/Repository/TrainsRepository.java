package mydhili.RailwayTicketBooking.Repository;

import mydhili.RailwayTicketBooking.Entity.Trains;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainsRepository extends JpaRepository<Trains,String> {
}
