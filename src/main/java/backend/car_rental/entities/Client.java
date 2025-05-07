package backend.car_rental.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

@Builder
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    private Long phone;
    private String email;
    private LocalDate bornDate; //Formato: "yyyy-MM-dd"

    private Long licenseNumber;
    private String licenseName;
    private String licenseAddress;
    private LocalDate licenseExpirationDate;
    private Boolean mainDriver; //True if the client is the main driver

    
    public Client() {

    }
    
    public Client(Long id, String name, String surname, Long phone, String email, LocalDate bornDate,
            Long licenseNumber, String licenseName, String licenseAddress, LocalDate licenseExpirationDate,
            Boolean mainDriver) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.bornDate = bornDate;
        this.licenseNumber = licenseNumber;
        this.licenseName = licenseName;
        this.licenseAddress = licenseAddress;
        this.licenseExpirationDate = licenseExpirationDate;
        this.mainDriver = mainDriver;
    }

    public Client(String name, String surname, Long phone, String email, LocalDate bornDate, Long licenseNumber,
            String licenseName, String licenseAdress, LocalDate licenseExpirationDate, Boolean mainDriver) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.bornDate = bornDate;
        this.licenseNumber = licenseNumber;
        this.licenseName = licenseName;
        this.licenseAddress = licenseAdress;
        this.licenseExpirationDate = licenseExpirationDate;
        this.mainDriver = mainDriver;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
   
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getPhone() {
        return phone;
    }
    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(Long licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseName() {
        return licenseName;
    }

    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }

    public String getLicenseAddress() {
        return licenseAddress;
    }

    public void setLicenseAddress(String licenseAdress) {
        this.licenseAddress = licenseAdress;
    }

    public LocalDate getLicenseExpirationDate() {
        return licenseExpirationDate;
    }

    public void setLicenseExpirationDate(LocalDate licenseExpirationDate) {
        this.licenseExpirationDate = licenseExpirationDate;
    }

    public Boolean getMainDriver() {
        return mainDriver;
    }

    public void setMainDriver(Boolean mainDriver) {
        this.mainDriver = mainDriver;
    }




   

    
}
