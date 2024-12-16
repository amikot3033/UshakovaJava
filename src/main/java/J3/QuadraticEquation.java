package J3;// 3. Класс Квадратное уравнение

class QuadraticEquation {
    private double a;
    private double b;
    private double c;

    public QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double[] findRoots() {

        if (a == 0){
            return new double[] {};
        }
        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            return new double[] {};
        } else if (discriminant == 0) {
            return new double[] {-b / (2 * a) };
        } else {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            return new double[] { root1, root2 };
        }
    }

    public double findVertex() {

        return -b / (2 * a);

    }

    public String findIntervals() {
        double vertex = findVertex();
        if (a > 0) {
            return "Убывание: (-∞, " + vertex + "), Возрастание: (" + vertex + ", ∞)";
        } else if (a == 0){
            if (b > 0) {
                return "Функция является линейным уравнением. Функция возрастает";
            }
            else if (b < 0) {
                return "Функция является линейным уравнением. Функция убывает";
            }
            else{
                return "Функция является линейным уравнением. Функция монотонна";
            }
        } else {
            return "Возрастание: (-∞, " + vertex + "), Убывание: (" + vertex + ", ∞)";
        }
    }

    public QuadraticEquation add(QuadraticEquation other) {
        double newA = this.a  + other.a;
        double newB = this.b  + other.b;
        double newC = this.c  + other.c;
        return new QuadraticEquation(newA, newB, newC);
    }

    public QuadraticEquation subtract(QuadraticEquation other) {
        double newA = this.a  - other.a;
        double newB = this.b  - other.b;
        double newC = this.c  - other.c;
        return new QuadraticEquation(newA, newB, newC);
    }

    public QuadraticEquation multiply(QuadraticEquation other) {
        double newA = this.a  * other.a;
        double newB = this.b  * other.b;
        double newC = this.c  * other.c;
        return new QuadraticEquation(newA, newB, newC);
    }

    public QuadraticEquation divide(QuadraticEquation other) {
        if (other.a != 0 & other.b != 0 & other.c != 0) {
            double newA = this.a / other.a;
            double newB = this.b / other.b;
            double newC = this.c / other.c;
            return new QuadraticEquation(newA, newB, newC);
        }
        else {
            System.out.println("Коэффициенты делителя не могут быть равны 0");
            return null;
        }
    }


    @Override
    public String toString() {
        return a + "x^2 + " + b + "x + " + c;
    }

    public static void main(String[] args) {
        QuadraticEquation[] equations = {
                new QuadraticEquation(0, -3, 2),
                new QuadraticEquation(1, 2, 1),
                new QuadraticEquation(2, 5, -3),
                new QuadraticEquation(1, 0, -4)
        };

        System.out.println("Уравнения: ");
        for (QuadraticEquation eq : equations) {
            System.out.println(eq);
        }

        double maxRoot = Double.NEGATIVE_INFINITY;
        double minRoot = Double.POSITIVE_INFINITY;

        for (QuadraticEquation eq : equations) {
            double[] roots = eq.findRoots();
            for (double root : roots) {
                if (root > maxRoot) {
                    maxRoot = root;
                }
                if (root < minRoot) {
                    minRoot = root;
                }
            }
        }

        System.out.println("Корни уравнений: ");



        for (QuadraticEquation eq : equations) {
            double[] roots = eq.findRoots();
            System.out.println(eq);
            for (double root : roots) {
                System.out.println(root);
            }
        }

        System.out.println("Максимальный корень: " + maxRoot);
        System.out.println("Минимальный корень: " + minRoot);

        System.out.println("Сложение");
        QuadraticEquation add = equations[1].add(equations[0]);
        System.out.println(equations[1] + " + " + equations[0] + " = " + add);

        System.out.println("Вычитание");
        QuadraticEquation sub = equations[1].subtract(equations[0]);
        System.out.println(equations[1] + " - " + equations[0] + " = " + sub);

        System.out.println("Умножение");
        QuadraticEquation mult =equations[0].multiply(equations[1]);
        System.out.println(equations[0] + " * " + equations[1] + " = " + mult);

        System.out.println("Деление");
        QuadraticEquation div = equations[0].divide(equations[1]);
        System.out.println(equations[0] + " / " + equations[1] + " = " + div);

        System.out.println("Интервалы уравнения" + equations[0]);
        System.out.println(equations[0].findIntervals());

        System.out.println("Интервалы уравнения" + equations[1]);
        System.out.println(equations[1].findIntervals());
    }
}