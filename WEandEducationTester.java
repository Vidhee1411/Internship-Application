import java.util.ArrayList;

import jdk.internal.jshell.tool.resources.l10n;
public class WEandEducationTester {
    
    public static void main( String[] args ) {
        WorkExperience Apple = new WorkExperience("Developer","Apple", "03/01/2019-07/01/2020", "Developing new apps");
        WorkExperience we = new WorkExperience("Program Tester", "Testing Company Inc.", "04/25/2020-06/29/2020", "This was a fun job");
        Education ed = new Education("UofSC", "Computer Science", 3.56, "Spring 2023");
        Resume resume = new Resume("Bob", "Xierling", "bob@email.usc.edu", "Redshirt sophomore");
        Review rev = new Review("Eileen", "Dover", 2.7, "Brandon does not know anything");
        /*ed.setName("Waterton College");
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
        System.out.println(rev.toString());*/

        Student student = new Student("Brandon", "Wheeler", "water@email.sc.edu", "cool", "sophomore");
        /*System.out.println(student.getFirstName());
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
        /*System.out.println(em.getPassword());
        System.out.println(em.getEmail());
        System.out.println(em.getFirstName() + " " + em.getLastName() + " " + em.getPermission() + " " + student.getPermission());*/

        CompanyProfile cp = new CompanyProfile("Wengleton's", "5523 Zephler Avenue, Granger, Alaska 99938", "We sell groceries");
        CompanyProfile cp2 = new CompanyProfile("RJ's gas", "1 Georgia Street, Calhoun, GA 30004", "Gas is good");
        CompanyProfile cp3 = new CompanyProfile("Okay products", "Miami, Florida", "Our products aren't great, they're okay.");
        CompanyProfile cp4 = new CompanyProfile("George's Groceries", "11111 Archaic Avenue, Sunflower, NY 10003", "Last local grocery store in the state");
        CompanyProfile cp5 = new CompanyProfile("Blissful Books", "5432 Forest Drive, Columbia, SC 29227", "Good books come from good stores.");
        /*System.out.println(cp.getCompanyName());
        System.out.println(cp.getAddress());
        System.out.println(cp.getDescription());
        cp.setAddress("2500 Ziegler Street, Quinoa, Maine 00004");
        cp.setCompanyName("Clayton's Ice Cream");
        cp.setDescription("We got bought . . . so sad.");
        System.out.println(cp.getCompanyName());
        System.out.println(cp.getAddress());
        System.out.println(cp.getDescription());
        cp.addReview(rev);*/

        JobListing jl = new JobListing("Professional Eater", "You will be training to face Joey Chestnut. Good luck . . .", "In-person", true, 500.25, "Quarterly Insurance Co.");
        JobListing jl2 = new JobListing("Professional Football player", "You will be gaining a lot of weight", "In-person", true, 1000, "Dallas Cowboys");
        JobListing jl3 = new JobListing("Duplicator of projects", "Essentially, you will be forging everyone else's work to make money", "Remote", true, 10.25, "Brett Akers Foundation");
        JobListing jl4 = new JobListing("Wellness Coordinator", "You will be making sure my mental health stays up to par. You are my personal assistant.", "In-person", true, 13.75, "Couch Potato");
        JobListing jl5 = new JobListing("Novice arguer", "You will argue your case to me and one day I may let you win.", "Remote", true, 8.50, "Arg. Co.");
        JobListing jl6 = new JobListing("Ice crusher", "Sometimes people just need crushed ice. Your job is to not ask why and just do it. ", "In person", true, 25.75, "Jud's Farm");
        JobListing jl7 = new JobListing("Hedgefund assistant bank manager", "You will be put in charge of the most powerful company in the world - don't mess up.", "In person", true, 750.67, "Crazy Big Corp.");
        JobListing jl8 = new JobListing("The guy who posts listings", "You will take over my job after I teach you. Be prepared to fall asleep on the job.", "In person", true, 3.89, "New Posts Inc.");
        SearchableDatabase sd = SearchableDatabase.getInstance();
        sd.addJobListing(jl);
        sd.addJobListing(jl2);
        sd.addJobListing(jl3);
        sd.addJobListing(jl4);
        sd.addJobListing(jl5);
        sd.addJobListing(jl6);
        sd.addJobListing(jl7);
        sd.addJobListing(jl8);
        //sd.removeJobListing(jl7);
        sd.addProfile(cp);
        sd.addProfile(cp2);
        sd.addProfile(cp3);
        sd.addProfile(cp4);
        sd.addProfile(cp5);
        ArrayList<CompanyProfile> result = sd.searchProfiles("Wengleton's");
        for (CompanyProfile list: result) {
            System.out.println(list.toString());
        }
        /*System.out.println(jl.toString());
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
        System.out.println(jl.getVisibility());*/
        
    }
}
