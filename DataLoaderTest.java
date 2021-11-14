import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
public class DataLoaderTest {
    private SearchableDatabase searchableDatabase = SearchableDatabase.getInstance();
    private ArrayList<User> userList = searchableDatabase.getUsers();
    private ArrayList<JobListing> jobList = searchableDatabase.getJobListings();
    private ArrayList<CompanyProfile> profileList = searchableDatabase.getCompanyProfiles();
    
    @BeforeEach
    public void setup() {
        userList.clear();
        jobList.clear();
        profileList.clear();
        searchableDatabase.getUsers().add(new Student("bill", "Smith", "bob@email.sc.edu", "password", "junior"));
        searchableDatabase.getUsers().add(new Student("bob", "Smith", "bob@email.sc.edu", "password", "junior"));
        JobListing testlisting0 = new JobListing("python dev", "test data1", "test loation1", false, 0.0, "test company1");
        JobListing testlisting1 = new JobListing("python dev", "test data1", "test loation1", false, 0.0, "test company1");
        searchableDatabase.getJobListings().add(testlisting0);
        searchableDatabase.getJobListings().add(testlisting1);
        CompanyProfile testprofile0 = new CompanyProfile("test company name0", "test address0", "test description 0");
        CompanyProfile testprofile1 = new CompanyProfile("test company name1", "test address1", "test description 1");
        testprofile0.addListing(testlisting0);
        testprofile1.addListing(testlisting1);
        searchableDatabase.getCompanyProfiles().add(testprofile0);
        searchableDatabase.getCompanyProfiles().add(testprofile1);
        DataWriter.saveUsers();
        DataWriter.saveJobListings();
        DataWriter.saveCompanyProfiles();
    }

    @AfterEach
    public void tearDown() {
        userList.clear();
        jobList.clear();
        profileList.clear();
        searchableDatabase.getUsers().clear();
        searchableDatabase.getJobListings().clear();
        searchableDatabase.getCompanyProfiles().clear();
        DataWriter.saveUsers();
        DataWriter.saveJobListings();
        DataWriter.saveCompanyProfiles();
    }

    @Test
    public void testGetTwoUsers(){
        userList = DataLoader.loadData().getUsers();
        assertEquals(2, userList.size());
    }

    @Test
    public void testGetZeroUsers(){
        searchableDatabase.getUsers().clear();
        DataWriter.saveUsers();
        searchableDatabase = DataLoader.loadData();
        userList = searchableDatabase.getUsers();
        assertEquals(0, userList.size());

    }

    @Test
    public void testGetFirstUser(){
        userList = DataLoader.loadData().getUsers();
        assertEquals("bill", userList.get(0).getFirstName());
    }

    @Test
    public void testGetTwoListings(){
        jobList = DataLoader.loadData().getJobListings();
        assertEquals(2, jobList.size());
    }

    @Test
    public void testGetZeroListings(){
        searchableDatabase.getCompanyProfiles().clear();
        DataWriter.saveJobListings();
        jobList = DataLoader.loadData().getJobListings();
        assertEquals(0, jobList.size());
    }

    @Test
    public void testGetFirstListing(){
        jobList = DataLoader.loadData().getJobListings();
        assertEquals("python dev", jobList.get(0).getTitle());
    }

    @Test
    public void testGetTwoCompanyProfiles(){
        profileList = DataLoader.loadData().getCompanyProfiles();
        assertEquals(2, profileList.size());
    }

    @Test
    public void testGetZeroCompanyProfiles(){
        searchableDatabase.getCompanyProfiles().clear();
        DataWriter.saveCompanyProfiles();
        profileList = DataLoader.loadData().getCompanyProfiles();
        assertEquals(0, profileList.size());
    }
    @Test
    public void testGetFirstCompanyProfile(){
        profileList = DataLoader.loadData().getCompanyProfiles();
        assertEquals("test company name0", profileList.get(0).getCompanyName());
    }
}
