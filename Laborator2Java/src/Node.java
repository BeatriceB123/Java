
public abstract class Node
{
    //name of location
    public String name;

    //if it's visitablle, and implements Visitable interface, il'll use this for open hour
    protected HourAndMinute openingTime;
    protected HourAndMinute closingTime;

    //if it's payable, and implements Payable interface, il'll use this
    protected double entryFee;

    //if it's classifiable, and implements Classifiable interface, il'll use this
    protected double rank;

    protected String getName()
    {
        return this.name;
    }

    Node() {}

    @Override
    public String toString () {
        return " '" + name + '\'';
    }
}
