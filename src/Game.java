import java.util.List;

/**
 * Maintains the logic of the quiz game.
 * Holds 10 quizzes each time we start a game.
 * Checks given answers and updates score.
 * Adds the current game's score to the user's total score.
 * Resets current game's score at the end of each round.
 */

public class Game {
    /** Holds the url value for the selected category */
    private String selectedCategory;
    /** List of quizzes for the game */
    private List<Quiz> quizList;
    /** List of possible answers to multiple choice questions */
    private List<String> choices;
    /** Player currently playing */
    private Player player;
    /** The score of the current game */
    private int gameScore;
    /** The current quiz to be asked */
    private int current;

    /** Sets the current player as the current logged one */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Starts a new quiz game by initializing all the necessary variables
     */
    public void startGame() {
        API api = new API();
        quizList = api.apiReader(selectedCategory);
        gameScore = 0;
        current = 0;
    }

    /**
     * Gets the game size
     * @return the size
     */
    public int getGameSize() {
        return quizList.size();
    }

    /**
     * Gets the url value of the player's selected category
     * @param categories The List of categories
     * @param choice The player's choice
     */
    public void selectCategory(List<QuizCategories> categories, int choice) {
        selectedCategory = categories.get(choice - 1).getApi_url();
    }

    /**
     * Checks the type of the question
     * @return True if the type of the question is multiple, false for else
     */
    public boolean checkType() {
        return "multiple".equals(quizList.get(current).getType());
    }

    /**
     * Gets the list of possible answers to the question
     * @return the possible answers
     */
    public List<String> getChoices() {
        return choices;
    }

    /**
     * Provides the current question
     * @return The current question
     */
    public String getCurrentQuestion() {
        return "Q" + (current+1) + ": " + quizList.get(current).getQuestion();
    }

    /**
     * Gets possible answers for the current question
     */
    public void getCurrentPossibleAnswers() {
        choices = quizList.get(current).getPossibleAnswers();
    }

    /**
     * Checks if the answer user provided is the correct one, towards the current quiz
     * and calls for the score to be updated if necessary
     * @param answer The answer to be checked
     */
    public void checkCurrentAnswer(String answer) {
        if(quizList.get(current).isCorrect(answer)) {
            System.out.println("\nCorrect");
            increaseScore();
        } else {
            System.out.println("\nIncorrect");
            System.out.println("\nCorrect Answer: " + quizList.get(current).getCorrectAnswer());
        }
        current++;
    }

    /** Increases score */
    public void increaseScore() {
        gameScore++;
    }

    /**
     * Provides the score of the current game
     * @return the current score
     */
    public int getScore() {
        return gameScore;
    }

    /** Resets the score */
    public void resetScore() {
        gameScore = 0;
    }

    /**
     * Adds the current game's final score to the user's total score
     * @return the new total score of the user
     */
    public int updateScore() {
        return player.getScore() + gameScore;
    }
}


