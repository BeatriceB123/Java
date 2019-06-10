package com.timbuchalka;

public abstract class Node
{
    //name of location
    String name;


    //if it's visitablle, and implements Visitable interface, il'll use this for open hour
    protected HourAndMinute openingTime = new HourAndMinute (23, 59);
    protected HourAndMinute closingTime = new HourAndMinute (23, 59);

    //if it's payable, and implements Payable interface, il'll use this
    public double entryFee = 0;

    //if it's classifiable, and implements Classifiable interface, il'll use this
    protected double rank = 0 ;

    Node() {}

    protected String getName()
    {
        return this.name;
    }

    @Override
    public String toString () {
        return " '" + name + '\'';
    }

    public double getEntryFee()
    {
        return this.entryFee;
    }
}
