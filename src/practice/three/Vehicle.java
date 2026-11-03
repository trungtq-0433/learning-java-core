package practice.three;

import java.time.Clock;
import java.time.Year;
import java.util.List;

public abstract class Vehicle {
    private static final List<String> VALID_MANUFACTURERS = List.of("Honda", "Yamaha", "Toyota", "Suzuki");

    private String plateNumber;
    private String manufacturer;
    private int manufactureYear;
    private String color;
    private Owner owner;

    public Vehicle(String plateNumber, String manufacturer, int manufactureYear, String color, Owner owner, Clock clock) {
        setPlateNumber(plateNumber);
        setManufacturer(manufacturer);
        setManufactureYear(manufactureYear, clock);
        this.color = color;
        setOwner(owner);
    }

    public String getPlateNumber() { return plateNumber; }
    public String getManufacturer() {return manufacturer; }
    public int getManufactureYear() {return manufactureYear; }
    public Owner getOwner() { return owner; }

    private void setPlateNumber(String plateNumber) {
        if (plateNumber == null || plateNumber.length() != 5) {
            throw new IllegalArgumentException("Plate number must be 5 character!");
        }
        this.plateNumber = plateNumber;
    }

    private void setManufacturer(String manufacturer) {
        this.manufacturer = VALID_MANUFACTURERS.stream()
                .filter(m -> m.equalsIgnoreCase(manufacturer))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        manufacturer + " must be one of the following values: " + String.join(", ", VALID_MANUFACTURERS)));
    }

    private void setManufactureYear(int manufactureYear, Clock clock) {
        int currentYear = Year.now(clock).getValue();
        if (manufactureYear <= 2000 || manufactureYear > currentYear) {
            throw new IllegalArgumentException(manufactureYear + " must be > 2000 and <= " + currentYear);
        }
        this.manufactureYear = manufactureYear;
    }

    private void setOwner(Owner owner) {
        if (owner == null) {
            throw new IllegalArgumentException("Owner is required");
        }
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Plate number: %s | Manufacturer: %s | Manufacture year: %d | Color: %s | Owner: %s".formatted(
            plateNumber, manufacturer, manufactureYear, color, owner.fullName());
    }
}
