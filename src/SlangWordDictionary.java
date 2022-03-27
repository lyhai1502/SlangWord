import java.io.IOException;
import java.util.*;

public class SlangWordDictionary {
    private HashMap<String, String> wordDict;
    private LinkedList<String> history = new LinkedList<>();

    private static final String slangFile = "src/slang.txt";

    public SlangWordDictionary() throws IOException {
        wordDict = HandleFile.readFile(slangFile);
    }

}
