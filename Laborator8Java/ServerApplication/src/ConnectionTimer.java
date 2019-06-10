import modelOfNetwork.Person;

public class ConnectionTimer implements Runnable {
    private Person person;
    private static final long waitTime = 60_000; //60 secunde de caciula

    ConnectionTimer ( Person person ) {
        this.person = person;
    }

    @Override
    public void run() {
        while(System.currentTimeMillis() - person.getLastCommand () < waitTime)
        {
            System.out.println("verify if " + person.getName () + " sends commands");
            if (!person.isLogged ())
            {
                stopTimer ();
                return;
            }
            try
            {
                Thread.sleep(3_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stopTimer ();
    }

    private void stopTimer(){
        System.out.println("Stop timer for " + person.getName ());
        try {
            person.setLogged (false);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
