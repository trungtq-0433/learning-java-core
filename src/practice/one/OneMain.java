import practice.one.Circle;
import practice.one.Rectangle;
import practice.one.Shape;

void main() {
    IO.println("Practice 1: Begin");
    List<Shape> shapes = List.of(new Circle(7), new Rectangle(12, 15));
    shapes.forEach(IO::println);
    IO.println("Practice 1: End");
}
