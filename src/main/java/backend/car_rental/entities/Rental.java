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
    private List<TravelLocation> travelLocations; //Null si el cliente no viaja fuera de Miami
    private GasTank gasTank;

    private int daysRented;
    private double totalPrice;

    //Notas adicionales sobre el alquiler
    private String notes;
    //A partir de las 3 horas, se cobra el dia completo
    public void calculateDaysRented(){ 

        Long totalHours = Duration.between(start, end).toHours();

        daysRented = (int) (totalHours / 24);
        
        if (totalHours % 24 > 3){
            daysRented++;
        }
    }

    public void calculateTotalPrice(){
        totalPrice = car.getPricePerDay() * daysRented 
        + tankCharge() + travelLocationCharge() 
        + insuranceChargue() + additionalDriversCharge();
    }

    //Sobrecarga de metodo para calcular el precio total con un precio por dia dado (Cuando esta afectado por un ajuste de precios)
    public void calculateTotalPrice(double pricePerDay){

        totalPrice = pricePerDay * daysRented 
        + tankCharge() + travelLocationCharge() 
        + insuranceChargue() + additionalDriversCharge();

        totalPrice = Math.round(totalPrice * 100.0) / 100.0; //Redondeo del precio total a dos decimales
    }

    //Si selecciono que devolvera el tanque vacio, se le cobra un monto
    //dependiendo del tipo del auto
    private int tankCharge(){
        if (gasTank == GasTank.EMPTY){
            return car.getTankPrice(); 
        } else {
            return 0;
        }
    }

    private double travelLocationCharge(){
        if (travelLocations == null || travelLocations.isEmpty()){
            return 2.15 * daysRented;
        } else {
            double locationCharge = travelLocations.stream().mapToDouble(TravelLocation::getPrice).sum();
            return locationCharge;
        }   
    }

    private int insuranceChargue(){
        if (insurance == Insurance.TOTAL){
            return 15 * daysRented;
        } else {
            return 0;
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
