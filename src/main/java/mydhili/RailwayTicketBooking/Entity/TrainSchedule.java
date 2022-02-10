package mydhili.RailwayTicketBooking.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
public class TrainSchedule {
    @Id
    @GeneratedValue
    private Long id;
//    @Column(nullable = false)
//    private String station;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private Time departingTime;
    @Column(nullable = false)
    private Time arrivalTime;
    @JsonIgnore
    @ManyToOne
    private Trains trains;

    @OneToMany(mappedBy = "trainSchedule")
    private List<BookedSeats> bookedSeats;

    public TrainSchedule() {
    }

    public TrainSchedule( Date date, Time departingTime, Time arrivalTime) {
        this.date = date;
        this.departingTime = departingTime;
        this.arrivalTime = arrivalTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getDepartingTime() {
        return departingTime;
    }

    public void setDepartingTime(Time departingTime) {
        this.departingTime = departingTime;
    }

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Trains getTrains() {
        return trains;
    }

    public void setTrains(Trains trains) {
        this.trains = trains;
    }

    public List<BookedSeats> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<BookedSeats> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }
}
