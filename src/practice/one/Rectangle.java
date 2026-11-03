package practice.one;

public class Rectangle extends Shape {

    public Rectangle(double width, double height) {
        super(width, height);
    }

    @Override
    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        IO.println("Rectangle with width: " + width + " and height: " + height);
        IO.println("Perimeter is: " + getPerimeter());
        IO.println("Area is: " + getArea());
    }
}
