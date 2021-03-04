package app_game;

import java.io.IOException;
import java.util.Scanner;

public class GameMenu {
    HelpMenu helpMenu = new HelpMenu();


    // Menu
    // ScoreBoard
    // Spela Qiizz -> Antal spelare, antal frågor osv osv
    // Gå till help menu
    // Stäng spelet
    //

    public void mainMenu() throws IOException, ClassNotFoundException {
        System.out.println(" Welcome to the Quizz Game");

        System.out.println("--  MENU --");
        System.out.println("1 - Show score board");
        System.out.println("2 - Play QuizzGame");
        System.out.println("3 - Help Menu");
        System.out.println("0 - Exit program");
        System.out.println("Välj från menyn med tangentbordet och avsluta med Enter:");

        String choice;
        Scanner scChoice = new Scanner(System.in);



        do{
            choice= scChoice.nextLine();
            switch (choice){
                case "1":
                    System.out.println("Visa ScoreBoard");
                    break;
                case "2":
                    Player player = new Player();

                    player.randomizeQuestions();
                    player.gameProgress();
                    break;
                case "3":
                    helpMenu.menu();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("välj rätt siffra");
            }
        }while(!choice.equals("0"));


    }
}
