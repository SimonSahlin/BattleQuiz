
package app_game;


import java.io.*;
import java.util.*;
public class Player extends Person implements Serializable {

            // PATHS:
            // change to your local absolutepath [serFile -> StoredPlayers.ser].
    File playerSerFile = new File("C:\\Users\\Adam-\\IdeaProjects\\BattleQuiz\\BattleQuiz\\game_files\\StoredPlayers.ser");

            // VARIABLES:
    private LinkedList<Player> playerLinkedList;
    private int scoreCounter;
    private int playedGamesCounter;

            // CONSTRUCTORS:
    public Player() {}
    public Player(LinkedList playerList){
        this.playerLinkedList = playerList;}
    public Player(String name, int age, String eMail, int scoreCounter, int played_games){

        super(name, age, eMail);
        this.scoreCounter = scoreCounter;
        this.playedGamesCounter = played_games;
    }


            // --- METHODS ---

    public void removePlayer() throws IOException,ClassNotFoundException {
        Scanner scRemove = new Scanner(System.in);
        LinkedList<Player> tempList = readPlayerListFromSer();

        System.out.println("Which Player do you want to remove? (Number from ScoreBoard)");

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

    } // COMPLETE
    public void writePlayerListToSer(LinkedList<Player> toStore) throws IOException {
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

    } // COMPLETE
    public void addPlayer(Player player) throws IOException, ClassNotFoundException {
        // New temporary linkedList to store info from serfile
        LinkedList<Player> tempLinkedList = readPlayerListFromSer();
        tempLinkedList.add(player);
        writePlayerListToSer(tempLinkedList);

    }       // COMPLETE
    public void showPlayerRecord() throws IOException, ClassNotFoundException {
        // KVAR ATT SORTERA EFTER FLEST VINSTER
        // Printing all Players with highest score in top.
        LinkedList<Player> tempListReadAll = readPlayerListFromSer();
        tempListReadAll.sort(((o1, o2) -> o2.getScore() - o1.getScore()));

        System.out.println(String.format("%10s %10s %10s %15s %5s %10s %5s", "Player", "", "Email", "", "Wins", "", "Played Games"));
        System.out.println(String.format("%s", "-------------------------------------------------------------------------------------------"));



        for (int i = 0; i < tempListReadAll.size(); i++) {

            System.out.println(String.format("%10s %10s %10s %10s %5s %10s %5s", tempListReadAll.get(i).getName(), "", tempListReadAll.get(i).geteMail(), "", tempListReadAll.get(i).getScore(), "", tempListReadAll.get(i).getPlayed_games()));
        }

    } // NOT COMPLETE
    public void clearPlayerRecord() throws IOException, ClassNotFoundException {
        LinkedList<Player> tempList = readPlayerListFromSer();
        tempList.clear();
        writePlayerListToSer(tempList);
    } // COMPLETE
    public void addOneScore(Player player) throws IOException, ClassNotFoundException {
        // TA IN LISTA
        // KOLLA MATCHA SPELARE
        // UPPGRADERA SCORE MED 1
        // SPARA LISTA

        LinkedList<Player> tempList = readPlayerListFromSer();

        for (int i = 0; i < tempList.size(); i++) {
            tempList.get(i);
            if (tempList.get(i).getName().equals(player.getName())){
                // System.out.println(tempList.get(i).getScore());

                tempList.get(i).setScore(tempList.get(i).getScore() +1);
                // System.out.println(tempList.get(i).getScore());
            }
        }

        writePlayerListToSer(tempList);

    } // COMPLETE
    public void addOnePlayed_games(Player player) throws IOException, ClassNotFoundException {
        // TA IN LISTA
        // KOLLA MATCHA SPELARE
        // UPPGRADERA SCORE MED 1
        // SPARA LISTA

        LinkedList<Player> tempList = readPlayerListFromSer();

        for (int i = 0; i < tempList.size(); i++) {
            tempList.get(i);
            if (tempList.get(i).getName().equals(player.getName())){
                // System.out.println(tempList.get(i).getPlayed_games());

                tempList.get(i).setPlayed_Games(tempList.get(i).getPlayed_games() +1);
               // System.out.println(tempList.get(i).getPlayed_games());
            }
        }

        writePlayerListToSer(tempList);
    } // COMPLETE
    public Player validateIfInTheRecord(Player player) throws IOException, ClassNotFoundException {

        LinkedList<Player> tempLinkedList = readPlayerListFromSer();
            // If player is in the list of prev players, return this player to the game.
        for (int i = 0; i < tempLinkedList.size(); i++) {
            if (tempLinkedList.get(i).getName().equals(player.getName())) {
                System.out.println("Welcome back " + tempLinkedList.get(i).getName() + ", Played Games: "+ tempLinkedList.get(i).getPlayed_games() + ", Total wins: " + tempLinkedList.get(i).getScore() + ".\n");
               // isInTheList = true;
                return tempLinkedList.get(i);
            }
        }
        // If not in the list of prev players, add player and return player.
        System.out.println(player.getName() + ", Welcome to your first game " + ".");
        addPlayer(player);
        return player;


    } //COMPLETE
    public int getScore() {
        return scoreCounter;
    }
    public void setScore(int score) {
        this.scoreCounter = score;

    }
    public void setPlayed_Games(int playedGamesCounter) {
        this.playedGamesCounter = playedGamesCounter;
    }
    public int getPlayed_games() {
        return playedGamesCounter;
    }


    ////////// RESETTING PLAYER-SER FILE WITH A FEW PLAYERS DUREING TESTING PHASE ////////////
    public void testResetPlayerrecordForDevPurposes() throws IOException, ClassNotFoundException {
        LinkedList<Player> localList = new LinkedList<Player>();

        localList.add(new Player("Robin",33,"roasd@msn.com", 3,3));
        localList.add(new Player("Kalle",22,"kalle@msn.com", 2,7));
        localList.add(new Player("Johan",11,"Johan@msn.com", 11,77));



        writePlayerListToSer(localList);



    }
                ////////// RESETTING SER FILE WITH A FEW PLAYERS DUREING TESTING PHASE ////////////
}
