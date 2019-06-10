import java.util.ArrayList;

public class MaximumMatching
{

    int M;//number of students
    int N;//number of projects

    // DFS -> true if a matching for student u is possible
    boolean bpm ( boolean bpGraph[][], int u, boolean seen[], int matchR[] ) {

        for (int v = 0; v < N; v++) //try every project
        {
            // If applicant u is interested
            // in job v and v is not visited
            if ( bpGraph[u][v] && !seen[v] ) {

                // Mark v as visited
                seen[v] = true;

                // If job 'v' is not assigned to
                // an applicant OR previously
                // assigned applicant for job v (which
                // is matchR[v]) has an alternate job available.
                // Since v is marked as visited in the
                // above line, matchR[v] in the following
                // recursive call will not get job 'v' again
                if ( matchR[v] < 0 || bpm (bpGraph, matchR[v],
                        seen, matchR) ) {
                    matchR[v] = u;
                    return true;
                }
            }
        }
        return false;
    }

    // Returns maximum number of matching from M to N
    int maxBPM ( boolean bpGraph[][] ) {
        // An array to keep track of the
        // applicants assigned to jobs.
        // The value of matchR[i] is the
        // applicant number assigned to job i,
        // the value -1 indicates nobody is assigned.
        int matchR[] = new int[N];

        // Initially all jobs are available
        for (int i = 0; i < N; ++i)
            matchR[i] = -1;

        // Count of jobs assigned to applicants
        int result = 0;
        for (int u = 0; u < M; u++) {
            // Mark all jobs as not seen
            // for next applicant.
            boolean seen[] = new boolean[N];
            for (int i = 0; i < N; ++i)
                seen[i] = false;

            // Find if the applicant 'u' can get a job
            if ( bpm (bpGraph, u, seen, matchR) )
                result++;
        }
        return result;
    }


    //this wiil receive the students
    //and will return the maximum matching number
    public void main ( Student... students ) throws java.lang.Exception
    {
        // Create a bipartite graph that describe the problem
        boolean bpGraph[][] = new boolean[100][100];
        //for every student-project pair we assign 1 if the project is acceptable
        //or 0 if it's not acceptable

        M = 0; //number of students
        N = 0;//number of projects
        ArrayList <String> projects = new ArrayList <> ();

        for (Student student : students)
        {
            M++;
            ArrayList <String> projectNames = new ArrayList <> (student.getProjectNames ());
            for (int i = 0; i < projectNames.size (); i++)
            {
                if ( !projects.contains (projectNames.get (i)) )
                {
                    N++;
                    projects.add (projectNames.get (i));
                }
                bpGraph[M - 1][N - 1] = true;
            }
        }

        int maxim = maxBPM (bpGraph);
        System.out.println ("Maximum number of matching is " + maxim);
        if(maxim == M)
            System.out.println ("It's possible to assign to all");
        else
            System.out.println ("Impossible");
    }
}
