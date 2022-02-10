package mydhili.RailwayTicketBooking.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BookedSeats {

    @Id
    @GeneratedValue
    private Long id;
    private String seats;
    private double price;
    @JsonIgnore
    @ManyToOne
    private  TrainSchedule trainSchedule;

    @JsonIgnore
    @ManyToOne
    private Passengers passenger;

    public BookedSeats() {
    }

    public BookedSeats(String seats, double price) {
        this.seats = seats;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TrainSchedule getTrainSchedule() {
        return trainSchedule;
    }

    public void setTrainSchedule(TrainSchedule trainSchedule) {
        this.trainSchedule = trainSchedule;
    }

    public Passengers getPassenger() {
        return passenger;
    }

    public void setPassenger(Passengers passenger) {
        this.passenger = passenger;
    }
}
