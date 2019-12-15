package ru.spbstu.telematics.student_family.lab4;
import static ru.spbstu.telematics.student_family.lab4.LabFour.*;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LabFourTest {

    @Test
    public void calculationTest() {
        Solver expected=new Solver();
        Assert.assertTrue(Math.abs(expected.getIntegralResult()-1.839)<0.001);

    }
}