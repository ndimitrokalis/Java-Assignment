/**
 * This class purpose is to declare the QuizCategories object,
 * provides to other classes any necessary information about
 * the QuizCategories like its category_id, category_name and
 * the value of its api_url.
 */

public class QuizCategories {
    /** The category's id */
    private int category_id;
    /** The name of the category */
    private String category;
    /** The value of it's api_url */
    private String api_url;

    /**
     * The QuizCategories constructor
     * @param category_id gives the id of the category
     * @param category gives the name of the category
     * @param api_url gives the value of it's api_url
     */
    public QuizCategories(int category_id, String category, String api_url) {
        this.category_id = category_id;
        this.category = category;
        this.api_url = api_url;
    }

    /**
     * Gives the id of the category
     * @return The id
     */
    public int getCategory_id() {
        return category_id;
    }

    /**
     * Gives the name of the category
     * @return The name
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gives the value of it's api_url
     * @return The value
     */
    public String getApi_url() {
        return api_url;
    }
}
