import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/*Ni ska implementera den konkreta klassen Player som extendar Person och lägger
        till hantering av variablerna score och played_games. Varje gång spelaren spelar
        ett spel ska played_games ökas på med 1 och om användaren vinner matchen ska score
        ökas med 1.

 */
public class Player extends Person {

    HandleFile hf = new HandleFile();
    LinkedList questionsFromSer = hf.readBackSer();
    LinkedList gameQuestions = new LinkedList();
    LinkedList<String> answer1 = new LinkedList();
    LinkedList<String> answer2 = new LinkedList();

    private int score;
    private int played_games;

    public Player() throws IOException, ClassNotFoundException {

    }


    public Player(String name, int age, String eMail, int score, int played_games) throws IOException, ClassNotFoundException {
        super(name, age, eMail);
        this.score = score;
        this.played_games = played_games;
    }


    public String toString() {
        return String.valueOf(score) + String.valueOf(played_games) + (super.toString());
    }


    //This method will ask 6 random questions taken from a .txt-file with serialised questions.
    public void randomizeQuestions() throws IOException, ClassNotFoundException { //Simon

        int allRandomQuestions = 6;//Change to dynamic. Length of the list of all questions.
        for (int i = 0; i < 6/*length of List, the list are from a .txt-file of serialiced objects. 6 radomized questions*/; i++) {
            int randNr = (int) (Math.random() * allRandomQuestions);
            gameQuestions.add(questionsFromSer.get(randNr)); //Add random questions to a new list of questions which will be asked to the players.
            questionsFromSer.remove(randNr); // Removing the index of the random number (removing the questions so not the same question is asked twice)
            allRandomQuestions -= 1; //
        }
    }

    //This method does ask the questions from the 6 randomized questions and stores answers in one LinkedList per player.
    public void gameProgress() { //Simon
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) { //Loops 3 times, once for each question
            System.out.println("Här kommer fråga " + (i + 1)); //Print which question is asked(1,2,3,4...)
            //Start timer.
            System.out.println(((QuestionBluePrint) gameQuestions.get(i)).question+"?"); //Asking said question.
            System.out.println(((QuestionBluePrint) gameQuestions.get(i)).options.toString().replace("*", "")); //Showing options
            //End timer.
            for (int j = 0; j < 1; j++) { //Loops 2 times for every once the upper loop loops.
                System.out.println("Spelare " + (j + 1) + " svarar: "); //Prints which player that are gonna answer
                //Start timer
                answer1.add(scanner.nextLine()); //Waiting for answer and stores it in a LinkedList called "answer".
                //End timer
            }
                for (int m = 0; m < 1; m++) {
                    System.out.println("Spelare " + (m + 2) + " svarar: "); //Prints which player that are gonna answer
                    System.out.println(((QuestionBluePrint) gameQuestions.get(i+3)).question+"?"); //Asking said question.
                    System.out.println(((QuestionBluePrint) gameQuestions.get(i+3)).options.toString().replace("*", "")); //Showing options
                    //Start timer
                    answer2.add(scanner.nextLine()); //Waiting for answer and stores it in a LinkedList called "answer".
                    //End timer
                }
        }
        System.out.println(answer1 + " " + answer2);
    }
            //public void trackPlayedGames(){

            //}

    public void trackScore(){

        if (answer1.get(0).equals("A") && ((QuestionBluePrint) gameQuestions.get(0)).options.get(0).contains("*")){
               setScore(+1);
        }

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPlayed_games() {
        return played_games;
    }

    public void setPlayed_games(int played_games) {
        this.played_games = played_games;
    }

}








