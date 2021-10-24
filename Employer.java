import java.util.UUID;
/**
 * The Employer class creates a employer account in the internship system.
 * @author Vidhee Patel
 */
public class Employer extends User {
     private CompanyProfile associatedCompany;
     private static final int PERMISSION = 1;
    
     public Employer(String firstName, String lastName, String email, String password, UUID id) {
         super(firstName, lastName, email, password, id);
        
     }

     public Employer(String firstName, String lastName, String email, String password, CompanyProfile associatedCompany,UUID id) {
        super(firstName, lastName, email, password, id);
        this.associatedCompany = associatedCompany;

    }

     public CompanyProfile createCompanyProfile(String name, String hqaddress){
         return associatedCompany;
     }

     public void associateCompany(CompanyProfile company) {

     }

     public JobListing postListing() {
        return null;
     }

     public void editListing(){

     }

     public boolean removeListing() {
         return true;
     }

     public void toggleListingVisibility() {

     }

     public Review reviewStudent(String firstName, String lastName, int rating, String comment, Student student) {
         return null;
     }

     public UUID getUUID() {
        return super.getUUID();
     }
}
