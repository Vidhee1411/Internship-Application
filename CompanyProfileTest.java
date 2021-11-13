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
    
    @Test
    public void testAddListingvalid() {
        CompanyProfile c2 = new CompanyProfile("Global Enterprise", "112 Greene St", "Leading company in providing techonology in energy field");
        c2.addListing(new JobListing("Software Developer"," Inter will get oppurtunity to deal with real softwares","South Carolina",true, 20.00, "Global Enterprise"));
        assertEquals("Software Developer", c2.getListings().get(0).getTitle());
    }

    @Test
    public void testAddListingNull() {
        CompanyProfile c2 = new CompanyProfile("Global Enterprise", "112 Greene St", "Leading company in providing techonology in energy field");
        c2.addListing(null);
        assertNotEquals(1, c2.getListings().size());     
    }

    @Test
    public void testRemoveListingvalid() {
        CompanyProfile c2 = new CompanyProfile("Global Enterprise", "112 Greene St", "Leading company in providing techonology in energy field");
        JobListing j1 = new JobListing("Software Developer"," Inter will get oppurtunity to deal with real softwares","South Carolina",true, 20.00, "Global Enterprise");
        c2.addListing(j1);
        c2.removeListing(j1);
        assertEquals(0, c2.getListings().size());
    }

    @Test
    public void testRemoveWrongComapaniesListing() {
        CompanyProfile c2 = new CompanyProfile("Global Enterprise", "112 Greene St", "Leading company in providing techonology in energy field");
        JobListing j1 = new JobListing("Software Developer"," Inter will get oppurtunity to deal with real softwares","South Carolina",true, 20.00, "Global Enterprise");
        CompanyProfile c3 = new CompanyProfile("Dose", "123 Assembly St.", "This is company");
        JobListing j2 = new JobListing("Data Science", "Data Science intern listing","Utah",true,19.0,"Dose");
        c2.addListing(j1);
        c3.addListing(j2);
        c3.removeListing(j1);
        assertEquals(1, c3.getListings().size());
    }
}

   