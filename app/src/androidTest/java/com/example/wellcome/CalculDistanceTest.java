package com.example.wellcome;

import static org.junit.Assert.assertEquals;

import com.example.wellcome.utils.CalculDistance;

import org.junit.Test;

public class CalculDistanceTest {
    @Test
    public void calculeDistanceTest()
    {
        CalculDistance c =new CalculDistance();
        assertEquals(c.distance(0.0,0.0,0.0,0.0),0);
    }
}
