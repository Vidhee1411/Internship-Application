import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    private Student student;

    @BeforeEach
    public void setup() {
        student = new Student("Frodo", "Baggins", "hobbits@shire.net", "elevenses", "sophomore", UUID.randomUUID(), 
            new ArrayList<String>(), new ArrayList<String>(), new ArrayList<Review>());
    }

    @Test
    public void testCreateResumeNull() {
        Resume resume = new Resume("Frodo", "Baggins", "hobbits@shire.net", "elevenses", null, null, null, null);
        try {
            student.createResume(null, null, null, null);
        } catch(NullPointerException e) {
            assertEquals(0, 1);
        }
        assertEquals(resume.getEmail(), student.getResume().getEmail());
    }

    @Test
    public void testCreateResumeEmptyArrays() {
        ArrayList<Integer> skillIndexes = new ArrayList<>();
        ArrayList<Integer> classIndexes = new ArrayList<>();
        ArrayList<WorkExperience> workExperiences = new ArrayList<>();
        ArrayList<Education> educations = new ArrayList<>();

        Resume resume = new Resume("Frodo", "Baggins", "hobbits@shire.net", "sophomore", new ArrayList<String>(), 
            new ArrayList<String>(), educations, workExperiences);
        student.createResume(skillIndexes, classIndexes, workExperiences, educations);
        assertEquals(resume.getEmail(), student.getResume().getEmail());
    }

    @Test
    public void testCreateResumeFilled() {
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
        assertEquals(resume.getSkills().get(1), student.getResume().getSkills().get(1));
    }

    @Test
    public void testReviewCompanyWithValidReview() {
        CompanyProfile c1 = new CompanyProfile("Example Inc", "1234 Here Street", "A testing company");
        student.reviewCompany(5, "Awesome experience", c1);
        assertEquals("Awesome experience", c1.getReviews().get(0).getMessage());
    }

    @Test
    public void testReviewCompanyWithNegativeRating() {
        CompanyProfile c1 = new CompanyProfile("Example Inc", "1234 Here Street", "A testing company");
        student.reviewCompany(-5, "Time to tank your rating", c1);
        assertEquals(0, c1.getReviews().size());
    }

    @Test
    public void testReviewCompanyWithZeroRating() {
        CompanyProfile c1 = new CompanyProfile("Example Inc", "1234 Here Street", "A testing company");
        student.reviewCompany(0, "Can you even be this bad?", c1);
        assertEquals(0, c1.getReviews().size());
    }

    @Test
    public void testReviewCompanyWithRatingOverFive() {
        CompanyProfile c1 = new CompanyProfile("Example Inc", "1234 Here Street", "A testing company");
        student.reviewCompany(102, "This is probably cheating", c1);
        assertEquals(0, c1.getReviews().size());
    }

    @Test
    public void testReviewCompanyRatingWithNullMessage() {
        CompanyProfile c1 = new CompanyProfile("Example Inc", "1234 Here Street", "A testing company");
        student.reviewCompany(4, null, c1);
        assertEquals(0, c1.getReviews().size());
    }

    @Test
    public void testApplyForInternshipValidJobListing() {
        JobListing j1 = new JobListing();
        student.applyForInternship(j1);
        assertEquals("Frodo", j1.getApplicants().get(0).getFirstName());
    }

    @Test
    public void testApplyForSameInternshipTwice() {
        JobListing j1 = new JobListing();
        student.applyForInternship(j1);
        student.applyForInternship(j1);
        assertEquals(1, j1.getApplicants().size());
    }

    @Test
    public void testAddReviewValidReview() {
        student.addReview(new Review("Dante","DMC",4.5,"Royally awesome!"));
        assertEquals(4.5, student.getReviews().get(0).getRating());
    }

    @Test
    public void testAddReviewNull() {
        student.addReview(null);
        assertEquals(0, student.getReviews().size());
    }
}
