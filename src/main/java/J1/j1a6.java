package J1;

public class j1a6 {

    public static void main(String[] args) {
//        Вывести фамилию разработчика, дату и время получения задания, а также
//        дату и время сдачи задания.
        System.out.println("Ushakova");
        System.out.println("2024-09-13T22:06:15.717088300");
        java.time.LocalDateTime currentDateTime = java.time.LocalDateTime.now();
        System.out.println(currentDateTime);
    }
}
