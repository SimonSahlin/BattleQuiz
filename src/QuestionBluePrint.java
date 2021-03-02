import java.io.Serializable;
import java.util.List;

public class QuestionBluePrint implements Serializable
{
    String question;
    List<String> options;



    QuestionBluePrint(String question, List<String> options)
    {
        this.question = question;
        this.options =  options;

    }


}





/*
import java.io.Serializable;

public class QuestionBluePrint implements Serializable {
    String q;
    String a1;
    String a2;
    String a3;

    public QuestionBluePrint(String q, String a1, String a2, String a3){
        this.q = q;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;

    }


    public String toString(){
        return  q + ": A) "+ a1 + " B) " + a2 + " C) " + a3;
    }


}
*/