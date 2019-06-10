import java.time.LocalDate;

/**
 * This is the superclass for Application and Essay.
 */
public abstract class Project {
    private String name;
    private LocalDate deadline;

    public String getName() {
        return name;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

//    /**
//     * This method overrides the equals method from the Object class
//     * @param obj receives an object with which the comparison is made
//     * @return true if they are identical/have the same name and false if they don't
//     */
//    @Override
//    public boolean equals(Object obj){
//        if(obj==this) return true; // if they are identical returns true
//        if(!(obj instanceof Project)) return false; // if the Object obj is not an instance of Project returns false
//        return this.name.equals(((Project) obj).name); // compares this.name with obj.name and returns true if they have the same name and false otherwise
//    }

    /**
     * This method overrides the toString method from the Object class
     * Is never used as the toString() method in Application or Essay are used instead
     * @return a string with the information it has stored
     */
    @Override
    public String toString() {
        return "Project{" +
               "name = '" + name + '\'' +
               ", deadline = " + deadline +
               '}';
    }
}