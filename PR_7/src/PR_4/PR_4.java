package PR_4;

import java.lang.Math;

public class PR_4 {

    // Метод для розв'язання системи нерівностей
    public static String solve(double a, double b, double c) {
        String result = "";
        double discriminant = b * b - 4 * c;

        // Перша нерівність: x - a < 0 → x < a
        double x1 = a;

        // Друга нерівність: x^2 + b*x + c > 0
        if (discriminant < 0) {
            // Квадратний тричлен не має коренів, знак ">" визначається коефіцієнтом при x^2
            // Якщо старший коефіцієнт додатний, то розв'язок - усі числа
            if (1 > 0) {
                result = "(-∞, " + x1 + ")";
            }
        } else if (discriminant == 0) {
            // Один корінь
            double root = -b / 2;
            if (root < x1) {
                result = "(-∞, " + root + ") ∪ (" + root + ", " + x1 + ")";
            } else {
                result = "(-∞, " + x1 + ")";
            }
        } else {
            // Два корені
            double sqrtD = Math.sqrt(discriminant);
            double root1 = (-b - sqrtD) / 2;
            double root2 = (-b + sqrtD) / 2;
            // Впорядкуємо корені
            double rMin = Math.min(root1, root2);
            double rMax = Math.max(root1, root2);

            // Розв'язок квадратної нерівності x^2 + bx + c > 0: (-∞, rMin) ∪ (rMax, ∞)
            if (x1 <= rMin) {
                result = "(-∞, " + x1 + ")";
            } else if (x1 >= rMax) {
                result = "(-∞, " + rMin + ") ∪ (" + rMax + ", " + x1 + ")";
            } else {
                result = "(-∞, " + rMin + ") ∪ (" + x1 + ", " + rMax + ")";
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Приклади для демонстрації всіх гілок розгалуження
        System.out.println("a=3, b=-1, c=-6 → " + solve(3, -1, -6));
        System.out.println("a=-2, b=1, c=2 → " + solve(-2, 1, 2));
        System.out.println("a=1, b=-2, c=1 → " + solve(1, -2, 1));
        System.out.println("a=10, b=2, c=-3 → " + solve(10, 2, -3));
        System.out.println("a=-5, b=1, c=-4 → " + solve(-5, 1, -4));
    }
}

