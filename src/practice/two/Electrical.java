package practice.two;

import java.math.BigDecimal;

public final class Electrical extends Goods {
    private static final BigDecimal VAT_RATE = new BigDecimal("0.1");

    private final int warrantyMonths;
    private final double capacityKW;

    public Electrical(String code, String name, int inventoryQuantity, BigDecimal unitPrice, int warrantyMonths, double capacityKW) {
        super(code, name, inventoryQuantity, unitPrice);
        this.warrantyMonths = requireNonNegative(warrantyMonths, "Warranty months");
        this.capacityKW = requireNonNegative(capacityKW, "Capacity KW");
    }

    @Override
    protected BigDecimal getVatRate() {
        return VAT_RATE;
    }

    @Override
    public ConsumptionStatus evaluateConsumption() {
        return getInventoryQuantity() < 3 ? ConsumptionStatus.SOLD : ConsumptionStatus.NOT_EVALUATED;
    }

    @Override
    public String toString() {
        return super.toString() + " | Warranty: %d months | Capacity: %.1f KW".formatted(warrantyMonths, capacityKW);
    }
}
