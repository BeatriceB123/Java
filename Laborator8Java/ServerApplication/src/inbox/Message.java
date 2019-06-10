package inbox;

import modelOfNetwork.Person;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private Person sender;
    private Person receiver;
    private String messageString;
    private String date;
    private boolean isRead;

    public Message ( Person sender, Person receiver, String messageString ) {
        this.sender = sender;
        this.receiver = receiver;
        this.messageString = messageString;
        setIsRead (false);
        DateFormat dateFormat = new SimpleDateFormat ("HH:mm:ss");
        date = dateFormat.format (new Date ());
    }

    void setIsRead ( boolean value ) {
        isRead = value;
    }

    boolean isRead () {
        return isRead;
    }

    Person getSender () {
        return sender;
    }

    @Override
    public String toString () {
        return "--------------------------------###4" +
               "Sender:  " + sender.getName () + "###4" +
               "Receiver:" + receiver.getName () + "###4" +
               "Message: " + messageString + "###4" +
               "HH:mm:ss:" + date +
               "###4--------------------------------###4";
    }
}
