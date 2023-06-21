package org.inwork;

import java.util.Random;

public class Program {
    private static final Random random = new Random();
    private static final int CHAR_BOUND_L = 65;
    private static final int CHAR_BOUND_H = 90;

    public static void main(String[] args) {
        System.out.println(generateSymbols(15));

    }

    private static String generateSymbols(int amount){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < amount; i++) {
            stringBuilder.append((char) random.nextInt(CHAR_BOUND_L,CHAR_BOUND_H+1));
        }
        return stringBuilder.toString();
    }
}