package J7;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.io.*;
import java.util.*;

public class FileCounter {


    public static int countWords(ArrayList<String> list, String word){
    return Collections.frequency(list, word);
    }

    public static HashMap UniqueFric(ArrayList<String> list){
        List<String> visited = new ArrayList<>();
        HashMap<String, Integer> freq = new HashMap<>();
        for (String current: list)
        {
            if (!visited.contains(current)){
                visited.add(current);
            }
        }
        for (String item: visited)
        {
            freq.put(item, Collections.frequency(list, item));
        }
        return freq;
    }

    public static String printRes(ArrayList list, String word){
        HashMap<String, Integer> freq = UniqueFric(list);
        String res = "";
        if (freq.get(word) != null){
            return res + word + " - " + freq.get(word);
        }
        else {
            return res + word + " - " + 0;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("src/main/java/J7/file.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String str;

        ArrayList<String> list = new ArrayList<String>();
        while(true){
            try {
                if (!((str = reader.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(!str.isEmpty()){
                for (String word: str.split(" ") )
                list.add(word);
            }
        }

        HashMap<String, Integer> freq = UniqueFric(list);

        String[] words = new String[]{"hello", "world", "bye"};
        for (String word: words){
            System.out.println(printRes(list, word));
        }
    }

}
