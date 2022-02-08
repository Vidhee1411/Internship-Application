import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JobLisitingTest {
     private JobListing j1;
    @BeforeEach
    public void setup() {
        j1 = new JobListing("Software Developer"," Inter will get oppurtunity to deal with real softwares","South Carolina",true, 20.00, "Global Enterprise");
    }

    @Test
    public void addRequiredSkillValidTest() {
        j1.addRequiredSkill("Java");
        assertEquals("Java", j1.getRequiredSkills().get(0));
    }

    @Test
    public void addRequiredSkillNullTest() {
        j1.addRequiredSkill(null);
        assertEquals(0, j1.getRequiredSkills().size());
    }

    @Test 
    public void addSameRequiredSkillTwiceTest() {
        j1.addRequiredSkill("Java");
        j1.addRequiredSkill("Java");
        assertEquals(1, j1.getRequiredSkills().size());
    }

    @Test
    public void removeRequiredSkillValidTest() {
        j1.addRequiredSkill("Python");
        j1.removeRequiredSkill("Python");
        assertNotEquals(1, j1.getRequiredSkills().size());
    }

    @Test
    public void removeRequiredSkillNullTest() {
        j1.addRequiredSkill("C#");
        j1.removeRequiredSkill(null);
        assertEquals(1, j1.getRequiredSkills().size());
        }

    @Test
    public void removeRequiredSkillOfWrongListingTest() {
        JobListing j2 = new JobListing("Data Science"," Inter will get oppurtunity to deal with company's code","Chicago",true, 16.00, "Bose");
        j1.addRequiredSkill("Hardworking");
        j2.addRequiredSkill("Python");
        j2.removeRequiredSkill("Hardworking");
        assertEquals(1, j2.getRequiredSkills().size());
        }

    @Test
    public void removeRequiredSkillCaseTest() {
        j1.addRequiredSkill("Javascript");
        j1.removeRequiredSkill("javaScript");
        assertEquals(0, j1.getRequiredSkills().size());
    }

    @Test
    public void applyValidTest() {
        Student student = new Student("Lauren","Foltzo","lauren@email.sc.edu","lemonsqueezy", "Junior");
        j1.apply(student);
        assertEquals(1,j1.getApplicants().size());
    }
    
    @Test
    public void applyTwiceTest() {
        Student student = new Student("Lauren","Foltzo","lauren@email.sc.edu","lemonsqueezy", "Junior");
        j1.apply(student);
        j1.apply(student);
        assertEquals(1, j1.getApplicants().size());
    }

}

