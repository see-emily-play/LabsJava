package ru.spbstu.telematics.student_family.lab4;
import static ru.spbstu.telematics.student_family.lab4.LabFourWithExecutors.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class LabFourWithExecutorsTest {

    @Test
    public void calculationTest() throws ExecutionException, InterruptedException {
        IntegralSolver expected=new IntegralSolver();
        Assert.assertTrue(Math.abs(expected.calculateIntegral()-94.2987)<0.0001);

    }

}