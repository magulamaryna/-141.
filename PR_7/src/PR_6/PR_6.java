package PR_6;

public class PR_6 {

    // sin(x) за рядом Тейлора
    public static double sin(double x) {
        double term = x;
        double sum = 0;
        int k = 0;

        while (Math.abs(term) >= 0.00001) {
            term = Math.pow(-1, k) * Math.pow(x, 2 * k + 1) / factorial(2 * k + 1);
            sum += term;
            k++;
        }

        return sum;
    }

    // cos(x) за рядом Тейлора
    public static double cos(double x) {
        double term = 1;
        double sum = 0;
        int k = 0;

        while (Math.abs(term) >= 0.00001) {
            term = Math.pow(-1, k) * Math.pow(x, 2 * k) / factorial(2 * k);
            sum += term;
            k++;
        }

        return sum;
    }

    // Факторіал
    private static double factorial(int n) {
        double result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // f(x) через ряди
    public static double fUsingSeries(double x) {
        return 15.0 / (1 + sin(3 * x)) + 1.0 / (1 + cos(5 * x));
    }

    // f(x) через Math
    public static double fUsingMath(double x) {
        return 15.0 / (1 + Math.sin(3 * x)) + 1.0 / (1 + Math.cos(5 * x));
    }

    // Головна функція
    public static void main(String[] args) {
        double[] points = {0, 3, 5};

        System.out.printf("%-6s | %-18s | %-18s | %-18s%n", "x", "f(x) (ряд)", "f(x) (Math)", "Модуль різниці");
        System.out.println("----------------------------------------------------------------------");

        for (double x : points) {
            double approx = fUsingSeries(x);
            double exact = fUsingMath(x);
            double diff = Math.abs(approx - exact);

            System.out.printf("%-6.0f | %-18.10f | %-18.10f | %-18.10f%n", x, approx, exact, diff);
        }
    }
}
