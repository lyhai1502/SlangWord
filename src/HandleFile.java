import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;

public class HandleFile {
    public static HashMap<String, String> readFile(String filename) throws IOException {
        HashMap<String, String> hashMap = new HashMap<>();
        FileInputStream fis = null;
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;
        try {
            fis = new FileInputStream(filename);
            reader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(reader);
            String line = null;
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

}
