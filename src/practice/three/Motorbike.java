package practice.three;

public class Motorbike extends Vehicle {
    private double capacity;

    public Motorbike(String plateNumber, String manufacturer, int manufactureYear, String color, Owner owner, double capacity) {
        super(plateNumber, manufacturer, manufactureYear, color, owner);
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return super.toString() + " | Capacity: %.1f cc".formatted(capacity);
    }
}