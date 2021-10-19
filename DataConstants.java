/**
 * The DataConstants class holds all of the constants that have to do with
 * storing and retrieving our data from the JSON files.
 * @author Joshua DuPuis 
 */
public class DataConstants {
    /**
     * file names 
     */
    protected static final String student_File_Name = "JSON\\Student.json";
    protected static final String admin_File_Name = "JSON\\Administrator.json";
    protected static final String employer_File_Name = "JSON\\employers.json";
    protected static final String company_Profile_File_Name = "JSON\\companyProfiles.json";
    protected static final String job_Listing_File_Name = "JSON\\jobListings.json";
    protected static final String resume_File_Name = "JSON\\Resume.json";
    /**
     * admin fields
     */
    protected static final String admin_ID = "userID";
    protected static final String admin_First_Name = "firstName";
    protected static final String admin_Last_Name = "lastName";
    protected static final String admin_Email = "email";
    protected static final String admin_Password = "password";
    protected static final String admin_Permission = "Permission";
    /**
     * student fields 
     */
     protected static final String student_ID = "userID";
     protected static final String student_First_Name = "firstName";
     protected static final String student_Last_Name = "lastName";
     protected static final String student_Email = "email";
     protected static final String student_Password = "password";
     protected static final String student_permission = "Permission";
     protected static final String student_Reviews = "reviews";
     protected static final String student_Resume_ID = "resume";
     /**
      * employer fields 
      */
      protected static final String employer_ID = "userID";
     protected static final String employer_First_Name = "firstName";
     protected static final String employer_Last_Name = "lastName";
     protected static final String employer_Email = "email";
     protected static final String employer_Password = "password";
     protected static final String employer_ = "Permission";
     protected static final String employer_Reviews = "reviews";
     protected static final String employer_Associated_Company = "associatedCompanyID";
     /**
      * company profile fields 
      */
      protected static final String company_ID = "companyID";
      protected static final String company_HQ_Adress = "hqAdress";
      protected static final String company_Name = "companyName";
      protected static final String company_Description = "description";
      protected static final String company_Listings_IDS = "currentListingIDs";
      protected static final String company_Associated_Employer_IDs = "associatedEmployersIDs";
      protected static final String company_Reviews = "reviews";
    /**
     * joblisting fields
     */
    protected static final String listing_ID = "listingID";
    protected static final String listing_Title = "title";
    protected static final String listing_Paid = "paid";
    protected static final String listing_Pay_Rate = "payRate";
    protected static final String listing_Required_Skills = "requiredSkills";
    protected static final String listing_Company_Name = "companyName";
    protected static final String listing_Hidden = "hidden";
    protected static final String listing_Applicant_IDS = "applicantIDs";
    /**
     * resume fields
     */
    protected static final String resume_User_ID = "userID";
    protected static final String resume_School_year = "yearInSchool";
    protected static final String resume_Skills = "Skills";
    protected static final String resume_Classes = "classes";
    protected static final String resume_Work_Experience = "workexperiences";
    protected static final String resume_Education = "education";
    /**
     * work experience fields 
     */
    protected static final String experience_Title = "title";
    protected static final String experience_Company = "Company";
    protected static final String experience_Date_Range = "dateRange";
    protected static final String experience_Description = "description";
    /**
     * education fields
     */
    protected static final String education_University_Name = "universityName";
    protected static final String education_Major = "collegeMajor";
    protected static final String education_GPA = "GPA";
    protected static final String eudcation_Graduation_Date = "expectedGradDate";
    /**
     *  review fields 
     */
    protected static final String review_First_Name = "firstName";
    protected static final String review_Last_Name = "lastName";
    protected static final String review_Rating = "rating";
    protected static final String review_Message = "message";
    protected static final String review_Hidden = "hidden";
   






}
