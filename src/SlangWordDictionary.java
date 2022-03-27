import java.io.IOException;
import java.util.*;

public class SlangWordDictionary {
    private final HashMap<String, String>  wordDict;
    private final LinkedList<String> history = new LinkedList<>();

    private static final String slangFile = "src/data/slang.txt";

    public SlangWordDictionary() throws IOException {
        wordDict = HandleFile.readFile(slangFile);
    }

    public void findBySlangWord(String wordFind) {
        String def = wordDict.get(wordFind.toLowerCase(Locale.ROOT));
        if (def != null) {
            System.out.println("Definition of " + wordFind + ": " + def);
        } else {
            System.out.println("Can't found " + wordFind);
        }
        history.add(wordFind);
    }

}
