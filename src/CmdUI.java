import java.util.List;
import java.util.Scanner;

/**
 * Provides the Command Line (CMD) user interface for the quiz game
 */

public class CmdUI {
    /** reads user input from the command line */
    private Scanner scanner;
    /** game logic */
    private Game game;
    /** player currently playing */
    private Player player;

    /**
     * Default constructor, initializes variables
     */
    public CmdUI() {
        game = new Game();
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the login menu, giving player the choice to
     * either create an account or login to an existing one
     * @return the user's choice
     */
    public int loginMenu() {
        System.out.println("\n1. Login");
        System.out.println("2. Register");
        System.out.println("0. EXIT");
        System.out.print("\nSelect: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    /**
     * Displays the game menu, providing the player
     * the option to start the game or view his or other player's stats
     * @return the user's choice
     */
    public int playerMenu() {
        System.out.println("\n1. Play");
        System.out.println("2. Game History");
        System.out.println("3. Scoreboards");
        System.out.println("0. LOG OUT");
        System.out.print("\nSelect: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    /**
     * Displays the statistics menu and the option
     * to check the scoreboard for either highest score
     * or highest consistency
     * @return the user's choice
     */
    public int statisticsMenu() {
        System.out.println("\n1. Best scores");
        System.out.println("2. Most consistent players");
        System.out.println("0. GO BACK");
        System.out.print("\nSelect: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    /**
     * Displays the available categories and asks the player
     * to choose, it then calls the selectCategory method from
     * player class providing with the list of categories and
     * the player's choice
     */
    public void DisplayCategory() {
        System.out.println();
        List<QuizCategories> categories = player.getCategories();
        for (QuizCategories aCategory : categories) {
            System.out.println(aCategory.getCategory_id() + ") " + aCategory.getCategory());
        }
        System.out.print("\nSelect: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        game.selectCategory(categories, choice);
    }

    /**
     * Lists the user's game history
     */
    public void showPlayerHistory() {
        System.out.println("\n#  Player /  Date   /  Time  /  Score");
        int index = 1;
        if (player.getPlayerHistory().isEmpty())
            System.out.println("Empty");
        else {
            for (String item : player.getPlayerHistory()) {
                System.out.println(index + ". " + item);
                index++;
            }
        }
    }

    /**
     * Gets the next quiz from the list and provides either
     * the possible answers if it's a multiple choice question,
     * or True or False
     */
    public void nextQuestion() {
        System.out.println("\n" + game.getCurrentQuestion());
        if (game.checkType()) {
            System.out.print("\n");
            int index = 1;
            for (String item : game.getChoices()) {
                System.out.println(index + ") " + item);
                index++;
            }
        } else {
            System.out.println("\nTrue or False ?");
        }
        System.out.println("\nCurrent score: " + game.getScore());
        System.out.print("\nAnswer: ");
        String answer = scanner.nextLine();
        game.checkCurrentAnswer(answer);
    }

    /**
     * Asks the player if they wish to play another round
     * @return the user's response for yes or no as "y" or "n"
     */
    public String playAgain() {
        System.out.print("\nPlay Again (y/n): ");
        String response = scanner.nextLine();
        return response;
    }

    /**
     * Runs the game by using the previous methods
     */
    public void run() {
        int choice = 0;
        System.out.println("WELCOME TO THE ULTIMATE QUIZ GAME\n");
        System.out.println("Login or create an account to continue\n");
        do {
            choice = loginMenu();
            switch (choice) {
                case 1:
                    if (login()) {
                        int option1;
                        do {
                            option1 = playerMenu();
                            switch (option1) {
                                case 1:
                                    DisplayCategory();
                                    String response;
                                    do {
                                        game.startGame();
                                        for (int i = 0; i < 10; i++) {
                                            nextQuestion();
                                        }
                                        System.out.println("\nFinal score: " + game.getScore());
                                        player.saveScore();
                                        player.saveGame();
                                        player.loadPlayer();
                                        game.resetScore();
                                        response = playAgain();
                                    } while (response.equals("y"));
                                    break;
                                case 2:
                                    showPlayerHistory();
                                    break;
                                case 3:
                                    int option3;
                                    do {
                                        option3 = statisticsMenu();
                                        switch (option3) {
                                            case 1:
                                                System.out.println("\n     SCOREBOARD");
                                                System.out.println("\n#  Player / Score");
                                                int position1 = 1;
                                                if (player.getScoreboard().isEmpty())
                                                    System.out.println("Empty");
                                                else {
                                                    for (String item : player.getScoreboard()) {
                                                        System.out.println(position1 + ". " + item);
                                                        position1++;
                                                    }
                                                }
                                                break;
                                            case 2:
                                                System.out.println("\n     MOST CONSISTENT");
                                                System.out.println("\n#  Player / AVG Score");
                                                int position2 = 1;
                                                for (String item : player.getAverageScores()) {
                                                    System.out.println(position2 + ". " + item);
                                                    position2++;
                                                }
                                                break;
                                        }
                                    } while (option3 != 0);
                            }
                        } while (option1 != 0);
                    } else {
                        System.out.println("\nIncorrect credentials");
                        break;
                    }
                    break;
                case 2:
                    register();
                    break;
            }
        } while (choice != 0);
        System.out.println("\nThank you for playing <3");
        System.out.println("\nGoodbye! :)");
        System.out.println("\nMade by Nick Dimitrokalis");
    }

    /**
     * Asks user for a unique new username and password
     * to create an account
     */
    public void register() {
        System.out.print("\nGive Username: ");
        String username = scanner.nextLine();
        System.out.print("Give Password: ");
        String password = scanner.next();
        player = new Player(username, password);
        player.saveNewPlayer();
    }

    /**
     * Asks user for username and password to login into an existing account,
     * checks if the account exists and or the credentials the user provided are correct
     * @return the answer to whether the account exists or credentials are correct
     */
    public boolean login() {
        System.out.print("\nUsername: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        player = new Player(username, password);
        boolean ok = player.loadPlayer();
        game.setPlayer(player);
        player.setGame(game);
        return ok;
    }
}
