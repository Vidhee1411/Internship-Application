import java.util.ArrayList;
public class WEandEducationTester {
    
    public static void main( String[] args ) {
        WorkExperience we = new WorkExperience("Program Tester", "Testing Company Inc.", "04/25/2020-06/29/2020", "This was a fun job");
        Education ed = new Education();
        Resume resume = new Resume("Bob", "Xierling", "bob@email.usc.edu", "Redshirt sophomore");
        ed.setName("Waterton College");
        ed.setMajor("Sauce Maker");
        ed.setGPA(3.27);
        ed.setExpectedGradDate("01/2025");

        System.out.println(we.getJobTitle());
        System.out.println(we.getCompany());
        System.out.println(we.getDateRange());
        System.out.println(we.getDescription());

        System.out.println(ed.getName());
        System.out.println(ed.getMajor());
        System.out.println(ed.getGPA());
        System.out.println(ed.getExpectedGradDate());

        System.out.println(resume.getFirstName());
        System.out.println(resume.getLastName());
        System.out.println(resume.getEmail());
        System.out.println(resume.getYearInSchool());

        resume.editEmail("BobUSC1@email.sc.edu");
        resume.editYearInSchool("Redshirt Junior");

        System.out.println(resume.getEmail());
        System.out.println(resume.getYearInSchool());

        resume.addEducation(ed);
        resume.addExperience(we);

        ArrayList <WorkExperience> w = resume.getExperiences();
        ArrayList <Education> r = resume.getEducation();

        for (WorkExperience q: w) {
            System.out.println(q.getJobTitle());
            System.out.println(q.getCompany());
            System.out.println(q.getDateRange());
            System.out.println(q.getDescription());
        }

        for (Education f: r) {
            System.out.println(f.getName());
            System.out.println(f.getMajor());
            System.out.println(f.getGPA());
            System.out.println(f.getExpectedGradDate());
        }




    }
}
