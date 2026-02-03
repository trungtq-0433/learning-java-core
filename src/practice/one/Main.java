import practice.one.*;

void main() {
    IO.println("Practice 1: Begin");
    var shapes = List.of(new Circle(14), new Rectangle(12, 15));
    shapes.forEach(s -> s.displayInfo());
    IO.println("Practice 1: End");
}
