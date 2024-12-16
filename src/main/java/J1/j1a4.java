package J1;

public class j1a4 {

    public static void main(String[] args) {
        //Ввести пароль из командной строки и сравнить его со строкой-образцом
        String origin = "helloworld";
        String pass = args[0];
        System.out.println(origin);
        System.out.println(pass);
        if (pass.equals(origin))
        {
            System.out.println("rigth");
        }
        else
        {
            System.out.println("wrong");
        }
    }
}
