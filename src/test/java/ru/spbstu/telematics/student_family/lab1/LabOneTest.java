package ru.spbstu.telematics.student_family.lab1;

import org.junit.Assert;
import org.junit.Test;

import static ru.spbstu.telematics.student_family.lab1.LabOne.*;

public class LabOneTest {

    @Test
    public void generateWithLettersTest() {
        int length=10;
        Assert.assertEquals(generateWithLetters(length).length(), length);
    }

    @Test
    public void generateWithNumbersTest() {
        int length=10;
        Assert.assertEquals(generateWithNumbers(length).length(), length);
    }
}