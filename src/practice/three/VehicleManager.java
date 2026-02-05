package practice.three;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

public class VehicleManager {
    private final List<Vehicle> vehicles = new ArrayList<Vehicle>();
    private final List<String> validManufacturers = List.of("Honda", "Yamaha", "Toyota", "Suzuki");

    public boolean addVehicle(Vehicle v) {
        boolean exists = vehicles.stream().anyMatch(item -> item.getPlateNumber().equalsIgnoreCase(v.getPlateNumber()));

        if (exists) {
            IO.println("Error: " + v.getPlateNumber() + " already exists");
            return false;
        }

        if (!isOwnerValid(v.getOwner())){
            IO.println("Error: " + v.getOwner() + " already exists");
            return false;
        }

        if (!validManufacturers.contains(v.getManufacturer())) {
            IO.println("Error: " + v.getManufacturer() + " must be one of the following values: " + String.join(", ", validManufacturers));
            return false;
        }

        int currentYear = Year.now().getValue();
        if (v.getManufactureYear() <= 2000 || v.getManufactureYear() > currentYear) {
            IO.println("Error: " + v.getManufactureYear() + " must be > 2000 and <= " + currentYear);
            return false;
        }

        return vehicles.add(v);
    }

    public Optional<Vehicle> findByPlateNumber(String plateNumber) {
        return vehicles.stream()
                .filter(v -> v.getPlateNumber().equalsIgnoreCase(plateNumber))
                .findFirst();
    }

    public List<Vehicle> findVehiclesByOwnerIdCard(String idCard) {
        return vehicles.stream()
                .filter(v -> v.getOwner().idCard().equals(idCard))
                .toList();
    }

    public void deleteByManufacturer(String manufacturer) {
        vehicles.removeIf(v -> v.getManufacturer().equalsIgnoreCase(manufacturer));
        IO.println("Successfully deleted all vehicles from brand: " + manufacturer);
    }

    public String getTopManufacturer() {
        return vehicles.stream()
                .collect(Collectors.groupingBy(Vehicle::getManufacturer, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No data");
    }

    public void displayManufacturersOrderedByCountDesc() {
        vehicles.stream()
                .collect(Collectors.groupingBy(Vehicle::getManufacturer, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(e -> IO.println("Manufacturer: %s | Count: %d%n".formatted(e.getKey(), e.getValue())));
    }

    public void displayVehicleTypeStatistics() {
        vehicles.stream()
                .collect(Collectors.groupingBy(Object::getClass, Collectors.counting()))
                .forEach((k, v) -> IO.println("Vehicle type: %s | Count: %d%n".formatted(k.getSimpleName(), v)));
    }

    private boolean isOwnerValid(Owner newOwner) {
        return vehicles.stream()
            .map(Vehicle::getOwner)
            .filter(o -> o.idCard().equals(newOwner.idCard()))
            .findFirst()
            .map(existing -> existing.equals(newOwner)).orElse(true);
    }
}
