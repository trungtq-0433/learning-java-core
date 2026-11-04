package practice.three;

import java.time.Clock;
import java.time.Year;

public abstract sealed class Vehicle permits Car, Truck, Motorbike {
    private final String plateNumber;
    private final Manufacturer manufacturer;
    private final int manufactureYear;
    private final String color;
    private final Owner owner;

    protected Vehicle(String plateNumber, Manufacturer manufacturer, int manufactureYear, String color, Owner owner, Clock clock) {
        this.plateNumber = requireValidPlateNumber(plateNumber);
        this.manufacturer = requireNonNull(manufacturer, "Manufacturer");
        this.manufactureYear = requireValidYear(manufactureYear, clock);
        this.color = requireNotBlank(color, "Color");
        this.owner = requireNonNull(owner, "Owner");
    }

    public String getPlateNumber() { return plateNumber; }
    public Manufacturer getManufacturer() { return manufacturer; }
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

    private static <T> T requireNonNull(T value, String field) {
        if (value == null) {
            throw new IllegalArgumentException(field + " is required");
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

    private static int requireValidYear(int manufactureYear, Clock clock) {
        int currentYear = Year.now(clock).getValue();
        if (manufactureYear <= 2000 || manufactureYear > currentYear) {
            throw new IllegalArgumentException("Manufacture year must be > 2000 and <= " + currentYear);
        }
        return manufactureYear;
    }
}
