import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.lang.NullPointerException;

import static org.junit.jupiter.api.Assertions.*;

public class AdministratorTest {
    private Administrator admin;
    private SearchableDatabase searchableDatabase;

    @BeforeEach
    public void setup() {
        admin = new Administrator("Adam", "Inistrator", "admin@admin.net", "12345678", UUID.randomUUID());
        searchableDatabase = SearchableDatabase.getInstance();
    }

    @AfterEach
    public void tearDown() {
        searchableDatabase.getUsers().clear();
        searchableDatabase.getJobListings().clear();
        searchableDatabase.getCompanyProfiles().clear();
    }

    @Test
    public void testHideReviewEmptyConstructor() {
        Review review = new Review();
        admin.hideReview(review);
        assertTrue(review.getVisibility());
    }

    @Test
    public void testHideReviewFullConstructor() {
        Review review = new Review("Test", "1", 4.9, "Blah blah");
        try {
            admin.hideReview(review);
        } catch(NullPointerException e) {
            assertNotNull(null);
        }
        assertFalse(review.getVisibility());
    }

    @Test
    public void testHideReviewHiddenToVisible() {
        Review review = new Review();
        admin.hideReview(review);
        admin.hideReview(review);
        assertFalse(review.getVisibility());
    }

    @Test
    public void testRemoveValidListing() {
        JobListing toRemove = new JobListing();
        toRemove.setUUID(UUID.randomUUID());
        toRemove.editTitle("Job To Remove");
        searchableDatabase.addJobListing(toRemove);

        admin.removeListing(toRemove);
        assertEquals(0, searchableDatabase.getJobListings().size());
    }

    
    @Test
    public void testRemoveNullListing() {
        try {
            assertFalse(admin.removeListing(null));
        } catch(NullPointerException e) {
            assertNotNull(null);
        }
    }

    @Test
    public void testRemoveValidStudent() {
        Student stud = new Student("Joe", "Clash", "jclash@email.sc.edu", "pleaseNotNow", "senior");
        searchableDatabase.addUser(stud);
        admin.removeAccount(stud);
        assertEquals(0, searchableDatabase.getUsers().size());
    }

    @Test
    public void testRemoveValidStudentTwice() {
        Student stud = new Student("Joe", "Clash", "jclash@email.sc.edu", "pleaseNotNow", "senior");
        searchableDatabase.addUser(stud);
        admin.removeAccount(stud);
        try {
        admin.removeAccount(stud);
        int i = 0;
        assertEquals(1, i);
        } catch (NullPointerException e){
            assertNull(null);
        }
    }

    @Test
    public void testRemoveStudentNotInDatabase() {
        Student stud = new Student("Joe", "Clash", "jclash@email.sc.edu", "pleaseNotNow", "senior");
        try {
            admin.removeAccount(stud);
            int i = 0;
            assertEquals(1, i);
            } catch (NullPointerException e){
                assertNull(null);
            }
    }

    @Test
    public void testRemoveNullStudent() {
        Student stud = null;
        searchableDatabase.addUser(stud);
        admin.removeAccount(stud);
        assertEquals(0, searchableDatabase.getUsers().size());
    }

    @Test
    public void testRemoveValidEmployer() {
        Employer emp = new Employer("Joe", "Washing", "j@jman.org", "organized");
        searchableDatabase.addUser(emp);
        admin.removeAccount(emp);
        assertEquals(0, searchableDatabase.getUsers().size());
    }

    @Test
    public void testRemoveNullEmployer() {
        Employer emp = null;
        searchableDatabase.addUser(emp);
        admin.removeAccount(emp);
        assertEquals(0, searchableDatabase.getUsers().size());
    }

    @Test
    public void testRegisterNormalAdmin() {
        admin.registerAccount("Ad", "Mihn", "admin3@a.edu", "bestADMIN");
        assertEquals(1, searchableDatabase.getUsers().size());
    }

    @Test
    public void testRegisterSameAdminTwice() {
        admin.registerAccount("Ad", "Mihn", "admin3@a.edu", "bestADMIN");
        admin.registerAccount("Ad", "Mihn", "admin3@a.edu", "bestADMIN");
        assertEquals(1, searchableDatabase.getUsers().size());
    }

    @Test
    public void testRegisterAdminWithNullValues() {
        String firstName = null;
        String lastName = null;
        String email = null;
        String password = null;
        admin.registerAccount(firstName, lastName, email, password);
        assertEquals(0, searchableDatabase.getUsers().size());
    }

}
