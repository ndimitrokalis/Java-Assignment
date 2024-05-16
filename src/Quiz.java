import java.util.Collections;
import java.util.List;

/**
 * This class is in charge of declaring the Quiz object,
 * provides to other classes any necessary information about
 * the Quiz like its question, its possible answers as well as
 * checking if the answer of the user is correct.
 */

public class Quiz {
    /** The question's type of the Quiz */
    private String type;
    /** The difficulty of the Quiz */
    private String difficulty;
    /** The question's category of the Quiz */
    private String category;
    /** The question of the Quiz */
    private String question;
    /** The correct answer to the Quiz */
    private String correctAnswer;
    /** A list with the incorrect answers to the Quiz */
    private List<String> incorrectAnswers;

    /**
     * The Quiz constructor
     * @param type gives the type of the question
     * @param difficulty gives the difficulty of the question
     * @param category gives the category of the question
     * @param question gives the question of the Quiz
     * @param correctAnswer gives the correct answer to the question
     * @param incorrectAnswers gives the list of incorrect answers to the question
     */
    public Quiz(String type, String difficulty, String category, String question, String correctAnswer, List<String> incorrectAnswers) {
        this.type = type;
        this.difficulty = difficulty;
        this.category = category;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }

    /**
     * Gives the type of the question
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Gives the question of the Quiz
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Gives the correct answer to the question
     * @return the correct answer
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Gives the list of all possible answers,
     * meaning both the correct and incorrect answers to the question
     * after it shuffles them.
     * @return the shuffled list of possible answers
     */
    public List<String> getPossibleAnswers() {
        List<String> possibleAnswers = incorrectAnswers;
        possibleAnswers.add(correctAnswer);
        Collections.shuffle(possibleAnswers);
        return possibleAnswers;
    }

    /**
     * Checks if the string answer of the player is
     * the correct answer to the Quiz
     * @param answer the string answer
     * @return true if is correct or false otherwise
     */
    public boolean isCorrect(String answer) {
        return this.correctAnswer.equals(answer);
    }

    /**
     * Gives the difficulty of the question
     * @return the difficulty
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Gives the category of the question
     * @return the category
     */
    public String getCategory() {
        return category;
    }
}
