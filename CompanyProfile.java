import java.util.UUID;
/**
 * The class creates a company profile in the internship system.
 * @author Vidhee Patel abd Josh DuPuis
 */
import java.util.ArrayList;
import java.util.UUID;

public class CompanyProfile {
    private UUID companyID;
    private String companyName;
    private String hqAddress;
    private String description;
    private ArrayList<JobListing> listings;
    private ArrayList<Review> reviewList;

    /**
     * The CompanyProfile constructor creates a CompanyProfile object that
     * contains a company's name, location, and a short description about the
     * company.
     * @param companyName The name of the company
     * @param hqAddress The address of the company's headquarters
     * @param description The short description about the company
     * @param id The UUID of the company.
     */
    public CompanyProfile(String companyName, String hqAddress, String description){
        this.companyName = companyName;
        this.hqAddress = hqAddress;
        this.description = description;
        listings = new ArrayList<JobListing>();
        reviewList = new ArrayList<Review>();
    }
    /**
     *  Company profile constructor used when loading data 
     * @param companyName the name of the company
     * @param hqAddress the address of the company hq
     * @param description description of the company
     * @param companyID company uuid
     * @param reviews reviews of the company
     * @param listings joblistings associated with the company
     */
    public CompanyProfile(String companyName, String hqAddress, String description,UUID companyID, ArrayList<Review> reviews, ArrayList<JobListing> listings ){
        this.companyName = companyName;
        this.hqAddress = hqAddress;
        this.description = description;
        this.companyID = companyID;
        this.reviewList = reviews;
        this.listings = listings;
    }

    public UUID getUUID(){
        return this.companyID;
    }

    /**
     * The getCompanyName method returns the name of the company.
     * @return The name of the company
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * The setCompanyName method allows a user to change the name of a company.
     * @param name The new name of the company
     */
    public void setCompanyName(String name) {
        this.companyName = name;
    }

    /**
     * The addReview method adds a review to the profile's ArrayList of
     * Reviews.
     * @param review The review to be added to the company's ArrayList
     */
    public void addReview(Review review){
        this.reviewList.add(review);
    }

    /**
     * The getReviews method returns a list of all of the reviews
     * associated with the company.
     * @return An ArrayList containing all of the reviews associated with the
     * company
     */
    public ArrayList<Review> getReviews() {
        return reviewList;
    }

    /**
     * The getDescription method returns the description of the company.
     * @return The description of the company
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * The setDescription method alters the description of the company that
     * appears on their profile.
     * @param description The new description of the company that will appear
     * on their profile.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * The getAddress method returns the address of a company's headquarters.
     * @return A company's headquarters address
     */
    public String getAddress() {
        return this.hqAddress;
    }

    /**
     * The setAddress method allows a user to set a company's address to appear
     * on their profile.
     * @param address The new address of the company
     */
    public void setAddress(String address) {
        this.hqAddress = address;
    }

    /**
     * The addListing method adds a JobListing that an employer associated
     * with the company created to the company's profile.
     * @param listing The listing to be added ot the company profile
     */
    public void addListing(JobListing listing) {
        this.listings.add(listing);
    }

    /**
     * The getListings method returns all of the jobListings created by
     * employers who work for a specific company.
     * @return A list of all of the JobListings associated with a company
     */
    public ArrayList<JobListing> getListings() {
        return this.listings;
    }

    /**
     * The removeLisitng method removes a listing associated with a company if
     * it is in its list of listings.
     * @return True if the listing was removed, false if it was not in their
     * list of listings
     */
    public boolean removeListing(JobListing listing) {
        return listings.remove(listing);
    }

    /**
     * The getUUID method returns the UUID of the CompanyProfile
     * @return The UUID Of the company profile
     */
    public UUID getUUID() {
        return companyID;
    }
}
