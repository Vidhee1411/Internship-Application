import java.util.ArrayList;
import java.util.UUID;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class DataWriterTest {
    private SearchableDatabase searchableDatabase = SearchableDatabase.getInstance();
    private ArrayList<User> userList = searchableDatabase.getUsers();
    private ArrayList<JobListing> listingList = searchableDatabase.getJobListings();
    private ArrayList<CompanyProfile> profileList = searchableDatabase.getCompanyProfiles();

    @BeforeEach
    public void setup() {
        SearchableDatabase.getInstance().getUsers().clear();
        SearchableDatabase.getInstance().getJobListings().clear();
        SearchableDatabase.getInstance().getCompanyProfiles().clear();
        DataWriter.saveUsers();
    }

    @AfterEach
    public void tearDown() {
        SearchableDatabase.getInstance().getUsers().clear();
        SearchableDatabase.getInstance().getJobListings().clear();
        SearchableDatabase.getInstance().getCompanyProfiles().clear();
        DataWriter.saveUsers();
    }

    @Test
    void testWritingZeroUsers() {
        userList = searchableDatabase.getUsers();
        assertEquals(0, userList.size());
    }

    @Test
    void testWritingOneStudentNoResumeOrReviews() {
        Student s1 = new Student("Baba","Booey","realemail@sc.edu","password","junior");
        s1.setUserUUID(UUID.fromString("s1"));
        userList.add(s1);
        DataWriter.saveUsers();
        assertEquals("Booey", DataLoader.loadData().getUsers().get(0).getLastName());
    }

    @Test
    void testWritingThreeStudentsNoResumesOrReviews() {
        Student s1 = new Student("Baba","Booey","realemail@sc.edu","password","junior");
        Student s2 = new Student("Daba","Twooey","2ndrealemail@sc.edu","password2","freshman");
        Student s3 = new Student("Gaba","Three-ey","3rdrealemail@sc.edu","password3","senior");
        s1.setUserUUID(UUID.fromString("s1"));
        s1.setUserUUID(UUID.fromString("s2"));
        s1.setUserUUID(UUID.fromString("s3"));
        userList.add(s1);
        userList.add(s2);
        userList.add(s3);
        DataWriter.saveUsers();
        assertEquals("Three-ey", DataLoader.loadData().getUsers().get(2).getLastName());
    }

    @Test
    void testWritingOneStudentWithEmptyResume() {
        Student s1 = new Student("Student","1","email@email.sc.edu","password!","senior");
        s1.createResume(new ArrayList<Integer>(), new ArrayList<Integer>(), new ArrayList<WorkExperience>(), new ArrayList<Education>());
        userList.add(s1);
        DataWriter.saveUsers();

        Student testStudent = (Student) DataLoader.loadData().getUsers().get(0);
        assertEquals(s1.getResume(), testStudent.getResume());
    }

    @Test 
    void testWritingOneStudentWithFilledResume() {
        Student s1 = new Student("Student","1","email@email.sc.edu","password!","senior");
        s1.addSkill("Java");
        s1.addClass("Intro to Science");
        ArrayList<Integer> skillIndexes = new ArrayList<>();
        skillIndexes.add(0);
        ArrayList<Integer> classIndexes = new ArrayList<>();
        classIndexes.add(0);
        ArrayList<WorkExperience> workExperiences = new ArrayList<>();
        workExperiences.add(new WorkExperience("Job 323","Real Inc","7/7/21-7/7/22","Some programming experience"));
        ArrayList<Education> educations = new ArrayList<>();
        educations.add(new Education("UofSA","Comp. Sci.",3.55,"Spring 2020"));

        s1.createResume(skillIndexes, classIndexes,workExperiences, educations);
        userList.add(s1);
        DataWriter.saveUsers();

        Student testStudent = (Student) DataLoader.loadData().getUsers().get(0);
        assertEquals(s1.getResume(), testStudent.getResume());
    }

    @Test
    void testWritingOneStudentWithReview() {
        Student s1 = new Student("Student","1","email@email.sc.edu","password!","senior");
        Review review = new Review("IGN","", 0.78, "Too much water");
        s1.addReview(review);
        userList.add(s1);
        DataWriter.saveUsers();

        Student testStudent = (Student) DataLoader.loadData().getUsers().get(0);
        assertEquals(review.getRating(), testStudent.getReviews().get(0).getRating());
    }

    @Test
    void testWritingOneEmployerNoCompany() {
        Employer e1 = new Employer("Guy","McReal","business@bus.nes","yeppers");
        e1.setUserUUID(UUID.fromString("e1"));
        userList.add(e1);
        DataWriter.saveUsers();
        assertEquals("McReal", DataLoader.loadData().getUsers().get(0).getLastName());
    }

    @Test
    void testWritingOneEmployerWithCompanyAndID() {
        CompanyProfile compProfile = new CompanyProfile("Walter's Rock Candy", "12345 Street Road", "Say My Name.");
        Employer e1 = new Employer("Walter","White","pizzaRoof@fake.email","weHaveToCook", compProfile, UUID.fromString("e1"));
        userList.add(e1);
        DataWriter.saveUsers();
        assertEquals("pizzaRoof@fake.email", DataLoader.loadData().getUsers().get(0).getEmail());
    }

    @Test
    void testWritingThreeEmployers() {
        Employer e1 = new Employer("Guy","McReal","business@bus.nes","yeppers");
        e1.setUserUUID(UUID.fromString("e1"));
        CompanyProfile compProfile = new CompanyProfile("Walter's Rock Candy", "12345 Street Road", "Say My Name.");
        Employer e2 = new Employer("Walter","White","pizzaRoof@fake.email","weHaveToCook", compProfile, UUID.fromString("e2"));
        Employer e3 = new Employer("Jesse","James","gotta@catch.all","passwords");
        e3.setUserUUID(UUID.fromString("e3"));
        userList.add(e1);
        userList.add(e2);
        userList.add(e3);
        DataWriter.saveUsers();
        assertEquals("James", DataLoader.loadData().getUsers().get(2).getLastName());
    }

    @Test
    void testWritingOneAdmin() {
        Administrator a1 = new Administrator("Admin", "Istrator", "admin@admin", "admin", UUID.fromString("a1"));
        userList.add(a1);
        DataWriter.saveUsers();
        assertEquals("Istrator", DataLoader.loadData().getUsers().get(0).getLastName());
    }

    @Test
    void testWritingThreeAdmins() {
        Administrator a1 = new Administrator("Admin", "Istrator", "admin@admin", "admin", UUID.fromString("a1"));
        Administrator a2 = new Administrator("Adam", "Inistrator", "admin2@admin2", "12345678", UUID.fromString("a2"));
        Administrator a3 = new Administrator("First", "McLast", "admin3@admin3", "hehehehe", UUID.fromString("a3"));
        userList.add(a1);
        userList.add(a2);
        userList.add(a3);
        DataWriter.saveUsers();
        assertEquals("McLast", DataLoader.loadData().getUsers().get(2).getLastName());
    }

    @Test
    void testWritingOneOfEachUser() {
        Administrator a1 = new Administrator("Admin", "Istrator", "admin@admin", "admin", UUID.fromString("a1"));
        Employer e1 = new Employer("Guy","McReal","business@bus.nes","yeppers");
        Student s1 = new Student("Baba","Booey","realemail@sc.edu","password","junior");
        userList.add(a1);
        userList.add(e1);
        userList.add(s1);
        DataWriter.saveUsers();
        assertEquals("Istrator", DataLoader.loadData().getUsers().get(2).getLastName());
    }

    @Test
    void testWritingOneJobListingNoSkillsNoApplicants() {
        JobListing j1 = new JobListing("New Job","It's a job","Right Here",true,12.00,"My Company");
        j1.setUUID(UUID.fromString("j1"));
        listingList.add(j1);
        DataWriter.saveJobListings();
        assertEquals(12.00, DataLoader.loadData().getJobListings().get(0).getPayRate());
    }

    @Test
    void testWritingThreeJobListingsNoSkillsNoApplicants() {
        JobListing j1 = new JobListing("New Job","It's a job","Right Here",true,12.00,"My Company");
        JobListing j2 = new JobListing("Part-time Server","You get to serve pizza!","NYC",true,15.50,"Pete's Pizza");
        JobListing j3 = new JobListing("Assistant Clerk","You'll be paid in experience","Online",false,0.00,"Help Desk Inc");
        j1.setUUID(UUID.fromString("j1"));
        j2.setUUID(UUID.fromString("j2"));
        j3.setUUID(UUID.fromString("j3"));
        listingList.add(j1);
        listingList.add(j2);
        listingList.add(j3);
        DataWriter.saveJobListings();
        assertFalse(DataLoader.loadData().getJobListings().get(2).getPaid());
    }

    @Test
    void testWritingOneJobListingWithSkillsAndApplicants() {
        ArrayList<Student> applicants = new ArrayList<>();
        applicants.add(new Student("Student","1","email@email.sc.edu","password!","senior"));
        applicants.add(new Student("Student","2","email2@email.sc.edu","password12345!","junior"));
        ArrayList<String> requiredSkills = new ArrayList<>();
        requiredSkills.add("Java Programmer");
        requiredSkills.add("Great communicator");

        JobListing j1 = new JobListing("New Job","It's a job","Right Here",true,12.00,"My Company",
            UUID.fromString("j1"),applicants,requiredSkills, true);
        listingList.add(j1);
        DataWriter.saveJobListings();
        assertEquals("2", DataLoader.loadData().getJobListings().get(0).getApplicants().get(1).getLastName());
    }

    @Test
    void testWritingOneEmptyJobListing() {
        JobListing j1 = new JobListing();
        listingList.add(j1);
        DataWriter.saveJobListings();
        assertTrue(DataLoader.loadData().getJobListings().get(0).getVisibility());
    }

    @Test
    void testWritingOneCompanyProfile() {
        CompanyProfile c1 = new CompanyProfile("Real Company INC", "12321 Street", "We are real and very employment");
        profileList.add(c1);
        DataWriter.saveCompanyProfiles();
        assertEquals("12321 Street", DataLoader.loadData().getCompanyProfiles().get(0).getAddress());
    }

    @Test
    void testWritingThreeCompanyProfiles() {
        CompanyProfile c1 = new CompanyProfile("Real Company INC", "12321 Street", "We are real and very employment");
        CompanyProfile c2 = new CompanyProfile("Java the Hutt's Coding Corp", "Galaxy Far, Far Away", "Code, you must.");
        CompanyProfile c3 = new CompanyProfile("Almost Bankrupt Company", "555 Desperation Avenue", "Please, we need new workers");
        profileList.add(c1);
        profileList.add(c2);
        profileList.add(c3);
        DataWriter.saveCompanyProfiles();
        assertEquals("555 Desperation Avenue", DataLoader.loadData().getCompanyProfiles().get(2).getAddress());
    }

    @Test
    void testWritingCompanyProfileWithListings() {
        JobListing j1 = new JobListing("New Job","It's a job","Right Here",true,12.00,"My Company");
        ArrayList<JobListing> c1Listings = new ArrayList<>();
        c1Listings.add(j1);
        CompanyProfile c1 = new CompanyProfile("Real Company INC", "12321 Street", "We are real and very employment", UUID.fromString("c1"),
            new ArrayList<Review>(), c1Listings);
        profileList.add(c1);
        DataWriter.saveCompanyProfiles();
        assertEquals(c1Listings, DataLoader.loadData().getCompanyProfiles().get(0).getListings());
    }

    @Test
    void testWritingCompanyProfileWithListingsAndReviews() {
        JobListing j1 = new JobListing("New Job","It's a job","Right Here",true,12.00,"My Company");
        ArrayList<JobListing> c1Listings = new ArrayList<>();
        c1Listings.add(j1);
        Review r1 = new Review("Johnny","Cash",2.00,"I focus on the pain, the only thing that's real");
        ArrayList<Review> c1Reviews = new ArrayList<>();
        c1Reviews.add(r1);

        CompanyProfile c1 = new CompanyProfile("Real Company INC", "12321 Street", "We are real and very employment", UUID.fromString("c1"),
            c1Reviews, c1Listings);
        profileList.add(c1);
        DataWriter.saveCompanyProfiles();
        assertEquals(c1Reviews, DataLoader.loadData().getCompanyProfiles().get(0).getReviews());
    }
}
