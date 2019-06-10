package com.timbuchalka;

public interface Visitable
{

    public void setOpenTime(HourAndMinute open);
    public void setCloseTime(HourAndMinute close);
    public HourAndMinute getOpenTime();
    public HourAndMinute getCloseTime();

    /**
     * A default initialization that sets a predefined starting and closing time
     */
    default public void defaultInitialization() {
        System.out.println ("New default method is added in interface and we set time with it");
        HourAndMinute h1 = new HourAndMinute (9, 30);
        HourAndMinute h2 = new HourAndMinute (20, 0);
        setOpenTime (h1);
        setCloseTime (h2);
    }

    default Duration getDefaultVisitingDuration()
    {
        HourAndMinute h1 = new HourAndMinute (9, 30);
        HourAndMinute h2 = new HourAndMinute (20, 0);
        return new Duration (h1, h2);
    }

    static Duration getVisitingDuration(HourAndMinute h1, HourAndMinute h2)
    {
        return new Duration (h1, h2);
    }

    /**
     * Prints two sets of hours and minutes.
     * @param h1 First set of hour-minute to be printed
     * @param h2 Second set of hour-minute to be printed
     */
    static void printTime(HourAndMinute h1, HourAndMinute h2)
    {
        System.out.println (h1.getHour () + ":" + h1.getMinute ());
        System.out.println (h2.getHour () + ":" + h2.getMinute ());
    }

    /**
     * Prints the duration
     * @param d This parameter represents the duration.
     */
    static void printDuration(Duration d)
    {
        System.out.println (d.getDurationInMinutes () + " total minutes: " +
                            d.getHours () + " hours and " + d.getMinutes () + " minutes ");
        System.out.println ();
    }

}
