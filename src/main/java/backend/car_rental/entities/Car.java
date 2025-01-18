package backend.car_rental.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;


@Entity
@Table(name = "cars")
@Builder
@Data
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plate; //Patente
    
    private String brand; //Marca

    private String model; //Modelo

    private int year; //Año de fabricacion

    private String color;

    private int passengersAmount; //Cantidad de pasajeros que entran en el auto

    private boolean manual; //True si es manual, false si es automatico

    private int suitcasesAmount; //Cantidad de maletas que puede llevar el auto

    private double pricePerDay; //Precio por dia

    private boolean hidden;//True si se quiere ocultar al auto

    public Car() {
    }

   

    public Car(Long id, String plate, String brand, String model, int year ,
            String color, int passengersAmount, boolean manual,
            int suitcasesAmount, double pricePerDay, boolean hidden) {
        this.id = id;
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.passengersAmount = passengersAmount;
        this.manual = manual;
        this.suitcasesAmount = suitcasesAmount;
        this.pricePerDay = pricePerDay;
        this.hidden = hidden;
        this.year = year;
    }

}
