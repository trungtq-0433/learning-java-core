package practice.two;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract sealed class Goods permits Food, Electrical, Crockery {
    private final String code;
    private final String name;
    private final int inventoryQuantity;
    private final BigDecimal unitPrice;

    protected Goods(String code, String name, int inventoryQuantity, BigDecimal unitPrice) {
        this.code = requireNotBlank(code, "Code");
        this.name = requireNotBlank(name, "Name");
        this.inventoryQuantity = requireNonNegative(inventoryQuantity, "Inventory quantity");
        this.unitPrice = requireNonNegative(unitPrice, "Unit price");
    }

    public String getCode() {
        return code;
    }

    protected int getInventoryQuantity() {
        return inventoryQuantity;
    }

    public final BigDecimal calculateVAT() {
        return unitPrice.multiply(getVatRate()).setScale(2, RoundingMode.HALF_UP);
    }

    protected abstract BigDecimal getVatRate();

    public abstract ConsumptionStatus evaluateConsumption();

    @Override
    public String toString() {
        return "Code: %s | Name: %s | Inventory Quantity: %d | Unit Price: %.2f | VAT: %.2f | Evaluate: %s".formatted(
                code, name, inventoryQuantity, unitPrice, calculateVAT(), evaluateConsumption());
    }

    protected static String requireNotBlank(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value;
    }

    protected static <T> T requireNonNull(T value, String field) {
        if (value == null) {
            throw new IllegalArgumentException(field + " is required");
        }
        return value;
    }

    protected static int requireNonNegative(int value, String field) {
        if (value < 0) {
            throw new IllegalArgumentException(field + " must be >= 0");
        }
        return value;
    }

    protected static double requireNonNegative(double value, String field) {
        if (!Double.isFinite(value) || value < 0) {
            throw new IllegalArgumentException(field + " must be >= 0");
        }
        return value;
    }

    private static BigDecimal requireNonNegative(BigDecimal value, String field) {
        if (requireNonNull(value, field).signum() < 0) {
            throw new IllegalArgumentException(field + " must be >= 0");
        }
        return value;
    }
}
