package org.inwork;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Program {
    private static final Random random = new Random();
    private static final int CHAR_BOUND_L = 65;
    private static final int CHAR_BOUND_H = 90;
    private static final String TO_SEARCH = "GeekBrains";


    public static void main(String[] args) throws IOException {
        System.out.println(generateSymbols(15));
        writeFileContents("s5sample.txt", 30);
        writeFileContents2("s5sample2.txt", 30, 5);

    }

    private static String generateSymbols(int amount) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < amount; i++) {
            stringBuilder.append((char) random.nextInt(CHAR_BOUND_L, CHAR_BOUND_H + 1));
        }
        return stringBuilder.toString();
    }

    private static void writeFileContents(String filename, int length) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
            fileOutputStream.write(generateSymbols(length).getBytes());

        }
    }
//        FileOutputStream fileOutputStream = new FileOutputStream(filename);
//        fileOutputStream.write(generateSymbols(length).getBytes());
//        fileOutputStream.flush();
//        fileOutputStream.close();

    private static void writeFileContents2(String filename, int length, int words) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
            for (int i = 0; i < words; i++) {

                if (random.nextInt(5) == 5 / 2) {
                    fileOutputStream.write(TO_SEARCH.getBytes());
                } else fileOutputStream.write(generateSymbols(length).getBytes());
            }
            fileOutputStream.write(' ');

        }
    }
}