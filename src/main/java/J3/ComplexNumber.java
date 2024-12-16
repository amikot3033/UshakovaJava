package J3;

// 2. Класс Комплексное число
class ComplexNumber {
    private double real; // Действительная часть
    private double imaginary; // Мнимая часть

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(this.real + other.real, this.imaginary + other.imaginary);
    }

    public ComplexNumber sum(ComplexNumber other) {
        double newReal = this.real + other.real;
        double newImaginary = other.imaginary - this.imaginary;
        return new ComplexNumber(newReal, newImaginary);
    }

    public ComplexNumber subtract(ComplexNumber other) {
        double newReal = this.real - other.real;
        double newImaginary = + this.imaginary - other.imaginary;
        return new ComplexNumber(newReal, newImaginary);
    }

    public ComplexNumber multiply(ComplexNumber other) {
        double newReal = this.real * other.real - this.imaginary * other.imaginary;
        double newImaginary = this.real * other.imaginary + this.imaginary * other.real;
        return new ComplexNumber(newReal, newImaginary);
    }

    public ComplexNumber divide(ComplexNumber other) {
        if (other.real == 0 && other.imaginary == 0) {
            throw new ArithmeticException("Деление на ноль невозможно.");
        }

        double denominator = other.real * other.real + other.imaginary * other.imaginary;
        double newReal = (real * other.real + imaginary * other.imaginary) / denominator;
        double newImaginary = (imaginary * other.real - real * other.imaginary) / denominator;

        return new ComplexNumber(newReal, newImaginary);
    }

    @Override
    public String toString() {
        return real + " + " + imaginary + "i";
    }

    public static void main(String[] args) {
        ComplexNumber[] complexNumbers = {
                new ComplexNumber(1, 1),
                new ComplexNumber(2, -1),
                new ComplexNumber(-3, 4),
                new ComplexNumber(0, -2)};


        System.out.println("Исходный массив:");
        for (ComplexNumber cn : complexNumbers) {
            System.out.println(cn);
        }

        ComplexNumber sum = sumComplexNumbers(complexNumbers);
        System.out.println("сумма всех чисел: " + sum);

        ComplexNumber product = multiplyComplexNumbers(complexNumbers);
        System.out.println("умножение всех чисел: " + product);

        System.out.println("Сложение");
        ComplexNumber add = complexNumbers[1].sum(complexNumbers[0]);
        System.out.println(complexNumbers[1] + " + " + complexNumbers[0] + " = " + add);

        System.out.println("Вычитание");
        ComplexNumber sub = complexNumbers[1].subtract(complexNumbers[0]);
        System.out.println(complexNumbers[1] + " - " + complexNumbers[0] + " = " + sub);

        System.out.println("Умножение");
        ComplexNumber mult =complexNumbers[0].multiply(complexNumbers[1]);
        System.out.println(complexNumbers[0] + " * " + complexNumbers[1] + " = " + mult);

        System.out.println("Деление");
        ComplexNumber div = complexNumbers[0].divide(complexNumbers[1]);
        System.out.println(complexNumbers[0] + " / " + complexNumbers[1] + " = " + div);
    }

    public static ComplexNumber sumComplexNumbers(ComplexNumber[] complexNumbers) {
        ComplexNumber result = new ComplexNumber(0, 0);
        for (ComplexNumber cn : complexNumbers) {
            result = result.add(cn);
        }
        return result;
    }

    public static ComplexNumber multiplyComplexNumbers(ComplexNumber[] complexNumbers) {
        ComplexNumber result = new ComplexNumber(1, 0);
        for (ComplexNumber cn : complexNumbers) {
            result = result.multiply(cn);
        }
        return result;
    }
}