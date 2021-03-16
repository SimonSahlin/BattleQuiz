package app_game;

import java.io.IOException;
import java.util.Scanner;


public class Game_launch {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //// IF IO MESSAGE DUE TO SERIALIZATION OCCOURS AFTER DOWNLOAD THIS VERSION DO THIS:
        //// UNCOMMENT CODE IN BELOW AND COMMENT AWAY THE REST OF MAIN. RUN PROGRAM AND THEN RUN PROGRAM AS NORMAL


        // Instances
        GuidePrint guidePrint = new GuidePrint();
        QuestionHandler questionHandler = new QuestionHandler();
        Player player = new Player();
        GameEngine gameEngine = new GameEngine();


        //-------------- TEST AREA --------------//
          player.testResetPlayerrecordForDevPurposes();
         //questionHandler.resetQuestionsFromTextFile();
        //-------------- PROGRAM --------------//

        guidePrint.battleQuizz();
        guidePrint.mainMenuFull();

        // Main menu:
        String choiceMain;
        Scanner scChoiceMain = new Scanner(System.in);
        do{
            choiceMain = scChoiceMain.nextLine();
            switch (choiceMain){
                case "1":
                    player.showPlayerRecord();
                    guidePrint.mainMenuMini();
                    break;
                case "2":
                    gameEngine.randomizeQuestions();
                    gameEngine.gameProgress();
                    guidePrint.mainMenuMini();
                    break;
                case "3":
                    ////// Help menu: ///////
                    guidePrint.helpMenuFull();
                    String choiceHelp;
                    Scanner scChoiceHelp = new Scanner(System.in);

                            do {
                                 choiceHelp = scChoiceHelp.nextLine();
                                /// HELP MENU
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
                                    case "admin":
                                        /// ADMIN MENU ///
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
                                                        case "0":
                                                            guidePrint.helpMenuFull();
                                                            break;
                                                        default:
                                                            System.out.println("Wrong entry, try again: ");
                                                    }
                                                } while (!choiceAdmin.equals("0"));
                                        break;
                                    case "0":
                                        guidePrint.mainMenuFull();
                                        break;
                                    default:
                                        System.out.println("Wrong entry, try again:");
                                        break;
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