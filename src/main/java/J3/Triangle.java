package J3;
import java.util.*;

import java.util.*;

class Triangle {
    double a, b, c;
    double angleA, angleB, angleC;

    public Triangle(double a, double b, double c, double angleA, double angleB, double angleC) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.angleA = angleA;
        this.angleB = angleB;
        this.angleC = angleC;
    }

    //периметр
    public double perimeter() {
        return a + b + c;
    }

    // площадь
    public double area() {
        double s = perimeter() / 2; // полупериметр
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    //тип треугольника
    public String triangleType() {
        if (a == b && b == c) {
            return "Equilateral";
        } else if (a == b || b == c || a == c) {
            return "Isosceles";
        } else if (angleA == 90 || angleB == 90 || angleC == 90) {
            return "Right-angled";
        } else {
            return "Scalene";
        }
    }

    public static Triangle findMaxArea(List<Triangle> triangles) {
        Triangle max = triangles.get(0);

        for (Triangle t : triangles) {
            if (t.area() > max.area()) {
                max = t;
            }
        }
        return max;
    }

    public static Triangle findMinArea(List<Triangle> triangles) {
        Triangle min = triangles.get(0);

        for (Triangle t : triangles) {
            if (t.area() < min.area()) {
                min = t;
            }
        }
        return min;
    }

    public static Triangle findMaxPer(List<Triangle> triangles) {
        Triangle max = triangles.get(0);

        for (Triangle t : triangles) {
            if (t.perimeter() > max.perimeter()) {
                max = t;
            }
        }
        return max;
    }

    public static Triangle findMinPer(List<Triangle> triangles) {
        Triangle min = triangles.get(0);

        for (Triangle t : triangles) {
            if (t.perimeter() < min.perimeter()) {
                min = t;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        // Создание списка треугольников
        List<Triangle> triangles = new ArrayList<>();
        triangles.add(new Triangle(3, 4, 5, 90, 60, 30)); // прямоугольный
        triangles.add(new Triangle(3, 4, 5, 90, 60, 30)); // прямоугольный
        triangles.add(new Triangle(5, 6, 7, 90, 60, 30)); // прямоугольный
        triangles.add(new Triangle(5, 5, 8, 45, 45, 90));
        triangles.add(new Triangle(5, 5, 9, 45, 45, 90));// равнобедренный
        triangles.add(new Triangle(6, 6, 6, 60, 60, 60)); // равносторонний
        triangles.add(new Triangle(7, 8, 10, 50, 60, 70)); // произвольный

        // Разделение треугольников на разные списки по типу
        Map<String, List<Triangle>> groupedTriangles = new HashMap<>();
        for (Triangle t : triangles) {
            String type = t.triangleType();
            groupedTriangles.putIfAbsent(type, new ArrayList<>());
            groupedTriangles.get(type).add(t);
        }

        // Поиск максимального по площади треугольника в каждом типе
        for (Map.Entry<String, List<Triangle>> entry : groupedTriangles.entrySet()) {
            String type = entry.getKey();
            List<Triangle> typeTriangles = entry.getValue();

            Triangle maxAreaTriangle = Triangle.findMaxArea(typeTriangles);
            Triangle maxPerTriangle = Triangle.findMaxPer(typeTriangles);

            Triangle minAreaTriangle = Triangle.findMinArea(typeTriangles);
            Triangle minPerTriangle = Triangle.findMinPer(typeTriangles);


            System.out.println("Тип треугольника: " + type);
            System.out.println("Количество: " + typeTriangles.size());
            if (maxAreaTriangle != null) {
                System.out.println("Максимальная площадь: " + maxAreaTriangle.area());
                System.out.println("Минимальная площадь: " + minAreaTriangle.area());
            }

            if (maxPerTriangle != null) {
                System.out.println("Максимальный периметр: " + maxPerTriangle.perimeter());
                System.out.println("Минимальный периметр: " + minPerTriangle.perimeter());
            }
            System.out.println();
        }
    }
}


