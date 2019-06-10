import java.lang.reflect.Array;
import java.util.ArrayList;

public class VerifyAllocate
{
    ArrayList<ArrayList<Integer>> permutations = new ArrayList <> ();

    private void permute(ArrayList<Integer> permutation, int left, int right)
    {
        if (left == right)
        {
            permutations.add(permutation);
            System.out.println (permutation + "*");
           // System.out.println (permutations);
        }
        else
        {
            for (int i = left; i <= right; i++)
            {
                permutation = swap(permutation,left,i);
                permute(permutation, left+1, right);
                permutation = swap(permutation,left,i);
            }
        }
    }

    public ArrayList<Integer> swap( ArrayList <Integer> array, int i, int j)
    {
        Integer aux = array.get (i);
        array.set (i, array.get (j));
        array.set(j, aux);
        //System.out.println (array);
        return array;
    }

    public void print(ArrayList<Integer> array)
    {
        permute (array, 0, array.size()-1);

        for(int i=0; i<permutations.size(); i++)
            //for(int j=0; j<permutations.get(i).size(); j++)
            System.out.println (permutations.get(i)); //.get(j));
        //Permutation permutation = new Permutation();
        //permutation.permute(str, 0, n-1);
    }


}
