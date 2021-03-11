package app_game;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class GameEngine {

    QuestionHandler questionHandler = new QuestionHandler();
    LinkedList<QuestionHandler> questionsFromSerFile = questionHandler.readBackSer();
    LinkedList<QuestionHandler> gameQuestions = new LinkedList();
    LinkedList<String> answerPlayer1 = new LinkedList();
    LinkedList<String> answerPlayer2 = new LinkedList();
    LinkedList<String> cheatSheet = new LinkedList<>();
    HashMap answerConverter = new HashMap();

    public int player1Score = 0;
    public int player2Score = 0;

    public GameEngine() throws IOException, ClassNotFoundException {
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
    public void gameProgress() throws IOException, ClassNotFoundException { //Simon
        Scanner scanner = new Scanner(System.in);
        //creating the the two players
        Player player1 = newPlayer();
        Player player2 = newPlayer();

        for (int i = 0; i < 3; i++) { //Loops 3 times, once for each question
            char optionLetter = 'A';
            System.out.println("Här kommer fråga " + (i + 1)); //Print which question is asked(1,2,3,4...)
            System.out.println(((gameQuestions.get(i)).question + "?")); //Asking said question.
            for (int n = 0; n < 4; n++) {
                System.out.println(optionLetter++ + ". " + gameQuestions.get(i).options.get(n).replace("*", "")); //Showing optionA, B, C & D
            }
            for (int j = 0; j < 1; j++) { //Loops 2 times for every once the upper loop loops.
                System.out.println(player1.getName() + " svarar: "); //Prints which player that are gonna answer
                //Start timer
                answerPlayer1.add(scanner.nextLine()); //Waiting for answer and stores it in a LinkedList called "answer".
                //End timer
            }
            for (int m = 0; m < 1; m++) {
                optionLetter = 'A';
                System.out.println(((gameQuestions.get(i + 3)).question + "?"));
                for (int k = 0; k < 4; k++) {
                    System.out.println(optionLetter++ + ". " + gameQuestions.get(i + 3).options.get(k).replace("*", "")); //Showing optionA, B, C & D
                }
                System.out.println(player2.getName() + " svarar: "); //Prints which player that are gonna answer
                //Start timer
                answerPlayer2.add(scanner.nextLine()); //Waiting for answer and stores it in a LinkedList called "answer".
                //End timer
            }
        }
        System.out.println("Spelare 1: " + answerPlayer1 + "\nSpelare 2: " + answerPlayer2);
        trackScore();
        System.out.println("Rätt svar - spelare 1: [ " + cheatSheet.get(0) + " " + cheatSheet.get(1) + " " + cheatSheet.get(2) + " ] " + "\nRätt svar - Spelare 2: [ " + cheatSheet.get(3) + " " + cheatSheet.get(4) + " " + cheatSheet.get(5) + " ]");
        System.out.println(player1Score + " " + player2Score);
    }

    public static Player newPlayer() throws IOException, ClassNotFoundException {
        /* //PlayerNumber is what player number is being printed for user and being "created"
        String playerNumber = "";
        switch (playerNbr) {
            case 1:
                playerNumber = " One";
                break;
            case 2:
                playerNumber = " Two";
                break;
        }*/
        //Scan in player's info
        Scanner scan = new Scanner(System.in);
        System.out.println("Type in player name: ");
        String name = scan.next();
        System.out.println("What's your age, " + name + "?");
        int age = scan.nextInt();
        System.out.println("Type in your email " + name + ": ");
        String eMail = scan.next();

        return new Player(name, age, eMail, 0, 0);
    }

    public void creatCheatSheet() {
        answerConverter.put(0, "A");
        answerConverter.put(1, "B");
        answerConverter.put(2, "C");
        answerConverter.put(3, "D");

        for (int i = 0; i < gameQuestions.size(); i++) {
            for (int j = 0; j < gameQuestions.get(i).options.size(); j++) {
                if (gameQuestions.get(i).options.get(j).contains("*")) {
                    String value = answerConverter.get(j).toString();
                    cheatSheet.add(value);
                }
            }
        }
    }

    public void trackScore() {
        creatCheatSheet();
        for (int i = 0; i < 3; i++) {
            if (cheatSheet.get(i).equals(answerPlayer1.get(i))) {
                player1Score += 1;
            }
            if (cheatSheet.get(i + 3).equals(answerPlayer2.get(i))) {
                player2Score += 1;
            }
        }
    }

}