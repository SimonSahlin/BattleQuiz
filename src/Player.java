import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/*Ni ska implementera den konkreta klassen Player som extendar Person och lägger
        till hantering av variablerna score och played_games. Varje gång spelaren spelar
        ett spel ska played_games ökas på med 1 och om användaren vinner matchen ska score
        ökas med 1.

 */
public class Player extends Person {

    HandleFile handleFile = new HandleFile();
    LinkedList<QuestionBluePrint> questionsFromSerFile = handleFile.readBackSer();
    LinkedList<QuestionBluePrint> gameQuestions = new LinkedList();
    LinkedList<String> answerPlayer1 = new LinkedList();
    LinkedList<String> answerPlayer2 = new LinkedList();
    LinkedList<String> cheatSheet = new LinkedList<>();
    HashMap answerConverter = new HashMap();



    private int score; //<-- bort med skiten?
    public int player1Score = 0;
    public int player2Score = 0;
    private int counterPlayedGames;

    public Player() throws IOException, ClassNotFoundException {

    }


    public Player(String name, int age, String eMail, int score, int played_games) throws IOException, ClassNotFoundException {
        super(name, age, eMail);
        this.score = score;
        this.counterPlayedGames = played_games;
    }


    public String toString() {
        return String.valueOf(score) + String.valueOf(counterPlayedGames) + (super.toString());
    }


    //This method will ask 6 random questions taken from a .txt-file with serialised questions.
    public void randomizeQuestions() throws IOException, ClassNotFoundException { //Simon

        int allRandomQuestions = 6;//Change to dynamic. Length of the list of all questions.
        for (int i = 0; i < 6/*length of List, the list are from a .txt-file of serialiced objects. 6 radomized questions*/; i++) {
            int randNr = (int) (Math.random() * allRandomQuestions);
            gameQuestions.add(questionsFromSerFile.get(randNr)); //Add random questions to a new list of questions which will be asked to the players.
            questionsFromSerFile.remove(randNr); // Removing the index of the random number (removing the questions so not the same question is asked twice)
            allRandomQuestions -= 1; //
        }
    }

    //This method does ask the questions from the 6 randomized questions and stores answers in one LinkedList per player.
    public void gameProgress() { //Simon
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) { //Loops 3 times, once for each question
            System.out.println("Här kommer fråga " + (i + 1)); //Print which question is asked(1,2,3,4...)
            //Start timer.
            System.out.println((( gameQuestions.get(i)).question+"?")); //Asking said question.
            System.out.print("| A. " + gameQuestions.get(i).options.get(0).replace("*","") + " |");; //Showing optionA
            System.out.print(" B. " + gameQuestions.get(i).options.get(1).replace("*","") + " |");; //Showing optionA
            System.out.print(" C. " + gameQuestions.get(i).options.get(2).replace("*","") + " |");; //Showing optionA
            System.out.println(" D. " + gameQuestions.get(i).options.get(3).replace("*","") + " |");; //Showing optionA
            //End timer.
            for (int j = 0; j < 1; j++) { //Loops 2 times for every once the upper loop loops.
                System.out.println("Spelare 1 svarar: "); //Prints which player that are gonna answer
                //Start timer
                answerPlayer1.add(scanner.nextLine()); //Waiting for answer and stores it in a LinkedList called "answer".
                //End timer
            }
                for (int m = 0; m < 1; m++) {
                    System.out.println((( gameQuestions.get(i+3)).question+"?"));
                    System.out.println("Spelare 2 svarar: "); //Prints which player that are gonna answer
                    System.out.print("| A. " + gameQuestions.get(i+3).options.get(0).replace("*","") + " |");; //Showing optionA
                    System.out.print(" B. " + gameQuestions.get(i+3).options.get(1).replace("*","") + " |");; //Showing optionA
                    System.out.print(" C. " + gameQuestions.get(i+3).options.get(2).replace("*","") + " |");; //Showing optionA
                    System.out.println(" D. " + gameQuestions.get(i+3).options.get(3).replace("*","") + " |");; //Showing optionA
                    //System.out.println(((QuestionBluePrint) gameQuestions.get(i+3)).question+"?"); //Asking said question.
                    //System.out.println(((QuestionBluePrint) gameQuestions.get(i+3)).options.toString().replace("*", "")); //Showing options
                    //Start timer
                    answerPlayer2.add(scanner.nextLine()); //Waiting for answer and stores it in a LinkedList called "answer".
                    //End timer
                }
        }
        System.out.println("Spelare 1: " + answerPlayer1 + "\nSpelare 2: " + answerPlayer2);
        trackScore();
        System.out.println("Rätt svar - spelare 1: [ " + cheatSheet.get(0) + " " + cheatSheet.get(1) + " " + cheatSheet.get(2) + " ] " +  "\nRätt svar - Spelare 2: [ " + cheatSheet.get(3) + " " + cheatSheet.get(4) + " " + cheatSheet.get(5) + " ]");
        System.out.println(player1Score + " " + player2Score);
    }
            //public void trackPlayedGames(){

            //}

    public void creatCheatSheet(){
        answerConverter.put(0, "A");
        answerConverter.put(1, "B");
        answerConverter.put(2, "C");
        answerConverter.put(3, "D");

        for (int i = 0; i<gameQuestions.size(); i++){
            for (int j = 0; j<gameQuestions.get(i).options.size(); j++) {
                if (gameQuestions.get(i).options.get(j).contains("*")) {
                    String value = answerConverter.get(j).toString();
                    cheatSheet.add(value);
                }
            }

        }

    }

    public void trackScore(){
        creatCheatSheet();
        for (int i = 0; i<3; i++){

            if (cheatSheet.get(i).equals(answerPlayer1.get(i))){
                player1Score += 1;
            }

            if (cheatSheet.get(i+3).equals(answerPlayer2.get(i))){
                player2Score += 1;
            }

        }


    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPlayed_games() {
        return counterPlayedGames;
    }

    public void setPlayed_games(int played_games) {
        this.counterPlayedGames = played_games;
    }

}








