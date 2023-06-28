package org.inwork;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
        concatenate("s5sample.txt", "s5sample2.txt", "s5sampleOut.txt");
        System.out.println(searchStrInFile("s5sampleOut.txt", TO_SEARCH));

        outputFileSystem(new File("/Users/olegmonogarov/IdeaProjects/java_core_in_seminar_5"),
                "", true);

        String[] fileNames = new String[10];
        for (int i = 0; i < fileNames.length; i++) {
            fileNames[i] = "file_" + i + ".txt";
            writeFileContents2(fileNames[i], 100, 4);
            System.out.printf("Файл %s создан\n", fileNames[i]);
        }
        List<String> result = searchMatch(fileNames, TO_SEARCH);
        for (String s : result) {
            System.out.printf("Файл %s содержит искомое слово %s\n", s, TO_SEARCH);
        }
    }

    public static void outputFileSystem(File file, String indent, boolean isLast) {
        System.out.print(indent);
        if (isLast) {
            System.out.print("└╌ ");
            indent += " ";
        } else {
            System.out.print("├╌ ");
            indent += "│ ";
        }
        System.out.println(file.getName());

        File[] files = file.listFiles();
        if (files == null) return;

        int subDir = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) subDir++;
        }

        int subDirCounter = 0;
        int subFileCounter = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                outputFileSystem(files[i], indent, subDirCounter == subDir - 1);
                subDirCounter++;

            } else {
                outputFileSystem(files[i], indent, true);
//                subFileCounter++;
            }
        }
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

    private static void concatenate(String fileIn1, String fileIn2, String fileOut) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileOut)) {
            int c;
            try (FileInputStream fileInputStream = new FileInputStream(fileIn1)) {
                while ((c = fileInputStream.read()) != -1) {
                    fileOutputStream.write(c);
                }
            }
            try (FileInputStream fileInputStream = new FileInputStream(fileIn2)) {
                while ((c = fileInputStream.read()) != -1) {
                    fileOutputStream.write(c);
                }
            }
        }
    }

    private static boolean searchStrInFile(String filename, String search) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(filename)) {
            byte[] searchData = search.getBytes();
            int c;
            int i = 0;
            while ((c = fileInputStream.read()) != -1) {
                if (c == searchData[i]) {
                    i++;
                } else {
                    i = 0;
                    if (c == searchData[i]) i++;
                }
                if (i == searchData.length) return true;
            }
            return false;

        }
    }

    private static List<String> searchMatch(String[] files, String search) throws IOException {
        List<String> list = new ArrayList<>();
        File path = new File(new File(".").getCanonicalPath());
        File[] dir = path.listFiles();
        for (int i = 0; i < dir.length; i++) {
            if (dir[i].isDirectory()) {
                continue;
            }
            for (int j = 0; j < files.length; j++) {
                if (dir[i].getName().equals(files[j])) {
                    if (searchStrInFile(dir[i].getName(), search)) {
                        list.add(dir[i].getName());

                    }
                }

            }

        }
        return list;

    }
}