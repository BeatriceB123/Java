package modelOfNetwork;

import inbox.Inbox;
import inbox.Message;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name; //unic!
    private boolean logged;
    private List <Person> friends;
    private Inbox inbox;
    private long lastCommand;
    private final Object lock = new Object();
    private Socket clientSocket;

    public Socket getClientSocket () {
        return clientSocket;
    }
    List <Person> getFriends () {
        return friends;
    }

    public Person ( String name, Socket clientSocket  ) {
        setName (name);
        setLogged (false);
        friends = new ArrayList <> ();
        inbox = new Inbox ();
        this.clientSocket = clientSocket;
    }

    public void commandSend(){
        synchronized (lock) {
            lastCommand = System.currentTimeMillis();
        }
    }

    public long getLastCommand(){
        synchronized (lock){
            return lastCommand;
        }
    }

    public void sendMessageToAllFriends(String messageText){
        for(Person friend:friends){
            Message message = new Message (this, friend, messageText);
            friend.addMessage (message);
        }
    }

    public static boolean theSamePerson ( Person person1, Person person2 ) {
        return (person1.getName ().equals (person2.getName ()));
    }

    void addFriend ( Person person ) {
        if(!friends.contains (person))
            friends.add (person);
    }

    private void addMessage ( Message message ){
        inbox.addMessage (message);
    }

    public String getMessages(boolean justNewMessages){
        return inbox.getMessages (justNewMessages);
    }

    public String getMessagesWith(Person person){
        return inbox.getMessagesWithSender(person);
    }

    public String getName () {
        return name;
    }

    private void setName ( String name ) {
        this.name = name;
    }

    public boolean isLogged () {
        return logged;
    }

    public void setLogged ( boolean logged ) {
        this.logged = logged;
    }

    @Override
    public String toString () {
        StringBuilder result = new StringBuilder ("(" + name + " " + (logged ? 1 : 0) + "), with friends: (");
        for(Person person:friends){
            result.append (person.getName ()).append (" ");
        }
        result.append (");");
        return result.toString ();
    }
}
