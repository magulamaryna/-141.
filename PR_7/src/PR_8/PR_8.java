package PR_8;

import java.io.*;
import java.util.Scanner;

public class PR_8 {

    public static int[][] readMatrixFromFile(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException("Файл " + filename + " не знайдено. Шлях: " + file.getAbsolutePath());
        }

        int[][] matrix = new int[5][5];
        try (Scanner scanner = new Scanner(file)) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (scanner.hasNextInt()) {
                        matrix[i][j] = scanner.nextInt();
                    } else {
                        throw new IOException("Недостатньо елементів у файлі або неправильний формат даних.");
                    }
                }
            }
        }
        return matrix;
    }

    public static int[] countPositiveElements(int[][] matrix) {
        int[] positiveCounts = new int[5];
        for (int i = 0; i < 5; i++) {
            int count = 0;
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] > 0) {
                    count++;
                }
            }
            positiveCounts[i] = count;
        }
        return positiveCounts;
    }

    public static int[][] createMatrixB(int[][] matrixA, int[] positiveCounts) {
        int[][] matrixB = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                matrixB[i][j] = matrixA[i][j + 1];
            }
            matrixB[i][4] = positiveCounts[i];
        }
        return matrixB;
    }

    public static void writeMatrixToFile(int[][] matrix, String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    writer.print(matrix[i][j] + " ");
                }
                writer.println();
            }
        }
    }

    public static void main(String[] args) {
        String inputFilename = "C:\\Users\\Admin\\IntelliJ IDEA\\PR_7\\src\\PR_8\\matrixA.txt";
        String outputFilename = "C:\\Users\\Admin\\IntelliJ IDEA\\PR_7\\src\\PR_8\\matrixB.txt";

        try {
            int[][] matrixA = readMatrixFromFile(inputFilename);

            int[] positiveCounts = countPositiveElements(matrixA);

            int[][] matrixB = createMatrixB(matrixA, positiveCounts);

            writeMatrixToFile(matrixB, outputFilename);

            System.out.println("Матриця A:");
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    System.out.print(matrixA[i][j] + " ");
                }
                System.out.println();
            }

            System.out.println("\nКількість додатних елементів у кожному рядку:");
            for (int count : positiveCounts) {
                System.out.print(count + " ");
            }

            System.out.println("\n\nМатриця B:");
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    System.out.print(matrixB[i][j] + " ");
                }
                System.out.println();
            }

            System.out.println("\nМатриця B записана у файл " + outputFilename);

        } catch (FileNotFoundException e) {
            System.out.println("Помилка: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}

