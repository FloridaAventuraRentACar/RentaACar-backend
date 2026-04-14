package backend.car_rental.entities;

import backend.car_rental.enums.CarSize;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String brand; // Marca

    @NotNull
    private String model; // Modelo

    private int year; // Año de fabricacion

    private String color;

    private boolean showColorInName;

    private int passengersAmount; // Cantidad de pasajeros que entran en el auto

    private int suitcasesAmount; // Cantidad de maletas que puede llevar el auto

    @NotNull
    private double pricePerDay; // Precio por dia

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private CarSize size; 

    @Max(value = 200)
    @Min(value = 0)
    private int tankPrice; // Precio por llenar el tanque
    
    private boolean hidden;// True si se quiere ocultar al auto

    public String getName(){
        return brand + " " + model;
    }
}
