package practice.two;

import java.time.Clock;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Crockery extends Goods {
    private String manufacturer;
    private LocalDate dateReceived;
    private final Clock clock;

    public Crockery(String code, String name, int inventoryQuantity, double unitPrice, String manufacturer, LocalDate dateReceived, Clock clock) {
        super(code, name, inventoryQuantity, unitPrice);
        this.manufacturer = manufacturer;
        this.dateReceived = dateReceived;
        this.clock = Objects.requireNonNull(clock);
    }

    @Override
    protected double getVatRate() {
        return 0.1;
    }

    @Override
    public String evaluateConsumption() {
        long storageDays = dateReceived.until(LocalDate.now(clock), ChronoUnit.DAYS);
        if (inventoryQuantity > 50 && storageDays > 10) {
            return "Slow sales";
        }
        return "Not evaluated";
    }

    @Override
    public String toString() {
        return super.toString() + " | Manufacturer: %s | Date received: %s".formatted(manufacturer, dateReceived);
    }
}
