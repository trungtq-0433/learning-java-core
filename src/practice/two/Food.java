package practice.two;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;

public final class Food extends Goods {
    private static final BigDecimal VAT_RATE = new BigDecimal("0.05");

    private final LocalDate mfgDate;
    private final LocalDate expDate;
    private final String supplier;
    private final Clock clock;

    public Food(String code, String name, int inventoryQuantity, BigDecimal unitPrice, LocalDate mfgDate, LocalDate expDate, String supplier, Clock clock) {
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
    protected BigDecimal getVatRate() {
        return VAT_RATE;
    }

    @Override
    public ConsumptionStatus evaluateConsumption() {
        boolean isExpired = LocalDate.now(clock).isAfter(expDate);
        return getInventoryQuantity() > 0 && isExpired ? ConsumptionStatus.HARD_TO_SELL : ConsumptionStatus.NOT_EVALUATED;
    }

    @Override
    public String toString() {
        return super.toString() + " | Mfg date: %s | Exp date: %s | Supplier: %s".formatted(mfgDate, expDate, supplier);
    }
}
