import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

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
}
