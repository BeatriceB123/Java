package Tools;

import java.util.ArrayList;
import java.util.List;

public class Tools {

    public static boolean isNumberOfLetter(char c)
    {
        if( c >= 'a' && c <= 'z')
            return true;
        if( c >= 'A' && c <= 'Z')
            return true;
        if( c >= '0' && c<='9')
            return true;
        return false;
    }
    public static boolean validateName(String name)
    {
        for(int i=0; i<name.length (); i++)
            if(!isNumberOfLetter (name.charAt (i)))
                return false;
        return true;
    }

    public static boolean validateDescription(String description)
    {
        for(int i=0; i<description.length (); i++)
            if(!isNumberOfLetter (description.charAt (i)) && description.charAt (i)!=' ')
                return false;
        return true;
    }

    public static boolean validatePath(String path, String endsWith)
    {
        if(!path.endsWith (endsWith))
            return false;

        for(int i=0; i<path.length (); i++)
            if(!isNumberOfLetter (path.charAt (i)) && path.charAt (i)!= '/' &&
               path.charAt (i)!= '\\'&& path.charAt (i)!= ' ' && path.charAt (i)!= '.'&&
               path.charAt (i)!=':' && path.charAt (i)!='-') {
                System.out.println (path.charAt (i));
                return false;
            }

        return true;
    }

    public static List <String> getArguments( String argumentString)
    {
        String[] aux = argumentString.split("\"");
        //now we have in aux strings with all the arguments and some trash (white spaces or null strings)

        List<String> result = new ArrayList <> ();
        //here we will put good things;

        for(int i=0; i<aux.length; i++)
        {
            if(!aux[i].equals (" ") && !aux[i].equals ("\n") && !aux[i].equals (""))
            {
                //we keep only consistent things( ~arguments)
                result.add(aux[i]);
            }
        }
        return result;
    }

    /**
     *
     * @param name - name of a graph/smthelse
     * @return -1 if it's not used(or in list), x din [0,catalog.graphList.size ()-1] is used
     */
    public static int getPosition ( String name, List<String> stringOfNames ) {
        for (int i = 0; i <stringOfNames.size (); i++) {
            if ( stringOfNames.get (i).equals (name) ) {
                return i;
            }
        }
        return -1;
    }
}
