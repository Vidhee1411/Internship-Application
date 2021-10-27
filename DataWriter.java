import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * The DataWriter class writes data that is entered by different users to the
 * JSON files.
 * 
 * @author Zachary Young
 * @author Joshua DuPuis
 */
public class DataWriter extends DataConstants {
    /**
     * The saveUsers method writes all of the Users in the system to the JSON Users
     * file to store data on each user.
     */
    public static void saveUsers() {
        SearchableDatabase users = SearchableDatabase.getInstance();
        ArrayList<User> userList = users.getUsers();
        
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Employer> employers = new ArrayList<>();
        ArrayList<Administrator> administrators = new ArrayList<>();

        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            switch(user.getPermission()) {
                case 0: //User is a student
                    students.add((Student) user);
                    break;
                case 1: //User is an employer
                    employers.add((Employer) user);
                    break;
                case 2:
                    administrators.add((Administrator) user);
                    break;
                default:
                    System.out.println("User " + user.getFirstName() + " " + user.getLastName() + " (ID: " +
                    user.getUserUUID() + ") has invalid permissions. They will not be written to the database.");
            }
        }

        saveStudents(students);
        saveEmployers(employers);
        saveAdministrators(administrators);
    }

    /**
     * Private helper method for writing an ArrayList of Students to the Student.json file.
     * @param studentList The ArrayList of Students to write
     */
    private static void saveStudents(ArrayList<Student> studentList) {
        JSONArray jsonStudents = new JSONArray();

        for(Student student : studentList) {
            JSONObject studentDetails = new JSONObject();
            studentDetails.put(STUDENT_ID, student.getUUID().toString());
            studentDetails.put(STUDENT_FIRST_NAME, student.getFirstName());
            studentDetails.put(STUDENT_LAST_NAME, student.getLastName());
            studentDetails.put(STUDENT_EMAIL, student.getEmail());
            studentDetails.put(STUDENT_PASSWORD, student.getPassword());
            studentDetails.put(STUDENT_PERMISSION, 0);
            studentDetails.put(STUDENT_REVIEWS, reviewsToJSONArray(student.getReviews()).toJSONString());
            studentDetails.put(STUDENT_CLASSES, arrayListToJsonArray(student.getClasses()));
            studentDetails.put(STUDENT_SKILLS, arrayListToJsonArray(student.getSkills()));
            studentDetails.put(STUDENT_RESUMES, resumeToJSONArray(student.getResume(), student));
            jsonStudents.add(studentDetails);
        }
       
        try {
            FileWriter file = new FileWriter(STUDENT_FILE_NAME);
            file.write(jsonStudents.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Private helper method used to convert an ArrayList of reviews to a JSONArray.
     * @param reviews The ArrayList of reviews to convert
     * @return The JSONArray representation of reviews
     */
    private static JSONArray reviewsToJSONArray(ArrayList<Review> reviews) {
        JSONArray jsonReviews = new JSONArray();
        for(Review review : reviews) {
            //Since reviews are objects with their own set of fields, put info in a JSONObject
            JSONObject reviewDetails = new JSONObject();
            reviewDetails.put(REVIEW_FIRST_NAME, review.getFirstName());
            reviewDetails.put(REVIEW_LAST_NAME, review.getLastName());
            reviewDetails.put(REVIEW_RATING, review.getRating());
            reviewDetails.put(REVIEW_MESSAGE, review.getMessage());
            reviewDetails.put(REVIEW_HIDDEN, review.getHidden());
            jsonReviews.add(reviewDetails);
        }
        return jsonReviews;
    }

    /**
     * Private helper method used to convert an ArrayList of reviews to a JSONArray.
     * @param resumes The ArrayList of reviews to convert
     * @return The JSONArray representation of reviews
     */
    private static JSONArray resumeToJSONArray(Resume resume, Student student) {
        JSONArray jsonResumes = new JSONArray();
        JSONObject resumeDetails = new JSONObject();
        resumeDetails.put(RESUME_SCHOOL_YEAR, resume.getYearInSchool());
        resumeDetails.put(RESUME_WORK_EXPERIENCE, workExperiencesToJsonArray(resume.getExperiences()).toJSONString());
        resumeDetails.put(RESUME_EDUCATION, educationsToJsonArray(resume.getEducation()).toJSONString());
        resumeDetails.put(RESUME_SKILL_INDEXES, arrayListToJsonArray(createIndexesArray(resume.getSkills(), student.getSkills())).toJSONString());
        resumeDetails.put(RESUME_CLASS_INDEXES, arrayListToJsonArray(createIndexesArray(resume.getClasses(), student.getClasses())).toJSONString());
        jsonResumes.add(resumeDetails);
        return jsonResumes;
    }

    /**
     * Private helper method used to create an arrayList of indexes given a subList and mainList of elements. Returns
     * an arrayList of the indexes of all the subList elements that are within the mainList.
     * @param subList The list of elements to find the indexes of
     * @param mainList The overall list of elements
     * @return An arrayList with the mainList indexes of all the subList elements
     */
    private static ArrayList<Integer> createIndexesArray(ArrayList<String> subList, ArrayList<String> mainList) {
        ArrayList<Integer> indexArray = new ArrayList<>();
        int index;
        for(String str : subList) {
            index = mainList.indexOf(str);
            //Redundancy check; if an element is somehow in the resume but not the student's account, don't keep it
            if(index != -1) indexArray.add(index);
        }
        return indexArray;
    }

    /**
     * Private helper method used to convert an ArrayList of WorkExperiences to a JSONArray.
     * @param experiences The ArrayList of WorkExperiences to convert
     * @return The JSONArray representation of experiences
     */
    private static JSONArray workExperiencesToJsonArray(ArrayList<WorkExperience> experiences) {
        JSONArray jsonWorkExperiences = new JSONArray();
        for(WorkExperience experience : experiences) {
            //Since workExperiences are separate objects with their own info, put their info into separate JSONObjects
            JSONObject experienceDetails = new JSONObject();
            experienceDetails.put(EXPERIENCE_TITLE, experience.getJobTitle());
            experienceDetails.put(EXPERIENCE_COMPANY, experience.getCompany());
            experienceDetails.put(EXPERIENCE_DATE_RANGE, experience.getDateRange());
            experienceDetails.put(EXPERIENCE_DESCRIPTION, experience.getDescription());
            jsonWorkExperiences.add(experienceDetails);
        }
        return jsonWorkExperiences;
    }

    /**
     * Private helper method used to convert an ArrayList of Educations to a JSONArray.
     * @param educations The ArrayList of Educations to convert
     * @return The JSONArray representation of educations
     */
    private static JSONArray educationsToJsonArray(ArrayList<Education> educations) {
        JSONArray jsonEducations = new JSONArray();
        for(Education education : educations) {
            //Since workExperiences are separate objects with their own info, put their info into separate JSONObjects
            JSONObject educationDetails = new JSONObject();
            educationDetails.put(EDUCATION_UNIVERSITY_NAME, education.getName());
            educationDetails.put(EDUCATION_MAJOR, education.getMajor());
            educationDetails.put(EDUCATION_GPA, education.getGPA());
            educationDetails.put(EDUCATION_GRADUATION_DATE, education.getExpectedGradDate());
            jsonEducations.add(educationDetails);
        }
        return jsonEducations;
    }

    /**
     * Private helper method for writing an arrayList of Employers to the Employers.json file.
     * @param employerList The ArrayList of Employer to write
     */
    private static void saveEmployers(ArrayList<Employer> employerList) {
        JSONArray jsonEmployer = new JSONArray();
        for(Employer employer : employerList) {
            JSONObject employerDetails = new JSONObject();
            employerDetails.put(EMPLOYER_ID, employer.getUUID().toString());
            employerDetails.put(EMPLOYER_FIRST_NAME, employer.getFirstName());
            employerDetails.put(EMPLOYER_LAST_NAME, employer.getLastName());
            employerDetails.put(EMPLOYER_EMAIL, employer.getEmail());
            employerDetails.put(EMPLOYER_PASSWORD, employer.getPassword());
            employerDetails.put(EMPLOYER_PERMISSION, 1);
            employerDetails.put(EMPLOYER_ASSOCIATED_COMPANY, employer.getCompany().toString());
            jsonEmployer.add(employerDetails);
        }

        try {
            FileWriter file = new FileWriter(EMPLOYER_FILE_NAME);
            file.write(jsonEmployer.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Private helper method for writing an arrayList of Administrator to the Administrator.json file.
     * @param adminList The ArrayList of Students to write
     */
    private static void saveAdministrators(ArrayList<Administrator> adminList) {
        JSONArray jsonAdministrator = new JSONArray();
        for(Administrator administrator : adminList) {
            JSONObject adminDetails = new JSONObject();
            adminDetails.put(ADMIN_ID, administrator.getUUID().toString());
            adminDetails.put(ADMIN_FIRST_NAME, administrator.getFirstName());
            adminDetails.put(ADMIN_LAST_NAME, administrator.getLastName());
            adminDetails.put(ADMIN_EMAIL, administrator.getEmail());
            adminDetails.put(ADMIN_PASSWORD, administrator.getPassword());
            adminDetails.put(ADMIN_PERMISSION, -1);
            jsonAdministrator.add(adminDetails);
        }

        try {
            FileWriter file = new FileWriter(ADMIN_FILE_NAME);
            file.write(jsonAdministrator.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The saveJobListings method writes all of the JobListings in the system to the
     * JSON JobListings file to store data on each JobListing
     */
    public static void saveJobListings() {
        SearchableDatabase companyJobDatabase = SearchableDatabase.getInstance();
        ArrayList<JobListing> jobsList = companyJobDatabase.getJobListings();
        JSONArray jsonListings = new JSONArray();

        for (int i = 0; i < jobsList.size(); i++) {
            jsonListings.add(getJobListingJSON(jobsList.get(i)));
        }

        try {
            FileWriter file = new FileWriter(JOB_LISTING_FILE_NAME);
            file.write(jsonListings.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts a given JobListing into a JSONObject.
     * 
     * @param jobListing The JobListing to convert into a JSONObject
     * @return The JSONObject representation of the JobListing
     */
    public static JSONObject getJobListingJSON(JobListing jobListing) {
        JSONObject listingDetails = new JSONObject();
        listingDetails.put(LISTING_ID, jobListing.getUUID().toString());
        listingDetails.put(LISTING_TITLE, jobListing.getTitle());
        listingDetails.put(LISTING_DESCRIPTION, jobListing.getDescription());
        listingDetails.put(LISTING_PAID, jobListing.getPaid());
        listingDetails.put(LISTING_PAY_RATE, jobListing.getPayRate());
        listingDetails.put(LISTING_REQUIRED_SKILLS, arrayListToJsonArray(jobListing.getRequiredSkills()).toJSONString());
        listingDetails.put(LISTING_COMPANY_NAME, jobListing.getCompany());
        listingDetails.put(LISTING_HIDDEN, jobListing.getVisibility());
        listingDetails.put(LISTING_APPLICANT_IDS, studentsToJsonIDArray(jobListing.getApplicants()).toJSONString());
        return listingDetails;
    }

    /**
     * Private helper method used to convert an ArrayList of Students into a JSONArray of the Students' IDs
     * @param students The arrayList of Students to convert
     * @return A JSONArray containing the IDs of the Students
     */
    private static JSONArray studentsToJsonIDArray(ArrayList<Student> students) {
        JSONArray jsonApplicants = new JSONArray();
        for(Student student: students) {
            //May have to remove the toString if it doesn't work
            jsonApplicants.add(student.getUUID().toString());
        }
        return jsonApplicants;
    } 

    /**
     * The saveCompanyProfiles method writes all of the CompanyProfiles in the system to the
     * JSON Companyprofiles file to store data on each CompanyProfile.
     */
    public static void saveCompanyProfiles() {
        SearchableDatabase companyJobDatabase = SearchableDatabase.getInstance();
        ArrayList<CompanyProfile> companyProfileList = companyJobDatabase.getCompanyProfiles();
        JSONArray jsonCompanies = new JSONArray();

        for(int i = 0; i < companyProfileList.size(); i++) {
            jsonCompanies.add(getCompanyProfileJSON(companyProfileList.get(i)));
        }

        try {
            FileWriter file = new FileWriter(COMPANY_PROFILE_FILE_NAME);
            file.write(jsonCompanies.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts a given CompanyProfile into a JSONObject.
     * 
     * @param company The CompanyProfile to convert into a JSONObject
     * @return The JSONObject representation of the CompanyProfile
     */
    public static JSONObject getCompanyProfileJSON(CompanyProfile company) {
        JSONObject companyDetails = new JSONObject();
        companyDetails.put(COMPANY_ID, company.getUUID().toString());
        companyDetails.put(COMPANY_NAME, company.getCompanyName());
        companyDetails.put(COMPANY_HQ_ADDRESS, company.getAddress());
        companyDetails.put(COMPANY_DESCRIPTION, company.getDescription());
        companyDetails.put(COMPANY_LISTINGS_IDS, listingsToJsonIDArray(company.getListings()).toJSONString());
        companyDetails.put(COMPANY_REVIEWS, company.getReviews());
        return companyDetails;
    }

    /**
     * Private helper method used to convert an ArrayList of JobListings into a JSONArray of the listings' IDs
     * @param listings The arrayList of JobListings to convert
     * @return A JSONArray containing the IDs of the JobListings
     */
    private static JSONArray listingsToJsonIDArray(ArrayList<JobListing> listings) {
        JSONArray jsonListings = new JSONArray();
        for(JobListing listing : listings) {
            jsonListings.add(listing.getUUID());
        }
        return jsonListings;
    } 

    /**
     * Private helper method used to convert an ArrayList of objects into a JSONArray
     * @param arrayList The ArrayList of objects to convert
     * @return A JSONArray of the objects
     */
    private static JSONArray arrayListToJsonArray(ArrayList<? extends Object> arrayList) {
        JSONArray jsonArray = new JSONArray();
        for(Object object : arrayList) {
            jsonArray.add(object);
        }
        return jsonArray;
    }
}