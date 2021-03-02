import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class HandleFile {
    //väg till filen, ÄNDRA TILL ER LOKALA PLATS...
    File f = new File("C:\\Users\\Adam-\\OneDrive\\Dokument\\GitHub\\BattleQuiz\\src\\q.ser");

    public void initSetUp() throws IOException {
        QuestionSetup questionSetup = new QuestionSetup();
        questionSetup.setUpQuestionList();
        writeToSer(questionSetup.questionList);
    }
    public void writeToSer(Object toStore) throws IOException {

        //skriver över filen varje gång den används med Objektet vi skickar med(LinkedList, ( Därav viktigt att vi
        //  hämtar filen, modifierar och sen sparar över den nya versionen )

        FileOutputStream fileOutput = new FileOutputStream(f);
        ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);

        objectOutput.writeObject(toStore);
        objectOutput.flush();
        objectOutput.close();

        System.out.println("success with writing list to file");


    }
    public LinkedList readBackSer() throws IOException, ClassNotFoundException {

        FileInputStream fileInput = new FileInputStream(f);
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);

        //Måste hämtas ut i samma format som den skickas in, enligt ListBluePrint
        ListBluePrint listAfterDeSer = new ListBluePrint((LinkedList) objectInput.readObject());


        System.out.println("succes in getting object back :");
        // .questionList i enlighet med klassen ListBluePrint
        return listAfterDeSer.questionList1;
    }
    public void addQuestion() throws IOException, ClassNotFoundException {
        // ny temporär linkedlist som hämtar  den sparade filens LinkedList.
        LinkedList tempLinkedList = readBackSer();

        LinkedList answerList = new LinkedList();
       // List<String> answerList = null;
        // Scanner för att lägga in frågorna i enlighet med QuestionBluePrint-formatet.
        Scanner sc = new Scanner(System.in);

        System.out.println("mata in fråga");
        String q = sc.nextLine();
        System.out.println("mata in svar A");

        String o1 = validateOption(sc.nextLine().toLowerCase().trim());

        System.out.println("mata in svar B");
        String o2 = validateOption(sc.nextLine().toLowerCase().trim());

        System.out.println("mata in svar C");
        String o3 = validateOption(sc.nextLine().toLowerCase().trim());

        answerList.add(o1);
        answerList.add(o2);
        answerList.add(o3);


        // Här skapas ett fråge-Objekt från klassen QuestionBluePrint där vi tar in stringar från scannern.
        QuestionBluePrint newQuestion = new QuestionBluePrint(q, answerList);

        // lägger till den skapade frågan in i tempLinkedList,
        tempLinkedList.add(newQuestion);

        // Skapar ett Objekt av ListBluePrint (för att kunna serializera på nytt), där vi skickar med den gamla listan
        // med en adderad fråga.
        ListBluePrint lbp2 = new ListBluePrint(tempLinkedList);

        //Skriver den nya listan till fil.
        writeToSer(lbp2);

         System.out.println("Du har lagt till frågan: " + newQuestion);






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
        }else{return "NÅGOT ÄR FEL, DELETA DENNA FRÅGA OCH FÖRSÖK IGEN";
        }

    }
    public void editQuestion() throws IOException, ClassNotFoundException {
        Scanner scEdit = new Scanner(System.in);
        //LinkedList tempList = readBackSer();
        System.out.println("vilken fråga vill du modifiera? (nr)");


        int numberToEdit = scEdit.nextInt()-1;
       // System.out.println(
        //tempList.get(numberToEdit));
        System.out.println(" EJ KLAR MED DENNA BIT:::: FORTSÄTT");


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

        oos.writeObject(tempBPL);
        oos.flush();
        oos.close();

        System.out.println("success with writing list to file after deleting question nr " + (NumberToRemove+1));



    }
    public void showAllQuestions() throws IOException, ClassNotFoundException {
        // Läs upp alla rad för rad med indexering synligt
        LinkedList tempList = readBackSer();

        for (int i = 0; i < tempList.size(); i++) {
            System.out.println(i +1 + " " + tempList.get(i));
        }

    }


}
