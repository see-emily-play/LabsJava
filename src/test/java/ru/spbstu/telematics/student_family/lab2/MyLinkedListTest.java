package ru.spbstu.telematics.student_family.lab2;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

public class MyLinkedListTest {

    @Test
    public void getSize() {
        MyLinkedList<String> expected=new MyLinkedList<>();
        expected.addFirst("Hello World!");
        expected.addLast("Bye!");

        LinkedList<String> actual=new LinkedList<>();
        actual.addFirst("Hello World!");
        actual.addLast("Bye!");
        Assert.assertEquals(expected.getSize(), actual.size());
    }

    @Test
    public void getFirst() {
        MyLinkedList<String> expected=new MyLinkedList<>();
        expected.addFirst("Hello World!");
        LinkedList<String> actual=new LinkedList<>();
        actual.addFirst("Hello World!");
        Assert.assertEquals(expected.getFirst(), actual.getFirst());
    }

    @Test
    public void getLast() {
        MyLinkedList<String> expected=new MyLinkedList<>();

        expected.addLast("Java");
        expected.addLast("Hello World!");

        LinkedList<String> actual=new LinkedList<>();

        actual.addLast("Java");
        actual.addLast("Hello World!");
        Assert.assertEquals(expected.getLast(), actual.getLast());
    }

    @Test
    public void addFirst() {
        MyLinkedList<String> expected=new MyLinkedList<>();
        expected.addFirst("string");

        LinkedList<String> actual=new LinkedList<>();
        actual.addFirst("string");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addLast() {
        MyLinkedList<String> expected=new MyLinkedList<>();
        expected.addLast("string");

        LinkedList<String> actual=new LinkedList<>();
        actual.addLast("string");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void removeFirst() {
        MyLinkedList<String> expected=new MyLinkedList<>();
        expected.addFirst("Hello World!");
        expected.addFirst("string");
        expected.removeFirst();

        LinkedList<String> actual=new LinkedList<>();
        actual.addFirst("Hello World!");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void removeLast() {
        MyLinkedList<String> expected=new MyLinkedList<>();
        expected.addFirst("Hello World!");
        expected.addFirst("string");
        expected.removeLast();

        LinkedList<String> actual=new LinkedList<>();
        actual.addFirst("string");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void remove() {
        MyLinkedList<String> expected=new MyLinkedList<>();
        expected.addFirst("string 2");
        expected.addFirst("Hello World!");
        expected.addFirst("string");
        expected.remove(1);

        LinkedList<String> actual=new LinkedList<>();
        actual.addFirst("string 2");
        actual.addFirst("string");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void add() {
        MyLinkedList<String> expected=new MyLinkedList<>();
        expected.add(0, "string");

        LinkedList<String> actual=new LinkedList<>();
        actual.add(0,"string");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void contains() {
        MyLinkedList<String> expected=new MyLinkedList<>();
        expected.addFirst("Hello World!");

        LinkedList<String> actual=new LinkedList<>();
        actual.addFirst("Hello World!");
        Assert.assertEquals(expected.contains("Hello World!"), actual.contains("Hello World!"));
    }

    @Test
    public void equals(){
        MyLinkedList<String> list1=new MyLinkedList<>();
        list1.addFirst("Hello World!");
        list1.addFirst("Bye");

        LinkedList<String> list2=new LinkedList<>();
        list2.addFirst("Hello World!");
        list2.addFirst("Bye");

        Assert.assertEquals(list1, list2);
    }
}