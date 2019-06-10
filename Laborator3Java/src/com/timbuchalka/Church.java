package com.timbuchalka;

public class Church extends Node implements Visitable
{
    Church ( String name ) {
        this.name = name;
    }

    @Override
    public HourAndMinute getOpenTime () {
        return this.openingTime;
    }

    @Override
    public HourAndMinute getCloseTime ( ) {
        return this.closingTime;
    }

    @Override
    public void setOpenTime ( HourAndMinute open ) {
        this.openingTime = open;
    }

    @Override
    public void setCloseTime ( HourAndMinute close ) {
        this.closingTime = close;
    }
}
