package backend.car_rental.enums;

//This are the possible travel locations if the client is traveling away from Miami
public enum TravelLocation {
    KeyWest(20.70),
    Orlando(32.00),
    WestPalmBeach(32.50),
    Daytona(30.00),
    ClearWater(24.70),
    IslaMorada(15.00),
    Naples(20.00);

    private final double price;

    TravelLocation(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
