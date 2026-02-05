package practice.three;

public class Car extends Vehicle {
    private int seats;
    private String engineType;

    public Car(String plateNumber, String manufacturer, int manufactureYear, String color, Owner owner, int seats, String engineType) {
        super(plateNumber, manufacturer, manufactureYear, color, owner);
        this.seats = seats;
        this.engineType = engineType;
    }

    @Override
    public String toString() {
        return super.toString() + " | Seats: %d | Engine type: %s".formatted(seats, engineType);
    }
}
