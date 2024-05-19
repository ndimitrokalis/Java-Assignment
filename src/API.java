import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is in charge of creating a list of quizzes,
 * it creates 10 quizzes at the time as objects using the quiz API.
 */

public class API {
    /** Initializes the ErrorLogger */
    private ErrorLogger errorLogger;

    /** Creates a new instance of the ErrorLogger */
    public API() {
        errorLogger = new ErrorLogger();
    }

    /**
     * Reads 10 quizzes from the API, converts them into JSON objects
     * and places them in an JSONArray. Then uses getQuizzes to turn them
     * into Quiz objects and put them in a list.
     * @return The Quiz list.
     */

    public List<Quiz> apiReader(String category) {
        List<Quiz> quizList = new ArrayList<>();
        try {
            URL url = new URL("https://opentdb.com/api.php?amount=10" + category);
            InputStreamReader isr = new InputStreamReader(url.openStream());
            JSONParser jp = new JSONParser();
            JSONObject jo = (JSONObject) jp.parse(isr);
            JSONArray quizzes = (JSONArray) jo.get("results");
            for(Object item : quizzes ) {
                quizList.addAll(getQuizzes((JSONObject) item));
            }
        } catch (MalformedURLException e) {
            errorLogger.error(new Date() + "," + getClass().getSimpleName() + ", " + e.getMessage());
            System.out.println("\nError: Invalid URL");
        } catch (IOException e) {
            errorLogger.error(new Date() + "," + getClass().getSimpleName() + ", " + e.getMessage());
            System.out.println("\nError: Unreadable URL");
        } catch (ParseException e) {
            errorLogger.error(new Date() + "," + getClass().getSimpleName() + ", " + e.getMessage());
            System.out.println("\nError: Parse Failure");
        }
        return quizList;
    }

    /**
     * Makes a Quiz object at a time out of the JSONObjects inside the JSONArray.
     * @param item is the JSONObject inside the JSONArray.
     * @return a list of Quiz objects, adding one Quiz object at a time.
     */

    private static List<Quiz> getQuizzes(JSONObject item) {
        String type = (String) item.get("type");
        String difficulty = (String) item.get("difficulty");
        String category = (String) item.get("category");
        String question = (String) item.get("question");
        String correctAnswer = (String) item.get("correct_answer");
        JSONArray incorrectAnswersArray = (JSONArray) item.get("incorrect_answers");
        List<String> incorrectAnswers = new ArrayList<>();
        for (Object answer : incorrectAnswersArray) {
            incorrectAnswers.add((String) answer);
        }
        List<Quiz> quizList = new ArrayList<>();
        Quiz aQuiz = new Quiz(type, difficulty, category, question, correctAnswer, incorrectAnswers);
        quizList.add(aQuiz);
        return quizList;
    }
}