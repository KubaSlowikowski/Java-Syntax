package Day12.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    private List<String> list = new ArrayList<>();

    public DataReader(String inputPath) {
        readFile(inputPath);
    }

    private void readFile(String inputPath) {
        try(BufferedReader fileReader = new BufferedReader(new FileReader(inputPath))) {
            while(true) {
                String s = fileReader.readLine();
                if(s!=null) {
                    list.add(s);
                }
                else break;
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAsList() {
        return list;
    }
}
