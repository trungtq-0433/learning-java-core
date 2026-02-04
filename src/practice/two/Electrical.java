package practice.two;

public class Electrical extends Goods {
    private int warrantyMonths;
    private double capacityKW;

    public Electrical(String code, String name, int inventoryQuantity, double unitPrice, int warrantyMonths, double capacityKW) {
        super(code, name, inventoryQuantity, unitPrice);
        setWarrantyMonths(warrantyMonths);
        setCapacityKW(capacityKW);
    }


    @Override
    public double calculateVAT() {
        return unitPrice * 0.1;
    }

    @Override
    public String evaluateConsumption() {
        return (inventoryQuantity < 3) ? "Sold" : "Not evaluated";
    }

    private void setWarrantyMonths(int warrantyMonths) {
        if(warrantyMonths < 0) {
            throw new IllegalArgumentException("Warranty months must be >= 0");
        }

        this.warrantyMonths = warrantyMonths;
    }

    private void setCapacityKW(double capacityKW) {
        if(capacityKW < 0) {
            throw new IllegalArgumentException("Capacity KW must be >= 0");
        }

        this.capacityKW = capacityKW;
    }
}