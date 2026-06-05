package PR_2_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PR_2_2 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println("Введіть температуру в градусах Цельсія:");
        float TC = Float.parseFloat(reader.readLine());

        float TF = (TC * 9 / 5) + 32;

        System.out.println("Температура у Фаренгейтах: " + TF);
    }
}
