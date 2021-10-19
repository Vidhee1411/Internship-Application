/**
 * The DataConstants class holds all of the constants that have to do with
 * storing and retrieving our data from the JSON files.
 * @author Joshua DuPuis 
 */
public class DataConstants {
    /**
     * file names 
     */
    protected static final String STUDENT_FILE_NAME = "JSON\\Student.json";
    protected static final String ADMIN_FILE_NAME = "JSON\\Administrator.json";
    protected static final String EMPLOYER_FILE_NAME = "JSON\\employers.json";
    protected static final String COMPANY_PROFILE_FILE_NAME = "JSON\\companyProfiles.json";
    protected static final String JOB_LISTING_FILE_NAME = "JSON\\jobListings.json";
    protected static final String RESUME_FILE_NAME = "JSON\\Resume.json";
    /**
     * admin fields
     */
    protected static final String ADMIN_ID = "userID";
    protected static final String ADMIN_FIRST_NAME = "firstName";
    protected static final String ADMIN_LAST_NAME = "lastName";
    protected static final String ADMIN_EMAIL = "email";
    protected static final String ADMIN_PASSWORD = "password";
    protected static final String ADMIN_PERMISSION = "Permission";
    /**
     * student fields 
     */
     protected static final String STUDENT_ID = "userID";
     protected static final String STUDENT_FIRST_NAME = "firstName";
     protected static final String STUDENT_LAST_NAME = "lastName";
     protected static final String STUDENT_EMAIL = "email";
     protected static final String STUDENT_PASSWORD = "password";
     protected static final String STUDENT_PERMISSION = "Permission";
     protected static final String STUDENT_REVIEWS = "reviews";
     protected static final String STUDENT_RESUME_ID = "resume";
     /**
      * employer fields 
      */
      protected static final String EMPLOYER_ID = "userID";
     protected static final String EMPLOYER_FIRST_NAME = "firstName";
     protected static final String EMPLOYER_LAST_NAME = "lastName";
     protected static final String EMPLOYER_EMAIL = "email";
     protected static final String EMPLOYER_PASSWORD = "password";
     protected static final String EMPLOYER_PERMISSION = "Permission";
     protected static final String EMPLOYER_REVIEWS = "reviews";
     protected static final String EMPLOYER_ASSOCIATED_COMPANY = "associatedCompanyID";
     /**
      * company profile fields 
      */
      protected static final String COMPANY_ID = "companyID";
      protected static final String COMPANY_HQ_ADDRESS = "hqAddress";
      protected static final String COMPANY_NAME = "companyName";
      protected static final String COMPANY_DESCRIPTION = "description";
      protected static final String COMPANY_LISTINGS_IDS = "currentListingIDs";
      protected static final String COMPANY_ASSOCIATED_EMPLOYER_IDS = "associatedEmployersIDs";
      protected static final String COMPANY_REVIEWS = "reviews";
    /**
     * joblisting fields
     */
    protected static final String LISTING_ID = "listingID";
    protected static final String LISTING_TITLE = "title";
    protected static final String LISTING_DESCRIPTION = "description";
    protected static final String LISTING_PAID = "paid";
    protected static final String LISTING_PAY_RATE = "payRate";
    protected static final String LISTING_REQUIRED_SKILLS = "requiredSkills";
    protected static final String LISTING_COMPANY_NAME = "companyName";
    protected static final String LISTING_HIDDEN = "hidden";
    protected static final String LISTING_APPLICANT_IDS = "applicantIDs";
    /**
     * resume fields
     */
    protected static final String RESUME_USER_ID = "userID";
    protected static final String RESUME_SCHOOL_YEAR = "yearInSchool";
    protected static final String RESUME_SKILLS = "Skills";
    protected static final String RESUME_CLASSES = "classes";
    protected static final String RESUME_WORK_EXPERIENCE = "workexperiences";
    protected static final String RESUME_EDUCATION = "education";
    /**
     * work experience fields 
     */
    protected static final String EXPERIENCE_TITLE = "title";
    protected static final String EXPERIENCE_COMPANY = "Company";
    protected static final String EXPERIENCE_DATE_RANGE = "dateRange";
    protected static final String EXPERIENCE_DESCRIPTION = "description";
    /**
     * education fields
     */
    protected static final String EDUCATION_UNIVERSITY_NAME = "universityName";
    protected static final String EDUCATION_MAJOR = "collegeMajor";
    protected static final String EDUCATION_GPA = "GPA";
    protected static final String EDUCATION_GRADUATION_DATE = "expectedGradDate";
    /**
     *  review fields 
     */
    protected static final String REVIEW_FIRST_NAME = "firstName";
    protected static final String REVIEW_LAST_NAME = "lastName";
    protected static final String REVIEW_RATING = "rating";
    protected static final String REVIEW_MESSAGE = "message";
    protected static final String REVIEW_HIDDEN = "hidden";
   






}
