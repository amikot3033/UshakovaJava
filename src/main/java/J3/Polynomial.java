package J3;// 4. Класс Полином

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Polynomial {
    private List<Double> coefficients;

    public Polynomial(List<Double> coefficients) {
        this.coefficients = new ArrayList<>(coefficients);
    }

    public Polynomial add(Polynomial other) {
        int maxDegree = Math.max(this.coefficients.size(), other.coefficients.size());
        List<Double> resultCoefficients = new ArrayList<>(Collections.nCopies(maxDegree, 0.0));

        for (int i = 0; i < this.coefficients.size(); i++) {
            resultCoefficients.set(i, resultCoefficients.get(i) + this.coefficients.get(i));
        }
        for (int i = 0; i < other.coefficients.size(); i++) {
            resultCoefficients.set(i, resultCoefficients.get(i) + other.coefficients.get(i));
        }

        return new Polynomial(resultCoefficients);
    }

    // Метод вычитания полиномов
    public Polynomial subtract(Polynomial other) {
        int maxDegree = Math.max(this.coefficients.size(), other.coefficients.size());
        List<Double> resultCoefficients = new ArrayList<>(Collections.nCopies(maxDegree, 0.0));

        for (int i = 0; i < this.coefficients.size(); i++) {
            resultCoefficients.set(i, resultCoefficients.get(i) + this.coefficients.get(i));
        }
        for (int i = 0; i < other.coefficients.size(); i++) {
            resultCoefficients.set(i, resultCoefficients.get(i) - other.coefficients.get(i));
        }

        return new Polynomial(resultCoefficients);
    }

    // Метод умножения полиномов
    public Polynomial multiply(Polynomial other) {
        int degree1 = this.coefficients.size();
        int degree2 = other.coefficients.size();
        List<Double> resultCoefficients = new ArrayList<>(Collections.nCopies(degree1 + degree2 - 1, 0.0));

        for (int i = 0; i < degree1; i++) {
            for (int j = 0; j < degree2; j++) {
                resultCoefficients.set(i + j,
                        resultCoefficients.get(i + j) + this.coefficients.get(i) * other.coefficients.get(j));
            }
        }

        return new Polynomial(resultCoefficients);
    }

    // Метод деления полиномов (деление с остатком)
    public Polynomial[] divide(Polynomial divisor) {
        if (divisor.coefficients.size() == 1 && divisor.coefficients.get(0) == 0) {
            throw new ArithmeticException("Деление на ноль невозможно.");
        }

        List<Double> dividendCoefficients = new ArrayList<>(this.coefficients);
        List<Double> quotientCoefficients = new ArrayList<>(Collections.nCopies(
                Math.max(0, dividendCoefficients.size() - divisor.coefficients.size() + 1), 0.0));

        while (dividendCoefficients.size() >= divisor.coefficients.size()) {
            int currentDegree = dividendCoefficients.size() - divisor.coefficients.size();
            double scale = dividendCoefficients.get(dividendCoefficients.size() - 1) /
                    divisor.coefficients.get(divisor.coefficients.size() - 1);

            quotientCoefficients.set(currentDegree, scale);

            for (int i = 0; i < divisor.coefficients.size(); i++) {
                int index = currentDegree + i;
                dividendCoefficients.set(index, dividendCoefficients.get(index) -
                        scale * divisor.coefficients.get(i));
            }

            while (dividendCoefficients.size() > 0 &&
                    Math.abs(dividendCoefficients.get(dividendCoefficients.size() - 1)) < 1e-9) {
                dividendCoefficients.remove(dividendCoefficients.size() - 1);
            }
        }

        return new Polynomial[]{
                new Polynomial(quotientCoefficients), // Частное
                new Polynomial(dividendCoefficients)  // Остаток
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < coefficients.size(); i++) {
            if (coefficients.get(i) != 0) {
                if (sb.length() > 0 && coefficients.get(i) > 0) {
                    sb.append(" + ");
                }
                sb.append(coefficients.get(i)).append("x^").append(i);
            }
        }
        return sb.length() > 0 ? sb.toString() : "0";
    }

    public static void main(String[] args) {
        Polynomial[] polynomials = {
                new Polynomial(Arrays.asList(1.0, 2.0, 3.0)), // 3x^2 + 2x + 1
                new Polynomial(Arrays.asList(0.0, 1.0, 4.0, 5.0)), // 5x^3 + 4x^2 + x
                new Polynomial(Arrays.asList(2.0, -1.0)), // -x + 2
                new Polynomial(Arrays.asList(3.0)) // 3
    };

        System.out.println("Многочлены:");
        for (Polynomial poly : polynomials) {
            System.out.println(poly);
        }

        Polynomial sum = new Polynomial(Collections.singletonList(0.0)); // Нулевой полином
        for (Polynomial poly : polynomials) {
            sum = sum.add(poly);
        }

        System.out.println("Сумма всех многочленов: " + sum);

        Polynomial p1 = polynomials[0];
        Polynomial p2 = polynomials[1];

        System.out.println("p1: " + p1);
        System.out.println("p2: " + p2);
        System.out.println("Сумма: " + p1.add(p2));
        System.out.println("Разность: " + p1.subtract(p2));
        System.out.println("Произведение: " +  p1.multiply(p2));

        Polynomial[] div = p1.divide(p2);

        System.out.println("Частное: " + div[0]);
        System.out.println("Остаток: " + div[1]);
    }
}