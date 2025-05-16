package backend.car_rental.entities;


import java.time.LocalDateTime;

import java.time.temporal.ChronoUnit;

import backend.car_rental.enums.BabySeat;
import backend.car_rental.enums.GasTank;
import backend.car_rental.enums.Insurance;
import backend.car_rental.enums.Location;
import backend.car_rental.enums.TravelLocation;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    private Location pickupLocation;
    private Location returnLocation;
    private Insurance insurance; //Can be deductable or total
    private BabySeat babySeat;
    private TravelLocation travelLocation;//Null if the client is not traveling
    private GasTank gasTank;

    private int daysRented;
    private double totalPrice;
    
    @PrePersist
    public void prePersist(){

        calculateDaysRented();        
        calculateTotalPrice();
    }

    private void calculateDaysRented(){ //Cambiar para que coincida con la regla de negocio
        daysRented = (int) ChronoUnit.DAYS.between(start, end);
    }

    private void calculateTotalPrice(){
        totalPrice = car.getPricePerDay() * daysRented 
        + gasTankCharge() + travelLocationCharge() 
        + insuranceChargue() + babySeatCharge();
    }

    //Si selecciono que devolvera el tanque vacio, se le cobra un monto
    //dependiendo del tipo del auto
    private int gasTankCharge(){
        if (gasTank == GasTank.EMPTY){
            return car.getType().getPrice(); 
        } else {
            return 0;
        }
    }

    private double travelLocationCharge(){
        if (travelLocation == null){
            return 0;
        } else {
            return travelLocation.getPrice();
        }   
    }

    private int insuranceChargue(){
        if (insurance == Insurance.DEDUCTABLE){
            return 15 * daysRented;
        } else {
            return 0;
        }
    }

    private int babySeatCharge(){
        if (babySeat == BabySeat.NONE){
            return 0;
        } else {
            return 3 * daysRented;
        }
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


    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }


    public double getTotalPrice() {
        return totalPrice;
    }


    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public Location getPickupLocation() {
        return pickupLocation;
    }


    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }


    public Location getReturnLocation() {
        return returnLocation;
    }


    public void setReturnLocation(Location returnLocation) {
        this.returnLocation = returnLocation;
    }


    public Insurance getInsurance() {
        return insurance;
    }


    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }


    public BabySeat getBabySeat() {
        return babySeat;
    }


    public void setBabySeat(BabySeat babySeat) {
        this.babySeat = babySeat;
    }


    public TravelLocation getTravelLocation() {
        return travelLocation;
    }


    public void setTravelLocation(TravelLocation travelLocation) {
        this.travelLocation = travelLocation;
    }


    public GasTank getGasTank() {
        return gasTank;
    }


    public void setGasTank(GasTank gasTank) {
        this.gasTank = gasTank;
    }    
    
}
