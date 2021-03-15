
package app_game;

import java.io.IOException;

public class Player extends Person {

    // --- VARIABLES ---
    private int scoreCounter;
    private int playedGamesCounter;

    // --- CONSTRUCTORS ---
    public Player() throws IOException, ClassNotFoundException {

    }
    public Player(String name, int age, String eMail, int scoreCounter, int played_games) throws IOException, ClassNotFoundException {
        super(name, age, eMail);
        this.scoreCounter = scoreCounter;
        this.playedGamesCounter = played_games;
    }

    // --- METHODS ---
    public String playerInformationToString() {
        return String.valueOf(scoreCounter) + String.valueOf(playedGamesCounter) + (super.personInformationToString());
    }

    // --- Getters & Setters ---
    public int getScoreCounter() {
        return scoreCounter;
    }

    public void setScoreCounter(int scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    public int getPlayed_games() {
        return playedGamesCounter;
    }

    public void setPlayed_games(int played_games) {
        this.playedGamesCounter = played_games;
    }

}
