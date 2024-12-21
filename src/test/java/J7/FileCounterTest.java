package J7;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static J7.FileCounter.UniqueFric;
import static J7.FileCounter.printRes;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileCounterTest {

    @Test
    void tetsUnuqie(){
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

        assertEquals("hello - 4", printRes(list, "hello"));
        assertEquals("bye - 0", printRes(list, "bye"));
    }
}
