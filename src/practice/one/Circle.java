package practice.one;

public class Circle extends Shape {
    public Circle(double diameter) {
        super(diameter, diameter);
    }

    @Override
    public double getArea() {
        double radius = width / 2;
        return Math.PI * Math.pow(radius, 2);
    }

    public double getCircumference() {
        return Math.PI * width;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        IO.println("Circle with diameter: " + width);
        IO.println("Circumference is: " + getCircumference());
        IO.println("Area is: " + getArea());
    }
}
