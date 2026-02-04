package practice.two;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Crockery extends Goods {
    private String manufacturer;
    private LocalDate dateReceived;

    public Crockery(String code, String name, int inventoryQuantity, double unitPrice, String manufacturer, LocalDate dateReceived) {
        super(code, name, inventoryQuantity, unitPrice);
        this.manufacturer = manufacturer;
        this.dateReceived = dateReceived;
    }

    @Override
    public double calculateVAT() {
        return unitPrice * 0.1;
    }

    @Override
    public String evaluateConsumption() {
        long storageDays = dateReceived.until(LocalDate.now(), ChronoUnit.DAYS);
        if (inventoryQuantity > 50 && storageDays > 10) {
            return "Slow sales";
        }
        return "Not evaluated";
    }
}
