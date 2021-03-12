
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

    LinkedList<Player> playerlistlist;





    ////////////// testarea ///////

    private int score;

    private int counterPlayedGames;

    public Player() {}
    public Player(LinkedList playerlistlist){

        this.playerlistlist = playerlistlist;}
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
    // TO BE IMPLEMENTEND
    /*
     public void resetQuestionsFromTextFile() throws IOException {
        //Method to read txt file and add every question as objects to questionList
        //Contains the information from QuestionBank.txt - Each line on an Index
        LinkedList<String> linesFromQuestionBank = new LinkedList<>();
        //List which can hold objects created from QuestionHandler class - Each object on an Index
        LinkedList<QuestionHandler> questionList = new LinkedList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(pathQuestionTextFile));
        bufferedReader.lines().forEach(line -> linesFromQuestionBank.addLast(line));
        // bufferedReader.lines().forEach(linesFromQuestionBank::addLast); SAMMA SOM OVAN MEN MED METODREFERENS, OM VI VILL.

        for(String line : linesFromQuestionBank){
            String question = (line.substring(0, line.indexOf(":")));
            List<String> options = Arrays.asList(line.substring(line.indexOf(":")).split(","));
            options.set(0, options.get(0).replaceFirst(":",""));
            questionList.add(new QuestionHandler(question, options));
        }
        writeToSer(questionList);
        System.out.println("successfully initiated questionbank from txt-file.");
    }
        public void removeQuestion() throws IOException,ClassNotFoundException {
        Scanner scRemove = new Scanner(System.in);
        LinkedList<QuestionHandler> tempList = readBackSer();

        System.out.println("Which question do you want to remove? (number from list of questions)");

        try {
            int NumberToRemove = scRemove.nextInt() - 1;
            tempList.remove(NumberToRemove);

            //Write modified list to ser-file again.
            writeToSer(tempList);
            System.out.println("Successful with removal of question number  " + (NumberToRemove + 1));
        }catch(Exception e){
            System.out.println("Enter a valid number..\n");
            removeQuestion();
        }

    }
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

    public void writePlayerListToSer(Object toStore) throws IOException {
        //Overwrites the info on StoredPlayers.ser with the info sent as a parameter in this method.

        FileOutputStream fileOutput = new FileOutputStream(playerSerFile);
        ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

        objectOutput.writeObject(toStore);
        objectOutput.flush();
        objectOutput.close();
    }
    public LinkedList<Player> readPlayerListFromSer() throws IOException, ClassNotFoundException {

        FileInputStream fileInput = new FileInputStream(playerSerFile);
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        Player listAfterDeSer = new Player((LinkedList) objectInput.readObject());


        return  listAfterDeSer.playerlistlist;
        //
        //obj1.playerlistlist;
    }
    /*
    public void addQuestion() throws IOException, ClassNotFoundException {
        // New temp. linkedList to store info from q.ser(readBackser)
        LinkedList<QuestionHandler> tempLinkedList = readBackSer();

        LinkedList answerList = new LinkedList();

        // Scanner to insert new question.
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter new Question:");
        String q = sc.nextLine();
        System.out.println("Enter answer A:");
        String answer1 = validateOption(sc.nextLine().toLowerCase().trim());
        System.out.println("Enter answer B:");
        String answer2 = validateOption(sc.nextLine().toLowerCase().trim());
        System.out.println("Enter answer C:");
        String answer3 = validateOption(sc.nextLine().toLowerCase().trim());
        System.out.println("Enter answer D:");
        String answer4 = validateOption(sc.nextLine().toLowerCase().trim());

        // adding answers to list
        answerList.add(answer1);
        answerList.add(answer2);
        answerList.add(answer3);
        answerList.add(answer4);

        // Add the new question to templinkedlist
        tempLinkedList.add(new QuestionHandler(q, answerList));

        //Write the modified list back to q.ser.
        writeToSer(tempLinkedList);

        System.out.println("Question added: " + tempLinkedList.getLast().question + tempLinkedList.getLast().options);
    }
    public String validateOption(String strOption){
        System.out.println("Is this the correct option? - Answer with 'y' or 'n' ( YES or NO)");
        Scanner scValidate = new Scanner(System.in);

        String validationAnswer = scValidate.next().toLowerCase();

        if(validationAnswer.equals("y") ){
            return "*"+strOption ;
        }else if(validationAnswer.equals("n")){
            return strOption;
        }else{return "Wrong validation input";
        }

    }
    public void showAllQuestions() throws IOException, ClassNotFoundException {
        // Printing all questions, one row per question with a numeric list in the eyes of the viewer.
        LinkedList<QuestionHandler> tempListReadAll = readBackSer();
        for (int i = 0; i < tempListReadAll.size(); i++) {
            System.out.println(i +1 + " " +tempListReadAll.get(i).question + " - " + tempListReadAll.get(i).options);
        }
    }

     */


    public void test1() throws IOException, ClassNotFoundException {
/**/
        LinkedList<Player> localList = new LinkedList<>();

        localList.add(new Player("Johan",11,"Joahne@mail.com",3,4));
        localList.add(new Player("Kalle",22,"kalle@mail.com",1,5));
        localList.add(new Player("lisa", 22,"asd",3,8));

        System.out.println(localList.getLast().getName() + " " +
                localList.getLast().getAge() + " " +
                localList.getLast().geteMail() + " " +
                localList.getLast().getScore()+ " " +
                localList.getLast().getPlayed_games()
        );



       writePlayerListToSer(localList);

        LinkedList<Player> listfromfile = readPlayerListFromSer();

        System.out.println(listfromfile.getLast().getName() + " " +
                listfromfile.getLast().getAge() + " " +
                listfromfile.getLast().geteMail() + " " +
                listfromfile.getLast().getScore()+ " " +
                listfromfile.getLast().getPlayed_games() + " " +
                listfromfile.getLast().toString()
        );




    }

    ////////////// testarea ///////
}
