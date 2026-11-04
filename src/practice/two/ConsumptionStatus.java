package practice.two;

public enum ConsumptionStatus {
    HARD_TO_SELL("Hard to sell"),
    SLOW_SALES("Slow sales"),
    SOLD("Sold"),
    NOT_EVALUATED("Not evaluated");

    private final String label;

    ConsumptionStatus(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
