import java.io.IOException;
import java.util.*;

public class SlangWordDictionary {
    private HashMap<String, String>  wordDict;
    private final LinkedList<String> history = new LinkedList<>();

    private static final String slangFile = "src/data/slang.txt";
    private static final String slangFile_Added = "src/data/slang_added.txt";
    private static final String slangFile_Edited = "src/data/slang_edited.txt";
    private static final String slangFile_Deleted = "src/data/slang_deleted.txt";

    public SlangWordDictionary() throws IOException {
        wordDict = HandleFile.readFile(slangFile);
    }

    public void findBySlangWord(String wordFind) {
        String def = wordDict.get(wordFind.toLowerCase(Locale.ROOT));
        if (def != null) {
            String[] params = def.split("\\| ");
            System.out.println("Definition of " + wordFind + ": ");
            for (String param : params) System.out.println(param);

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
        }
        history.add(word);

    }

    public void showHistory() {
        if(history.isEmpty())
            System.out.println("History is empty.");
        else {
            System.out.println("History of finding: ");
            for (String line : history) {
                System.out.println(line);
            }
        }
    }

    public void addSlangWord(Scanner scanner) throws IOException {
        System.out.println("Enter new slang word you want to add: ");
        String newSlg = scanner.nextLine();
        String def = wordDict.get(newSlg.toLowerCase(Locale.ROOT));
        while (def != null){
            System.out.println("Entered string has been duplicated. Please enter another.");
            newSlg = scanner.nextLine();
            def = wordDict.get(newSlg.toLowerCase(Locale.ROOT));
        }
        System.out.println("Enter new definition of this word: ");
        String newDef = scanner.nextLine();
        wordDict.put(newSlg, newDef);
        HandleFile.writeHashmapToFile(slangFile_Added, wordDict);
    }

    public void editSlangWord(Scanner scanner) throws IOException {
        System.out.println("Enter word you want to edit.");
        String newSlg = scanner.nextLine();

        String def = wordDict.get(newSlg.toLowerCase(Locale.ROOT));
        while(def == null){
            System.out.println("Entered word has not been found. Enter another slang word.");
            newSlg = scanner.nextLine();
            def = wordDict.get(newSlg.toLowerCase(Locale.ROOT));
        }
        System.out.println("Enter new definition of this word: ");
        def = scanner.nextLine();
        wordDict.put(newSlg, def);
        HandleFile.writeHashmapToFile(slangFile_Edited, wordDict);
    }

    public void deleteSlangWord(Scanner scanner) throws IOException {
        System.out.println("Enter word you want to delete.");
        String newSlg = scanner.nextLine();
        String def = wordDict.get(newSlg.toLowerCase(Locale.ROOT));
        while (def == null) {
            System.out.println("Entered word has not been found. Enter another slang word.");
            newSlg = scanner.nextLine();
            def = wordDict.get(newSlg.toLowerCase(Locale.ROOT));
        }
        System.out.println("Do you want to delete this word? (Y/N)");
        String option = scanner.nextLine();
        if (option.toLowerCase(Locale.ROOT).equals("y")) {
            wordDict.remove(newSlg);
            String findDef = wordDict.get(newSlg.toLowerCase(Locale.ROOT));
            if (findDef == null) {
                System.out.println("Successfully deleted " + newSlg + ".");
                HandleFile.writeHashmapToFile(slangFile_Deleted, wordDict);
            } else System.out.println("Failed to delete " + newSlg);
        }
        else System.out.println("This word has not been deleted.");
    }

    public void reset() throws IOException {
        wordDict = HandleFile.readFile(slangFile);
        if(!wordDict.isEmpty()) System.out.println("Successfully reset slang words dictionary.");
        else System.out.println("Failed to reset slang words dictionary.");
    }


    public void random1SlangWord(){
        Random generator = new Random();
        Object[] keys = wordDict.keySet().toArray();
        Object randomKey = keys[generator.nextInt(keys.length)];

        System.out.println("Random word on this day:\n"+ randomKey + ": " + wordDict.get(randomKey.toString()));
    }

    public void quizRandomSlangWord(Scanner scanner){
        Random generator = new Random();
        Object[] keys = wordDict.keySet().toArray();
        Object randomKey1 = keys[generator.nextInt(keys.length)];
        Object randomKey2 = keys[generator.nextInt(keys.length)];
        Object randomKey3 = keys[generator.nextInt(keys.length)];
        Object randomKey4 = keys[generator.nextInt(keys.length)];

        List<Object> answerList =  Arrays.asList(randomKey1, randomKey2, randomKey3, randomKey4);

        Object answer = answerList.get(generator.nextInt(answerList.size()));
        //System.out.println(wordDict.get(answer));
        String again ;
        do {
            again = "n";
        System.out.println("What is the definition of '" + answer + "'?");
        System.out.println("A: " + wordDict.get(randomKey1.toString()));
        System.out.println("B: " + wordDict.get(randomKey2.toString()));
        System.out.println("C: " + wordDict.get(randomKey3.toString()));
        System.out.println("D: " + wordDict.get(randomKey4.toString()));

        System.out.println("Your answer: ");
            String choose = scanner.nextLine().toLowerCase(Locale.ROOT);
            switch (choose) {
                case "a":
                    // randomKey1
                    if (randomKey1.equals(answer)) {
                        System.out.println("Your answer was CORRECT.");
                        System.out.println(answer + ": "+wordDict.get(answer.toString()));
                    }
                    else {
                        System.out.println("Your answer was INCORRECT.");
                        System.out.println("The correct answer: " + wordDict.get(answer.toString()));
                    }
                    break;
                case "b":
                    if (randomKey2.equals(answer)) {
                        System.out.println("Your answer was CORRECT.");
                        System.out.println(answer + ": "+wordDict.get(answer.toString()));
                    }
                    else {
                        System.out.println("Your answer was INCORRECT.");
                        System.out.println("The correct answer: " + wordDict.get(answer.toString()));
                    }
                    break;
                case "c":
                    if (randomKey3.equals(answer)) {
                        System.out.println("Your answer was CORRECT.");
                        System.out.println(answer + ": "+wordDict.get(answer.toString()));
                    }
                    else {
                        System.out.println("Your answer was INCORRECT.");
                        System.out.println("The correct answer: " + wordDict.get(answer.toString()));
                    }
                    break;
                case "d":
                    if (randomKey4.equals(answer)) {
                        System.out.println("Your answer was CORRECT.");
                        System.out.println(answer + ": "+wordDict.get(answer.toString()));
                    }
                    else {
                        System.out.println("Your answer was INCORRECT.");
                        System.out.println("The correct answer: " + wordDict.get(answer.toString()));
                    }
                    break;
                default:
                    System.out.println("Your answer was not SUITABLE.\nDo you want to choose again? (y/n)");
                    again = scanner.nextLine().toLowerCase(Locale.ROOT);
                    break;
            }
        }while(again.equals("y"));
    }


}
