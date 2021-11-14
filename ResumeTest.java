import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResumeTest {
     private Resume r1;
    
     @BeforeEach
    public void setup() {
        r1 = new Resume("Gina", "D'souza", "gina@email.sc.edu", "Junior");
    }

    @Test
    public void addSkillvalidTest() {
        r1.addSkill("Java");
        assertEquals("Java", r1.getSkills().get(0));
    }

    @Test 
    public void addSameSkillTwiceTest() {
        r1.addSkill("Java");
        r1.addSkill("Java");
        assertEquals(1, r1.getSkills().size());
    }

    @Test
    public void addRequiredSkillNullTest() {
        r1.addSkill(null);
        assertEquals(0, r1.getSkills().size());
    }

    @Test
    public void addClassValidtest() {
        r1.addClass("CSCE 247");
        assertEquals("CSCE 247", r1.getClasses().get(0));
    }

    @Test 
    public void addSameClassTwiceTest() {
        r1.addClass("CSCE 246");
        r1.addClass("CSCE 246");
        assertEquals(1, r1.getClasses().size());
    }

    @Test
    public void addClassNullTest() {
        r1.addClass(null);
        assertEquals(0, r1.getClasses().size());
    }

    @Test 
    public void addWorkExperienceValidTest() {
       WorkExperience w1 = new WorkExperience("Developer", "Quina", "10/2020-10/2021", "Great Job");
       r1.addExperience(w1);
       assertEquals(1, r1.getExperiences().size());
    }

    @Test
    public void addWorkExperinceNullTest() {
        r1.addExperience(null);
        assertEquals(0, r1.getExperiences().size());
    }

    @Test
    public void addWorkExperienceSameTest() {
        WorkExperience w1 = new WorkExperience("Developer", "Quina", "10/2020-10/2021", "Great Job");
        r1.addExperience(w1);
        r1.addExperience(w1);
        assertEquals(1, r1.getExperiences().size());
    }

    @Test 
    public void addEducationValidTest() {
       Education e1 = new Education("UofSC", "CS",3.91,"String 2023");
       r1.addEducation(e1);
       assertEquals(1, r1.getEducation().size());
    }

    @Test
    public void addEducationNullTest() {
        r1.addEducation(null);
        assertEquals(0, r1.getEducation().size());
    }

    @Test
    public void addEducationSameTest() {
        Education e1 = new Education("UofSC", "CS",3.91,"String 2023");
        r1.addEducation(e1);
        r1.addEducation(e1);
        assertEquals(1, r1.getEducation().size());
    }
    
    @Test
    public void removeSkillValidTest() {
        r1.addSkill("Java");
        r1.removeSkill("Java");
        assertEquals(0, r1.getSkills().size());
    }

    @Test
    public void removeClassValidTest() {
        r1.addClass("CSCE 247");
        r1.removeClass("CSCE 247");
        assertEquals(0,r1.getClasses().size());
    }

    @Test 
    public void removeEducationValiTest() {
        Education e1 = new Education();
        r1.addEducation(e1);
        r1.removeEducation(e1);
        assertEquals(0,r1.getEducation().size());
    }

    @Test
    public void removeExperienceValidTest() {
        WorkExperience w1 = new WorkExperience("Developer", "Quina", "10/2020-10/2021", "Great Job");
        r1.addExperience(w1);
        r1.removeExperience(w1);
        assertEquals(0,r1.getExperiences().size());
    }
}


