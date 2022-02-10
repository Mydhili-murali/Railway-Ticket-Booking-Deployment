package mydhili.RailwayTicketBooking.Service;

import mydhili.RailwayTicketBooking.Entity.Trains;
import mydhili.RailwayTicketBooking.Repository.TrainsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainsService {
    @Autowired
    private TrainsRepository repo;

    public void saveTrain(Trains trains) {
        repo.save(trains);
    }

    public Trains getByTrainNumber(String trainNumber) {
        return repo.getById(trainNumber);
    }

    public List<Trains> listAllTrains() {
        return repo.findAll();
    }
}
