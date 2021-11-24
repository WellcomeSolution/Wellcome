package com.example.wellcome.utils;


public class Matcheur {
    public boolean Match(Double latUse, Double LngUse, Double lat, Double Lng,Double filter )
    {
        CalculDistance d = new CalculDistance();
        if (d.distance(latUse,LngUse,lat,Lng)<=filter)return true;
        return false;
    }
}
