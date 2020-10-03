/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floormastery.Controller.ui;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author Mohamed
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        print(prompt);
        return Double.parseDouble(sc.nextLine());
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double num;
        do {
            print(prompt);
            num = Double.parseDouble(sc.nextLine());
        } while (num < min || num > max);

        return num;
    }

    @Override
    public float readFloat(String prompt) {
        print(prompt);
        return Float.parseFloat(sc.nextLine());
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float num;
        do {
            print(prompt);
            num = Float.parseFloat(sc.nextLine());
        } while (num < min || num > max);

        return num;
    }

    @Override
    public int readInt(String prompt) {
        print(prompt);
        return Integer.parseInt(sc.nextLine());
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int num;
        do {
            print(prompt);
            num = Integer.parseInt(sc.nextLine());
        } while (num < min || num > max);

        return num;
    }

    @Override
    public long readLong(String prompt) {

        print(prompt);
        return Long.parseLong(sc.nextLine());
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long num;
        do {
            print(prompt);
            num = Long.parseLong(sc.nextLine());
        } while (num < min || num > max);

        return num;
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        return sc.nextLine();
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        print(prompt);
        BigDecimal newBigDecimal = new BigDecimal(sc.nextLine());
        return newBigDecimal;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        BigDecimal value = new BigDecimal(sc.nextLine());

        do {
            print(prompt);
            value = new BigDecimal(sc.nextLine());
            if (value.compareTo(min) == -1 || value.compareTo(max) == 1) {
                print("Error value outside of range!!!");
            }

        } while (value.compareTo(min) == -1 || value.compareTo(max) == 1);
        return value;
    }

}
