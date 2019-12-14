package ru.spbstu.telematics.student_family.lab1;

import java.util.Random;
import java.util.Scanner;

public class LabOne {
    public static StringBuilder generateWithLetters(int length){
        StringBuilder resultString = new StringBuilder();
        Random rand=new Random();
        char data;
        for (int i=0; i<length; i++) {
            data = (char)(rand.nextInt(26) + 97); //случайная генерация кода символа a-z
            resultString.append(data);
        }
        return resultString;
    }

    public static StringBuilder generateWithNumbers(int length){
        Random rand=new Random();
        StringBuilder resultString = new StringBuilder();
        for (int i=0; i<length; i++) {
            resultString.append(rand.nextInt(10)); //случайная генерация числа 0-9
        }
        return resultString;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length;
        do { //считывается длина строки
            System.out.println("Enter length of string (int and > 0):");
            while (!sc.hasNextInt()) {
                System.out.println("Error, try again");
                sc.next();
            }
            length = sc.nextInt();
        } while (length <= 0);

        System.out.println("1 - generate string with numbers");
        System.out.println("2 - generate string with letters");

        int choice;
        do { //считывается
            System.out.println("Choose (1 or 2)");
            while (!sc.hasNextInt()) {
                System.out.println("Error, try again");
                sc.next();
            }
            choice = sc.nextInt();
        } while (choice <= 0 || choice >= 3);

        if (choice == 1){ //если строка будет из цифр
            System.out.println(generateWithNumbers(length));
        }
        else{ //если строка будет из букв
            System.out.println(generateWithLetters(length));
        }
    }
}
