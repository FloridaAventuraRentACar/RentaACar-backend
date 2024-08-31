package backend.car_rental.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String plate; //Patente
    
    private String brand; //Marca

    private String model; //Modelo

    private String color;

    private int passengersAmount; //Cantidad de pasajeros que entran en el auto

    private boolean automatic; //True si es automatico, false si es manual

    private int suitcasesAmount; //Cantidad de maletas que puede llevar el auto

    private double pricePerDay; //Precio por dia

    private boolean deleted;//True si el auto fue eliminado

    public Car() {
    }

    public Car(String brand, String model, String color, int passengersAmount, Boolean automatic, int suitcasesAmount,
            double pricePerDay) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.passengersAmount = passengersAmount;
        this.automatic = automatic;
        this.suitcasesAmount = suitcasesAmount;
        this.pricePerDay = pricePerDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPassengersAmount() {
        return passengersAmount;
    }

    public void setPassengersAmount(int passengersAmount) {
        this.passengersAmount = passengersAmount;
    }

    public boolean getAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }

    public int getSuitcasesAmount() {
        return suitcasesAmount;
    }

    public void setSuitcasesAmount(int suitcasesAmount) {
        this.suitcasesAmount = suitcasesAmount;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((plate == null) ? 0 : plate.hashCode());
        result = prime * result + ((brand == null) ? 0 : brand.hashCode());
        result = prime * result + ((model == null) ? 0 : model.hashCode());
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + passengersAmount;
        result = prime * result + (automatic ? 1231 : 1237);
        result = prime * result + suitcasesAmount;
        long temp;
        temp = Double.doubleToLongBits(pricePerDay);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Car other = (Car) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (plate == null) {
            if (other.plate != null)
                return false;
        } else if (!plate.equals(other.plate))
            return false;
        if (brand == null) {
            if (other.brand != null)
                return false;
        } else if (!brand.equals(other.brand))
            return false;
        if (model == null) {
            if (other.model != null)
                return false;
        } else if (!model.equals(other.model))
            return false;
        if (color == null) {
            if (other.color != null)
                return false;
        } else if (!color.equals(other.color))
            return false;
        if (passengersAmount != other.passengersAmount)
            return false;
        if (automatic != other.automatic)
            return false;
        if (suitcasesAmount != other.suitcasesAmount)
            return false;
        if (Double.doubleToLongBits(pricePerDay) != Double.doubleToLongBits(other.pricePerDay))
            return false;
        return true;
    }

   
    
    




}
