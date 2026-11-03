package practice.three;

import java.time.Clock;
import java.time.Year;
import java.util.List;

public abstract sealed class Vehicle permits Car, Truck, Motorbike {
    private static final List<String> VALID_MANUFACTURERS = List.of("Honda", "Yamaha", "Toyota", "Suzuki");

    private final String plateNumber;
    private final String manufacturer;
    private final int manufactureYear;
    private final String color;
    private final Owner owner;

    protected Vehicle(String plateNumber, String manufacturer, int manufactureYear, String color, Owner owner, Clock clock) {
        this.plateNumber = requireValidPlateNumber(plateNumber);
        this.manufacturer = toKnownManufacturer(manufacturer);
        this.manufactureYear = requireValidYear(manufactureYear, clock);
        this.color = requireNotBlank(color, "Color");
        if (owner == null) {
            throw new IllegalArgumentException("Owner is required");
        }
        this.owner = owner;
    }

    public String getPlateNumber() { return plateNumber; }
    public String getManufacturer() { return manufacturer; }
    public int getManufactureYear() { return manufactureYear; }
    public Owner getOwner() { return owner; }

    @Override
    public String toString() {
        return "Plate number: %s | Manufacturer: %s | Manufacture year: %d | Color: %s | Owner: %s".formatted(
                plateNumber, manufacturer, manufactureYear, color, owner.fullName());
    }

    protected static String requireNotBlank(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value;
    }

    protected static int requirePositive(int value, String field) {
        if (value <= 0) {
            throw new IllegalArgumentException(field + " must be > 0");
        }
        return value;
    }

    protected static double requirePositive(double value, String field) {
        if (!Double.isFinite(value) || value <= 0) {
            throw new IllegalArgumentException(field + " must be > 0");
        }
        return value;
    }

    private static String requireValidPlateNumber(String plateNumber) {
        if (plateNumber == null || plateNumber.length() != 5) {
            throw new IllegalArgumentException("Plate number must be 5 characters");
        }
        return plateNumber;
    }

    private static String toKnownManufacturer(String manufacturer) {
        return VALID_MANUFACTURERS.stream()
                .filter(m -> m.equalsIgnoreCase(manufacturer))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        manufacturer + " must be one of the following values: " + String.join(", ", VALID_MANUFACTURERS)));
    }

    private static int requireValidYear(int manufactureYear, Clock clock) {
        int currentYear = Year.now(clock).getValue();
        if (manufactureYear <= 2000 || manufactureYear > currentYear) {
            throw new IllegalArgumentException("Manufacture year must be > 2000 and <= " + currentYear);
        }
        return manufactureYear;
    }
}
