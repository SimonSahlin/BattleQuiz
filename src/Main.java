import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {


        HandleFile hf = new HandleFile();
        Player player = new Player();

        player.randomizeQuestions();
        player.gameProgress();


        //hf.initSetUp();
        //hf.showAllQuestions();
        //hf.readBackSer();
        //hf.addQuestion();

       //GameMenu gameMenu = new GameMenu();
       //gameMenu.mainMenu();


        GameMenu gm = new GameMenu();
        gm.mainMenu();


    }



}