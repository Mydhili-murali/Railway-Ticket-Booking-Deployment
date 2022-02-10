package mydhili.RailwayTicketBooking.Repository;

import mydhili.RailwayTicketBooking.Entity.TrainSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainScheduleRepository extends JpaRepository<TrainSchedule,Long> {
    public List<TrainSchedule> findByTrainsTrainNumber(String trainNumber);
}
