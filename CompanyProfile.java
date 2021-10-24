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

    public CompanyProfile(String companyName, String hqAddress, String description,UUID companyID){

    }

    public CompanyProfile(String companyName, String hqAddress, String description,UUID companyID, ArrayList<Review> reviews, ArrayList<JobListing> listings ){

    }

    public UUID getUUID(){
        return this.companyID;
    }

    public String getCompanyName() {
        return "";
    }

    public  void setCompanyName(String name) {

    }

    public void setReview(ArrayList<Review> reviews){
        this.reviewList = reviews;
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

}
