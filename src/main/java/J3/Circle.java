package J3;
import java.util.*;

class Circle {
    double radius;
    double x, y; // Координаты центра

    public Circle(double radius, double x, double y) {
        this.radius = radius;
        this.x = x;
        this.y = y;
    }

    public double area() {
        return Math.PI * radius * radius;
    }

    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return String.format("Окружность (x: "+ x + ", y: " + y + ", радиус: " + radius + ")");
    }

    public static boolean areCollinear(Circle c1, Circle c2, Circle c3) {
        return (c2.x - c1.x) * (c3.y - c1.y) == (c3.x - c1.x) * (c2.y - c1.y);
    }

    public static void main(String[] args) {
        // Создаем массив окружностей
        Circle[] circles = {
                new Circle(5, 0, 0),
                new Circle(3, 2, 4),
                new Circle(7, 4, 8),
                new Circle(4, 0, 2),
                new Circle(4, 0, 4),
                new Circle(6, 8, 16),
                new Circle(2, 10, 20),
                new Circle(4, 3, 3)
        };


        List<List<Circle>> groups = new ArrayList<>();
        Set<Circle> visited = new HashSet<>();

        for (int i = 0; i < circles.length; i++) {
            if (visited.contains(circles[i])) continue;

            Circle c1 = circles[i];
            for (int j = i + 1; j < circles.length; j++) {
                Circle c2 = circles[j];

                List<Circle> group = new ArrayList<>();
                group.add(c1);
                group.add(c2);

                for (int k = 0; k < circles.length; k++) {
                    if (k != i && k != j && !visited.contains(circles[k])) {
                        Circle c3 = circles[k];
                        if (areCollinear(c1, c2, c3)) {
                            group.add(c3);
                        }
                    }
                }

                if (group.size() > 2 && groups.stream().noneMatch(g -> g.containsAll(group))) {
                    groups.add(group);
                    visited.addAll(group);
                }
            }
        }

        for (int i = 0; i < groups.size(); i++) {
            List<Circle> group = groups.get(i);
            System.out.println("Группа " + (i + 1) + ":");
            for (int j = 0; j < group.size(); j++) {
                for (int k = j + 1; k < group.size(); k++) {
                    System.out.println(group.get(j) + " и " + group.get(k) + " лежат на одной прямой.");
                }
            }

            Circle maxAreaCircle = group.stream().max(Comparator.comparingDouble(Circle::area)).orElse(null);
            Circle minAreaCircle = group.stream().min(Comparator.comparingDouble(Circle::area)).orElse(null);
            Circle maxPerCircle = group.stream().max(Comparator.comparingDouble(Circle::perimeter)).orElse(null);
            Circle minPerCircle = group.stream().min(Comparator.comparingDouble(Circle::perimeter)).orElse(null);

            if (maxAreaCircle != null && minAreaCircle != null) {
                System.out.println("Наибольшая площадь в группе: " + maxAreaCircle + " = " + maxAreaCircle.area());
                System.out.println("Наименьшая площадь в группе: " + minAreaCircle + " = " + minAreaCircle.area());
                System.out.println("Наибольший периметр в группе: " + maxPerCircle + " = " + maxPerCircle.perimeter());
                System.out.println("Наименьший периметр в группе: " + minPerCircle + " = " + minPerCircle.perimeter() +"\n");
            }
        }

        Circle maxRadiusCircle = Arrays.stream(circles).max(Comparator.comparingDouble(c -> c.radius)).orElse(null);
        System.out.println(maxRadiusCircle + " имеет наибольший радиус = " + maxRadiusCircle.radius);
        System.out.println(maxRadiusCircle + " имеет наибольшую площадь = " + maxRadiusCircle.area());
        System.out.println(maxRadiusCircle + " имеет наибольший периметр = " + maxRadiusCircle.perimeter());

        Circle minRadiusCircle = Arrays.stream(circles).min(Comparator.comparingDouble(c -> c.radius)).orElse(null);
        System.out.println(minRadiusCircle + " имеет наименьший радиус = " + minRadiusCircle.radius);
        System.out.println(minRadiusCircle + " имеет наименьшую площадь = " + minRadiusCircle.area());
        System.out.println(minRadiusCircle + " имеет наименьший периметр = " + minRadiusCircle.perimeter());

    }
}

