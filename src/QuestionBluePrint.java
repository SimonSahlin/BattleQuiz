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

    public QuestionBluePrint() {
    }
}

