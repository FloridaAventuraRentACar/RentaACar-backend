package backend.car_rental.entities;

import backend.car_rental.enums.CarType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    private boolean isManual; // True si es manual, false si es automatico

    private int suitcasesAmount; // Cantidad de maletas que puede llevar el auto

    @NotNull
    private double pricePerDay; // Precio por dia

    private String imageUrl;

    private CarType type; // Dependiendo el tipo, se le cobrara distinto el tanque de nafta

    private boolean hidden;// True si se quiere ocultar al auto
}
