import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;

public class HandleFile {

    // Phan doc file hashMap duoc tham khao tu Internet. Phan tram tham khao: 30%
    public static HashMap<String, String> readFile(String filename) throws IOException {
        HashMap<String, String> hashMap = new HashMap<>();
        FileInputStream fis;
        InputStreamReader reader;
        BufferedReader bufferedReader;
        try {
            fis = new FileInputStream(filename);
            reader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.isEmpty())
                    continue;
                String[] params = line.split("`");
                if (params.length >= 2) {
                    hashMap.put(params[0].toLowerCase(Locale.ROOT), params[1]);
                }
            }
            return hashMap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    public static void writeHashmapToFile(String filename, HashMap<String, String> dict) throws IOException {
        try (FileWriter writer = new FileWriter(filename);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            for (String key : dict.keySet()) {
                String line = String.join("`", key, dict.get(key));
                line += "\n";
                bufferedWriter.write(line);
            }
        }
    }

    // Phan doc file history nay tham khao tu Internet. Phan tram tham khao: 30%
    public static LinkedList<String> readHistory(String filename) throws IOException {
        LinkedList<String> history = new LinkedList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                history.add(line);
            }
        } catch (FileNotFoundException ex) {
            return history;
        }
        return history;
    }

    public static void writeHistory(String filename, LinkedList<String> history) throws IOException {
        try (FileWriter writer = new FileWriter(filename);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            for (String word : history) {
                bufferedWriter.write(word + "\n");
            }
        }
    }
}
