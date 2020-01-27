package ru.spbstu.telematics.student_family.lab3;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class LabThree {
    private static int numberOfVisitors;
    private static int numberOfLeft;
    private static boolean museumIsOpen = false;
    static private Object lockForDirector=new Object();
    static private Object lockForController=new Object();
    private static boolean entranceIsAllowed = false;

    static class Director implements Runnable {

        @Override
        public void run() {
            System.out.println("Директор открыл музей");
            synchronized (lockForDirector) {
                museumIsOpen = true;
                lockForDirector.notify();
            }
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Директор закрыл музей");
            synchronized (lockForDirector) {
                museumIsOpen = false;
                lockForDirector.notify();
            }
        }
    }

    static class Controller implements Runnable {

        @Override
        public void run() {
            synchronized (lockForDirector) {
                if (!museumIsOpen) {
                    try {
                        lockForDirector.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            entranceIsAllowed = true;
            System.out.println("Вход открыт");

            synchronized (lockForDirector) {
                if (museumIsOpen) {
                    try {
                        lockForDirector.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            entranceIsAllowed = false;
            System.out.println("Вход закрыт");
        }
    }

    static class Visitor implements Runnable {

        Semaphore semEast;
        Semaphore semWest;
        int id;

        Visitor(Semaphore semE, Semaphore semW, int id) {
            semEast = semE;
            semWest = semW;
            this.id = id;
        }

        @Override
        public void run() {
            Random rand = new Random();
            try {
                Thread.sleep(rand.nextInt(200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (entranceIsAllowed) {
                try {
                    semEast.acquire();
                    System.out.println(id + " зашёл в музей");
                    numberOfVisitors++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semEast.release();
                }

                try {
                    Thread.sleep(rand.nextInt(500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    semWest.acquire();
                    System.out.println(id + " вышел из музея");
                    numberOfLeft++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semWest.release();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Director director=new Director();
        Semaphore semE=new Semaphore(5);
        Semaphore semW=new Semaphore(5);
        Controller controller=new Controller();
        List<Thread> threads=new LinkedList<>();

        Thread dir = new Thread(director);
        Thread cont= new Thread(controller);
        dir.start();
        cont.start();

        for(int i=0; i<10; i++)
        {
            Visitor visitor = new Visitor(semE, semW, i);
            threads.add(new Thread(visitor));
        }

        for(Thread t: threads){
            t.start();
        }

        for(Thread t: threads){
            t.join();
        }

        System.out.println(numberOfVisitors + " постеителей зашло" );
        System.out.println(numberOfLeft + " посетителей вышло" );
    }

    void startSimulation() throws InterruptedException {
        Director director=new Director();
        Semaphore semE=new Semaphore(5);
        Semaphore semW=new Semaphore(5);
        Controller controller=new Controller();
        List<Thread> threads=new LinkedList<>();

        Thread dir = new Thread(director);
        Thread cont= new Thread(controller);
        dir.start();
        cont.start();

        for(int i=0; i<15; i++)
        {
            Visitor visitor = new Visitor(semE, semW, i);
            threads.add(new Thread(visitor));
        }

        for(Thread t: threads){
            t.start();
        }

        for(Thread t: threads){
            t.join();
        }
    }

    int getNumberOfVisitors() {
        return numberOfVisitors;
    }


    int getNumberOfLeft(){
        return numberOfLeft;
    }

}

