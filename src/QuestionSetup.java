import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class QuestionSetup
{
    LinkedList<String> questions = new LinkedList<>();
    LinkedList<QuestionBluePrint> questionList = new LinkedList<>();



    public void setUpQuestionList() throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/Robin/Documents/ProgrammeringEC/05- Avancerad Java/InlämningsUppgift/testQuizGame/src/QuestionBank.txt"));
        bufferedReader.lines().forEach(line -> questions.addLast(line));

        for(String line : questions){
            String question = (line.substring(0, line.indexOf(":")));
            List <String> options = Arrays.asList(line.substring(line.indexOf(":")).split(","));
            QuestionBluePrint questionBlueprint = new QuestionBluePrint(question,options);
            questionList.add(questionBlueprint);
        }

    }

}
