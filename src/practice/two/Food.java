package practice.two;

import java.time.Clock;
import java.time.LocalDate;

public final class Food extends Goods {
    private final LocalDate mfgDate;
    private final LocalDate expDate;
    private final String supplier;
    private final Clock clock;

    public Food(String code, String name, int inventoryQuantity, double unitPrice, LocalDate mfgDate, LocalDate expDate, String supplier, Clock clock) {
        super(code, name, inventoryQuantity, unitPrice);
        this.mfgDate = requireNonNull(mfgDate, "Mfg date");
        if (requireNonNull(expDate, "Exp date").isBefore(mfgDate)) {
            throw new IllegalArgumentException("Exp date must be >= " + mfgDate);
        }
        this.expDate = expDate;
        this.supplier = requireNotBlank(supplier, "Supplier");
        this.clock = requireNonNull(clock, "Clock");
    }

    @Override
    protected double getVatRate() {
        return 0.05;
    }

    @Override
    public String evaluateConsumption() {
        boolean isExpired = LocalDate.now(clock).isAfter(expDate);
        return getInventoryQuantity() > 0 && isExpired ? "Hard to sell" : "Not evaluated";
    }

    @Override
    public String toString() {
        return super.toString() + " | Mfg date: %s | Exp date: %s | Supplier: %s".formatted(mfgDate, expDate, supplier);
    }
}
