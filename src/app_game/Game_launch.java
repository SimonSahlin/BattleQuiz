package app_game;

import java.io.IOException;
import java.util.Scanner;


public class Game_launch {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Instances
        GuidePrint guidePrint = new GuidePrint();
        QuestionHandler questionHandler = new QuestionHandler();


        /////////////TEST AREA ///////////////////////



        ////////////////////////// PROGRAM /////////
        guidePrint.battleQuizz();
        guidePrint.mainMenuFull();

        // Main menu:
        String choiceMain;
        Scanner scChoiceMain = new Scanner(System.in);
        do{
            choiceMain = scChoiceMain.nextLine();
            switch (choiceMain){
                case "1":
                    System.out.println("Visa ScoreBoard-- WORK TO BE DONE.. ");
                    guidePrint.mainMenuMini();
                    break;
                case "2":
                    Player player = new Player();

                    player.randomizeQuestions();
                    player.gameProgress();
                    guidePrint.mainMenuMini();
                    break;
                case "3":
                    // Help menu: //
                    guidePrint.helpMenuFull();
                    String choiceHelp;
                    Scanner scChoiceHelp = new Scanner(System.in);

                                    do {
                                        choiceHelp = scChoiceHelp.nextLine();
                                        switch (choiceHelp) {
                                            case "1":
                                                questionHandler.showAllQuestions();
                                                guidePrint.helpMenuMini();
                                                break;

                                            case "2":
                                                questionHandler.addQuestion();
                                                guidePrint.helpMenuMini();

                                                break;

                                            case "3":
                                                questionHandler.removeQuestion();
                                                guidePrint.helpMenuMini();
                                                break;

                                            case "4":
                                                questionHandler.editQuestion();
                                                guidePrint.helpMenuMini();
                                                break;

                                            case "99":
                                                questionHandler.resetQuestionsFromTextFile();
                                                guidePrint.helpMenuMini();
                                                break;
                                            case "0":
                                                guidePrint.mainMenuFull();
                                                break;

                                            default:
                                                System.out.println("Wrong entry, try again:");
                                        }
                                    }while(!choiceHelp.equals("0"));
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Wrong entry, try again:");
            }
        }while(!choiceMain.equals("0"));




    }



}