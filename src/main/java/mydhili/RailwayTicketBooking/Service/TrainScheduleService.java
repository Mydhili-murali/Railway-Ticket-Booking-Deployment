package mydhili.RailwayTicketBooking.Service;

import mydhili.RailwayTicketBooking.Entity.TrainSchedule;
import mydhili.RailwayTicketBooking.Repository.TrainScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainScheduleService {
    @Autowired
    private TrainScheduleRepository repo;

    public void saveTrainSchedule(TrainSchedule trainSchedule) {
        repo.save(trainSchedule);
    }

    public List<TrainSchedule> trainScheduleDetails(String trainNumber) {
        return repo.findByTrainsTrainNumber(trainNumber);
    }

    public TrainSchedule getById(Long id) {
        return repo.getById(id);
    }
}
