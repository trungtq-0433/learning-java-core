package practice.three;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class VehicleManager {
    public record ManufacturerCount(String manufacturer, long count) {}

    private final List<Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        if (findByPlateNumber(vehicle.getPlateNumber()).isPresent()) {
            throw new IllegalArgumentException("Plate number " + vehicle.getPlateNumber() + " already exists");
        }
        if (hasConflictingOwner(vehicle.getOwner())) {
            throw new IllegalArgumentException("ID card is already registered to an owner with a different name or email");
        }
        vehicles.add(vehicle);
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

    public int deleteByManufacturer(String manufacturer) {
        int sizeBefore = vehicles.size();
        vehicles.removeIf(v -> v.getManufacturer().equalsIgnoreCase(manufacturer));
        return sizeBefore - vehicles.size();
    }

    public List<ManufacturerCount> getManufacturerRanking() {
        return vehicles.stream()
                .collect(Collectors.groupingBy(Vehicle::getManufacturer, Collectors.counting()))
                .entrySet().stream()
                .map(e -> new ManufacturerCount(e.getKey(), e.getValue()))
                .sorted(Comparator.comparingLong(ManufacturerCount::count).reversed()
                        .thenComparing(ManufacturerCount::manufacturer))
                .toList();
    }

    public Optional<String> getTopManufacturer() {
        return getManufacturerRanking().stream().findFirst().map(ManufacturerCount::manufacturer);
    }

    public Map<String, Long> countByVehicleType() {
        return vehicles.stream()
                .collect(Collectors.groupingBy(v -> v.getClass().getSimpleName(), TreeMap::new, Collectors.counting()));
    }

    private boolean hasConflictingOwner(Owner owner) {
        return vehicles.stream()
                .map(Vehicle::getOwner)
                .anyMatch(existing -> existing.idCard().equals(owner.idCard()) && !existing.equals(owner));
    }
}
