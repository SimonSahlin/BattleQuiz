import java.io.IOException;
import java.util.Scanner;

public class HelpMenu {
    HandleFile handleFile = new HandleFile();

    public void menu() throws IOException, ClassNotFoundException {
        System.out.println("-- HELP MENU --");
        System.out.println("1 - Show all questions");
        System.out.println("2 - Add question");
        System.out.println("3 - Remove question");
        System.out.println("4 - Edit question");
        System.out.println("0 - Go back to Main menu");

        System.out.println("Välj från menyn med tangentbordet och avsluta med Enter:");
        String choice = null;
        Scanner UserInputMenu = new Scanner(System.in);


        do {
            choice = UserInputMenu.nextLine();
            switch (choice) {
                case "1":
                    handleFile.showAllQuestions();
                    break;

                case "2":
                    handleFile.addQuestion();

                    break;

                case "3":
                    handleFile.removeQuestion();
                    break;

                case "4":
                    handleFile.editQuestion();
                    break;

                case "99":
                    handleFile.initSetUp();

                case "0":

                    break;

                default:
                    System.out.println("välj rätt siffra");
            }
        }while(!choice.equals("0"));

    }


}
