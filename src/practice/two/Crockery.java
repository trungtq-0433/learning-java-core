package practice.two;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class Crockery extends Goods {
    private static final BigDecimal VAT_RATE = new BigDecimal("0.1");

    private final String manufacturer;
    private final LocalDate dateReceived;
    private final Clock clock;

    public Crockery(String code, String name, int inventoryQuantity, BigDecimal unitPrice, String manufacturer, LocalDate dateReceived, Clock clock) {
        super(code, name, inventoryQuantity, unitPrice);
        this.manufacturer = requireNotBlank(manufacturer, "Manufacturer");
        this.dateReceived = requireNonNull(dateReceived, "Date received");
        this.clock = requireNonNull(clock, "Clock");
    }

    @Override
    protected BigDecimal getVatRate() {
        return VAT_RATE;
    }

    @Override
    public ConsumptionStatus evaluateConsumption() {
        long storageDays = dateReceived.until(LocalDate.now(clock), ChronoUnit.DAYS);
        return getInventoryQuantity() > 50 && storageDays > 10 ? ConsumptionStatus.SLOW_SALES : ConsumptionStatus.NOT_EVALUATED;
    }

    @Override
    public String toString() {
        return super.toString() + " | Manufacturer: %s | Date received: %s".formatted(manufacturer, dateReceived);
    }
}
