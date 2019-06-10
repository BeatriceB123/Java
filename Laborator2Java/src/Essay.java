import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This class is used for projects of type essay.
 */
public class Essay extends Project {
    private String topic;
    private static ArrayList<String> essayList = new ArrayList<String>(); //used to make sure we dont have the same essay twice

    public Essay(Topic topic, String name, LocalDate deadline) {
        if(!essayList.contains(name))
            essayList.add(name);//if the essay name is unique from the others, add it to the static field essayNames
        else throw new Error("This essay was already listed.");
        this.topic = topic.toString();
        this.setName(name);
        this.setDeadline(deadline);
    }

    public enum Topic {
        ALGORITHMS, DATA_STRUCTURES, WEB, DATABASES;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * This method overrides the toString method from the Object class
     * @return a string with the information it has stored
     */
    @Override
    public String toString() {
        return "Essay{" +
               "name = '" + this.getName() + '\'' +
               " topic = '" + topic + '\'' +
               " deadline = '" + this.getDeadline() + '\'' +
               '}';
    }
}
