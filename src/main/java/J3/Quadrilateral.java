package J3;

import java.util.*;

class Quadrilateral {
    double a, b, c, d;
    double angleAB, angleBC, angleCD, angleDA;

    public Quadrilateral(double a, double b, double c, double d, double angleAB, double angleBC, double angleCD, double angleDA) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.angleAB = angleAB;
        this.angleBC = angleBC;
        this.angleCD = angleCD;
        this.angleDA = angleDA;
    }

    public double perimeter() {
        return a + b + c + d;
    }

    public double area() {
        double semiPerimeter = perimeter() / 2;
        double part1 = (semiPerimeter - a) * (semiPerimeter - b) * (semiPerimeter - c) * (semiPerimeter - d);
        double part2 = Math.cos((Math.toRadians(angleAB + angleCD)) / 2);
        return Math.sqrt(part1 - (a * c * b * d * part2 * part2));
    }

    public String quadrilateralType() {
        if (a == b && b == c && c == d) {
            if (angleAB == 90 && angleBC == 90 && angleCD == 90 && angleDA == 90) {
                return "Square";
            } else {
                return "Rhombus";
            }
        } else if ((a == c && b == d) && (angleAB == 90 && angleBC == 90 && angleCD == 90 && angleDA == 90)) {
            return "Rectangle";
        } else {
            return "Arbitrary";
        }
    }

    public static Quadrilateral findMaxArea(List<Quadrilateral> quads) {
        if (quads.isEmpty()) return null;
        Quadrilateral max = quads.get(0);
        for (Quadrilateral q : quads) {
            if (q.area() > max.area()) {
                max = q;
            }
        }
        return max;
    }

    public static Quadrilateral findMinArea(List<Quadrilateral> quads) {
        if (quads.isEmpty()) return null;
        Quadrilateral min = quads.get(0);
        for (Quadrilateral q : quads) {
            if (q.area() < min.area()) {
                min = q;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        List<Quadrilateral> quads = new ArrayList<>();
        quads.add(new Quadrilateral(4, 4, 4, 4, 90, 90, 90, 90));
        quads.add(new Quadrilateral(5, 5, 5, 5, 90, 90, 90, 90));// квадрат
        quads.add(new Quadrilateral(5, 5, 5, 5, 60, 120, 60, 120)); // ромб
        quads.add(new Quadrilateral(6, 4, 6, 4, 90, 90, 90, 90)); // прямоугольник
        quads.add(new Quadrilateral(3, 4, 5, 6, 70, 110, 85, 95)); // произвольный

        Map<String, List<Quadrilateral>> groupedQuads = new HashMap<>();
        for (Quadrilateral q : quads) {
            String type = q.quadrilateralType();
            groupedQuads.putIfAbsent(type, new ArrayList<>());
            groupedQuads.get(type).add(q);
        }

        for (Map.Entry<String, List<Quadrilateral>> entry : groupedQuads.entrySet()) {
            String type = entry.getKey();
            List<Quadrilateral> typeQuads = entry.getValue();

            Quadrilateral maxAreaQuad = Quadrilateral.findMaxArea(typeQuads);
            Quadrilateral minAreaQuad = Quadrilateral.findMinArea(typeQuads);

            System.out.println("Тип четырёхугольника: " + type);
            System.out.println("Количество: " + typeQuads.size());
            if (maxAreaQuad != null && minAreaQuad != null) {
                System.out.println("Максимальная площадь: " + maxAreaQuad.area());
                System.out.println("Минимальная площадь: " + minAreaQuad.area());
                System.out.println("Максимальный периметр: " + maxAreaQuad.perimeter());
                System.out.println("Минимальный периметр: " + minAreaQuad.perimeter());
            }
            System.out.println();
        }
    }
}

