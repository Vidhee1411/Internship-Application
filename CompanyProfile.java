import java.util.UUID;
/**
 * The class creates a company profile in the internship system.
 * @author Vidhee Patel
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

    public CompanyProfile(String companyName, String hqAddress, String description, UUID id){

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

    public String getCompanyName() {
        return "";
    }

    public  void setCompanyName(String name) {

    }

    public void addReview(Review review){
        this.reviewList.add(review);
    }

    public String displayReviews() {
        return "";
    }

    public String getDescription() {
        return "";
    }

    public void setDescription() {

    }

    public void addListing() {

    }

    public ArrayList<JobListing> getListings() {
        return null;
    }

    public boolean removeListing() {
        return true;
    }

    public UUID getUUID() {
        return companyID;
    }
}
