package inbox;

import modelOfNetwork.Person;

import java.util.ArrayList;
import java.util.List;

import static modelOfNetwork.Person.theSamePerson;

public class Inbox {
    private List <Message> listOfMesages;

    public Inbox () {
        listOfMesages = new ArrayList <> ();
    }

    public void addMessage ( Message message ) {
        this.listOfMesages.add (message);
    }

    public String getMessagesWithSender ( Person person ) {
        StringBuilder result = new StringBuilder ("Messages from " + person.getName () + "are: \n");
        for (Message message : listOfMesages) {
            if ( theSamePerson (message.getSender (), person) )
                result.append (message);
        }
        return result.toString ();
    }

    public String getMessages ( boolean justNewMessages ) {
        StringBuilder result = new StringBuilder ("Messages:" + "###4");
        boolean thereAreMessages = false;
        for (Message message : listOfMesages) {
            if ( !(justNewMessages && message.isRead ()) ) {
                result.append (message).append ("###4");
                thereAreMessages = true;
            }
            message.setIsRead (true);
        }
        return (thereAreMessages?result.toString ():"No messages");
    }
}
