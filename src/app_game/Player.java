
package app_game;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/*Ni ska implementera den konkreta klassen app_game.Player som extendar app_game.Person och lägger
        till hantering av variablerna score och played_games. Varje gång spelaren spelar
        ett spel ska played_games ökas på med 1 och om användaren vinner matchen ska score
        ökas med 1.
 */
public class Player extends Person {

    private int score; //<-- bort med skiten?

    private int counterPlayedGames;

    public Player() throws IOException, ClassNotFoundException {

    }

    public Player(String name, int age, String eMail, int score, int played_games) throws IOException, ClassNotFoundException {
        super(name, age, eMail);
        this.score = score;
        this.counterPlayedGames = played_games;
    }

    public String toString() {
        return String.valueOf(score) + String.valueOf(counterPlayedGames) + (super.toString());
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPlayed_games() {
        return counterPlayedGames;
    }

    public void setPlayed_games(int played_games) {
        this.counterPlayedGames = played_games;
    }

}
