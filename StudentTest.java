import java.util.ArrayList;
import java.util.UUID;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    private Student student;

    @BeforeEach
    public void setup() {
        student = new Student("Frodo", "Baggins", "hobbits@shire.net", "elevenses", "sophomore", UUID.fromString("hobbit"), null, null, null);
    }

    @Test
    void testCreateResumeNull() {
        Resume resume = new Resume("Frodo", "Baggins", "hobbits@shire.net", "elevenses", null, null, null, null);
        student.createResume(null, null, null, null);
        assertEquals(resume, student.getResume());
    }

    @Test
    void testCreateResumeEmptyArrays() {
        ArrayList<Integer> skillIndexes = new ArrayList<>();
        ArrayList<Integer> classIndexes = new ArrayList<>();
        ArrayList<WorkExperience> workExperiences = new ArrayList<>();
        ArrayList<Education> educations = new ArrayList<>();

        Resume resume = new Resume("Frodo", "Baggins", "hobbits@shire.net", "sophomore", new ArrayList<String>(), 
            new ArrayList<String>(), educations, workExperiences);
        student.createResume(skillIndexes, classIndexes, workExperiences, educations);
        assertEquals(resume, student.getResume());
    }

    @Test
    void testCreateResumeFilled() {
        student.addSkill("JavaDoc");
        student.addSkill("Python");
        student.addClass("Engineering 101");
        student.addClass("Stats and Prob");

        //Set up comparison resume
        ArrayList<Integer> skillIndexes = new ArrayList<>();
        skillIndexes.add(0);
        skillIndexes.add(1);
        ArrayList<Integer> classIndexes = new ArrayList<>();
        classIndexes.add(1);
        ArrayList<WorkExperience> workExperiences = new ArrayList<>();
        workExperiences.add(new WorkExperience("Job 323","Real Inc","7/7/21-7/7/22","Some programming experience"));
        ArrayList<Education> educations = new ArrayList<>();
        educations.add(new Education("UofSA","Comp. Sci.",3.55,"Spring 2020"));
        ArrayList<String> resumeSkills = new ArrayList<>();
        resumeSkills.add(student.getSkills().get(0));
        resumeSkills.add(student.getSkills().get(1));
        ArrayList<String> resumeClasses = new ArrayList<>();
        resumeClasses.add(student.getClasses().get(1));

        Resume resume = new Resume("Frodo", "Baggins", "hobbits@shire.net", "elevenses", resumeSkills, resumeClasses, educations, workExperiences);
        student.createResume(skillIndexes, classIndexes, workExperiences, educations);
        assertEquals(resume, student.getResume());
    }

    @Test
    void testReviewCompanyWithValidReview() {
        CompanyProfile c1 = new CompanyProfile("Example Inc", "1234 Here Street", "A testing company");
        student.reviewCompany(5, "Awesome experience", c1);
        assertEquals("Awesome experience", c1.getReviews().get(0).getMessage());
    }

    @Test
    void testReviewCompanyWithNegativeRating() {
        CompanyProfile c1 = new CompanyProfile("Example Inc", "1234 Here Street", "A testing company");
        student.reviewCompany(-5, "Time to tank your rating", c1);
        assertEquals(0, c1.getReviews().size());
    }

    @Test
    void testReviewCompanyWithZeroRating() {
        CompanyProfile c1 = new CompanyProfile("Example Inc", "1234 Here Street", "A testing company");
        student.reviewCompany(0, "Can you even be this bad?", c1);
        assertEquals(0, c1.getReviews().size());
    }

    @Test
    void testReviewCompanyWithRatingOverFive() {
        CompanyProfile c1 = new CompanyProfile("Example Inc", "1234 Here Street", "A testing company");
        student.reviewCompany(102, "This is probably cheating", c1);
        assertEquals(0, c1.getReviews().size());
    }

    @Test
    void testReviewCompanyRatingWithNullMessage() {
        CompanyProfile c1 = new CompanyProfile("Example Inc", "1234 Here Street", "A testing company");
        student.reviewCompany(4, null, c1);
        assertEquals(0, c1.getReviews().size());
    }

    @Test
    void testApplyForInternshipValidJobListing() {
        JobListing j1 = new JobListing();
        student.applyForInternship(j1);
        assertEquals("Frodo", j1.getApplicants().get(0).getFirstName());
    }

    @Test
    void testApplyForSameInternshipTwice() {
        JobListing j1 = new JobListing();
        student.applyForInternship(j1);
        student.applyForInternship(j1);
        assertEquals(1, j1.getApplicants().size());
    }

    @Test
    void testAddReviewValidReview() {
        student.addReview(new Review("Dante","DMC",4.5,"Royally awesome!"));
        assertEquals(4.5, student.getReviews().get(0).getRating());
    }

    @Test
    void testAddReviewNull() {
        student.addReview(null);
        assertEquals(0, student.getReviews().size());
    }
}
