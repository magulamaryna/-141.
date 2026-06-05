import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PR_2_1 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println("Введіть ваше ім'я:");
        String name = reader.readLine();

        System.out.println("Введіть ваш вік:");
        int age = Integer.parseInt(reader.readLine());

        int birthYear = 2026 - age;

        System.out.println("Привіт, " + name + "!");
        System.out.println("Ваш рік народження: " + birthYear);
    }
}


