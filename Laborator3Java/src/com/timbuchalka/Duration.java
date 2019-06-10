package com.timbuchalka;

public class Duration
{
    private int durationInMinutes;
    private double durationInHours;

    private int minutes;
    private int hours;

    /**
     * This constructor will modify the fields so that they contain how long
     * the given locations remain open for
     * @param openTime This parameter indicates the opening time
     * @param closeTime This parameter indicates the closing time
     */
    Duration( HourAndMinute openTime, HourAndMinute closeTime)
    {
        int hours = closeTime.getHour () - openTime.getHour ();
        if( openTime.getHour () > closeTime.getHour ())
            hours += 24;

        int minutes = hours * 60;
        minutes += closeTime.getMinute ();
        minutes -= openTime.getMinute ();

        setDurationInHours (minutes / 60.0);
        setDurationInMinutes (minutes);
        setHours ((int) durationInHours);
        setMinutes ((int) ((durationInHours - (double)this.hours) * 60 ));
    }

    public int getMinutes () {
        return minutes;
    }

    private void setMinutes ( int minutes ) {
        this.minutes = minutes;
    }

    public int getHours () {
        return hours;
    }

    private void setHours ( int hours ) {
        this.hours = hours;
    }

    public int getDurationInMinutes () {
        return durationInMinutes;
    }

    private void setDurationInMinutes ( int durationInMinutes ) {
        this.durationInMinutes = durationInMinutes;
    }

    public double getDurationInHours () {
        return durationInHours;
    }

    private void setDurationInHours ( double durationInHours ) {
        this.durationInHours = durationInHours;
    }
}
