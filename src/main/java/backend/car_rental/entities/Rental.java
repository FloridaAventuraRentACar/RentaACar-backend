package backend.car_rental.entities;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import backend.car_rental.enums.BabySeat;
import backend.car_rental.enums.GasTank;
import backend.car_rental.enums.Insurance;
import backend.car_rental.enums.Location;
import backend.car_rental.enums.TravelLocation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Client> clients;

    //El formato de LocalDateTime es: "2024-08-30T14:30:00"
    private LocalDateTime start;
    private LocalDateTime end;

    private Location pickupLocation;
    private Location returnLocation;
    
    private Insurance insurance; //Puede ser deductable o total
    private BabySeat babySeat;
    private TravelLocation travelLocation; //Null si el cliente no viaja fuera de Miami
    private GasTank gasTank;

    private int daysRented;
    private double totalPrice;
    
    @PrePersist
    public void prePersist(){

        calculateDaysRented();        
        calculateTotalPrice();
    }

    @PreUpdate
    public void preUpdate(){

        calculateDaysRented();        
        calculateTotalPrice();
        //No calculo el total ya que se puede modificar desde el frontend y no coincidir con la regla de negocio.
    }

    //A partir de las 3 horas, se cobra el dia completo
    private void calculateDaysRented(){ 

        Long totalHours = Duration.between(start, end).toHours();

        daysRented = (int) (totalHours / 24);
        
        if (totalHours % 24 > 3){
            daysRented++;
        }
    }

    private void calculateTotalPrice(){
        totalPrice = car.getPricePerDay() * daysRented 
        + gasTankCharge() + travelLocationCharge() 
        + insuranceChargue() + babySeatCharge() + additionalDriversCharge();
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
        if (insurance == Insurance.TOTAL){
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
    
    //A partir del segundo conductor adicional se le cobra 5 dolares por dia
    private int additionalDriversCharge(){
        if (clients.size() > 2){
            return (daysRented * 5) * (clients.size() - 2);
        } else {
            return 0;
            
        }
    }
}
