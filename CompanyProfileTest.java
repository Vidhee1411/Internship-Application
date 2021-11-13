import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

public class CompanyProfileTest {
    @BeforeClass
    public static void oneTimeSetup() {

    }

    @AfterClass
    public static void oneTimeTearDown() {

    }

    @Before
    public static void setUp() {

    }

    @After
    public static void tearDown() {

    }

    @Test
    public void testAddReviewValidReview() {
        CompanyProfile c1 = new CompanyProfile("Global Enterprise", "112 Greene St", "Leading company in providing techonology in energy field");
        Review r1 = new Review("Sarah", "Zachary", 3.9, "Great Company");
        c1.addReview(r1);
        assertEquals("Great Company",c1.getReviews().get(0).getMessage());
    }

    @Test
    public void testAddReviewCompanyNull() {
        CompanyProfile c2 = new CompanyProfile("Global Enterprise", "112 Greene St", "Leading company in providing techonology in energy field");
        c2.addReview(null);
        assertEquals(0, c2.getReviews().size());
    }

    
}

   