import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class QuestionSetup
{
    //Contains the information from QuestionBank.txt - Each line on an Index
    LinkedList<String> linesFromQuestionBank = new LinkedList<>();
    //List which can hold objects created from QuestioBluePrint class - Each object on an Index
    LinkedList<QuestionBluePrint> questionList = new LinkedList<>();


    //Method to read txt file and add every question as objects to questionList
    public void setUpQuestionList() throws IOException
    {

        BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/Robin/Documents/ProgrammeringEC/05- Avancerad Java/InlämningsUppgift/BattleQuiz/BattleQuiz/src/QuestionBank.txt"));
        bufferedReader.lines().forEach(line -> linesFromQuestionBank.addLast(line));

        for(String line : linesFromQuestionBank){
            String question = (line.substring(0, line.indexOf(":")));
            List <String> options = Arrays.asList(line.substring(line.indexOf(":")).split(","));
            QuestionBluePrint questionBlueprint = new QuestionBluePrint(question,options);
            questionList.add(questionBlueprint);
        }

    }

}
