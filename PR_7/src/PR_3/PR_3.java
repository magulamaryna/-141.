package PR_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PR_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введіть перше число: ");
        float a = Float.parseFloat(reader.readLine());

        System.out.println("Введіть друге число: ");
        float b = Float.parseFloat(reader.readLine());

        System.out.println("Введіть третє число: ");
        float c = Float.parseFloat(reader.readLine());

        float min = a;
        float max = a;

        if (b < min) min = b;
        if (c < min) min = c;

        if (b > max) max = b;
        if (c > max) max = c;

        System.out.println("Найменше число: " + min);
        System.out.println("Найбільше число: " + max);
    }
}
