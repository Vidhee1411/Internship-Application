/**
 * The Review class allows a user to write a review of either a student or a
 * company to be posted in the system.
 * @author 10/18/2021 Joshua Dupuis
 * @author 10/17/2021 Vidhee Patel 
 */
public class Review {
    private String reviewerFirstName;
    private String reviewerLastName;
    private double rating;
    private String description;
    private boolean hidden;

    /**
     * The default Review constructor creates an empty review
     */
    public Review() {
        this.reviewerFirstName = "";
        this.reviewerLastName = "";
        this.rating = 0;
        this.description = "";
        this.hidden = false;
    }

    /**
     * The parameterized Review constructor creates a review and sets its
     * attributes equal to the values the user enters.
     * @param firstName The first name of the reviewer
     * @param lastName The last name of the reviewer
     * @param rating The rating the user gives the entity they are reviewing
     * @param description The description of the review
     */
    public Review(String firstName, String lastName, double rating, String description) {
        this.reviewerFirstName = firstName;
        this.reviewerLastName = lastName;
        this.rating = rating;
        this.description = description;
    }

    /**
     * The toggleVisibility method changes the visibility of a review - if the
     * review is hidden, it reveals it and vice versa.
     */
    public void toggleVisibility() {
        this.hidden = !(this.hidden);
    }

    /**
     * The getVisibility method returns the review's visibility.
     * @return True if the review should be visible, false otherwise
     */
    public boolean getVisibility() {
        return this.hidden;
    }

    public String getFirstName(){
        return this.reviewerFirstName;
    }

    public String getLastName(){
        return this.reviewerLastName;
    }

    public Double getRating(){
        return this.rating;
    }

    public String getMessage(){
        return this.description;
    }

    /**
     * The setRating method allows a user to change the rating on a review.
     * @param rating The new rating for the review
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * The setDescription method allows the user to change the description of
     * a review.
     * @param description The new description for the review
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * The toString method turns the resume into a String that can be printed
     * to the console.
     * @return A String containing the review
     */
    public String toString() {
        return reviewerFirstName + " " + reviewerLastName + " - " + rating + "/10 \nDescription: " + description;
    }
}
