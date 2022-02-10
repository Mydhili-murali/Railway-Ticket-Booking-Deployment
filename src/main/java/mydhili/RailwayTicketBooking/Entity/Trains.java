package mydhili.RailwayTicketBooking.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Trains {
    @Id
    private String trainNumber;
    @Column(nullable = false)
    private String trainName;
    @Column(nullable=false)
    private String routingFrom;
    @Column(nullable = false)
    private String routingTo;
    @OneToMany(mappedBy = "trains")
    private List<TrainSchedule> trainSchedules;

    public Trains() {
    }

    public Trains(String trainNumber, String trainName, String routingFrom, String routingTo) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.routingFrom = routingFrom;
        this.routingTo = routingTo;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getRoutingFrom() {
        return routingFrom;
    }

    public void setRoutingFrom(String routingFrom) {
        this.routingFrom = routingFrom;
    }

    public String getRoutingTo() {
        return routingTo;
    }

    public void setRoutingTo(String routingTo) {
        this.routingTo = routingTo;
    }

    public List<TrainSchedule> getTrainSchedules() {
        return trainSchedules;
    }

    public void setTrainSchedules(List<TrainSchedule> trainSchedules) {
        this.trainSchedules = trainSchedules;
    }
}