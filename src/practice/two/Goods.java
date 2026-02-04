package practice.two;

public abstract class Goods {
    protected String code;
    protected String name;
    protected int inventoryQuantity;
    protected double unitPrice;

    public Goods(String code, String name, int inventoryQuantity, double unitPrice) {
        this.code = code;
        this.name = name;
        setInventoryQuantity(inventoryQuantity);
        this.unitPrice = unitPrice;
    }

    public String getCode() {
        return code;
    }

    public abstract double calculateVAT();

    public abstract String evaluateConsumption();

    private void setInventoryQuantity(int inventoryQuantity) {
        if(inventoryQuantity < 0) {
            throw new IllegalArgumentException("Inventory quantity must be >= 0");
        }
        this.inventoryQuantity = inventoryQuantity;
    }

    @Override
    public String toString() {
        return String.format("Code: %s | Name: %s | Inventory Quantity: %d | Unit Price: %2f | VAT: %2f | Evaluate: %s", code, name, inventoryQuantity, unitPrice, calculateVAT(), evaluateConsumption());
    }
}
