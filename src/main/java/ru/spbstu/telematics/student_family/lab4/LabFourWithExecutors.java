package ru.spbstu.telematics.student_family.lab4;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class LabFourWithExecutors {

    static class IntegralSolver {
        double integralResult = 0;
        int intervalNumber;
        double step;
        int a, b;
        double currentInterval;

        static class FunctionCalculator {
            double calculate(double x) {
                return Math.pow(x, 15) / Math.pow((Math.pow(x, 3) + 16), 6);
            }
        }

        IntegralSolver(){
            intervalNumber = 100000; //число интервалов чётно
            a = 0;
            b = 700;
            step = (double) (b - a) / (intervalNumber);
            currentInterval = a;
        }

        double calculateIntegral() throws ExecutionException, InterruptedException {
            ArrayList<Double> results = new ArrayList<>(intervalNumber + 1);
            ExecutorService threadPool = Executors.newFixedThreadPool(10);
            FunctionCalculator calculator = new FunctionCalculator();

            Date begin = new Date();
            List<Future<Double>> futures = new ArrayList<>(intervalNumber + 1);
            for (int i = 0; i < intervalNumber + 1; i++) {
                final double tmp = currentInterval;
                futures.add(
                        threadPool.submit(() -> calculator.calculate(tmp))
                        );
                currentInterval += step;
            }
            for (Future<Double> future : futures) {
                results.add(future.get());
            }

            //Способ с асинхронной записью
            /*List<CompletableFuture<Double>> futures = new ArrayList<>(intervalNumber + 1);
            for (int i = 0; i < intervalNumber + 1; i++) {
                final int j=i;
                final double tmp = currentInterval;
                futures.add(
                        CompletableFuture.supplyAsync(
                                () -> calculator.calculate(tmp),
                                threadPool
                        ).thenAccept(
                                result -> {if (j == 0 || j == intervalNumber)
                                    integralResult += result;
                                else if (j % 2 == 0)
                                     integralResult += 2 * result;
                                else integralResult += 4 * result;})
                );
                currentInterval += step;
            }
            CompletableFuture<Void> allFutures=CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));*/


            threadPool.shutdown();
            Date end = new Date();

            for (int i = 0; i < intervalNumber + 1; i++) {
                if (i == 0 || i == intervalNumber)
                    integralResult += results.get(i);
                else if (i % 2 == 0)
                    integralResult += 2 * results.get(i);
                else integralResult += 4 * results.get(i);
            }
            integralResult = integralResult * step / 3;

            System.out.println("Интеграл равен " + integralResult);
            System.out.println("Расчёт окончен, время " + (end.getTime() - begin.getTime()) + " мс");
            return integralResult;
        }

        public static void main(String[] args) throws ExecutionException, InterruptedException {
            IntegralSolver solver=new IntegralSolver();
            solver.calculateIntegral();
        }
    }
}
