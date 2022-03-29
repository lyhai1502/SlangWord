import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static void showMenu() {
        System.out.println("================ MENU =========================");
        System.out.println("0: Exit");
        System.out.println("1: Find by slang word");
        System.out.println("2: Find by definition");
        System.out.println("3: Show history finding");
        System.out.println("4: Add new slang word");
        System.out.println("5: Edit slang word");
        System.out.println("6: Delete 1 slang word");
        System.out.println("7: Reset all slang word");
        System.out.println("8: Random 1 slang word");
        System.out.println("9: Quiz: Show 1 random slang word with 4 answers");
        System.out.println("10: Quiz: Show 1 definition with 4 answers");
        System.out.println("===============================================");
    }
    private  static void stopProgram() throws IOException {
        System.out.println("Press enter to continue.");
        System.in.read();
    }

    public static void main(String[] args) throws IOException {
        SlangWordDictionary wordDictionary = new SlangWordDictionary();
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            showMenu();
            System.out.println("Input your option (0-10): ");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 0 ->
                        // Exit
                        System.out.println("You have exited the program.");
                case 1 -> {
                    // Find by slang word
                    System.out.println("Enter slang word: ");
                    String sword = scanner.nextLine();
                    wordDictionary.findBySlangWord(sword);
                    stopProgram();
                }
                case 2 -> {
                    // Find by definition
                    System.out.println("Enter definition: ");
                    String def = scanner.nextLine();
                    wordDictionary.findByDefinition(def);
                    stopProgram();
                }
                case 3 -> {
                    // Show history
                    wordDictionary.showHistory();
                    stopProgram();
                }
                case 4 ->{
                    // Add slang word
                    wordDictionary.addSlangWord(scanner);
                    stopProgram();
                }
                case 5 -> {
                    // Edit slang word
                    wordDictionary.editSlangWord(scanner);
                    stopProgram();
                }
                case 6 -> {
                    // Delete slang word
                    wordDictionary.deleteSlangWord(scanner);
                    stopProgram();
                }
                case 7 -> {
                    // Reset slang word
                    wordDictionary.reset();
                    stopProgram();
                }
                case 8 -> {
                    // Random slang word
                    wordDictionary.random1SlangWord();
                    stopProgram();
                }
                case 9 -> {
                    // Show random slang word with 4 answer
                    wordDictionary.quizRandomSlangWord(scanner);
                    stopProgram();
                }
                case 10 -> {
                    // Show random definition with 4 answer
                    wordDictionary.quizRandomDefinition(scanner);
                    stopProgram();
                }
                default -> {
                    System.out.println("Your choose was not SUITABLE.");
                    stopProgram();

                }
            }
        } while (option != 0);
    }
}
