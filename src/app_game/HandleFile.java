package app_game;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class HandleFile {
    //väg till filen, ÄNDRA TILL ER LOKALA PLATS...
    File f = new File("/Users/Robin/Documents/ProgrammeringEC/05- Avancerad Java/InlämningsUppgift/BattleQuiz/BattleQuiz/game_files/q.ser");

    public void initSetUp() throws IOException {
        QuestionSetup questionSetup = new QuestionSetup();
        questionSetup.setUpQuestionList();
        writeToSer(questionSetup.questionList);

        System.out.println("successfully initiated questionbank från txt-file.");
    }
    public void writeToSer(Object toStore) throws IOException {

        //skriver över filen varje gång den används med Objektet vi skickar med(LinkedList, ( Därav viktigt att vi
        //  hämtar filen, modifierar och sen sparar över den nya versionen )

        FileOutputStream fileOutput = new FileOutputStream(f);
        ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

        objectOutput.writeObject(toStore);
        objectOutput.flush();
        objectOutput.close();

        //System.out.println("success with writing list to file");


    }
    public LinkedList<QuestionBluePrint> readBackSer() throws IOException, ClassNotFoundException {

        FileInputStream fileInput = new FileInputStream(f);
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);

        //Måste hämtas ut i samma format som den skickas in, enligt app_game.ListBluePrint
        ListBluePrint listAfterDeSer = new ListBluePrint((LinkedList) objectInput.readObject());


        //System.out.println("succes in readBackser()");
        // .questionList1 i enlighet med klassen app_game.ListBluePrint
        return listAfterDeSer.questionList1;
    }
    public void addQuestion() throws IOException, ClassNotFoundException {
        // ny temporär linkedlist som hämtar  den sparade filens LinkedList.
        LinkedList<QuestionBluePrint> tempLinkedList = readBackSer();


        LinkedList answerList = new LinkedList();

        // Scanner för att lägga in frågorna i enlighet med app_game.QuestionBluePrint-formatet.
        Scanner sc = new Scanner(System.in);

        System.out.println("mata in fråga");
        String q = sc.nextLine();

        System.out.println("mata in svar A");
        String o1 = validateOption(sc.nextLine().toLowerCase().trim());

        System.out.println("mata in svar B");
        String o2 = validateOption(sc.nextLine().toLowerCase().trim());


        System.out.println("mata in svar C");
        String o3 = validateOption(sc.nextLine().toLowerCase().trim());

        System.out.println("mata in svar D");
        String o4 = validateOption(sc.nextLine().toLowerCase().trim());


        answerList.add(o1);
        answerList.add(o2);
        answerList.add(o3);
        answerList.add(o4);


        // Här skapas ett fråge-Objekt från klassen app_game.QuestionBluePrint där vi tar in stringar från scannern.
        QuestionBluePrint newQuestion = new QuestionBluePrint(q, answerList);


        // lägger till den skapade frågan in i tempLinkedList,

        tempLinkedList.add(newQuestion);
        System.out.println("added");
       // System.out.println(tempLinkedList.getLast().toString());

        // Skapar ett Objekt av app_game.ListBluePrint (för att kunna serializera på nytt), där vi skickar med den gamla listan
        // med en adderad fråga.
        ListBluePrint listBluePrintToSave = new ListBluePrint(tempLinkedList);
        //System.out.println(tempLinkedList);
        //System.out.println(listBluePrintToSave.questionList1);

        //Skriver den nya listan till fil.
       writeToSer(listBluePrintToSave.questionList1);

         System.out.println("Du har lagt till frågan: " + newQuestion.question + newQuestion.options);


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
    public void editQuestion() throws IOException, ClassNotFoundException {
        //1 LÄS IN LIST FRÅN SER
        //2 POCKA UT VALD INDEX
        //3 SKAPA EN NY FRÅGA
        //4 RADERA VALT INDEX
        //5 LÄGG IN FRÅGA I SAMMA INDEX
        //6 SPARA PÅ FIL
        Scanner scEdit = new Scanner(System.in);
                //1 Read questions from .ser
        LinkedList<QuestionBluePrint> tempList = readBackSer();
        System.out.println("What question do you want to change (number)?");

                // 2 pick index to edit
        int numberToEdit = scEdit.nextInt()-1;
        System.out.println("You are about to edit question nr " + (numberToEdit +1) + ": " +
                tempList.get(numberToEdit).question +"? "+ tempList.get(numberToEdit).options);


        LinkedList answerList = new LinkedList();

        // Scanner för att lägga in frågorna i enlighet med app_game.QuestionBluePrint-formatet.
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter question:");
        String q = sc.nextLine();

        System.out.println("Enter answer A");
        String o1 = validateOption(sc.nextLine().toLowerCase().trim());

        System.out.println("Enter answer B");
        String o2 = validateOption(sc.nextLine().toLowerCase().trim());


        System.out.println("Enter answer C");
        String o3 = validateOption(sc.nextLine().toLowerCase().trim());

        System.out.println("Enter answer D");
        String o4 = validateOption(sc.nextLine().toLowerCase().trim());


        answerList.add(o1);
        answerList.add(o2);
        answerList.add(o3);
        answerList.add(o4);


        // Här skapas ett fråge-Objekt från klassen app_game.QuestionBluePrint där vi tar in stringar från scannern.
                    //3 Create a new question-object to store scanned info in.
        QuestionBluePrint newQuestion = new QuestionBluePrint(q, answerList);

                    //4 Remove previous question at this index.
        tempList.remove(numberToEdit);

        // lägger till den skapade frågan in i tempLinkedList,
                    // 5 Add the edited question in the same index-position as the deleted one.
        tempList.add(numberToEdit, newQuestion);
        System.out.println("Question number " + (numberToEdit + 1) + " is edited to :" + newQuestion.question + "? " + newQuestion.options + ".") ;


        // Skapar ett Objekt av app_game.ListBluePrint (för att kunna serializera på nytt), där vi skickar med den gamla listan
        // med en adderad fråga på rätt index-plats.
                // 6 Create object for serialization and fill it with the edited linkedlist AND save it.
        ListBluePrint listBluePrintToSave = new ListBluePrint(tempList);


        //Skriver den nya listan till fil.
        writeToSer(listBluePrintToSave.questionList1);

      //  System.out.println("Du har lagt till frågan: " + newQuestion.question + newQuestion.options);

        /////////







    }
    public void removeQuestion() throws IOException,ClassNotFoundException {

        Scanner scRemove = new Scanner(System.in);

        // LÄS IN, TA BORT, SPARA
        LinkedList tempList = readBackSer();

        System.out.println("vilken fråga vill du ta bort? (nr i show all questions)");
        int NumberToRemove = scRemove.nextInt()-1;
        tempList.remove(NumberToRemove);

        //spara
        ListBluePrint tempBPL = new ListBluePrint(tempList);

        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(tempBPL.questionList1);
        oos.flush();
        oos.close();

        System.out.println("Successful with removal of question number  " + (NumberToRemove+1));

    }
    public void showAllQuestions() throws IOException, ClassNotFoundException {
        // Läs upp alla rad för rad med indexering synligt

        LinkedList<QuestionBluePrint> tempListReadAll = readBackSer();



        for (int i = 0; i < tempListReadAll.size(); i++) {

            System.out.println(i +1 + " " +tempListReadAll.get(i).question + " - " + tempListReadAll.get(i).options);

        }

    }



}
