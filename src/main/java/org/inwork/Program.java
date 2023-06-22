package org.inwork;

import java.io.*;
import java.util.Random;

public class Program {
    private static final Random random = new Random();
    private static final int CHAR_BOUND_L = 65;
    private static final int CHAR_BOUND_H = 90;
    private static final String TO_SEARCH = "GeekBrains";


    public static void main(String[] args) throws IOException {
        System.out.println(generateSymbols(15));
        writeFileContents("s5sample.txt", 30);
        System.out.println(searchStrInFile("s5sample.txt", TO_SEARCH));
        writeFileContents2("s5sample2.txt", 30, 5);
        System.out.println(searchStrInFile("s5sample2.txt", TO_SEARCH));
        concatenate("s5sample.txt", "s5sample2.txt","s5sampleOut.txt");
        System.out.println(searchStrInFile("s5sampleOut.txt", TO_SEARCH));

    }

    public static void outputFileSystem(File file, String indent, boolean isLast){
        System.out.println(indent);
        if (isLast) {
            System.out.print("└ ");
            indent += "  ";
        }
        else {
            System.out.print("├");
            indent += "│ ";
        }
        System.out.println(file.getName());
        File[] files = file.listFiles();


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
    private static void concatenate(String fileIn1, String fileIn2, String fileOut) throws IOException{
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileOut)){
            int c;
            try (FileInputStream fileInputStream = new FileInputStream(fileIn1)){
                while ((c = fileInputStream.read()) != -1){
                    fileOutputStream.write(c);
                }
            }
            try (FileInputStream fileInputStream = new FileInputStream(fileIn2)){
                while ((c = fileInputStream.read()) != -1){
                    fileOutputStream.write(c);
                }
            }
        }
    }
    private static boolean searchStrInFile(String filename, String search) throws IOException{
        try(FileInputStream fileInputStream = new FileInputStream(filename)){
            byte[] searchData = search.getBytes();
            int c;
            int i = 0;
            while ((c = fileInputStream.read()) != -1){
                if (c == searchData[i]) {
                    i++;
                }
                else {
                    i = 0;
                    if (c == searchData[i]) i++;
                }
                if ( i == searchData.length) return true;
            }
            return false;

        }
    }
}