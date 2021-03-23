package app_game;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class GameEngine {

    // ------- INSTANCES -------
    Player player = new Player();

    // ------- VARIABLES -------
    private final QuestionHandler questionHandler = new QuestionHandler();
    private LinkedList<QuestionHandler> questionsFromSerFile = questionHandler.readQuestionListFromSer();
    private final LinkedList<QuestionHandler> gameQuestions = new LinkedList<>();
    private final LinkedList<String> answerPlayer1 = new LinkedList<>();
    private final LinkedList<String> answerPlayer2 = new LinkedList<>();
    private final LinkedList<String> cheatSheet = new LinkedList<>();
    private final HashMap<Integer, String> answerConverter = new HashMap<>();
    private Player player1;
    private Player player2;
    private LocalTime startTimePlayer1;
    private LocalTime stopTimePlayer1;
    private long totalTimePlayer1 = 0L;
    private LocalTime startTimePlayer2;
    private LocalTime stopTimePlayer2;
    private long totalTimePlayer2 = 0L;
    public int player1CorrectAnswers = 0;
    public int player2CorrectAnswers = 0;
    private static GameEngine gameEngine;

    // ------- CONSTRUCTORS -------
    private GameEngine() throws IOException, ClassNotFoundException {}


    // ------- METHODS -------
    public static GameEngine getInstance() throws IOException, ClassNotFoundException {
        if (gameEngine == null){
            gameEngine = new GameEngine();
        }
        return gameEngine;
    }

    public void randomizeQuestions() throws IOException, ClassNotFoundException {
        int allRandomQuestions = questionsFromSerFile.size();
        for (int i = 0; i < 6; i++) {
            int randNr = (int) (Math.random() * allRandomQuestions);
            gameQuestions.add(questionsFromSerFile.get(randNr));
            questionsFromSerFile.remove(randNr);
            allRandomQuestions -= 1;
        }
        questionsFromSerFile = questionHandler.readQuestionListFromSer();

    }

    public void gameProgress() throws IOException, ClassNotFoundException {
        String restart = "Y";
        Scanner scPlayerInput = new Scanner(System.in);

        System.out.println("Enter info about player 1: \n");
        player1 = player.validateIfInTheRecord(createNewPlayer());
        System.out.println("\nEnter info about player 2: \n");
        player2 = player.validateIfInTheRecord(createNewPlayer());

        while ( restart.equals("Y")) {

            for (int i = 0; i < 3; i++) {
                char optionLetter = 'A';
                System.out.println("Here's question " + (i + 1) +"\n");
                System.out.println(((gameQuestions.get(i)).question + "?"));
                for (int n = 0; n < 4; n++) {
                    System.out.println(optionLetter++ + ". " + gameQuestions.get(i).options.get(n).replace("*", ""));
                }
                System.out.println("\n" + player1.getName() + " answers: ");
                startTimerPlayer1();
                answerPlayer1.add(scPlayerInput.nextLine().toUpperCase());
                stopTimerPlayer1();


                optionLetter = 'A';
                System.out.println("\n" + ((gameQuestions.get(i + 3)).question + "?"));
                for (int k = 0; k < 4; k++) {
                    System.out.println(optionLetter++ + ". " + gameQuestions.get(i + 3).options.get(k).replace("*", ""));
                }
                System.out.println("\n" + player2.getName() + " answers: ");
                startTimerPlayer2();
                answerPlayer2.add(scPlayerInput.nextLine().toUpperCase());
                stopTimerPlayer2();

            }

            trackScore();

            //System.out.println("Rätt svar - " + player1.getName() + "  : [ " + cheatSheet.get(0) + " " + cheatSheet.get(1) + " " + cheatSheet.get(2) + " ] " + "\nRätt svar - " +player2.getName() + " [ " + cheatSheet.get(3) + " " + cheatSheet.get(4) + " " + cheatSheet.get(5) + " ]");

            reloadGameData();

            player.addOnePlayed_games(player1);
            player.addOnePlayed_games(player2);

            System.out.println("Play again ? [Y / N]");
            restart = scPlayerInput.nextLine().toUpperCase();
        }
    }

    public void reloadGameData() throws IOException, ClassNotFoundException {
        player1CorrectAnswers = 0;
        player2CorrectAnswers = 0;
        cheatSheet.clear();
        gameQuestions.clear();
        randomizeQuestions();
        totalTimePlayer1 = 0L;
        totalTimePlayer2 = 0L;
    }

    public static Player createNewPlayer(){

        int age = 0;
        String name = "";
        String eMail = "";

        Scanner scPlayerInformation = new Scanner(System.in);
        try {
            System.out.println("Type in player name: ");
            name = scPlayerInformation.next();
            System.out.println("What's your age, " + name + "?");
            age = scPlayerInformation.nextInt();
            System.out.println("Type in your email " + name + ": ");
            eMail = scPlayerInformation.next();
        }catch(Exception e){
            System.out.println("You need to type in your age correctly.");
            createNewPlayer();
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
                    String value = answerConverter.get(j);
                    cheatSheet.add(value);
                }
            }
        }
    }

    public void trackScore() throws IOException, ClassNotFoundException {
        creatCheatSheet();
        for (int i = 0; i < 3; i++) {
            if (cheatSheet.get(i).equals(answerPlayer1.get(i))) {
                player1CorrectAnswers += 1;
            }
            if (cheatSheet.get(i + 3).equals(answerPlayer2.get(i))) {
                player2CorrectAnswers += 1;
            }
        }
        System.out.println("\n" + player1.getName() + " scored " + player1CorrectAnswers + " points" + "\n" + player2.getName() + " scored " + player2CorrectAnswers + " points");
        if(player1CorrectAnswers > player2CorrectAnswers){

            player1.setScore(player1.getScore() + 1);
            System.out.println(player1.getName() + " wins!");

            player.addOneScore(player1);

        }else if ( player1CorrectAnswers < player2CorrectAnswers){
            player2.setScore(player2.getScore() + 1);
            System.out.println(player2.getName() + " wins!");

            player.addOneScore(player2);


        }else {
            // Equal correct answers.
            System.out.println("The score is tied, comparing answer time...");
            compareResponseTime();
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

    public void compareResponseTime() throws IOException, ClassNotFoundException {
        if (totalTimePlayer1 < totalTimePlayer2) {
            System.out.println(player1.getName() + " answered " + (totalTimePlayer2 - totalTimePlayer1) + " sec faster than " + player2.getName() + " and wins the match!");
           player1.setScore(player1.getScore()+1);

            player.addOneScore(player1);


        } else {
            System.out.println(player2.getName() + " answered " + (totalTimePlayer1 - totalTimePlayer2) + " sec faster than " + player1.getName() + " and wins the match!");
            player2.setScore(player2.getScore()+1);

            player.addOneScore(player2);



        }

    }


}