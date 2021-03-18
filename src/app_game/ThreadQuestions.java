package app_game;

import java.io.IOException;
public class ThreadQuestions extends Thread
{
    GameEngine gameEngine;

    public ThreadQuestions(GameEngine gameEngine)  {
            this.gameEngine = gameEngine;
    }

    public void run(){
        try {
            gameEngine.randomizeQuestions();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
