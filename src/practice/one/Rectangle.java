package practice.one;

public final class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this.width = requirePositive(width, "Width");
        this.height = requirePositive(height, "Height");
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    protected String describe() {
        return "Rectangle with width: %.2f and height: %.2f".formatted(width, height);
    }
}
