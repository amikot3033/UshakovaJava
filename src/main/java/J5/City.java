package J5;

public class City {

    private String name;

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public class Place {
        private String type;
        private String name;

        public Place(String type, String name) {
            this.type = type;
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return type + ": " + name;
        }
    }

    public static void main(String[] args) {
        City city = new City("Коноха");

        City.Place avenue = city.new Place("Проспект", "Мира");
        City.Place street = city.new Place("Улица", "Ленина");
        City.Place square = city.new Place("Площадь", "Победы");

        System.out.println("Город: " + city.getName());
        System.out.println(avenue);
        System.out.println(street);
        System.out.println(square);
    }
}

