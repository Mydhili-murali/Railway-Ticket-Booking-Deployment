package mydhili.RailwayTicketBooking.Repository;

import mydhili.RailwayTicketBooking.Entity.Passengers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengersRepository extends JpaRepository<Passengers, String> {

    public void deletePassengerByUserName(String id);
}

