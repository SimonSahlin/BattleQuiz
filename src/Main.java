import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        GameMenu gm = new GameMenu();
        gm.mainMenu();


        Player player = new Player();

        player.randomizeQuestions();
        player.gameProgress();


    }

}