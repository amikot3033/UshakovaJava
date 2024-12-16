package J1;

public class j1a3 {

    public static void main(String[] args) {
        //Вывести заданное количество случайных чисел с переходом и без перехода
        //на новую строку.

        int N = Integer.parseInt(args[0]);

        String out = "";
        for(int i = 0; i < N; i++) {
            int a = (int)(Math.random()*100);
            System.out.println(a);
            out = out + a + ' ';
        }
        System.out.println(out);
    }
}
