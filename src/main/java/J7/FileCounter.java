package J7;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.io.*;
import java.util.*;

public class FileCounter {


    public static int CountWords(ArrayList<String> list, String word){
    return Collections.frequency(list, word);
    }

    public static Map UniqueFric(List<String> list){
        Set<String> visited = new HashSet<>(list);

        Map<String, Integer> freq = new HashMap<>();

        for (String item: visited)
        {
            freq.put(item, Collections.frequency(list, item));
        }
        return freq;
    }

    public static String PrintRes(List list, String word){
        Map<String, Integer> freq = UniqueFric(list);
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

        List<String> list = new ArrayList<String>();
        while((str = reader.readLine()) != null) {
            if(!str.isEmpty()){
                Collections.addAll(list, str.split(" "));
            }
        }

        Map<String, Integer> freq = UniqueFric(list);

        String[] words = new String[]{"hello", "world", "bye"};
        for (String word: words){
            System.out.println(PrintRes(list, word));
        }
    }

}
