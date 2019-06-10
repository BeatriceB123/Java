import java.util.ArrayList;

/**
 * This class is used for creating objects that handle student information.
 */
public class Student {
    private String name;
    private int yearOfStudy;

    private ArrayList<Project> preferences = new ArrayList<Project>(); //used to store student project preferences
    private int numberOfPreferences = 0;

    private ArrayList<String> projectNames = new ArrayList<String>(); //used to store the name of the project preferences
    private static ArrayList<String> studentList = new ArrayList<String>(); //used to make sure we dont have the same student twice

    public Student(String name, int yearOfStudy) {
        if(!studentList.contains(name))
            studentList.add(name); //if the student name is unique from the others, add it to the static field studentList
        else throw new Error("This student was already listed.");
        this.name = name;
        this.yearOfStudy = yearOfStudy;
    }

    public ArrayList<String> getProjectNames() {
        return projectNames;
    }

    public ArrayList<Project> getPreferences() {
        return preferences;
    }

    public int getNumberOfPreferences() {
        return numberOfPreferences;
    }

    public String getName() {
        return name;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    /**
     * This method overrides the equals method from the Object class
     * @param obj receives an object with which the comparison is made
     * @return true if they are identical/have the same name and false if they don't
     */
    @Override
    public boolean equals(Object obj){
        if(obj==this) return true; // if they are identical returns true
        if(!(obj instanceof Student)) return false; // if the Object obj is not an instance of Student returns false
        return this.name.equals(((Student) obj).name); // compares this.name with obj.name and returns true if they have the same name and false otherwise
    }

    /**
     * This method overrides the hashCode method from the Object class
     * @return an integer value that represents the hashCode value of the field "name"
     */
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    /**
     * This method overrides the toString method from the Object class
     * @return a string with the information it has stored
     */
    @Override
    public String toString() {
        return "Student{" +
               "name = '" + name + '\'' +
               ", yearOfStudy = " + yearOfStudy +
               ", project preferences = " + projectNames +
               '}';
    }

    /**
     * The method populates the preferences array field and increments the field numberOfPreferences
     * @param preferences Receives a number of String preferences.
     */
    public void setPreferences(Project... preferences) {
        //inseamna ca poate primi ca parametru unul sau mai multe obiecte de tip Project
        for (Project preference : preferences) {
            this.preferences.add(preference); //adds the preference to preferences field
            this.numberOfPreferences++; // increments number of preferences
            this.projectNames.add(preference.getName()); // adds preference name to projectNames field
        }
    }
}
