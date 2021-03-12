package app_game;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class GameEngine {
        // VARIABLES:
    QuestionHandler questionHandler = new QuestionHandler();
    LinkedList<QuestionHandler> questionsFromSerFile = questionHandler.readBackSer();
    LinkedList<QuestionHandler> gameQuestions = new LinkedList();
    LinkedList<String> answerPlayer1 = new LinkedList();
    LinkedList<String> answerPlayer2 = new LinkedList();
    LinkedList<String> cheatSheet = new LinkedList<>();
    HashMap answerConverter = new HashMap();
    Player player1;
    Player player2;
    private LocalTime startTimePlayer1;
    private LocalTime stopTimePlayer1;
    private long totalTimePlayer1 = 0L;
    private LocalTime startTimePlayer2;
    private LocalTime stopTimePlayer2;
    private long totalTimePlayer2 = 0L;
    public int player1Score = 0;
    public int player2Score = 0;

            // CONSTRUCTORS :
    public GameEngine() throws IOException, ClassNotFoundException {
    }

            // METHODS:

    //This method will ask 6 random questions taken from a .txt-file with serialised questions.
    public void randomizeQuestions() throws IOException, ClassNotFoundException { //Simon
        int allRandomQuestions = questionsFromSerFile.size();//Change to dynamic. Length of the list of all questions.
        for (int i = 0; i < 6/*length of List, the list are from a .txt-file of serialiced objects. 6 radomized questions*/; i++) {
            int randNr = (int) (Math.random() * allRandomQuestions);
            gameQuestions.add(questionsFromSerFile.get(randNr)); //Add random questions to a new list of questions which will be asked to the players.
            questionsFromSerFile.remove(randNr); // Removing the index of the random number (removing the questions so not the same question is asked twice)
            allRandomQuestions -= 1; //
        }
        questionsFromSerFile = questionHandler.readBackSer();

    }

    //This method does ask the questions from the 6 randomized questions and stores answers in one LinkedList per player.
    public void gameProgress() throws IOException, ClassNotFoundException { //Simon
        String restart= "Y";
        Scanner scanner = new Scanner(System.in);


        System.out.println("Type in info about player 1");
        player1 = newPlayer();
        System.out.println("Succesfully added info about player 1");
        System.out.println("Type in info about player 2");
        player2 = newPlayer();
        System.out.println("Succesfully added info about player 2");


        while ( restart.equals("Y")) {

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
                    startTimerPlayer1();
                    answerPlayer1.add(scanner.nextLine()); //Waiting for answer and stores it in a LinkedList called "answer".
                    //End timer
                    stopTimerPlayer1();

                }
                for (int m = 0; m < 1; m++) {
                    optionLetter = 'A';
                    System.out.println(((gameQuestions.get(i + 3)).question + "?"));
                    for (int k = 0; k < 4; k++) {
                        System.out.println(optionLetter++ + ". " + gameQuestions.get(i + 3).options.get(k).replace("*", "")); //Showing optionA, B, C & D
                    }
                    System.out.println(player2.getName() + " svarar: "); //Prints which player that are gonna answer
                    //Start timer
                    startTimerPlayer2();
                    answerPlayer2.add(scanner.nextLine()); //Waiting for answer and stores it in a LinkedList called "answer".
                    //End timer
                    stopTimerPlayer2();
                }
            }

            System.out.println(player1.getName() + ": " + answerPlayer1 + "\n" + player2.getName() + ": " + answerPlayer2);

            trackScore();
            System.out.println("Rätt svar - " + player1.getName() + "  : [ " + cheatSheet.get(0) + " " + cheatSheet.get(1) + " " + cheatSheet.get(2) + " ] " + "\nRätt svar - " +player2.getName() + " [ " + cheatSheet.get(3) + " " + cheatSheet.get(4) + " " + cheatSheet.get(5) + " ]");
            System.out.println(player1Score + " " + player2Score);
            System.out.println("vunna matcher: " + player1.getScore() + "- " +  player2.getScore());
            reloadGameData();
            // SPELA IGEN? FIXA VALIDERING PÅ INPUTEN Y/N
            System.out.println("Play again ? [Y / N]");
            restart = scanner.nextLine().toUpperCase();
        }
    }

    public void reloadGameData() throws IOException, ClassNotFoundException {
        player1Score = 0;
        player2Score = 0;
        cheatSheet.clear();
        gameQuestions.clear();
        randomizeQuestions();
        totalTimePlayer1 = 0L;
        totalTimePlayer2 = 0L;


    }

    public static Player newPlayer() throws IOException, ClassNotFoundException {
        //PlayerNumber is what player number is being printed for user and being "created"

        //PlayerNumber is what player number is being printed for user and being "created"
        int age = 0;
        String name = "";
        String eMail = "";
        //Scan in player's info
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("Type in player name: ");
            name = scan.next();
            System.out.println("What's your age, " + name + "?");
            age = scan.nextInt();
            System.out.println("Type in your email " + name + ": ");
            eMail = scan.next();
        }catch(Exception e){
            System.out.println("You need to type in your age correctly.");
            newPlayer();
        }
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
        if(player1Score > player2Score){
            player1.setScore(player1.getScore() + 1);
            System.out.println(player1.getName() + " wins:  " + player1.getScore() );
        }else if ( player1Score < player2Score){
            player2.setScore(player2.getScore() + 1);
            System.out.println(player2.getName() + " wins:  " + player2.getScore() );
        }else if (player1Score == player2Score){
            System.out.println("Oavgjort tid avgör : ");
            compareResponseTime();
        }else {
            System.out.println("NGT FEL I TRACKSCORE");
        }
        answerPlayer1.clear();
        answerPlayer2.clear();


    }
    public void startTimerPlayer1() {
        startTimePlayer1 = LocalTime.now();
    }
    public void startTimerPlayer2() {
        startTimePlayer2 = LocalTime.now();
    }
    public void stopTimerPlayer1() {
        stopTimePlayer1 = LocalTime.now();
        Duration difference = Duration.between(startTimePlayer1, stopTimePlayer1);
        long responseTime = difference.getSeconds();
        totalTimePlayer1 += responseTime;
    }
    public void stopTimerPlayer2() {
        stopTimePlayer2 = LocalTime.now();
        Duration difference = Duration.between(startTimePlayer2, stopTimePlayer2);
        long responseTime = difference.getSeconds();
        totalTimePlayer2 += responseTime;
    }
    public void compareResponseTime() {
        if (totalTimePlayer1 < totalTimePlayer2) {
            System.out.println(player1.getName() + " svarade " + (totalTimePlayer2 - totalTimePlayer1) + " sek snabbare än " + player2.getName() + " och vinner matchen!");
           player1.setScore(player1.getScore()+1);

        } else {
            System.out.println(player2.getName() + " var snabbast och svarade " + (totalTimePlayer1 - totalTimePlayer2) + " sek snabbare än " + player1.getName() + " och vinner matchen!");
            player2.setScore(player2.getScore()+1);

        }

    }


}