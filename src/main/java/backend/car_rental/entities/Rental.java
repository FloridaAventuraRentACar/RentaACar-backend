package backend.car_rental.entities;


import java.time.LocalDateTime;

import java.time.temporal.ChronoUnit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Client client;

    //El formato de LocalDateTime es: "2024-08-30T14:30:00"
    private LocalDateTime start;
    private LocalDateTime end;

    private long daysRented;
    private double totalPrice;
    private boolean deleted; //True si el alquiler fue eliminado

    public Rental() {
    }

    

    public Rental(Long id, Car car, Client client, LocalDateTime start, LocalDateTime end, long daysRented,
            double totalPrice, boolean deleted) {
        this.id = id;
        this.car = car;
        this.client = client;
        this.start = start;
        this.end = end;
        this.daysRented = daysRented;
        this.totalPrice = totalPrice;
        this.deleted = deleted;
    }



    @PrePersist
    public void prePersist(){
        
        daysRented = ChronoUnit.DAYS.between(start, end);
        totalPrice = car.getPricePerDay() * daysRented;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Car getCar() {
        return car;
    }


    public void setCar(Car car) {
        this.car = car;
    }


    public Client getClient() {
        return client;
    }


    public void setClient(Client client) {
        this.client = client;
    }





    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public long getDaysRented() {
        return daysRented;
    }


    public void setDaysRented(long daysRented) {
        this.daysRented = daysRented;
    }


    public double getTotalPrice() {
        return totalPrice;
    }


    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public boolean isDeleted() {
        return deleted;
    }


    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    
    
}
