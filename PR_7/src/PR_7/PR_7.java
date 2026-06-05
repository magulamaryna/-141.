package PR_7;

import java.util.Arrays;

public class PR_7 {

    public static void main(String[] args) {

        // Масив A (довільні числа)
        float[] A = {2.5f, -1.2f, 3.8f, 0.5f, 7.1f, -4.3f, 6.6f, 1.0f, -2.7f, 4.2f};

        float[] B = new float[10];
        float[] C = new float[10];

        // Заповнення масиву B
        fillB(B);

        // Заповнення масиву C
        fillC(A, B, C);

        // Вивід масивів
        System.out.println("Масив A:");
        printArray(A);

        System.out.println("Масив B:");
        printArray(B);

        System.out.println("Масив C:");
        printArray(C);

        // Розмах
        System.out.println("Розмах A: " + range(A));
        System.out.println("Розмах B: " + range(B));
        System.out.println("Розмах C: " + range(C));

        // Сортування
        sortArray(A);
        sortArray(B);
        sortArray(C);

        System.out.println("Відсортований A:");
        printArray(A);

        System.out.println("Відсортований B:");
        printArray(B);

        System.out.println("Відсортований C:");
        printArray(C);
    }

    //  Метод заповнення B
    public static void fillB(float[] B) {
        for (int k = 0; k < B.length; k++) {
            B[k] = (float)(Math.sin(k * k) + 2 * Math.sin(k));
        }
    }

    //  Метод створення C
    public static void fillC(float[] A, float[] B, float[] C) {
        for (int i = 0; i < A.length; i++) {
            C[i] = Math.max(A[i], B[i]);
        }
    }

    //  Метод пошуку розмаху
    public static float range(float[] arr) {
        float min = arr[0];
        float max = arr[0];

        for (float num : arr) {
            if (num < min) min = num;
            if (num > max) max = num;
        }

        return max - min;
    }

    //  Метод сортування
    public static void sortArray(float[] arr) {
        Arrays.sort(arr);
    }

    //  Метод виводу
    public static void printArray(float[] arr) {
        for (float num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

