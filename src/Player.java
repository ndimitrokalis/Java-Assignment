import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is in charge of everything that has to do with the players,
 * this includes saving or loading a user, saving its total score or latest game,
 * getting the currently logged in player's game history or the scoreboard and
 * providing statistics about all players.
 */

public class Player {
    /** Initializes and gets the connection from the Database */
    private Connection c = Database.getConnection();
    /** Initializes and creates new instance of the ErrorLogger */
    private ErrorLogger errorLogger = new ErrorLogger();
    /** Initializes the game */
    private Game game;
    /** The new player's id */
    private int id;
    /** The new player's username */
    private String username;
    /** The new player's password */
    private String password;
    /** The new player's score */
    private int score;

    /**
     * The currently logged in player constructor
     * @param username gives current player username
     * @param password gives current player password
     */
    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * The new player constructor
     * @param id gives new player's id
     * @param username gives new player's username
     * @param password gives new player's password
     * @param score gives new player's score
     */
    public Player(int id, String username, String password, int score) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.score = score;
    }

    /**
     * Saves the new player into the database after it sanitizes user's input
     */
    public void saveNewPlayer() {
        String query = "INSERT INTO players (username, password, score) VALUES (?, ?, 0)";
        try {
            assert c != null;
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, username);
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
            ps.setString(2, hashedPassword);
            ps.execute();
        } catch (SQLException e) {
            errorLogger.error(new Date() + "," + getClass().getSimpleName() + ", " + e.getMessage());
            System.out.println("\nUsername is taken, please try again.");
        }
    }

    /**
     * Checks if the player provided exists and or if the credentials are correct
     * @return True if the player exists and the provided credentials are correct or
     * false if the player exists but its credentials are wrong
     */
    public boolean loadPlayer() {
        String query = "SELECT * FROM players WHERE username=?";
        try {
            assert c != null;
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                boolean match = BCrypt.checkpw(password, rs.getString("password"));
                if(match) {
                    id = rs.getInt("id");
                    score = rs.getInt("score");
                    return true;
                }
            }
        } catch (SQLException e) {
            errorLogger.error(new Date() + "," + getClass().getSimpleName() + ", " + e.getMessage());
            System.out.println("\nError: loading player");
        }
        return false;
    }

    /**
     * Saves the new score of the currently logged-in user to the database
     */
    public void saveScore() {
        int newScore = game.updateScore();
        String query = "UPDATE players SET score=? WHERE id=?";
        try {
            assert c != null;
            PreparedStatement st = c.prepareStatement(query);
            st.setInt(1, newScore);
            st.setInt(2, id);
            st.execute();
        } catch (SQLException e) {
            errorLogger.error(new Date() + "," + getClass().getSimpleName() + ", " + e.getMessage());
            System.out.println("\nError: saving score");
        }
    }

    /**
     * Saves the latest game of the currently logged-in user to the database
     */
    public void saveGame() {
        String query = "INSERT INTO history (username, date, score, player_id) VALUES (?, NOW(), ?, ?)";
        try {
            assert c != null;
            PreparedStatement st = c.prepareStatement(query);
            st.setString(1, username);
            st.setInt(2, game.getScore());
            st.setInt(3, id);
            st.execute();
        } catch (SQLException e) {
            errorLogger.error(new Date() + "," + getClass().getSimpleName() + ", " + e.getMessage());
            System.out.println("\nError: saving game");
        }
    }

    /**
     * Gets the game history of the currently logged-in player
     * @return The player's history
     */
    public List<String> getPlayerHistory() {
        List<String> history = new ArrayList<>();
        String query = "SELECT * FROM history WHERE player_id=?";
        try {
            assert c != null;
            PreparedStatement st = c.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                history.add(rs.getString("username") + "   " + rs.getString("date") + "       " + rs.getString("score"));
            }
        } catch (SQLException e) {
            errorLogger.error(new Date() + "," + getClass().getSimpleName() + ", " + e.getMessage());
            System.out.println("\nError: getting player's history");
        }
        return history;
    }

    /**
     * Gets the scoreboard of players with the highest scores
     * @return The players with the highest scores
     */
    public List<String> getScoreboard() {
        List<String> topScores = new ArrayList<>();
        String query = "SELECT username, score FROM players ORDER BY score DESC";
        try {
            assert c != null;
            PreparedStatement st = c.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                topScores.add(rs.getString("username") + "         " + rs.getString("score"));
            }
        } catch (SQLException e) {
            errorLogger.error(new Date() + "," + getClass().getSimpleName() + ", " + e.getMessage());
            System.out.println("\nError: loading scoreboard");
        }
        return topScores;
    }

    /**
     * Gets the scoreboard of players with the most consistent scores
     * @return The average score of each player in descending order
     */
    public List<String> getAverageScores() {
        List<String> averageScores = new ArrayList<>();
        String query = "SELECT username, AVG(score) AS Average FROM history GROUP BY player_id ORDER BY Average DESC";
        try {
            assert c != null;
            PreparedStatement st = c.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                averageScores.add(rs.getString("username") + "        " + rs.getString("Average"));
            }
        } catch (SQLException e) {
            errorLogger.error(new Date() + "," + getClass().getSimpleName() + ", " + e.getMessage());
            System.out.println("\nError: loading average scores");
        }
        return averageScores;
    }

    /**
     * Gets the available categories of quizzes and puts them in a list
     * @return The list of categories
     */
    public List<QuizCategories> getCategories() {
        List<QuizCategories> categories = new ArrayList<>();
        String query = "SELECT * FROM categories";
        try {
            PreparedStatement st = c.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                int category_id = rs.getInt("category_id");
                String category = rs.getString("category");
                String api_url = rs.getString("api_url");

                QuizCategories aCategory = new QuizCategories(category_id, category, api_url);
                categories.add(aCategory);
            }
        } catch (SQLException e) {
            errorLogger.error(new Date() + "," + getClass().getSimpleName() + ", " + e.getMessage());
            System.out.println("\nError: loading categories");
        }
        return categories;
    }

    /** Sets the game as the current running game */
    public void setGame(Game game) {
        this.game = game;
    }

    /** Gets previous total score of the currently logged-in player */
    public int getScore() {
        return score;
    }


}
