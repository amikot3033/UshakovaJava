package J3;
import java.util.*;

class TrPoint {
    double x, y;

    public TrPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double distance(TrPoint p1, TrPoint p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }
}

class Triangle {
    TrPoint a, b, c;

    public Triangle(TrPoint a, TrPoint b, TrPoint c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getPerimeter() {
        double ab = TrPoint.distance(a, b);
        double bc = TrPoint.distance(b, c);
        double ca = TrPoint.distance(c, a);
        return ab + bc + ca;
    }

    public double getArea() {
        double ab = TrPoint.distance(a, b);
        double bc = TrPoint.distance(b, c);
        double ca = TrPoint.distance(c, a);
        double s = getPerimeter() / 2;
        return Math.sqrt(s * (s - ab) * (s - bc) * (s - ca));
    }

    public String getType() {
        double ab = TrPoint.distance(a, b);
        double bc = TrPoint.distance(b, c);
        double ca = TrPoint.distance(c, a);

        if (ab == bc && bc == ca) {
            return "Равносторонний";
        } else if (ab == bc || bc == ca || ca == ab) {
            return "Равнобедренный";
        } else if (isRightAngle(ab, bc, ca)) {
            return "Прямоугольный";
        } else {
            return "Произвольный";
        }
    }

    private boolean isRightAngle(double ab, double bc, double ca) {
        double[] sides = {ab, bc, ca};
        Arrays.sort(sides);
        return Math.abs(sides[2] * sides[2] - (sides[0] * sides[0] + sides[1] * sides[1])) < 1e-6;
    }

    @Override
    public String toString() {
        return String.format("Triangle[(%.2f, %.2f), (%.2f, %.2f), (%.2f, %.2f)]", a.x, a.y, b.x, b.y, c.x, c.y);
    }
}

public class Main {
    public static void main(String[] args) {
        List<Triangle> triangles = new ArrayList<>();

        // Пример добавления треугольников
        triangles.add(new Triangle(new TrPoint(0, 0), new TrPoint(4, 0), new TrPoint(2, Math.sqrt(12)))); // Равносторонний
        triangles.add(new Triangle(new TrPoint(0, 0), new TrPoint(4, 0), new TrPoint(4, 3))); // Прямоугольный
        triangles.add(new Triangle(new TrPoint(0, 0), new TrPoint(4, 0), new TrPoint(2, 2))); // Произвольный
        triangles.add(new Triangle(new TrPoint(0, 0), new TrPoint(2, 0), new TrPoint(1, Math.sqrt(3)))); // Равнобедренный

        Map<String, List<Triangle>> groupedTriangles = new HashMap<>();

        for (Triangle t : triangles) {
            groupedTriangles.computeIfAbsent(t.getType(), k -> new ArrayList<>()).add(t);
        }

        for (Map.Entry<String, List<Triangle>> entry : groupedTriangles.entrySet()) {
            String type = entry.getKey();
            List<Triangle> group = entry.getValue();

            Triangle maxAreaTriangle = Collections.max(group, Comparator.comparingDouble(Triangle::getArea));
            Triangle minAreaTriangle = Collections.min(group, Comparator.comparingDouble(Triangle::getArea));
            Triangle maxPerimeterTriangle = Collections.max(group, Comparator.comparingDouble(Triangle::getPerimeter));
            Triangle minPerimeterTriangle = Collections.min(group, Comparator.comparingDouble(Triangle::getPerimeter));

            System.out.printf("Тип: %s\n", type);
            System.out.printf("Количество: %d\n", group.size());
            System.out.printf("Максимальная площадь: %.2f (%s)\n", maxAreaTriangle.getArea(), maxAreaTriangle);
            System.out.printf("Минимальная площадь: %.2f (%s)\n", minAreaTriangle.getArea(), minAreaTriangle);
            System.out.printf("Максимальный периметр: %.2f (%s)\n", maxPerimeterTriangle.getPerimeter(), maxPerimeterTriangle);
            System.out.printf("Минимальный периметр: %.2f (%s)\n\n", minPerimeterTriangle.getPerimeter(), minPerimeterTriangle);
        }
    }
}
