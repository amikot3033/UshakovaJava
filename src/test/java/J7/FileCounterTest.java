package J7;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static J7.FileCounter.UniqueFric;
import static J7.FileCounter.PrintRes;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileCounterTest {

    @Test
    void tetsUnuqie() throws IOException {
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

        assertEquals("hello - 4", PrintRes(list, "hello"));
        assertEquals("bye - 0", PrintRes(list, "bye"));
    }
}
