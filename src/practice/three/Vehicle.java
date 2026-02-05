package practice.three;

public abstract class Vehicle {
    private String plateNumber;
    private String manufacturer;
    private int manufactureYear;
    private String color;
    private Owner owner;

    public Vehicle(String plateNumber, String manufacturer, int manufactureYear, String color, Owner owner) {
        setPlateNumber(plateNumber);
        this.manufacturer = manufacturer;
        this.manufactureYear = manufactureYear;
        this.color = color;
        this.owner = owner;
    }

    public String getPlateNumber() { return plateNumber; }
    public String getManufacturer() {return manufacturer; }
    public int getManufactureYear() {return manufactureYear; }
    public Owner getOwner() { return owner; }

    public void setPlateNumber(String plateNumber) {
        if (plateNumber.length() != 5) {
            throw new IllegalArgumentException("Plate number must be 5 character!");
        }
        this.plateNumber = plateNumber;
    }

    @Override
    public String toString() {
        return "Plate number: %s | Manufacturer: %s | Manufacture year: %d | Color: %s | Owner: %s".formatted(
            plateNumber, manufacturer, manufactureYear, color, owner.fullName());
    }
}