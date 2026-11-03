package practice.two;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Objects;

public class Food extends Goods {
    private LocalDate mfgDate;
    private LocalDate expDate;
    private String supplier;
    private final Clock clock;

    public Food(String code, String name, int inventoryQuantity, double unitPrice, LocalDate mfgDate, LocalDate expDate, String supplier, Clock clock) {
        super(code, name, inventoryQuantity, unitPrice);
        this.mfgDate = mfgDate;
        setExpDate(expDate, mfgDate);
        this.supplier = supplier;
        this.clock = Objects.requireNonNull(clock);
    }

    @Override
    protected double getVatRate() {
        return 0.05;
    }

    @Override
    public String evaluateConsumption() {
        boolean isExpired = LocalDate.now(clock).isAfter(expDate);
        if (inventoryQuantity > 0 && isExpired) {
            return "Hard to sell";
        }
        return "Not evaluated";
    }

    @Override
    public String toString() {
        return super.toString() + " | Mfg date: %s | Exp date: %s | Supplier: %s".formatted(mfgDate, expDate, supplier);
    }

    private void setExpDate(LocalDate expDate, LocalDate mfgDate) {
        if (expDate.isBefore(mfgDate)) {
            throw new IllegalArgumentException("ExpDate must be >= " + mfgDate);
        }

        this.expDate = expDate;
    }
}
