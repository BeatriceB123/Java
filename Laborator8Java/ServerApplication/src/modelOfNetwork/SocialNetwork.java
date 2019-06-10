package modelOfNetwork;

import java.util.ArrayList;
import java.util.List;

public class SocialNetwork {
    private static List <Person> persons;

    public SocialNetwork () {
        persons = new ArrayList <> ();
    }

    public static List <Person> getPersons () {
        return persons;
    }

    public static void addFriendship( String name1, String name2){
        if(!nameOfPersonInList (name1))
            return;
        if(!nameOfPersonInList (name2))
            return;
        if(name1.equals (name2))
            return;
        Person p1 = getPersonByName (name1);
        Person p2 = getPersonByName (name2);

        assert p1!=null && p2!=null;
        p1.addFriend(p2);
        p2.addFriend(p1); //optional?
    }

    public static Person getPersonByName(String name){
        for(Person person:persons){
            if(person.getName ().equals (name))
                return person;
        }
        return null;
    }

    public static void addPerson ( Person newPerson ) {
        if ( !nameOfPersonInList (newPerson.getName ()) )
            persons.add (newPerson);
        else
            System.out.println ("Person is in list!");
    }

    public static void loggingInByName ( String name ) {
        if ( nameOfPersonInList (name) ) {
            for (Person personI : persons)
                if ( theSameName (personI.getName (), name) ) {
                    personI.setLogged (true);
                }
        } else
            System.out.println ("S-a ajuns cu o persoana neinregistrata la logare");
    }

    public static void loggingOutByName ( String name ) {
        if ( !nameOfPersonInList (name) )
            System.out.println ("S-a ajuns cu o persoana neinregistrata la delogare");
        for (Person personI : persons)
            if ( theSameName (personI.getName (), name) ) {
                personI.setLogged (false);
            }
    }

    private static boolean theSameName ( String name1, String name2 ) {
        return (name1.equals (name2));
    }

    public static boolean nameOfPersonInList ( String name ) {
        for (Person personI : persons)
            if ( personI.getName ().equals (name) )
                return true;
        return false;
    }

    @Override
    public String toString () {
        StringBuilder result = new StringBuilder ("Persons in our social network:\n");
        for (Person personI : persons)
            result.append (personI).append ("\n");
        return  result +"\n";
    }
}
