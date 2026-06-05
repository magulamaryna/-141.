package PR_11;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.time.format.DateTimeFormatter;
class CarRental implements Comparable<CarRental> {
    private String brand;
    private String licensePlate;
    private int year;
    private boolean isRented;
    private LocalDate rentalDate;
    private int rentalPeriod;
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public CarRental(String brand, String licensePlate, int year, boolean isRented, LocalDate rentalDate, int rentalPeriod) {
        this.brand = brand;
        this.licensePlate = licensePlate;
        this.year = year;
        this.isRented = isRented;
        this.rentalDate = isRented ? rentalDate : null;
        this.rentalPeriod = isRented ? rentalPeriod : 0;
    }
    public CarRental(String brand, String licensePlate, int year) {
        this(brand, licensePlate, year, false, null, 0);
    }
    public LocalDate getReturnDate() {
        return isRented ? rentalDate.plusDays(rentalPeriod) : null;
    }
    @Override
    public String toString() {
        return String.format("| %-10s | %-8s | %-4d | %-8s | %-12s | %3d днів |",
                brand, licensePlate, year,
                isRented ? "Так" : "Ні",
                isRented ? rentalDate.format(DATE_FORMAT) : "—",
                isRented ? rentalPeriod : 0);
    }
    public String toTextLine() {
        return String.join("|",
                brand,
                licensePlate,
                String.valueOf(year),
                String.valueOf(isRented),
                isRented ? rentalDate.format(DATE_FORMAT) : "",
                String.valueOf(rentalPeriod));
    }
    public static CarRental fromTextLine(String line) {
        String[] parts = line.split("\\|");
        String brand = parts[0].trim();
        String licensePlate = parts[1].trim();
        int year = Integer.parseInt(parts[2].trim());
        boolean isRented = Boolean.parseBoolean(parts[3].trim());
        if (isRented) {
            LocalDate rentalDate = LocalDate.parse(parts[4].trim(), DATE_FORMAT);
            int rentalPeriod = Integer.parseInt(parts[5].trim());
            return new CarRental(brand, licensePlate, year, true, rentalDate, rentalPeriod);
        } else {
            return new CarRental(brand, licensePlate, year);
        }
    }
    public int compareTo(CarRental other) {
        int cmp = Integer.compare(other.year, this.year); // новіші — перші
        if (cmp != 0) return cmp;
        if (this.isRented && other.isRented) {
            cmp = this.rentalDate.compareTo(other.rentalDate);
            if (cmp != 0) return cmp;
        } else if (this.isRented) {
            return -1;
        } else if (other.isRented) {
            return 1;
        }
        return this.brand.compareToIgnoreCase(other.brand);
    }
}
public class PR_11 {
    public static void main(String[] args) {
        List<CarRental> cars = loadFromTextFile("src\\PR_10\\cars.txt");
        // Додати нові авто
        System.out.println("\n Додаються нові автомобілі...");
        CarRental car1 = new CarRental("Lexus", "LX9999AA", 2023, true, LocalDate.of(2025, 4, 10), 14);
        CarRental car2 = new CarRental("Skoda", "SK8888BB", 2022);
        CarRental car3 = new CarRental("Mitsubishi", "MT7777CC", 2024, true, LocalDate.of(2025, 4, 15), 7);
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        System.out.println(car1 + "\n" + car2 + "\n" + car3);
        System.out.println("\n Нові автомобілі додано.");

        // Сортування
        System.out.println("\n Сортування списку автомобілів...");
        Collections.sort(cars);
        // Вивід
        System.out.println("\n Відсортований список автомобілів:");
        printTable(cars);
        // Збереження
        saveToTextFile(cars, "src\\PR_11\\cars.txt");
    }
    private static void printTable(List<CarRental> cars) {
        if (cars.isEmpty()) {
            System.out.println(" Немає даних.");
            return;
        }
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("| Марка      | Номер    | Рік  | Оренда   | Дата оренди  | Термін   |");
        System.out.println("-------------------------------------------------------------------------------");
        for (CarRental car : cars) {
            System.out.println(car);
        }
        System.out.println("-------------------------------------------------------------------------------");
    }
    private static void saveToTextFile(List<CarRental> cars, String filename) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for (CarRental car : cars) {
                out.println(car.toTextLine());
            }
            System.out.println(" Дані збережено у файл: " + filename);
        } catch (IOException e) {
            System.err.println(" Помилка запису у файл: " + e.getMessage());
        }
    }
    private static List<CarRental> loadFromTextFile(String filename) {
        List<CarRental> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(CarRental.fromTextLine(line));
            }
            System.out.println(" Дані зчитано з файлу: " + filename);
        } catch (IOException e) {
            System.err.println(" Помилка читання з файлу: " + e.getMessage());
        }
        return result;
    }
}


