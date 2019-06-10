package com.timbuchalka;

public class HourAndMinute {
    private int hour;
    private int minute;

    HourAndMinute(int hour, int minute) {
        setHour (hour);
        setMinute (minute);
    }

    public int getHour () {
        return hour;
    }

    public void setHour ( int hour ) {
        if(hour < 0 || hour > 23)
            throw new Error("Invalid hour");
        else
            this.hour = hour;
    }

    public int getMinute () {
        return minute;
    }

    public void setMinute ( int minute ) {
        if(minute < 0 || minute > 59 )
            throw new Error("Invalid minute");
        this.minute = minute;
    }
}
