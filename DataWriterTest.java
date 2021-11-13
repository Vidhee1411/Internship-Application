import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class DataWriterTest {
    private SearchableDatabase searchableDatabase = SearchableDatabase.getInstance();
    private ArrayList<User> userList = searchableDatabase.getUsers();
    private ArrayList<JobListing> jobList = searchableDatabase.getJobListings();
    private ArrayList<CompanyProfile> profileList = searchableDatabase.getCompanyProfiles();

    @BeforeEach
    public void setup() {
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
    public void testWritingZeroUsers() {
        userList = searchableDatabase.getUsers();
        assertEquals(0, userList.size());
    }

    @Test
    public void testWritingOneStudentNoResumeOrReviews() {
        Student s1 = new Student("Baba","Booey","realemail@sc.edu","password","junior");
        s1.setUserUUID(UUID.randomUUID());
        searchableDatabase.addUser(s1);
        DataWriter.saveUsers();
        assertEquals("Booey", DataLoader.loadData().getUsers().get(0).getLastName());
    }

    @Test
    public void testWritingThreeStudentsNoResumesOrReviews() {
        Student s1 = new Student("Baba","Booey","realemail@sc.edu","password","junior");
        Student s2 = new Student("Daba","Twooey","2ndrealemail@sc.edu","password2","freshman");
        Student s3 = new Student("Gaba","Three-ey","3rdrealemail@sc.edu","password3","senior");
        s1.setUserUUID(UUID.randomUUID());
        s2.setUserUUID(UUID.randomUUID());
        s3.setUserUUID(UUID.randomUUID());
        searchableDatabase.addUser(s1);
        searchableDatabase.addUser(s2);
        searchableDatabase.addUser(s3);
        DataWriter.saveUsers();
        assertEquals("Three-ey", DataLoader.loadData().getUsers().get(2).getLastName());
    }

    @Test
    public void testWritingOneStudentWithEmptyResume() {
        Student s1 = new Student("Student","1","email@email.sc.edu","password!","senior");
        s1.setUserUUID(UUID.randomUUID());
        s1.createResume(new ArrayList<Integer>(), new ArrayList<Integer>(), new ArrayList<WorkExperience>(), new ArrayList<Education>());
        searchableDatabase.addUser(s1);
        DataWriter.saveUsers();

        Student testStudent = (Student) DataLoader.loadData().getUsers().get(0);
        assertEquals(s1.getResume().getEmail(), testStudent.getResume().getEmail());
    }

    @Test 
    public void testWritingOneStudentWithFilledResume() {
        Student s1 = new Student("Student","1","email@email.sc.edu","password!","senior");
        s1.setUserUUID(UUID.randomUUID());
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
        searchableDatabase.addUser(s1);
        DataWriter.saveUsers();

        Student testStudent = (Student) DataLoader.loadData().getUsers().get(0);
        assertEquals(s1.getResume().getClass(), testStudent.getResume().getClass());
    }

    @Test
    public void testWritingOneStudentWithReview() {
        Student s1 = new Student("Student","1","email@email.sc.edu","password!","senior");
        s1.setUserUUID(UUID.randomUUID());
        Review review = new Review("IGN","", 0.78, "Too much water");
        s1.addReview(review);
        searchableDatabase.addUser(s1);
        DataWriter.saveUsers();

        Student testStudent = (Student) DataLoader.loadData().getUsers().get(0);
        assertEquals(review.getRating(), testStudent.getReviews().get(0).getRating());
    }

    @Test
    public void testWritingOneEmployerNoCompany() {
        Employer e1 = new Employer("Guy","McReal","business@bus.nes","yeppers");
        e1.setUserUUID(UUID.randomUUID());
        searchableDatabase.addUser(e1);
        DataWriter.saveUsers();
        assertEquals("McReal", DataLoader.loadData().getUsers().get(0).getLastName());
    }

    @Test
    public void testWritingOneEmployerWithCompanyAndID() {
        CompanyProfile compProfile = new CompanyProfile("Walter's Rock Candy", "12345 Street Road", "Say My Name.");
        compProfile.setUUID(UUID.randomUUID());
        Employer e1 = new Employer("Walter","White","pizzaRoof@fake.email","weHaveToCook", compProfile, UUID.randomUUID());
        e1.setUserUUID(UUID.randomUUID());
        searchableDatabase.addUser(e1);
        DataWriter.saveUsers();
        assertEquals("pizzaRoof@fake.email", DataLoader.loadData().getUsers().get(0).getEmail());
    }

    @Test
    public void testWritingThreeEmployers() {
        Employer e1 = new Employer("Guy","McReal","business@bus.nes","yeppers");
        e1.setUserUUID(UUID.randomUUID());
        CompanyProfile compProfile = new CompanyProfile("Walter's Rock Candy", "12345 Street Road", "Say My Name.");
        compProfile.setUUID(UUID.randomUUID());
        Employer e2 = new Employer("Walter","White","pizzaRoof@fake.email","weHaveToCook", compProfile, UUID.randomUUID());
        Employer e3 = new Employer("Jesse","James","gotta@catch.all","passwords");
        e3.setUserUUID(UUID.randomUUID());
        searchableDatabase.addUser(e1);
        searchableDatabase.addUser(e2);
        searchableDatabase.addUser(e3);
        DataWriter.saveUsers();
        assertEquals("James", DataLoader.loadData().getUsers().get(2).getLastName());
    }

    @Test
    public void testWritingOneAdmin() {
        Administrator a1 = new Administrator("Admin", "Istrator", "admin@admin", "admin", UUID.randomUUID());
        a1.setUserUUID(UUID.randomUUID());
        searchableDatabase.addUser(a1);
        DataWriter.saveUsers();
        assertEquals("Istrator", DataLoader.loadData().getUsers().get(0).getLastName());
    }

    @Test
    public void testWritingThreeAdmins() {
        Administrator a1 = new Administrator("Admin", "Istrator", "admin@admin", "admin", UUID.randomUUID());
        Administrator a2 = new Administrator("Adam", "Inistrator", "admin2@admin2", "12345678", UUID.randomUUID());
        Administrator a3 = new Administrator("First", "McLast", "admin3@admin3", "hehehehe", UUID.randomUUID());
        searchableDatabase.addUser(a1);
        searchableDatabase.addUser(a2);
        searchableDatabase.addUser(a3);
        DataWriter.saveUsers();
        assertEquals("McLast", DataLoader.loadData().getUsers().get(2).getLastName());
    }

    @Test
    public void testWritingOneOfEachUser() {
        Administrator a1 = new Administrator("Admin", "Istrator", "admin@admin", "admin", UUID.randomUUID());
        Employer e1 = new Employer("Guy","McReal","business@bus.nes","yeppers");
        e1.setUserUUID(UUID.randomUUID());
        Student s1 = new Student("Baba","Booey","realemail@sc.edu","password","junior");
        s1.setUserUUID(UUID.randomUUID());
        searchableDatabase.addUser(a1);
        searchableDatabase.addUser(e1);
        searchableDatabase.addUser(s1);
        DataWriter.saveUsers();
        assertEquals("McReal", DataLoader.loadData().getUsers().get(2).getLastName());
    }

    @Test
    public void testWritingOneJobListingNoSkillsNoApplicants() {
        JobListing j1 = new JobListing("New Job","It's a job","Right Here",true,12.00,"Testers United");
        j1.setUUID(UUID.randomUUID());
        CompanyProfile c1 = new CompanyProfile("Testers United", "4444 Four Drive", "It's a company");
        c1.setUUID(UUID.randomUUID());
        c1.addListing(j1);
        searchableDatabase.addProfile(c1);
        DataWriter.saveCompanyProfiles();
        DataWriter.saveJobListings();
        assertEquals(12.00, DataLoader.loadData().getJobListings().get(0).getPayRate());
    }

    @Test
    public void testWritingThreeJobListingsNoSkillsNoApplicants() {
        JobListing j1 = new JobListing("New Job","It's a job","Right Here",true,12.00,"My Company");
        JobListing j2 = new JobListing("Part-time Server","You get to serve pizza!","NYC",true,15.50,"Pete's Pizza");
        JobListing j3 = new JobListing("Assistant Clerk","You'll be paid in experience","Online",false,0.00,"Help Desk Inc");
        j1.setUUID(UUID.randomUUID());
        j2.setUUID(UUID.randomUUID());
        j3.setUUID(UUID.randomUUID());
        jobList.add(j1);
        jobList.add(j2);
        jobList.add(j3);
        CompanyProfile c1 = new CompanyProfile("Testers United", "4444 Four Drive", "It's a company");
        c1.setUUID(UUID.randomUUID());
        c1.addListing(j1);
        c1.addListing(j2);
        c1.addListing(j3);
        searchableDatabase.addProfile(c1);
        DataWriter.saveCompanyProfiles();
        DataWriter.saveJobListings();
        assertFalse(DataLoader.loadData().getJobListings().get(2).getPaid());
    }

    @Test
    public void testWritingOneJobListingWithSkillsAndApplicants() {
        ArrayList<Student> applicants = new ArrayList<>();
        Student s1 = new Student("Student","1","email@email.sc.edu","password!","senior");
        s1.setUserUUID(UUID.randomUUID());
        Student s2 = new Student("Student","2","email2@email.sc.edu","password12345!","junior");
        s2.setUserUUID(UUID.randomUUID());
        applicants.add(s1);
        applicants.add(s2);
        userList.add(s1);
        userList.add(s2);
        DataWriter.saveUsers();

        ArrayList<String> requiredSkills = new ArrayList<>();
        requiredSkills.add("Java Programmer");
        requiredSkills.add("Great communicator");

        JobListing j1 = new JobListing("New Job","It's a job","Right Here",true,12.00,"My Company",
            UUID.randomUUID(),applicants,requiredSkills,true);
        CompanyProfile c1 = new CompanyProfile("Testers United", "4444 Four Drive", "It's a company");
        c1.setUUID(UUID.randomUUID());
        c1.addListing(j1);
        searchableDatabase.addProfile(c1);
        DataWriter.saveCompanyProfiles();
        DataWriter.saveJobListings();
        assertEquals("2", DataLoader.loadData().getJobListings().get(0).getApplicants().get(1).getLastName());
    }

    @Test
    public void testWritingOneEmptyJobListing() {
        JobListing j1 = new JobListing();
        j1.setUUID(UUID.randomUUID());
        CompanyProfile c1 = new CompanyProfile("Testers United", "4444 Four Drive", "It's a company");
        c1.setUUID(UUID.randomUUID());
        c1.addListing(j1);
        searchableDatabase.addProfile(c1);
        DataWriter.saveCompanyProfiles();
        DataWriter.saveJobListings();
        assertTrue(DataLoader.loadData().getJobListings().get(0).getVisibility());
    }

    @Test
    public void testWritingOneCompanyProfile() {
        CompanyProfile c1 = new CompanyProfile("Real Company INC", "12321 Street", "We are real and very employment");
        c1.setUUID(UUID.randomUUID());
        searchableDatabase.addProfile(c1);
        DataWriter.saveCompanyProfiles();
        assertEquals("12321 Street", DataLoader.loadData().getCompanyProfiles().get(0).getAddress());
    }

    @Test
    public void testWritingThreeCompanyProfiles() {
        CompanyProfile c1 = new CompanyProfile("Real Company INC", "12321 Street", "We are real and very employment");
        c1.setUUID(UUID.randomUUID());
        CompanyProfile c2 = new CompanyProfile("Java the Hutt's Coding Corp", "Galaxy Far, Far Away", "Code, you must.");
        c2.setUUID(UUID.randomUUID());
        CompanyProfile c3 = new CompanyProfile("Almost Bankrupt Company", "555 Desperation Avenue", "Please, we need new workers");
        c3.setUUID(UUID.randomUUID());
        searchableDatabase.addProfile(c1);
        searchableDatabase.addProfile(c2);
        searchableDatabase.addProfile(c3);
        DataWriter.saveCompanyProfiles();
        assertEquals("555 Desperation Avenue", DataLoader.loadData().getCompanyProfiles().get(2).getAddress());
    }

    @Test
    public void testWritingCompanyProfileWithListings() {
        JobListing j1 = new JobListing("New Job","It's a job","Right Here",true,12.00,"My Company");
        j1.setUUID(UUID.randomUUID());
        jobList.add(j1);
        ArrayList<JobListing> c1Listings = new ArrayList<>();
        c1Listings.add(j1);
        CompanyProfile c1 = new CompanyProfile("Real Company INC", "12321 Street", "We are real and very employment", UUID.randomUUID(),
            new ArrayList<Review>(), c1Listings);
        searchableDatabase.addProfile(c1);
        DataWriter.saveJobListings();
        DataWriter.saveCompanyProfiles();
        
        assertEquals(c1Listings.get(0).getDescription(), DataLoader.loadData().getCompanyProfiles().get(0).getListings().get(0).getDescription());
    }

    @Test
    public void testWritingCompanyProfileWithListingsAndReviews() {
        JobListing j1 = new JobListing("New Job","It's a job","Right Here",true,12.00,"My Company");
        j1.setUUID(UUID.randomUUID());
        ArrayList<JobListing> c1Listings = new ArrayList<>();
        c1Listings.add(j1);
        Review r1 = new Review("Johnny","Cash",2.00,"I focus on the pain, the only thing that's real");
        ArrayList<Review> c1Reviews = new ArrayList<>();
        c1Reviews.add(r1);

        CompanyProfile c1 = new CompanyProfile("Real Company INC", "12321 Street", "We are real and very employment", UUID.randomUUID(),
            c1Reviews, c1Listings);
        c1.setUUID(UUID.randomUUID());
        searchableDatabase.addProfile(c1);
        DataWriter.saveCompanyProfiles();
        assertEquals(c1Reviews.get(0).getRating(), DataLoader.loadData().getCompanyProfiles().get(0).getReviews().get(0).getRating());
    }
}
