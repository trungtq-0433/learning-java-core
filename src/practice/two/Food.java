package practice.two;

import java.time.LocalDate;

public class Food extends Goods {
    private LocalDate mfgDate;
    private LocalDate expDate;
    private String supplier;

    public Food(String code, String name, int inventoryQuantity, double unitPrice, LocalDate mfgDate, LocalDate expDate, String supplier) {
        super(code, name, inventoryQuantity, unitPrice);
        this.mfgDate = mfgDate;
        setExpDate(expDate, mfgDate);
        this.supplier = supplier;
    }

    @Override
    public double calculateVAT() {
        return unitPrice * 0.05;
    }

    @Override
    public String evaluateConsumption() {
        boolean isExpired = LocalDate.now().isAfter(expDate);
        if (inventoryQuantity > 0 && isExpired) {
            return "Hard to sell";
        }
        return "Not evaluated";
    }

    private void setExpDate(LocalDate expDate, LocalDate mfgDate) {
        if (expDate.isBefore(mfgDate)) {
            throw new IllegalArgumentException("ExpDate must be >= " + mfgDate);
        }

        this.expDate = expDate;
    }
}
