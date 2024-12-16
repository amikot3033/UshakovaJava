package J1;

public class j1a5 {

    public static void main(String[] args) {
        //Ввести целые числа как аргументы командной строки, подсчитать их суммы и произведения.
        // Вывести результат на консоль.
        int sum = 0;
        int mult = 1;
        for(int i = args.length - 1; i >= 0; i--) {
            sum = sum + Integer.parseInt(args[i]);
            mult = mult * Integer.parseInt(args[i]);
        }
        System.out.println("sum = " + sum + " multiplication = " + mult);
    }
}
