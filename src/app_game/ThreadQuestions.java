package app_game;

import java.io.IOException;
public class ThreadQuestions extends Thread
{
    // ------- VARIABLES -------
    GameEngine gameEngine;

    // ------- CONSTRUCTORS -------
    public ThreadQuestions(GameEngine gameEngine)  {
            this.gameEngine = gameEngine;
    }

    // ------- METHODS -------
    public void run(){
        try {
            gameEngine.randomizeQuestions();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
