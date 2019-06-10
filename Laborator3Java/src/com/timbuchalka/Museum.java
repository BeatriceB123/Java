package com.timbuchalka;

public class Museum extends Node implements Classifiable, Payable, Visitable
{
    Museum(String name)
    {
        this.name = name;
    }

    @Override
    public double getRank () {
        return this.rank;
    }

    @Override
    public void setRank ( double rank ){
        if(rank < 0 )
            throw new Error("Invalid rank");
        else
            setRank (rank);
    }

    @Override
    public double getEntryFee () {
        return entryFee;
    }

    @Override
    public void setEntryFee ( double fee )
    {
        if(fee < 0 )
            throw new Error("Invalid entry fee");
        else
            this.entryFee = fee;
    }

    @Override
    public void setOpenTime ( HourAndMinute open ) {
        this.openingTime = open;
    }

    @Override
    public void setCloseTime ( HourAndMinute close ) {
        this.closingTime = close;
    }

    @Override
    public HourAndMinute getOpenTime (  ) {
        return this.openingTime;
    }

    @Override
    public HourAndMinute getCloseTime ( ) {
        return this.closingTime;
    }
}
