//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    Shape t = new Triangle(3, 4, 5);
    Shape r = new Rectangle(4, 5);
    Shape c = new Circle(4);
    Shape s = new Square(4);

    System.out.println(getCircumferenceAndArea(t));
    System.out.println(getCircumferenceAndArea(r));
    System.out.println(getCircumferenceAndArea(c));
    System.out.println(getCircumferenceAndArea(s));
}
private static String getCircumferenceAndArea(Shape shape) {
    String name = shape.getClass().getSimpleName();
    return name + " circumference,area=" + shape.getCircumference() + "," + shape.getArea();
}


public class Shape {
    public double getCircumference() {
        return 0;
    }

    public double getArea() {
        return 0;
    }
}

 
public class Triangle extends Shape {
    private double side1;
    private double side2;
    private double side3;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    public double getCircumference() {
        return side1 + side2 + side3;
    }

    @Override
    public double getArea() {
        double p = getCircumference() / 2;
        return Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
    }
}


public class Rectangle extends Shape {
    private double length;
    private double height;

    public Rectangle(double length, double height) {
        this.length = length;
        this.height = height;
    }

    @Override
    public double getCircumference() {
        return 2 * (length + height);
    }

    @Override
    public double getArea() {
        return length * height;
    }
}


public class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getCircumference() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}


public class Square extends Rectangle {
    public Square(double side) {
        super(side, side);
    }
}

