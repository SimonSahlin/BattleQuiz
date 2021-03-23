package app_game;

import java.io.IOException;
import java.util.Scanner;


public class Game_launch {
    public static void main(String[] args) throws IOException, ClassNotFoundException {


        // ------- INSTANCES -------
        GuidePrint guidePrint = new GuidePrint();
        QuestionHandler questionHandler = new QuestionHandler();
        Player player = new Player();
        GameEngine gameEngine = GameEngine.getInstance();


        // -------------- TEST AREA --------------
         // player.testResetPlayerRecordForDevPurposes();
         // questionHandler.resetQuestionListFromTextFile();

        // -------------- PROGRAM --------------

        guidePrint.battleQuizLogo();
        guidePrint.mainMenuFull();

        String choiceMain;
        Scanner scChoiceMain = new Scanner(System.in);

        // -------------- Menu --------------
        do{
            // -------------- Main menu --------------
            choiceMain = scChoiceMain.nextLine();
            switch (choiceMain){
                case "1":
                    player.showPlayerRecord();
                    guidePrint.mainMenuMini();
                    break;
                case "2":
                    ThreadQuestions secondThread = new ThreadQuestions(gameEngine);
                    secondThread.start();
                    gameEngine.gameProgress();
                    guidePrint.mainMenuMini();
                    break;
                case "admin":
                      // -------------- Admin menu --------------
                        guidePrint.adminMenuFull();
                        String choiceAdmin;
                        Scanner scChoiceAdmin = new Scanner(System.in);
                                do {
                                    choiceAdmin = scChoiceAdmin.nextLine();
                                    switch (choiceAdmin) {
                                        case "1":
                                            player.showPlayerRecord();
                                            guidePrint.adminMenuMini();
                                            break;
                                        case "2":
                                            player.removePlayer();
                                            guidePrint.adminMenuMini();
                                            break;
                                        case "3":
                                            player.clearPlayerRecord();
                                            guidePrint.adminMenuMini();
                                            break;
                                        case "4":
                                            questionHandler.showAllQuestions();
                                            guidePrint.adminMenuMini();
                                            break;
                                        case "5":
                                            questionHandler.addQuestion();
                                            guidePrint.adminMenuMini();
                                            break;
                                        case "6":
                                            questionHandler.removeQuestion();
                                            guidePrint.adminMenuMini();
                                            break;
                                        case "7":
                                            questionHandler.editQuestion();
                                            guidePrint.adminMenuMini();
                                            break;
                                        case "8":
                                            questionHandler.resetQuestionListFromTextFile();
                                            guidePrint.adminMenuMini();
                                            break;
                                        case "0":
                                            guidePrint.mainMenuFull();
                                            break;
                                        default:
                                            System.out.println("Wrong entry, try again: ");
                                    }
                                } while (!choiceAdmin.equals("0"));

    break;
                case "0":
                    break;
                default:
                    System.out.println("Wrong entry, try again:");
            }
        }while(!choiceMain.equals("0"));













    }



}