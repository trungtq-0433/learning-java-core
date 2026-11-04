package practice.one;

public abstract sealed class Shape permits Circle, Rectangle {
    public abstract double getArea();

    public abstract double getPerimeter();

    protected abstract String describe();

    @Override
    public String toString() {
        return "%s | Perimeter: %.2f | Area: %.2f".formatted(describe(), getPerimeter(), getArea());
    }

    protected static double requirePositive(double value, String field) {
        if (!Double.isFinite(value) || value <= 0) {
            throw new IllegalArgumentException(field + " must be a positive number");
        }
        return value;
    }
}
