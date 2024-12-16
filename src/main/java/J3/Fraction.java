package J3;// 1. Класс Дробь (Рациональная дробь)

class Fraction {
    private int numerator; // Числитель
    private int denominator; // Знаменатель

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    // Метод для упрощения дроби
    private void simplify() {
        int gcd = gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public Fraction add(Fraction other) {
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    public Fraction subtract(Fraction other) {
        int newNumerator = this.numerator * other.denominator - other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    public Fraction multiply(Fraction other) {
        return new Fraction(this.numerator * other.numerator, this.denominator * other.denominator);
    }

    public Fraction divide(Fraction other) {
        if (other.numerator == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return new Fraction(this.numerator * other.denominator, this.denominator * other.numerator);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }



    public static void main(String[] args) {
        Fraction[] fractions = {
                new Fraction(1, 2),
                new Fraction(2, 3),
                new Fraction(3, 4),
                new Fraction(4, 5)
        };

        System.out.println("Исходный массив дробей:");
        for (Fraction fraction : fractions) {
            System.out.println(fraction);
        }

        modifyEvenIndexFractions(fractions);

        System.out.println("Изменение каждого элемент массива с четным индексом путем добавления следующего за ним элемента.");
        for (Fraction fraction : fractions) {
            System.out.println(fraction);
        }
        System.out.println("Вычитание");
        Fraction sub = fractions[1].subtract(fractions[0]);
        System.out.println(fractions[1] + " - " + fractions[0] + " = " + sub);

        System.out.println("Умножение");
        Fraction mult = fractions[0].multiply(fractions[1]);
        System.out.println(fractions[0] + " * " + fractions[1] + " = " + mult);

        System.out.println("Деление");
        Fraction div = fractions[0].divide(fractions[1]);
        System.out.println(fractions[0] + " / " + fractions[1] + " = " + div);
    }

    public static void modifyEvenIndexFractions(Fraction[] fractions) {
        for (int i = 0; i < fractions.length - 1; i += 2) {
            fractions[i] = fractions[i].add(fractions[i + 1]);
        }
    }


}
