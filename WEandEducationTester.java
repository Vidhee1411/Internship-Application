public class WEandEducationTester {
    
    public static void main( String[] args ) {
        WorkExperience we = new WorkExperience("Program Tester", "Testing Company Inc.", "04/25/2020-06/29/2020", "This was a fun job");
        Education ed = new Education();
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

    }
}
