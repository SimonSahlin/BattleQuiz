package app_game;

import java.io.Serializable;
import java.util.LinkedList;


public class ListBluePrint implements Serializable {
    LinkedList<QuestionBluePrint> questionList1;



    public ListBluePrint(LinkedList questionList){
        this.questionList1 = questionList;}
}