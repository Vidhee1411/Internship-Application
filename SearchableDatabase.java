import java.util.ArrayList;
/**
 * The SearchableDatabase class contains all of the JobListings and
 * CompanyProfiles that a User can search through. It sorts the job listings
 * alphabetically and by pay so that the user can search with either filter.
 * This class is a singleton so that only one instance of the object can be
 * created. There do not need to be multiple searchable databases.
 * @author Joshua DuPuis
 */
public class SearchableDatabase {

    private static SearchableDatabase searchableDatabase;
    private ArrayList<JobListing> jobListings;
    private ArrayList<CompanyProfile> companyProfiles;
    private ArrayList<User> users;

    /** 
     * The SearchableDatabase constructor constructs the one instance of the
     * SearchableDatabase that the internship program will use.
    */
    private SearchableDatabase(){
        this.jobListings = new ArrayList<JobListing>();
        this.users = new ArrayList<User>();
    }

    /**
     * The getInstance method returns the instance of SearchableDatabase.
     * @return The internship system's SearchableDatabase
     */
    public static SearchableDatabase getInstance() {
        if(searchableDatabase == null){
            return new SearchableDatabase();
        }
        return searchableDatabase;
    }

    /**
     * The addJobListing method adds a JobListing to the searchable database
     * that students can search for and apply to.
     * @param listing The JobListing to be added to a database
     */
    public void addJobListing(JobListing listing) {

    }

    /**
     * The removeJobListing method allows an employer or an administrator to
     * remove a JobListing from the SearchableDatabase.
     * @param listing The JobListing to be removed from the database
     */
    public void removeJobListing(JobListing listing) {

    }

    public void setUsers(ArrayList<User> users){
        this.users = users;
    }

    public void setJobListings(ArrayList<JobListing>jobListings){
        this.jobListings = jobListings;
    }

    /**
     * The getJobListings method returns a list of all of the JobListings in
     * the SearchableDatabase.
     * @return An ArrayList containing all of the JobListings in the database
     */
    public ArrayList<JobListing> getJobListings() {
        return null;
    }

    /**
     * The searchListings method allows a user to search through JobListings by
     * entering the title of the listing they would like to find.
     * @param title The title of the JobListing the User would like to find
     * @return An ArrayList of all of the JobListings that contain the title
     * entered by the user.
     */
    public ArrayList<JobListing> searchListings(String title) {
        return null;
    }

    /**
     * The sortListingsbyPay method sorts all of the JobListings by pay in
     * ascending order.
     */
    private void sorListingsbyPay() {

    }

    /**
     * The sortListingsAlphabetically method puts all of the JobListings in
     * alphabetical order. 
     */
    private void sortListingsAlphabetically() {

    }

    /**
     * The getCompanyProfiles method returns a list of all of the
     * CompanyProfiles in the SearchableDatabase.
     * @return An ArrayList containing all of the CompanyProfiles in the
     * SearchableDatabase
     */
    public ArrayList<CompanyProfile> getCompanyProfiles() {
        return null;
    }

    /**
     * The searchProfiles method allows a user to search through all of the
     * CompanyProfiles in the SearchableDatabase by entering a company's name
     * into the system.
     * @param title The name of the company the user would like to search for
     * @return An ArrayList containing all of the profiles with that company
     * name
     */
    public ArrayList<CompanyProfile> searchProfiles(String title) {
        return null;
    }

    /**
     * The sortProfilesAlphabetically method puts all of the profiles in the
     * SearchableDatabase in alphabetical order.
     */
    private void sortProfilesAlphabetically() {

    }


}
