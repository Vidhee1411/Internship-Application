import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
public class SearchableDatabaseTest {
    private SearchableDatabase searchableDatabase = SearchableDatabase.getInstance();
    private JobListing testListing0 =  new JobListing("python dev", "test data1", "test loation1", false, 0.0, "test company1");
    private JobListing testListing1 =  new JobListing("java dev", "test data1", "test loation1", true, 25.00, "test company1");
    private Student testStudent0 = new Student("bill", "Smith", "bob@email.sc.edu", "password", "junior");
    private Student testStudent1 = new Student("bob", "Smith", "bob@email.sc.edu", "password", "junior");
    private CompanyProfile testProfile0 = new CompanyProfile("walmart", "test address0", "test description 0");
    private CompanyProfile testProfile1 = new CompanyProfile("bestbuy", "test address1", "test description 1");


    @BeforeEach
    public void setup() {
        searchableDatabase.getUsers().add(this.testStudent0);
        searchableDatabase.getUsers().add(this.testStudent1);
        testListing0.addRequiredSkill("java");
        testListing1.addRequiredSkill("python");
        testListing1.addRequiredSkill("java");
        searchableDatabase.getJobListings().add(this.testListing0);
        searchableDatabase.getJobListings().add(this.testListing1);
        testProfile0.addListing(this.testListing0);
        testProfile1.addListing(this.testListing1);
        searchableDatabase.getCompanyProfiles().add(testProfile0);
        searchableDatabase.getCompanyProfiles().add(testProfile1);
        DataWriter.saveUsers();
        DataWriter.saveJobListings();
        DataWriter.saveCompanyProfiles();
    }

    @AfterEach
    public void tearDown() {
        searchableDatabase.getUsers().clear();
        searchableDatabase.getJobListings().clear();
        searchableDatabase.getCompanyProfiles().clear();
        DataWriter.saveUsers();
        DataWriter.saveJobListings();
        DataWriter.saveCompanyProfiles();
    }

    @Test
    public void testNewAddJoblisting(){
        JobListing temp = new JobListing();
        searchableDatabase.addJobListing(temp);
        assertTrue(searchableDatabase.getJobListings().contains(temp));
    }

    @Test
    public void testAddExistingJoblisting(){
        assertFalse(searchableDatabase.addJobListing(testListing0));
    }

    @Test
    public void testAddNullJoblisting(){
        assertFalse(searchableDatabase.addJobListing(null));
    }

    @Test
    public void testRemoveExistingJoblisting(){
        searchableDatabase.removeJobListing(testListing0);
        assertTrue(!searchableDatabase.getJobListings().contains(testListing0));
    }

    @Test
    public void testRemoveNullJoblisting(){
        assertFalse(searchableDatabase.removeJobListing(null));
    }

    @Test
    public void testAddNewUser(){
        Student temp = new Student("tempname", "templastname", "tempemail", "temppassword", "tempyearinschool");
        searchableDatabase.addUser(temp);
        assertTrue(searchableDatabase.getUsers().contains(temp));
    }

    @Test
    public void testAddexistingUser(){
        assertFalse(searchableDatabase.addUser(testStudent0));
    }

    @Test
    public void testAddNullUser(){
        assertFalse(searchableDatabase.addUser(null));
    }

    @Test
    public void testRemoveExistingUser(){
        searchableDatabase.removeUser(testStudent0);
        assertFalse(searchableDatabase.getUsers().contains(testStudent0));
    }

    @Test   
    public void testRemoveNonExistentUser(){
        Student temp = new Student("tempname", "templastname", "tempemail", "temppassword", "tempyearinschool");
        assertFalse(searchableDatabase.removeUser(temp));
    }

    @Test
    public void testRemoveNullUser(){
        assertFalse(searchableDatabase.removeUser(null));
    }

    @Test
    public void testAddNewCompanyProfile(){
        CompanyProfile temp = new CompanyProfile("tempname", "tempaddress", "tempdescription");
        searchableDatabase.addProfile(temp);
        assertTrue(searchableDatabase.getCompanyProfiles().contains(temp));
    }

    @Test
    public void testAddExistingCompanyProfile(){
        assertFalse(searchableDatabase.addProfile(testProfile0));
    }

    @Test
    public void testAddNullCompanyProfile(){
        assertFalse(searchableDatabase.addProfile(null));
    }

    @Test
    public void testRemoveExistingCompanyProfile(){
        searchableDatabase.getCompanyProfiles().remove(testProfile0);
        assertFalse(searchableDatabase.getCompanyProfiles().contains(testProfile0));
    }

    @Test
    public void testRemoveNullCompanyProfile(){
        assertFalse(searchableDatabase.removeProfile(null));
    }


    @Test
    public void testSearchListingsbyTitle(){
        assertEquals(1, searchableDatabase.searchListings(testListing0.getTitle()).size());
    }

    @Test
    public void testSearchListingsbyTitleEmptyString(){
        assertEquals(2, searchableDatabase.searchListings("").size());
    }

    @Test
    public void testSearchListingsbyTitleHiddenListing(){
        searchableDatabase.getJobListings().get(0).setVisibility(false);
        assertEquals(0, searchableDatabase.searchListings("python dev").size());
    }

    @Test
    public void testSearchCompanyProfilesOneResult(){
        assertEquals(1, searchableDatabase.searchProfiles(testProfile0.getCompanyName()).size());
    }

    @Test
    public void testSearchCompanyProfilesEmptyString(){
        assertEquals(2, searchableDatabase.searchProfiles("").size());
    }

    @Test
    public void testSearchlistingsbyPayOneResult(){
        assertEquals(1, searchableDatabase.searchListingsbyPay(15.00).size());
    }

    @Test
    public void testSearchlistingsbyPayNegative(){
        assertEquals(2, searchableDatabase.searchListingsbyPay(-5.00).size());
    }

    @Test
    public void testSearchlistingsbyPayHiddenListing(){
        searchableDatabase.getJobListings().get(1).setVisibility(false);
        assertEquals(0, searchableDatabase.searchListingsbyPay(5.00).size());
    }

    @Test
    public void testSearchListingsbySkillOneResult(){
        assertEquals(1, searchableDatabase.searchListingsBySkill("python").size());
    }

    @Test
    public void testSearchListingsbySkillEmptyString(){
        assertEquals(0, searchableDatabase.searchListingsBySkill("").size());
    }

    @Test
    public void testSearchbySkillHiddenListing(){
        searchableDatabase.getJobListings().get(1).setVisibility(false);
        assertEquals(0, searchableDatabase.searchListingsBySkill("python").size());
    }

    @Test
    public void testSortListingsAlphabetically(){
        searchableDatabase.sortListingsAlphabetically();
        assertEquals("java dev", searchableDatabase.getJobListings().get(0).getTitle());
    }

    @Test
    public void testSortlistingsbypay(){
        searchableDatabase.sortListingsbyPay();
        assertEquals(25.0, searchableDatabase.getJobListings().get(0).getPayRate());
    }

    @Test
    public void testSortCompanyProfilesAlphabetically(){
        searchableDatabase.sortProfilesAlphabetically();
        assertEquals("bestbuy", searchableDatabase.getCompanyProfiles().get(0).getCompanyName());
    }
}
