import practice.three.Car;
import practice.three.Manufacturer;
import practice.three.Motorbike;
import practice.three.Owner;
import practice.three.Truck;
import practice.three.Vehicle;
import practice.three.VehicleManager;

final Clock clock = Clock.systemDefaultZone();
final VehicleManager vm = new VehicleManager();

void main() {
    var trung = new Owner("123456789123", "Trung", "trung@example.com");
    var conflictingOwner = new Owner("123456789123", "Hoang", "trung@example.com");

    tryAdd(() -> new Car("12345", Manufacturer.HONDA, 2010, "red", trung, 4, "Petrol", clock));
    tryAdd(() -> new Motorbike("22222", Manufacturer.YAMAHA, 2020, "blue", trung, 125, clock));
    tryAdd(() -> new Truck("33333", Manufacturer.HONDA, 2015, "white", trung, 4, clock));
    tryAdd(() -> new Truck("11111", Manufacturer.SUZUKI, 2011, "black", conflictingOwner, 4, clock));
    tryAdd(() -> new Car("12345", Manufacturer.TOYOTA, 2018, "grey", trung, 7, "Hybrid", clock));
    tryAdd(() -> new Car("99999", Manufacturer.from("BMW"), 2019, "black", trung, 5, "Diesel", clock));

    IO.println("Find 12345: " + vm.findByPlateNumber("12345").map(Vehicle::toString).orElse("not found"));
    IO.println("Vehicles of " + trung.fullName() + ":");
    vm.findVehiclesByOwnerIdCard(trung.idCard()).forEach(IO::println);
    IO.println("Top manufacturer: " + vm.getTopManufacturer().map(Manufacturer::toString).orElse("none"));
    vm.getManufacturerRanking().forEach(m -> IO.println("Manufacturer: %s | Count: %d".formatted(m.manufacturer(), m.count())));
    vm.countByVehicleType().forEach((type, count) -> IO.println("Vehicle type: %s | Count: %d".formatted(type, count)));
    IO.println("Deleted Honda vehicles: " + vm.deleteByManufacturer(Manufacturer.HONDA));
}

void tryAdd(Supplier<Vehicle> newVehicle) {
    try {
        Vehicle vehicle = newVehicle.get();
        vm.addVehicle(vehicle);
        IO.println("Added " + vehicle.getPlateNumber());
    } catch (IllegalArgumentException e) {
        IO.println("Error: " + e.getMessage());
    }
}
