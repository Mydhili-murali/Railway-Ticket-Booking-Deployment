package mydhili.RailwayTicketBooking.Service;

import mydhili.RailwayTicketBooking.Entity.Passengers;
import mydhili.RailwayTicketBooking.Repository.PassengersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class PassengersService {
    @Autowired
    private PassengersRepository repo;

    public void savePassenger(Passengers passenger) {
        repo.save(passenger);
    }

    public boolean existsById(String userName) {
        return repo.existsById(userName);
    }

    public Passengers findById(String userName) {
        return repo.findById(userName).get();
    }

    public List<Passengers> listAllPassengers() {
        return repo.findAll();
    }

    public void deletePassengerById(String id) {
        repo.deletePassengerByUserName(id);
    }
}
