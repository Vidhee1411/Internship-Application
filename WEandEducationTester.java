import java.util.ArrayList;
public class WEandEducationTester {
    
    public static void main( String[] args ) {
        WorkExperience Apple = new WorkExperience("Developer","Apple", "03/01/2019-07/01/2020", "Developing new apps");
        WorkExperience we = new WorkExperience("Program Tester", "Testing Company Inc.", "04/25/2020-06/29/2020", "This was a fun job");
        Education ed = new Education("UofSC", "Computer Science", 3.56, "Spring 2023");
        Resume resume = new Resume("Bob", "Xierling", "bob@email.usc.edu", "Redshirt sophomore");
        Review rev = new Review("Eileen", "Dover", 2.7, "Brandon does not know anything");
        ed.setName("Waterton College");
        ed.setMajor("Sauce Maker");
        ed.setGPA(3.27);
        ed.setExpectedGradDate("01/2025");
 
        System.out.println(Apple.getJobTitle());
        System.out.println(Apple.getCompany());
        System.out.println(Apple.getDateRange());
        System.out.println(Apple.getDescription());
        System.out.println();
        
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
        resume.addExperience(Apple);
        resume.addExperience(we);
        

        ArrayList <WorkExperience> w = resume.getExperiences();
        ArrayList <Education> r = resume.getEducation();

        for (WorkExperience q: w) {
            System.out.println(q.getJobTitle());
            System.out.println(q.getCompany());
            System.out.println(q.getDateRange());
            System.out.println(q.getDescription());
        }

        resume.removeExperience(Apple);

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
        
        System.out.println(rev.toString());
        rev.setDescription("Well . . . that's a rap! Henrico does not listen.");
        rev.setRating(1.7);
        System.out.println(rev.toString());

        Student student = new Student("Brandon", "Wheeler", "water@email.sc.edu", "cool", "sophomore");
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        System.out.println(student.getEmail());
        System.out.println(student.getPassword());
        System.out.println(student.getYearInSchool());
        System.out.println();

        System.out.println("Resume:");
        student.createResume();
        student.getResume().addSkill("Hard-working");
        student.getResume().addSkill("Java programming");
        student.getResume().addSkill("Totally unreal skill");
        student.getResume().addEducation(ed);
        student.getResume().addExperience(Apple);
        student.getResume().addExperience(we);
        System.out.println(student.getResume().toString());

        student.addReview(rev);
        ArrayList<Review> revs = student.getReviews();
        /*for (Review review: revs) {
            System.out.println(review.toString());
        }*/

        Employer em = new Employer("Joe", "Brainder", "jbtheCoolGuy@rockys.net", "WELLNEss1!");
        System.out.println(em.getPassword());
        System.out.println(em.getEmail());
        System.out.println(em.getFirstName() + " " + em.getLastName() + " " + em.getPermission() + " " + student.getPermission());

        CompanyProfile cp = new CompanyProfile("Wengleton's", "5523 Zephler Avenue, Granger, Alaska 99938", "We sell groceries");
        System.out.println(cp.getCompanyName());
        System.out.println(cp.getAddress());
        System.out.println(cp.getDescription());
        cp.setAddress("2500 Ziegler Street, Quinoa, Maine 00004");
        cp.setCompanyName("Clayton's Ice Cream");
        cp.setDescription("We got bought . . . so sad.");
        System.out.println(cp.getCompanyName());
        System.out.println(cp.getAddress());
        System.out.println(cp.getDescription());
        cp.addReview(rev);

        JobListing jl = new JobListing("Professional Eater", "You will be training to face Joey Chestnut. Good luck . . .", "In-person", true, 500.25, "Quarterly Insurance Co.");
        System.out.println(jl.toString());
        System.out.println(jl.getVisibility());
        jl.addRequiredSkill("programming");
        jl.addRequiredSkill("waterboarding");
        jl.addRequiredSkill("tight-rope walking");
        ArrayList<String> skil = jl.getRequiredSkills();
        for (String skills: skil) {
            System.out.println(skills);
        }
        jl.apply(student);
        ArrayList<Student> apps = jl.getApplicants();
        for (Student stud: apps) {
            System.out.print(stud.getFirstName() + " " + stud.getLastName());
            System.out.println();
        }

        jl.removeRequiredSkill("tight-rope walking");
        ArrayList<String> skillio = jl.getRequiredSkills();
        for (String skills: skillio) {
            System.out.println(skills);
        }

        jl.editTitle("The worst professional eater");
        jl.editDescription("I don't think you can do it. It's too hard.");
        jl.editPay(false);
        jl.editPayRate(0.0);
        jl.setVisibility(false);
        System.out.println(jl.toString());
        System.out.println(jl.getVisibility());
        
    }
}
