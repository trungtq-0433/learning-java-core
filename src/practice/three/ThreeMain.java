import practice.three.Car;
import practice.three.Owner;
import practice.three.Truck;
import practice.three.VehicleManager;

void main() {
    VehicleManager vm = new VehicleManager();
    Clock clock = Clock.systemDefaultZone();

    try {
        var owner = new Owner("123456789123", "Trung", "xxx@ccc.com");
        var ownerError = new Owner("123456789123", "Hoang", "xxx@ccc.com");

        vm.addVehicle(new Car("12345", "Honda", 2010, "red", owner, 4, "2KW", clock));
        vm.addVehicle(new Truck("11111", "Suzuki", 2011, "black", ownerError, 4, clock));

//        if (vm.findByPlateNumber("12345").isPresent()) IO.println("12345 exist!");
//        IO.println(vm.findVehiclesByOwnerIdCard("123456789123"));
//        IO.println(vm.getTopManufacturer());
//        vm.displayManufacturersOrderedByCountDesc();
//        vm.displayVehicleTypeStatistics();

    } catch (Exception e) {
        IO.println(e.getMessage());
    }
}
