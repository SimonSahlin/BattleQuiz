
package app_game;

import java.io.*;
import java.util.*;

/*Ni ska implementera den konkreta klassen app_game.Player som extendar app_game.Person och lägger
        till hantering av variablerna score och played_games. Varje gång spelaren spelar
        ett spel ska played_games ökas på med 1 och om användaren vinner matchen ska score
        ökas med 1.
 */
public class Player extends Person implements Serializable {
    ////////////// testarea ///////
    // PATHS:
    // change to your local absolutepath [serFile -> StoredPlayers.ser].
    File playerSerFile = new File("/Users/Robin/Documents/ProgrammeringEC/05- Avancerad Java/InlämningsUppgift/BattleQuizz/BattleQuiz/game_files/StoredPlayers.ser");

    LinkedList<Player> playerLinkedList;
    ////////////// testarea ///////

    private int score;

    private int counterPlayedGames;

    public Player() {}
    public Player(LinkedList playerList){
        this.playerLinkedList = playerList;}
    public Player(String name, int age, String eMail, int score, int played_games){
        super(name, age, eMail);
        this.score = score;
        this.counterPlayedGames = played_games;
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


    ////////////// testarea ///////

    // METHODS:
    // TO BE IMPLEMENTEND ?
    /*
    public void editQuestion() throws IOException, ClassNotFoundException {
        Scanner scEdit = new Scanner(System.in);
        //1 Read questions from .ser
        LinkedList<QuestionHandler> tempList = readBackSer();
        System.out.println("What question do you want to change (number)?");

        try {
            // 2 pick index to edit
            int numberToEdit = scEdit.nextInt() - 1;
            System.out.println("You are about to edit question nr " + (numberToEdit + 1) + ": " +
                    tempList.get(numberToEdit).question + "? " + tempList.get(numberToEdit).options);


            LinkedList answerList = new LinkedList();
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter question:");
            String q = sc.nextLine();
            System.out.println("Enter answer A");
            String answer1 = validateOption(sc.nextLine().toLowerCase().trim());
            System.out.println("Enter answer B");
            String answer2 = validateOption(sc.nextLine().toLowerCase().trim());
            System.out.println("Enter answer C");
            String answer3 = validateOption(sc.nextLine().toLowerCase().trim());
            System.out.println("Enter answer D");
            String answer4 = validateOption(sc.nextLine().toLowerCase().trim());


            answerList.add(answer1);
            answerList.add(answer2);
            answerList.add(answer3);
            answerList.add(answer4);

            //4 Remove previous question at this index.
            tempList.remove(numberToEdit);

            // 5 Add the edited(new) question in the same index-position as the deleted one(old one).
            tempList.add(numberToEdit, new QuestionHandler(q, answerList));

            // 6 Write modified list to ser-file
            System.out.println("Question number " + (numberToEdit + 1) + " is edited to :" + tempList.get(numberToEdit).question + "? " + tempList.get(numberToEdit).options + ".");
            writeToSer(tempList);
        }catch (Exception e){
            System.out.println("Enter a valid question number\n");
            editQuestion();
        }
    }
     */
    public void removePlayer() throws IOException,ClassNotFoundException {
        Scanner scRemove = new Scanner(System.in);
        LinkedList<Player> tempList = readPlayerListFromSer();

        System.out.println("Which Player do you want to remove? (number from list)");

        try {
            int NumberToRemove = scRemove.nextInt() - 1;
            tempList.remove(NumberToRemove);

            //Write modified list to ser-file again.
            writePlayerListToSer(tempList);
            System.out.println("Successful with removal of Player:  " + (NumberToRemove + 1));
        }catch(Exception e){
            System.out.println("Enter a valid number..\n");
            removePlayer();
        }

    }
    public void writePlayerListToSer(Object toStore) throws IOException {
        //Overwrites the info on StoredPlayers.ser with the info sent as a parameter in this method.

        FileOutputStream fileOutput = new FileOutputStream(playerSerFile);
        ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

        objectOutput.writeObject(toStore);
        objectOutput.flush();
        objectOutput.close();
    }// COMPLETE
    public LinkedList<Player> readPlayerListFromSer() throws IOException, ClassNotFoundException {

        FileInputStream fileInput = new FileInputStream(playerSerFile);
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        Player listAfterDeSer = new Player((LinkedList) objectInput.readObject());


        return  listAfterDeSer.playerLinkedList;
        //
        //obj1.playerlistlist;
    } // COMPLETE
    public void addPlayer(Player player) throws IOException, ClassNotFoundException {
        // New temporary linkedList to store info fråm serfile
        LinkedList<Player> tempLinkedList = readPlayerListFromSer();
        tempLinkedList.add(player);
        writePlayerListToSer(tempLinkedList);

        //System.out.println("Question added: " + tempLinkedList.getLast().question + tempLinkedList.getLast().options);
    }       // COMPLETE

    public void showPlayerRecord() throws IOException, ClassNotFoundException {
        // Printing all questions, one row per question with a numeric list in the eyes of the viewer.
        LinkedList<Player> tempListReadAll = readPlayerListFromSer();
        System.out.println("  Player:               Email:                    Wins:     Played Games:                 ");
        for (int i = 0; i < tempListReadAll.size(); i++) {
            System.out.println(i +1 + " " +tempListReadAll.get(i).getName() + "                 "+ tempListReadAll.get(i).geteMail() +"                 " + tempListReadAll.get(i).getScore() +"                 " + tempListReadAll.get(i).getPlayed_games()  );
        }
    } // COMPLETE
    public void clearPlayerRecord() throws IOException, ClassNotFoundException {
        LinkedList<Player> tempList = readPlayerListFromSer();
        tempList.clear();
        writePlayerListToSer(tempList);
    } // COMPLETE
    /////////////// IN PROGRESS ////////////////
    public Player validateIfInTheRecord(Player player) throws IOException, ClassNotFoundException {
        boolean isInTheList = false;
        LinkedList<Player> tempLinkedList = readPlayerListFromSer();
        for (int i = 0; i < tempLinkedList.size(); i++) {
            if (tempLinkedList.get(i).getName().equals(player.getName())){
                isInTheList = true;
                System.out.println("finns i systemet");
                return tempLinkedList.get(i);

            }else{isInTheList = false;
            }

        }
        if(isInTheList == true){
            System.out.println("Spelare med i listan");

        }else if (isInTheList == false){
            System.out.println("spelare inte spelat innan");
            addPlayer(player);
        }
        return player;
    } // NOT COMPLETE
    /////////////// IN PROGRESS ////////////////

    ////////// RESETTING SER FILE WITH A FEW PLAYERS DUREING TESTING PHASE ////////////
    public void testResetPlayerrecordForDevPurposes() throws IOException, ClassNotFoundException {
        LinkedList<Player> localList = new LinkedList<Player>();

        localList.add(new Player("Robin",33,"roasd@msn.com", 3,3));
        localList.add(new Player("Kalle",22,"kalle@msn.com", 2,7));
        localList.add(new Player("Johan",11,"Johan@msn.com", 11,77));

        writePlayerListToSer(localList);



    }
    ////////// RESETTING SER FILE WITH A FEW PLAYERS DUREING TESTING PHASE ////////////

    ///////// TEST OF DIFF METHODS INSIDE METHOD BELOW WHICH RUNS AS FIRST METHOD IN MAIN ///////////
    public void test1() throws IOException, ClassNotFoundException {

        // addPlayer(new Player("Lisa", 44,"lisa@hotmail.com",0,99)); // Test of adPlayer(). Works Fin
        testResetPlayerrecordForDevPurposes();
        //clearPlayerRecord();
        showPlayerRecord();





    }
    ///////// TEST OF DIFF METHODS INSIDE METHOD BELOW WHICH RUNS AS FIRST METHOD IN MAIN ///////////

}
