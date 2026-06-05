package PR_5;

import java.util.Scanner;

public class PR_5_b {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть ціле число A: ");
        int A = scanner.nextInt();

        System.out.print("Введіть ціле число B (B > A): ");
        int B = scanner.nextInt();

        int sum = 0;

        int i = 2 * A;

        do {
            if (i % 2 == 0) {
                sum += i;
            }
            i++;
        } while (i <= 3 * B);

        System.out.println("Сума парних чисел від " + (2 * A) + " до " + (3 * B) + ": " + sum);

        scanner.close();
    }
}
