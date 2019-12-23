package ru.spbstu.telematics.student_family.lab4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class LabFour {
    private static int intervalNumber;
    private static double[] results;

    public static class Solver {
        private double integralResult;
        CyclicBarrier barrier;

        Solver(){
            intervalNumber=140; //число интервалов чётно
            int a = 0;
            int b = 100;
            integralResult=0;
            double step = (double) (b - a) / (intervalNumber);
            results = new double[intervalNumber + 1];
            for (int i = 0; i < intervalNumber + 1; i++)
                results[i] = 0;

            Runnable merger = new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < intervalNumber + 1; i++) {
                        if (i == 0 || i == intervalNumber)
                            integralResult += results[i];
                        else if (i % 2 == 0)
                            integralResult += 2 * results[i];
                        else integralResult += 4 * results[i];
                    }
                    integralResult = integralResult * step / 3;
                    System.out.println("Интеграл равен " + integralResult);
                }
            };
            barrier = new CyclicBarrier(intervalNumber + 1, merger);

            List<Thread> threads = new ArrayList<>(intervalNumber);
            Date begin = new Date();
            double currentInterval = a;
            for (int i = 0; i < intervalNumber + 1; i++) {
                Thread thread = new Thread(new IntegralCalculator(barrier, i, currentInterval));
                threads.add(thread);
                thread.start();
                currentInterval += step;
            }

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Date end = new Date();

            System.out.println("Расчёт окончен, время " + (end.getTime() - begin.getTime()) + " мс");
        }

        double getIntegralResult(){
            return integralResult;
        }

        class IntegralCalculator extends Thread {
            int intervalNumber;
            double interval;
            CyclicBarrier barrier;

            IntegralCalculator(CyclicBarrier barrier, int intervalNumber, double interval) {
                this.barrier = barrier;
                this.intervalNumber = intervalNumber;
                this.interval = interval;
            }

            double function(double x) {
                return Math.pow(x, 8)/Math.pow((Math.pow(x, 3)+16), 3);
            }

            public void run() {
                results[intervalNumber] = (function(interval));
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        Solver solver = new Solver();
    }
}
