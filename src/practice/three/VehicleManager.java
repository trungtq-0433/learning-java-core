package practice.three;

import java.util.*;
import java.util.stream.Collectors;

public class VehicleManager {
    private final List<Vehicle> vehicles = new ArrayList<Vehicle>();

    public boolean addVehicle(Vehicle v) {
        boolean exists = vehicles.stream().anyMatch(item -> item.getPlateNumber().equalsIgnoreCase(v.getPlateNumber()));

        if (exists) {
            IO.println("Error: " + v.getPlateNumber() + " already exists");
            return false;
        }

        if (!isOwnerValid(v.getOwner())){
            IO.println("Error: ID card is already registered to an owner with a different name or email");
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

    public boolean deleteByManufacturer(String manufacturer) {
        boolean removed = vehicles.removeIf(v -> v.getManufacturer().equalsIgnoreCase(manufacturer));
        if (removed) {
            IO.println("Successfully deleted all vehicles from brand: " + manufacturer);
        } else {
            IO.println("No vehicles found for brand: " + manufacturer);
        }
        return removed;
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
                .forEach(e -> IO.println("Manufacturer: %s | Count: %d".formatted(e.getKey(), e.getValue())));
    }

    public void displayVehicleTypeStatistics() {
        vehicles.stream()
                .collect(Collectors.groupingBy(Object::getClass, Collectors.counting()))
                .forEach((k, v) -> IO.println("Vehicle type: %s | Count: %d".formatted(k.getSimpleName(), v)));
    }

    private boolean isOwnerValid(Owner newOwner) {
        return vehicles.stream()
            .map(Vehicle::getOwner)
            .filter(o -> o.idCard().equals(newOwner.idCard()))
            .findFirst()
            .map(existing -> existing.equals(newOwner)).orElse(true);
    }
}
