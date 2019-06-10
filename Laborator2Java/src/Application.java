import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This class is used for projects of type application.
 */
public class Application extends Project {
    private String language;
    private static ArrayList<String> applicationList = new ArrayList<>(); //used to make sure we dont have the same application twice

    public enum Language {
        C, PYTHON, JAVA, JAVASCRIPT;
    }

    public Application(Language language, String name, LocalDate deadline) {
        if(!applicationList.contains(name))
            applicationList.add(name); //if the application name is unique from the others, add it to the static field applicationNames
        else throw new Error("This application was already listed.");
        this.language = language.toString();
        this.setName(name);
        this.setDeadline(deadline);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * This method overrides the toString method from the Object class
     * @return a string with the information it has stored
     */
    @Override
    public String toString() {
        return "Application{" +
               "name = '" + this.getName() + '\'' +
               " language = '" + language + '\'' +
               " deadline = '" + this.getDeadline() + '\'' +
               '}';
    }
}
