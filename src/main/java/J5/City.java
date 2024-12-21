package J5;

import java.util.ArrayList;
import java.util.List;

public class City {

    private String name;
    private List<Place> places = new ArrayList<>();

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Place addPlace(String type, String name){
        Place place = new Place(type, name);
        places.add(place);
        return place;
    }

    public  void printPlace(){

        places.stream().map(Place::getType).distinct().forEach(type ->{
            List<Place> filtredPlaces = places.stream()
                    .filter(place -> place.getType().equals(type)).toList();

            System.out.println("\n" + type + ": " + filtredPlaces.size());

            filtredPlaces.forEach(place -> System.out.println(place.getType() + " " + place.getName()));
        });
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

        city.addPlace("Улица", "Улица");
        city.addPlace("Проспект", "Мира");
        city.addPlace("Улица", "Ленина");
        city.addPlace("Площадь", "Победы");

        System.out.println("Город: " + city.getName());
        city.printPlace();
    }
}

