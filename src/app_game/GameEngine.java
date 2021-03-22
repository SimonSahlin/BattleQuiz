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
    private LinkedList<QuestionHandler> questionsFromSerFile = questionHandler.readBackSer();
    private final LinkedList<QuestionHandler> gameQuestions = new LinkedList();
    private final LinkedList<String> answerPlayer1 = new LinkedList();
    private final LinkedList<String> answerPlayer2 = new LinkedList();
    private final LinkedList<String> cheatSheet = new LinkedList<>();
    private final HashMap answerConverter = new HashMap();
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
        questionsFromSerFile = questionHandler.readBackSer();

    }

    public void gameProgress() throws IOException, ClassNotFoundException {
        String restart= "Y";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter info about player 1: \n");
        player1 = player.validateIfInTheRecord(createNewPlayer());
        System.out.println("Enter info about player 2: \n");
        player2 = player.validateIfInTheRecord(createNewPlayer());

        while ( restart.equals("Y")) {

            for (int i = 0; i < 3; i++) {
                char optionLetter = 'A';
                System.out.println("Här kommer fråga " + (i + 1));
                System.out.println(((gameQuestions.get(i)).question + "?"));
                for (int n = 0; n < 4; n++) {
                    System.out.println(optionLetter++ + ". " + gameQuestions.get(i).options.get(n).replace("*", ""));
                }
                System.out.println(player1.getName() + " svarar: ");
                startTimerPlayer1();
                answerPlayer1.add(scanner.nextLine().toUpperCase());
                stopTimerPlayer1();


                optionLetter = 'A';
                System.out.println(((gameQuestions.get(i + 3)).question + "?"));
                for (int k = 0; k < 4; k++) {
                    System.out.println(optionLetter++ + ". " + gameQuestions.get(i + 3).options.get(k).replace("*", ""));
                }
                System.out.println(player2.getName() + " svarar: ");
                startTimerPlayer2();
                answerPlayer2.add(scanner.nextLine().toUpperCase());
                stopTimerPlayer2();

            }

            System.out.println(player1.getName() + ": " + answerPlayer1 + "\n" + player2.getName() + ": " + answerPlayer2);

            trackScore();
            System.out.println("Rätt svar - " + player1.getName() + "  : [ " + cheatSheet.get(0) + " " + cheatSheet.get(1) + " " + cheatSheet.get(2) + " ] " + "\nRätt svar - " +player2.getName() + " [ " + cheatSheet.get(3) + " " + cheatSheet.get(4) + " " + cheatSheet.get(5) + " ]");
            System.out.println(player1CorrectAnswers + " " + player2CorrectAnswers);
            System.out.println("vunna matcher: " + player1.getScore() + "- " +  player2.getScore());
            reloadGameData();

            player.addOnePlayed_games(player1);
            player.addOnePlayed_games(player2);

            System.out.println("Play again ? [Y / N]");
            restart = scanner.nextLine().toUpperCase();
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
                    String value = answerConverter.get(j).toString();
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
        if(player1CorrectAnswers > player2CorrectAnswers){

            player1.setScore(player1.getScore() + 1);
            System.out.println(player1.getName() + " wins:  " + player1.getScore() );

            player.addOneScore(player1);

        }else if ( player1CorrectAnswers < player2CorrectAnswers){
            player2.setScore(player2.getScore() + 1);
            System.out.println(player2.getName() + " wins:  " + player2.getScore() );

            player.addOneScore(player2);


        }else {
            // Equal corect answers.
            System.out.println("Oavgjort tid avgör : ");
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
            System.out.println(player1.getName() + " svarade " + (totalTimePlayer2 - totalTimePlayer1) + " sek snabbare än " + player2.getName() + " och vinner matchen!");
           player1.setScore(player1.getScore()+1);

            player.addOneScore(player1);


        } else {
            System.out.println(player2.getName() + " var snabbast och svarade " + (totalTimePlayer1 - totalTimePlayer2) + " sek snabbare än " + player1.getName() + " och vinner matchen!");
            player2.setScore(player2.getScore()+1);

            player.addOneScore(player2);



        }

    }


}