package practice.two;

public final class Electrical extends Goods {
    private final int warrantyMonths;
    private final double capacityKW;

    public Electrical(String code, String name, int inventoryQuantity, double unitPrice, int warrantyMonths, double capacityKW) {
        super(code, name, inventoryQuantity, unitPrice);
        this.warrantyMonths = requireNonNegative(warrantyMonths, "Warranty months");
        this.capacityKW = requireNonNegative(capacityKW, "Capacity KW");
    }

    @Override
    protected double getVatRate() {
        return 0.1;
    }

    @Override
    public String evaluateConsumption() {
        return getInventoryQuantity() < 3 ? "Sold" : "Not evaluated";
    }

    @Override
    public String toString() {
        return super.toString() + " | Warranty: %d months | Capacity: %.1f KW".formatted(warrantyMonths, capacityKW);
    }
}
