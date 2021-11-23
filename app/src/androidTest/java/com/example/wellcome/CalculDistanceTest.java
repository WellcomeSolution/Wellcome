package com.example.wellcome;

import static org.junit.Assert.assertEquals;

import com.example.wellcome.utils.CalculDistance;
import com.google.android.gms.nearby.messages.Distance;

import org.junit.Assert;
import org.junit.Test;

public class CalculDistanceTest {
    @Test
    public void CalculeDistanceTEST()
    {
        CalculDistance c =new CalculDistance();
        assertEquals(c.Distance(0.0,0.0,0.0,0.0),0);
    }
}
