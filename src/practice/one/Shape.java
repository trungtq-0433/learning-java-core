package practice.one;

public abstract class Shape {
    protected double width;
    protected double height;

    public Shape(double width, double height) {
        if (!isPositive(width) || !isPositive(height)) {
            throw new IllegalArgumentException("Dimensions must be positive numbers");
        }
        this.width = width;
        this.height = height;
    }

    public abstract double getArea();

    public void displayInfo() {
        IO.println("----------------------------------------------------------");
    }

    private static boolean isPositive(double value) {
        return Double.isFinite(value) && value > 0;
    }
}
