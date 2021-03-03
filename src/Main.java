import java.io.IOException;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        HelpMenu hm = new HelpMenu();
        hm.menu();

        HandleFile hf = new HandleFile();
       // hf.addQuestion();
        //hf.removeQuestion();
        // hf.showAllQuestions();
         //hf.initSetUp();
        //Player player = new Player();

        //player.randomizeQuestions();
        //player.gameProgress();

        //hf.initSetUp();
        //hf.readBackSer();
        //hf.addQuestion();
        //LinkedList tempList = hf.readBackSer();
        //LinkedList test2list = new LinkedList();

        //test2list.add(tempList.get(0));

        //System.out.println(
        //        ((QuestionBluePrint) test2list.get(0)).question
        //);








       //GameMenu gameMenu = new GameMenu();
       //gameMenu.mainMenu();


       /*
        HandleFile handleFile = new HandleFile();

        LinkedList test1 = handleFile.readBackSer();




        LinkedList test2list = new LinkedList();


        test2list.add(test1.get(1));
        test2list.add(test1.get(2));

        System.out.println(
                ((QuestionBluePrint) test2list.get(0)).a3
        );


        for (int i = 0; i < 2; i++) {
            System.out.println(test2list.get(i).toString().replace("**",""));

            String svar = "c";

            if(svar.toLowerCase().equals("a") && ((QuestionBluePrint) test2list.get(i)).a1.contains("**")) {
                System.out.println("rätt a");
            // score +1;
            }else if (svar.toLowerCase().equals("b") && ((QuestionBluePrint) test2list.get(i)).a2.contains("**")){
                System.out.println("rätt b");
            }else if (svar.toLowerCase().equals("c") && ((QuestionBluePrint) test2list.get(i)).a3.contains("**")){
                System.out.println("rätt c");
            }



        }


/*
LinkedList<String> test4 = new LinkedList<>();
        test4.add("Fråga1, svarA, SvarB, SvarC");
        test4.add("Fråga1, svarA, SvarB, SvarC");
        test4.add("Fråga1, svarA, SvarB, SvarC");
*/



    }



}