package J10.B;

import java.io.*;

class TrainFileConnector {
    public void saveTrainToFile(Train train, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(train);
            System.out.println("Данные поезда успешно сохранены в файл: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Train loadTrainFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Train) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}