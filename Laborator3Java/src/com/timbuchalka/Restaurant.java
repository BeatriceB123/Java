package com.timbuchalka;

public class Restaurant extends Node implements Classifiable
{
    Restaurant(String name)
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

}
