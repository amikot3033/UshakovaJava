package J7;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class FileCounter {

    public static int countWords(ArrayList<String> list, String word){
    return Collections.frequency(list, word);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("src/main/java/J7/file.txt"));
        String str;

        ArrayList<String> list = new ArrayList<String>();
        while((str = reader.readLine()) != null ){
            if(!str.isEmpty()){
                list.add(str);
            }}

        for (int i = 0; i < list.size(); i++)
        {
            if (countWords(list, list.get(i)) == 1){

                System.out.println(list.get(i) + " - " + Collections.frequency(list, list.get(i)));
            }
        }
    }



}
