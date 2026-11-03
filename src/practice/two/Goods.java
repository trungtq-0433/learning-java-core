package practice.two;

public abstract class Goods {
    protected String code;
    protected String name;
    protected int inventoryQuantity;
    protected double unitPrice;

    public Goods(String code, String name, int inventoryQuantity, double unitPrice) {
        this.code = requireNotBlank(code, "Code");
        this.name = requireNotBlank(name, "Name");
        setInventoryQuantity(inventoryQuantity);
        setUnitPrice(unitPrice);
    }

    public String getCode() {
        return code;
    }

    public double calculateVAT() {
        return unitPrice * getVatRate();
    }

    protected abstract double getVatRate();

    public abstract String evaluateConsumption();

    private void setInventoryQuantity(int inventoryQuantity) {
        if(inventoryQuantity < 0) {
            throw new IllegalArgumentException("Inventory quantity must be >= 0");
        }
        this.inventoryQuantity = inventoryQuantity;
    }

    private void setUnitPrice(double unitPrice) {
        if (!Double.isFinite(unitPrice) || unitPrice < 0) {
            throw new IllegalArgumentException("Unit price must be >= 0");
        }
        this.unitPrice = unitPrice;
    }

    private static String requireNotBlank(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value;
    }

    @Override
    public String toString() {
        return String.format("Code: %s | Name: %s | Inventory Quantity: %d | Unit Price: %.2f | VAT: %.2f | Evaluate: %s", code, name, inventoryQuantity, unitPrice, calculateVAT(), evaluateConsumption());
    }
}
