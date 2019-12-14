package ru.spbstu.telematics.student_family.lab2;

import java.util.Iterator;
import java.util.LinkedList;

public class MyLinkedList<T> implements Iterable<T> {
    private Node<T> Head;
    private Node<T> Tail;
    private int size;

    public MyLinkedList() {
        Head=null;
        Tail=null;
        size=0;
    }

    public int getSize() {
        return size;
    }

    public T getFirst() {
        if(Head!=null)
            return Head.data;
        else return null;
    }

    public T getLast(){
        if(Tail!=null)
            return Tail.data;
        else return null;
    }

    public void addFirst(T data){
        Node<T> newNode = new Node<>(data);
        if (Head==null)
            Tail = newNode;
        else {
            Head.previous=newNode;
            newNode.next=Head;
        }
        Head=newNode;
        size++;
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (Head==null)
            Head = newNode;
        else {
            Tail.next=newNode;
            newNode.previous=Tail;
        }
        Tail=newNode;
        size++;
    }

    public T removeFirst(){
        Node<T> tmp=Head;
        if(size>1) {
            Head = Head.next;
            Head.previous=null;
            size--;
        }
        else if(size==1){
            Head=null;
            Tail=null;
            size--;
        }
        if(tmp!=null)
            return tmp.data;
        else return null;
    }

    public T removeLast(){
        Node<T> tmp=Tail;
        if(size>1) {
            Tail = Tail.previous;
            Tail.next = null;
            size--;
        }
        else if(size==1) {
            Head=null;
            Tail=null;
            size--;
        }
        if(tmp!=null)
            return tmp.data;
        else return null;
    }

    public T remove(int index) throws IndexOutOfBoundsException{
        if(index >= size || index < 0) throw new IndexOutOfBoundsException("index " + index +", Size " + size);
        else {
            if (index == 0) return removeFirst();
            if (index == size - 1) return removeLast();
            int count=0;
            Node<T> tmp=Head;
            while(tmp!=null){
                if(count==index){
                    tmp.next.previous=tmp.previous;
                    tmp.previous.next=tmp.next;
                    tmp.previous=null;
                    tmp.next=null;
                    size--;
                    return tmp.data;
                }
                count++;
                tmp=tmp.next;
            }
        }
        return null;
    }

    public void add(int index, T item )throws IndexOutOfBoundsException{
        if(index > size || index < 0) throw new IndexOutOfBoundsException("index " + index +", Size " + size);
        else {
            if (index == 0) {
                addFirst(item);
                return;
            }
            if (index == size - 1) {
                addLast(item);
                return;
            }
            int count=0;
            Node<T> tmp=Head;
            while(tmp!=null){
                if(count == index - 1){
                    Node<T> newNode= new Node<>(item);
                    newNode.next=tmp.next;
                    tmp.next.previous=newNode;
                    tmp.next = newNode;
                    newNode.previous=tmp;
                    size++;
                    return;
                }
                count++;
                tmp=tmp.next;
            }
        }
    }

    public boolean contains(T item)
    {
        Node<T> tmp=Head;
        while(tmp!=null) {
            if (tmp.data.equals(item))
                return true;
            tmp=tmp.next;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj){
        if(this==obj) return true;
        if(obj==null) return false;
        LinkedList<T> list = (LinkedList<T>) obj;
        if(list.size() != size) return false;
        Node<T> tmp=Head;
        while (tmp!=null){
            if(tmp.data!=list.pollFirst()) return false;
            tmp=tmp.next;
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator<T>(Head);
    }

    class MyLinkedListIterator<T> implements Iterator<T> {

        Node<T> nextNode;

        MyLinkedListIterator(Node<T> item){
            nextNode=item;
        }

        @Override
        public boolean hasNext() {
            return nextNode!=null;
        }

        @Override
        public T next() {
            Node<T> curr=nextNode;
            if(nextNode!=null)
                nextNode=nextNode.next;
            return curr.data;
        }
    }

    class Node<T> {
        private Node<T> previous;
        private Node<T> next;
        private T data;

        Node(T item){
            data=item;
            previous=null;
            next=null;
        }
    }
}


