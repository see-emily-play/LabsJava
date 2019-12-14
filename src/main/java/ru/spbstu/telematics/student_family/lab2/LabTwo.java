package ru.spbstu.telematics.student_family.lab2;

import java.util.LinkedList;

public class LabTwo {
    public static void main(String[] args) {

        MyLinkedList<String> mylist = new MyLinkedList<String>();
        LinkedList<String> list=new LinkedList<>();

        //попытка удаления из пустого списка
        System.out.println("Linked List: removed " + list.pollLast());
        System.out.println("My Linked List: removed " +mylist.removeLast()+ "\n");

        //добавление новых элементов
        list.addFirst("Hello world");
        mylist.addFirst("Hello world");
        System.out.println("Linked List added first: " + list.getFirst());
        System.out.println("My Linked List added first: " + mylist.getFirst()+"\n");

        list.addLast("123456");
        mylist.addLast("123456");
        System.out.println("Linked List added last: " + list.getLast());
        System.out.println("My Linked List added last: " + mylist.getLast()+"\n");

        list.addLast("Bye");
        mylist.addLast("Bye");

        list.add(1, "new string");
        mylist.add(1, "new string");

        list.addFirst("Java");
        mylist.addFirst("Java");
        System.out.println("Test iterators");
        for(String str: list) {
            System.out.println("Linked List: " + str);
        }

        for(String str: mylist) {
            System.out.println("My Linked List: " + str);
        }
        System.out.println("\n");

        //проверка на наличие строки
        String hw = "Bye";
        System.out.println("Linked List contains " + hw+ ": " +list.contains(hw));
        System.out.println("My Linked List contains " + hw+ ": " +mylist.contains(hw)+"\n");

        //удаление первого элемента
        System.out.println("Linked List: removed " + list.pollFirst());
        System.out.println("My Linked List: removed " +mylist.removeFirst()+"\n");

        //удаление последнего элемента
        System.out.println("Linked List: removed " + list.pollLast());
        System.out.println("My Linked List: removed " +mylist.removeLast()+"\n");

        for(String str: list) {
            System.out.println("Linked List: " + str);
        }

        for(String str: mylist){
            System.out.println("My Linked List: " + str);
        }
        System.out.println("\n");

        try{
            list.remove(10);
        }
        catch (IndexOutOfBoundsException ex)
        {
            System.out.println("LinkedList out of range: " + ex.getMessage());
        }

        try{
            mylist.remove(10);
        }
        catch (IndexOutOfBoundsException ex)
        {
            System.out.println("ru.spbstu.parprog.lab2.MyLinkedList out of range: " + ex.getMessage());
        }
        System.out.println("\n");

        try{
            list.add(10, "err");
        }
        catch (IndexOutOfBoundsException ex)
        {
            System.out.println("LinkedList out of range: " + ex.getMessage());
        }

        try{
            mylist.add(10, "err");
        }
        catch (IndexOutOfBoundsException ex)
        {
            System.out.println("ru.spbstu.parprog.lab2.MyLinkedList out of range: " + ex.getMessage());
        }
        System.out.println("\n");

        list.add(0, "string");
        mylist.add(0, "string");

        for(String str: list) {
            System.out.println("Linked List: " + str);
        }

        for(String str: mylist){
            System.out.println("My Linked List: " + str);
        }
        System.out.println("\n");

        //удаление строки с индексом 1
        System.out.println("Linked List: removed " + list.remove(0));
        System.out.println("My Linked List: removed " +mylist.remove(0)+ "\n");

        for(String str: list) {
            System.out.println("Linked List: " + str);
        }

        for(String str: mylist){
            System.out.println("My Linked List: " + str);
        }
        System.out.println("\n");
    }
}
