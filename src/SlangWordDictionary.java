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

    public void findByDefinition(String word) {
        LinkedList<String> list = new LinkedList<>();
        for (String key : wordDict.keySet()) {
            String lowerWord = word.toLowerCase(Locale.ROOT);
            String lowerParentWord = wordDict.get(key).toLowerCase(Locale.ROOT);
            if (lowerParentWord.contains(lowerWord))
                list.add(key);
        }

        if(list.isEmpty()) System.out.println("No slang word has a definition containing the string entered.");
        else {
            System.out.println("========== All slang words contain entered string: =========== ");
            for (String key : list) {
                System.out.println(key + ": " + wordDict.get(key));
            }
            history.add(word);
        }

    }

}
