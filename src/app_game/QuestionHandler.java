package app_game;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class QuestionHandler implements Serializable {
    // PATHS:
    // change to your local absolutepath [serFile -> q.ser], [pathQuestionTextFile -> QuestionBank.txt].
    File serFile = new File("/Users/Robin/Documents/ProgrammeringEC/05- Avancerad Java/InlämningsUppgift/BattleQuiz/BattleQuiz/game_files/q.ser");
    String pathQuestionTextFile ="/Users/Robin/Documents/ProgrammeringEC/05- Avancerad Java/InlämningsUppgift/BattleQuiz/BattleQuiz/game_files/QuestionBank.txt";

                // VARIABLES
    LinkedList<QuestionHandler> questionList1;
    String question;
    List<String> options;
                // CONSTRUCTORS:
    public  QuestionHandler(){} // tom konstruktor
    public QuestionHandler(LinkedList questionList){ // konstruktor som används vid skapande av listor för serialisering( Tidigare ListBluePrint)
        this.questionList1 = questionList;}
    public QuestionHandler(String question, List<String> options){ // konstruktor som används vid skapande av enskilda frågor. (tidigare QuestionBluePrint)
        this.question = question;
        this.options =  options;

    }

                // METHODS:
        public void resetQuestionsFromTextFile() throws IOException {
        // QUESTIONBANK SETUP (questionsetup-class) merged med initSetUp

        //Method to read txt file and add every question as objects to questionList

        //Contains the information from QuestionBank.txt - Each line on an Index
        LinkedList<String> linesFromQuestionBank = new LinkedList<>();
        //List which can hold objects created from QuestionHandler class - Each object on an Index
        LinkedList<QuestionHandler> questionList = new LinkedList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(pathQuestionTextFile));
        bufferedReader.lines().forEach(line -> linesFromQuestionBank.addLast(line));

        for(String line : linesFromQuestionBank){
            String question = (line.substring(0, line.indexOf(":")));
            List <String> options = Arrays.asList(line.substring(line.indexOf(":")).split(","));
            options.set(0, options.get(0).replaceFirst(":",""));
            questionList.add(new QuestionHandler(question, options));
        }

        writeToSer(questionList);

        System.out.println("successfully initiated questionbank from txt-file.");
        System.out.println("resetQuestionsFromTextFile COMPLETE"); // TA BORT EFTER FUNK.

    }
    public void writeToSer(Object toStore) throws IOException {

        //skriver över filen varje gång den används med Objektet vi skickar med(LinkedList, ( Därav viktigt att vi
        //  hämtar filen, modifierar och sen sparar över den nya versionen )

        FileOutputStream fileOutput = new FileOutputStream(serFile);
        ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

        objectOutput.writeObject(toStore);
        objectOutput.flush();
        objectOutput.close();

        System.out.println("WRITE TO SER COMPLETE");
    }
    public LinkedList<QuestionHandler> readBackSer() throws IOException, ClassNotFoundException {

        FileInputStream fileInput = new FileInputStream(serFile);
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);

        //Måste hämtas ut i samma format som den skickas in, enligt app_game.ListBluePrint
        QuestionHandler listAfterDeSer = new QuestionHandler((LinkedList) objectInput.readObject());


        //System.out.println("succes in readBackser()");
        // .questionList1 i enlighet med klassen app_game.ListBluePrint
        System.out.println("readBackSer COMPLETE."); // TA BORT EFTER FUNK.
        return listAfterDeSer.questionList1;
    }
    public void addQuestion() throws IOException, ClassNotFoundException {
        // ny temporär linkedlist som hämtar  den sparade filens LinkedList.
        LinkedList<QuestionHandler> tempLinkedList = readBackSer();


        LinkedList answerList = new LinkedList();

        // Scanner för att lägga in frågorna i enlighet med app_game.QuestionBluePrint-formatet.
        Scanner sc = new Scanner(System.in);

        System.out.println("mata in fråga");
        String q = sc.nextLine();

        System.out.println("mata in svar A");
        String answer1 = validateOption(sc.nextLine().toLowerCase().trim());

        System.out.println("mata in svar B");
        String answer2 = validateOption(sc.nextLine().toLowerCase().trim());


        System.out.println("mata in svar C");
        String answer3 = validateOption(sc.nextLine().toLowerCase().trim());

        System.out.println("mata in svar D");
        String answer4 = validateOption(sc.nextLine().toLowerCase().trim());

        // Lägger till svar i answelist för att skicka vidare via construktorn.
        answerList.add(answer1);
        answerList.add(answer2);
        answerList.add(answer3);
        answerList.add(answer4);

        // lägger till den skapade frågan in i tempLinkedList,
        tempLinkedList.add(new QuestionHandler(q, answerList));


        //Skriver den modifierade listan till fil.
        writeToSer(tempLinkedList);

       System.out.println("Du har lagt till frågan: " + tempLinkedList.getLast().question + tempLinkedList.getLast().options);

        System.out.println("addQuestion COMPLETE"); // TA BORT EFTER FUNK.
        //öppna lägg till spara printa

    }
    public String validateOption(String strOption){
        System.out.println("Was this the correct option? - Answer with 'y' or 'n' ( YES or NO)");
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
        // Läs upp alla rad för rad med indexering synligt

        LinkedList<QuestionHandler> tempListReadAll = readBackSer();



        for (int i = 0; i < tempListReadAll.size(); i++) {


            System.out.println(i +1 + " " +tempListReadAll.get(i).question + " - " + tempListReadAll.get(i).options);



        }

    }
    public void removeQuestion() throws IOException,ClassNotFoundException {

        Scanner scRemove = new Scanner(System.in);

        // LÄS IN, TA BORT, SPARA
        LinkedList<QuestionHandler> tempList = readBackSer();

        System.out.println("vilken fråga vill du ta bort? (nr i show all questions)");
        int NumberToRemove = scRemove.nextInt()-1;
        tempList.remove(NumberToRemove);

        //spara över ser-fil
        writeToSer(tempList);

        System.out.println("Successful with removal of question number  " + (NumberToRemove+1));

    }
    public void editQuestion() throws IOException, ClassNotFoundException {
        //1 LÄS IN LIST FRÅN SER
        //2 POCKA UT VALD INDEX
        //3 SKAPA EN NY FRÅGA
        //4 RADERA VALT INDEX
        //5 LÄGG IN FRÅGA I SAMMA INDEX
        //6 SPARA PÅ FIL


        Scanner scEdit = new Scanner(System.in);
        //1 Read questions from .ser
        LinkedList<QuestionHandler> tempList = readBackSer();
        System.out.println("What question do you want to change (number)?");

        // 2 pick index to edit
        int numberToEdit = scEdit.nextInt()-1;
        System.out.println("You are about to edit question nr " + (numberToEdit +1) + ": " +
                tempList.get(numberToEdit).question +"? "+ tempList.get(numberToEdit).options);


        LinkedList answerList = new LinkedList();

        // Scanner för att lägga in frågorna i enlighet med QuestionBluePrint-formatet.
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

        // lägger till den skapade frågan in i tempLinkedList,
        // 5 Add the edited question in the same index-position as the deleted one.
        tempList.add(numberToEdit, new QuestionHandler(q,answerList));
        //System.out.println("Question number " + (numberToEdit + 1) + " is edited to :" + tempList.get(numberToEdit).question + "? " + tempList.get(numberToEdit).options + ".") ;
        //System.out.println("Question number " + (numberToEdit + 1) + " is edited to :" + newQuestion.question + "? " + newQuestion.options + ".") ;


        // Skapar ett Objekt av app_game.ListBluePrint (för att kunna serializera på nytt), där vi skickar med den gamla listan
        // med en adderad fråga på rätt index-plats.
        // 6 Create object for serialization and fill it with the edited linkedlist AND save it.
        //QuestionHandler listToSave = new QuestionHandler(tempList);


        //Skriver den nya listan till fil.

        System.out.println("Question number " + (numberToEdit + 1) + " is edited to :" + tempList.get(numberToEdit).question + "? " + tempList.get(numberToEdit).options + ".") ;
        writeToSer(tempList);

        //  System.out.println("Du har lagt till frågan: " + newQuestion.question + newQuestion.options);

        /////////







    }


}

















