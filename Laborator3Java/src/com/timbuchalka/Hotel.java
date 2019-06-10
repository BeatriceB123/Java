package com.timbuchalka;

public class Hotel extends Node implements Classifiable, Payable
{
    Hotel ( String name )
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
            this.rank = rank;
    }

    @Override
    public double getEntryFee () {
        return entryFee;
    }

    @Override
    public void setEntryFee ( double fee ){
        if(fee < 0 )
            throw new Error("Invalid entry fee");
        else
            this.entryFee = fee;
    }

}
