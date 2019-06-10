import java.util.ArrayList;

/**
 * This class is used to solve The Student-Project Allocation Problem.
 */
public class Problem {
    private ArrayList<Student> listOfStudents = new ArrayList<Student>(); //used to store the Student objects
    private int numberOfStudents = 0;
    private ArrayList<Project> listOfProjects = new ArrayList<Project>(); // used to store the Project objects

    public Problem() { //empty constructor as no fields needs to be initialized
    }

    /**
     * This method will attempt to solve The Student-Project Allocation Problem using a simple algorithm
     *
     * @return returns a string that either says the problem cannot be solved or lists an answer to it
     */
    public String problemSolving() {
        String returnString = new String(); //string to return
        int i, j, ok;
        ArrayList<Project> auxProjectList = new ArrayList<Project>(listOfProjects); //creates a copy of the listOfProjects array
        ArrayList<Student> auxStudentList = new ArrayList<Student>(listOfStudents); //creates a copy of the listOfStudents array
        Student auxStudent; //auxiliary Student object used for solving
        Project auxProject; //auxiliary Project object used for solving
        int projectNumber = auxProjectList.size(); //size of project list
        int auxProjectNumber = projectNumber; //auxiliary of project list
        int stage = 1; //the stage is used to prioritise students with the lower amount of preferences first
        while (auxProjectNumber > 0) { //this while will stop when all the projects are handed out to the students or the problem is deemed unsolvable
            for (i = 0; i < projectNumber; i++) //since the problem says that number of students = number of projects, we will be using projectNumber to go through each student
            {
                auxStudent = auxStudentList.get(i); //the auxiliary takes the value of element i from the array
                if (stage == auxStudent.getNumberOfPreferences()) { //if the student has a number of preferences equal to the stage it goes through the block of code
                    ok=0; //this checks if the student received a project so that it does not return "Problem cannot be solved" wrongly
                    for (j = 0; j < stage && ok!=1; j++) { //goes through all the preferences of the student or until it finds a suitable project
                        auxProject = auxStudent.getPreferences().get(j); //the auxiliary takes the value of element j from the array
                        if (listOfProjects.contains(auxProject)) //if the project they want hasn't been taken, it will be given to them
                        {
                            returnString += "Student " + auxStudent + " receives project " + auxProject + "\n"; //adds information to return string
                            auxProjectNumber--; //decrements the number of projects using the auxiliary
                            listOfProjects.remove(auxProject); //removes from array the project which has been given
                            ok=1; //increments 'ok' so that the for stops
                        }
                        else if(j==stage-1) return "Problem cannot be solved"; //if the project they want has been given already, then it means the problem cannot be solved
                    }
                }
            }
            stage++; //advances stage
        }
        return returnString;
    }

    /**
     * This method returns an array of all the projects (distinct) specified in the students preferences
     *
     * @return the array mentioned above
     */
    public ArrayList<Project> getProjects() {
        return listOfProjects;
    }

    /**
     * This method populates the listOfProjects field without duplicates.
     */
    private void projectList() {
        for (Student student : this.listOfStudents)
            for (Project project : student.getPreferences())
                if (!listOfProjects.contains(project))
                    listOfProjects.add(project);
    }

    /**
     * The method populates the students field
     *
     * @param students Receives a number of Student objects.
     */
    public void setStudents(Student... students) {
        for (Student student : students) {
            this.listOfStudents.add(student);
            this.numberOfStudents++;
        }
        projectList();
    }

    /**
     * This method overrides the toString method from the Object class
     *
     * @return a string with the information stored in the ArrayList students and ArrayList listOfProjects
     */
    @Override
    public String toString() {
        String returnString = new String();
        returnString += "Problem has " + numberOfStudents + " students. Student information: \n";
        for (Student student : listOfStudents)
            returnString += student + "\n";
        returnString += "Project list: \n";
        for (Project project : listOfProjects)
            returnString += project + "\n";
        return returnString;
    }
}
