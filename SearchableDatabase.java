import java.util.ArrayList;
import java.util.Comparator;
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
    private ArrayList<User> users;
    private ArrayList<CompanyProfile> companyProfiles;

    /** 
     * The SearchableDatabase constructor constructs the one instance of the
     * SearchableDatabase that the internship program will use.
    */
    private SearchableDatabase(){
        this.jobListings = new ArrayList<JobListing>();
        this.users = new ArrayList<User>();
        this.companyProfiles = new ArrayList<CompanyProfile>();
    }

    /**
     * The getInstance method returns the instance of SearchableDatabase.
     * @return The internship system's SearchableDatabase
     */
    public static SearchableDatabase getInstance() {
        if(searchableDatabase == null){
            SearchableDatabase temp = new SearchableDatabase();
            searchableDatabase = temp;
            return searchableDatabase;
        }
        return searchableDatabase;
    }

    /**
     * The addJobListing method adds a JobListing to the searchable database
     * that students can search for and apply to.
     * @param listing The JobListing to be added to a database
     */
    public void addJobListing(JobListing listing) {
        jobListings.add(listing);
    }

    /**
     * The removeJobListing method allows an employer or an administrator to
     * remove a JobListing from the SearchableDatabase.
     * @param listing The JobListing to be removed from the database
     */
    public void removeJobListing(JobListing listing) {
        jobListings.remove(listing);
    }
    /**
     * adds a user to the user ArrayList
     * @param user the user to be added 
     */
    public void addUser(User user){
        this.users.add(user);
    }
    /**
     * removes a user from the user ArrayList
     * @param user the user to be removed
     */
    public void removeUser(User user){
        this.users.remove(user);
    }

    /**
     * The addProfile method adds a company profile to the list of profiles in
     * the database.
     * @param profile The profile to be added to the list
     */
    public void addProfile(CompanyProfile profile) {
        this.companyProfiles.add(profile);
    }

    /**
     * The removeProfile method removes a company profile from the list of
     * profiles in the database.
     * @param profile The profile to be removed from the list
     */
    public void removeProfile(CompanyProfile profile) {
        this.companyProfiles.remove(profile);
    }

    /**
     * Sets the users ArrayList to the ArrayList passed to the setUsers method. 
     * @param users An ArrayList<User> containing all users
     */
    public void setUsers(ArrayList<User> users){
        this.users = users;
    }
    /**
     * Sets the joblistings Arraylist to the Arraylist passed to the Setjoblistings method. 
     * @param jobListings An ArrayList<JobListing> containing all joblistings 
     */
    public void setJobListings(ArrayList<JobListing>jobListings){
        this.jobListings = jobListings;
    }
    /**
     * sets the companyProfiles ArrayList to the ArrayList passed to the setCompanyProfiles methood. 
     * @param companyProfiles an ArrayList containing all companyprofiles
     */
    public void setCompanyProfiles(ArrayList<CompanyProfile> companyProfiles){
        this.companyProfiles = companyProfiles;
    }

    /**
     * The getJobListings method returns a list of all of the JobListings in
     * the SearchableDatabase.
     * @return An ArrayList containing all of the JobListings in the database
     */
    public ArrayList<JobListing> getJobListings() {
        return jobListings;
    }
    /**
     * gets the ArrayList<User> containing all users
     * @return an ArrayList<User> containing all users
     */
    public ArrayList<User> getUsers(){
        return this.users;
    }

      /**
     * The getCompanyProfiles method returns a list of all of the
     * CompanyProfiles in the SearchableDatabase.
     * @return An ArrayList containing all of the CompanyProfiles in the
     * SearchableDatabase
     */
    public ArrayList<CompanyProfile> getCompanyProfiles() {
        return companyProfiles;
    }
    public ArrayList<JobListing> searchbySkill(String skill){
        ArrayList<JobListing> output = new ArrayList<>();
        for(JobListing listing: this.jobListings){
               for(String requiredSkill: listing.getRequiredSkills()){
                   if(skill.contains(requiredSkill) && listing.getVisibility()) 
                   output.add(listing);
               }
           } 
           return output;    
        }
           
    /**
     * The searchListings method allows a user to search through JobListings by
     * entering the title of the listing they would like to find.
     * @param title The title of the JobListing the User would like to find
     * @return An ArrayList of all of the JobListings that contain the title
     * entered by the user.
     */
    public ArrayList<JobListing> searchListings(String title) {
        ArrayList<JobListing> output = new ArrayList<JobListing>();
        for(JobListing listing: jobListings){
            if(listing.getTitle().toLowerCase().contains(title.toLowerCase()) && listing.getVisibility()){
                output.add(listing);
            }
        }
        return output;
    }

      /**
     * The searchProfiles method allows a user to search through all of the
     * CompanyProfiles in the SearchableDatabase by entering a company's name
     * into the system.
     * @param title The name of the company the user would like to search for
     * @return An ArrayList containing all of the profiles with that company
     * name
     */
    public ArrayList<CompanyProfile> searchProfiles(String name) {
        ArrayList<CompanyProfile> output = new ArrayList<CompanyProfile>();
        for(CompanyProfile profile:companyProfiles){
            if(profile.getCompanyName().toLowerCase().contains((name.toLowerCase()))){
                output.add(profile);
            }
        }
        return output;
    }

    /**
     * The searchListingsbyPay method searches through all of the JobListings by minimum specified pay.
     * @param pay The minimum pay desired
     * @return The ArrayList of JobListings with the minimum pay required
     */
    public ArrayList<JobListing> searchListingsbyPay(Double pay) {
        ArrayList<JobListing> output = new ArrayList<>();
        for(JobListing listing:this.jobListings){
            if(listing.getPayRate() >= pay && listing.getVisibility()){
                output.add(listing);
            }
        }
        return output;
    }

    /**
     * The searchListingsBySkill method searches through all listings' skills for the specified skill.
     * If the listing has that required skill, it will be added to the output.
     * @param skillToSearch The skill desired
     * @return An ArrayList of JobListings with the skill required
     */
    public ArrayList<JobListing> searchListingsBySkill(String skillToSearch) {
        ArrayList<JobListing> output = new ArrayList<>();
        for(JobListing listing : this.jobListings) {
            if(!listing.getVisibility()) {
                continue;
            }
            for(String skill : listing.getRequiredSkills()) {
                if(skill.equalsIgnoreCase(skillToSearch)) {
                    output.add(listing);
                    break;
                }
            }
        }
        return output;
    }

    /**
     * sorts Joblistings by payrate
     */
    public void sortListingsbyPay(){
        this.jobListings.sort(Comparator.comparingDouble(JobListing::getPayRate));
    }
    /**
     * The sortListingsAlphabetically method puts all of the JobListings in
     * alphabetical order. 
     */
    public  void sortListingsAlphabetically() {
        jobListings.sort(Comparator.comparing(JobListing::getTitle));
    }

      /**
     * The sortProfilesAlphabetically method puts all of the profiles in the
     * SearchableDatabase in alphabetical order.
     */
    public void sortProfilesAlphabetically() {
        companyProfiles.sort(Comparator.comparing(CompanyProfile::getCompanyName));
    }
}