package ru.spbstu.telematics.student_family.lab3;

import org.junit.Assert;
import org.junit.Test;

public class LabThreeTest {

    @Test
    public void museumTest() throws InterruptedException {
        LabThree lab=new LabThree();
        lab.startSimulation();
        Assert.assertEquals(lab.getNumberOfLeft(), lab.getNumberOfVisitors());
    }
}