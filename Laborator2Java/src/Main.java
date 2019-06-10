import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        Student s1 = new Student("S1", 2);
        Student s2 = new Student("S2", 1);
        Student s3 = new Student("S3", 3);
        Student s4 = new Student("S4", 4);
        Student s5 = new Student("S5", 2);
        Application a1 = new Application(Application.Language.JAVA, "A1", LocalDate.parse("2019-06-01"));
        Essay e1 = new Essay(Essay.Topic.ALGORITHMS, "E1", LocalDate.of(2019, Month.JUNE, 1));
        Application a2 = new Application(Application.Language.C, "A2", LocalDate.parse("2019-06-03"));
        Essay e2 = new Essay(Essay.Topic.DATABASES, "E2", LocalDate.of(2019, Month.JUNE, 3));
        Application a3 = new Application(Application.Language.JAVASCRIPT, "A3", LocalDate.parse("2019-06-02"));
        //Essay e3 = new Essay(Essay.Topic.DATABASES, "E2", LocalDate.of(2018, Month.JUNE, 3)); // Throws error: essay already listed
        //Application a3 = new Application(Application.Language.C, "A2", LocalDate.parse("2019-06-05")); // Throws error: application already listed
        //Student s5 = new Student("S4", 1); // Throws error: student already listed
        s1.setPreferences(a1, a2, a3);
        s2.setPreferences(a1, e1);
        s3.setPreferences(a2, a3, e1);
        s4.setPreferences(a3, e2);
        s5.setPreferences(a3, e2, e1, a2, a3);
        Problem problem = new Problem();
        problem.setStudents(s1, s2, s3, s4, s5);
        System.out.println(problem);


        System.out.println(problem.getProjects());
        System.out.println(problem.problemSolving());

        System.out.println ();

        VerifyAllocate v = new VerifyAllocate ();
        ArrayList<Integer> a = new ArrayList <> ();
        for(int i=0; i<3; i++)
            a.add(i);
        v.print (a);
        System.out.println ();

        MaximumMatching m = new MaximumMatching ();
        m.main(s1, s2, s3, s4);


        //Node[] locations =

    }
}
