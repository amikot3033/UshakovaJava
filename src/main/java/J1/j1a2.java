package J1;

import java.util.Arrays;

public class j1a2 {

    public static void main(String[] args) {
        //2. Отобразить в окне консоли аргументы командной строки в обратном порядке.
        String newarguments = "";

        for(int i = args.length - 1; i >= 0; i--) {
            String lastword = args[i];
            newarguments = newarguments + lastword + ' ';
            System.out.println(lastword.toString());
        }
        System.out.println((Arrays.toString(args)));
        System.out.println(newarguments);
    }
}
