package practice.one;

public final class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = requirePositive(radius, "Radius");
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    protected String describe() {
        return "Circle with radius: %.2f".formatted(radius);
    }
}
