package J3;

import java.util.*;

class Line {
    double A, B, C; // Коэффициенты уравнения Ax + By + C = 0

    public Line(double A, double B, double C) {
        this.A = A;
        this.B = B;
        this.C = C;
    }

    // Точка пересечения с осью X (если существует)
    public String intersectionWithXAxis() {
        if (B == 0) {
            return "Прямая параллельна оси X.";
        }
        double x = -C / A;
        return String.format("(%.2f, 0)", x);
    }

    // Точка пересечения с осью Y (если существует)
    public String intersectionWithYAxis() {
        if (A == 0) {
            return "Прямая параллельна оси Y.";
        }
        double y = -C / B;
        return String.format("(0, %.2f)", y);
    }

    // Точка пересечения двух прямых (если не параллельны)
    public static String intersection(Line l1, Line l2) {
        double det = l1.A * l2.B - l2.A * l1.B;
        if (det == 0) {
            return "Прямые параллельны или совпадают.";
        }
        double x = (l2.B * -l1.C - l1.B * -l2.C) / det;
        double y = (l1.A * -l2.C - l2.A * -l1.C) / det;
        return String.format("(%.2f, %.2f)", x, y);
    }

    // Проверка параллельности двух прямых
    public static boolean areParallel(Line l1, Line l2) {
        return l1.A * l2.B == l1.B * l2.A;
    }

    @Override
    public String toString() {
        return String.format("Прямая (%.2fx + %.2fy + %.2f = 0)", A, B, C);
    }

    public static void main(String[] args) {
        // Создаем массив прямых
        Line[] lines = {
                new Line(1, -1, -3),
                new Line(2, -2, 5),
                new Line(3, 3, -6),
                new Line(0, 1, -4),  // Горизонтальная
                new Line(1, 0, -5),  // Вертикальная
                new Line(4, -4, 8),  // Параллельна первой
        };

        // Группируем параллельные прямые
        List<List<Line>> parallelGroups = new ArrayList<>();
        Set<Line> visited = new HashSet<>();

        for (int i = 0; i < lines.length; i++) {
            if (visited.contains(lines[i])) continue;

            List<Line> group = new ArrayList<>();
            group.add(lines[i]);
            for (int j = i + 1; j < lines.length; j++) {
                if (areParallel(lines[i], lines[j])) {
                    group.add(lines[j]);
                }
            }
            if (group.size() > 1) {
                parallelGroups.add(group);
                visited.addAll(group);
            }
        }

        // Вывод групп параллельных прямых
        for (int i = 0; i < parallelGroups.size(); i++) {
            System.out.println("Группа " + (i + 1) + ":");
            for (Line line : parallelGroups.get(i)) {
                System.out.println("  " + line);
            }
        }

        // Нахождение точек пересечения с осями координат
        for (Line line : lines) {
            System.out.println(line);
            System.out.println("  Пересечение с осью X: " + line.intersectionWithXAxis());
            System.out.println("  Пересечение с осью Y: " + line.intersectionWithYAxis());
        }

        // Нахождение точек пересечения каждой пары прямых
        System.out.println("\nТочки пересечения прямых:");
        for (int i = 0; i < lines.length; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                System.out.println(lines[i] + " и " + lines[j] + ": " + intersection(lines[i], lines[j]));
            }
        }
    }
}
