package PR_10;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
class CarRental {
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
    public String getBrand() { return brand; }
    public String getLicensePlate() { return licensePlate; }
    public int getYear() { return year; }
    public boolean isRented() { return isRented; }
    public LocalDate getRentalDate() { return rentalDate; }
    public int getRentalPeriod() { return rentalPeriod; }

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

    // Текстове представлення
    public String toTextLine() {
        return String.join("|",
                brand,
                licensePlate,
                String.valueOf(year),
                String.valueOf(isRented),
                isRented ? rentalDate.format(DATE_FORMAT) : "",
                String.valueOf(rentalPeriod));
    }
    // Відновлення з тексту
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
}
public class PR_10 {
    public static void main(String[] args) {
        List<CarRental> cars = new ArrayList<>();
        cars.add(new CarRental("Toyota", "AB1234CD", 2020, true, LocalDate.of(2025, 3, 20), 10));
        cars.add(new CarRental("Honda", "BC5678EF", 2019));
        cars.add(new CarRental("Ford", "CD9101GH", 2021, true, LocalDate.of(2025, 3, 25), 15));
        cars.add(new CarRental("BMW", "DE2345IJ", 2022));
        cars.add(new CarRental("Mercedes", "EF6789KL", 2018, true, LocalDate.of(2025, 4, 5), 7));
        cars.add(new CarRental("Audi", "FG1234MN", 2020));
        cars.add(new CarRental("Hyundai", "GH5678OP", 2021, true, LocalDate.of(2025, 3, 30), 5));
        cars.add(new CarRental("Volkswagen", "IJ9101QR", 2017));
        cars.add(new CarRental("Kia", "KL2345ST", 2019, true, LocalDate.of(2025, 4, 1), 10));
        cars.add(new CarRental("Nissan", "MN6789UV", 2016));
        // Вивід усіх автомобілів
        System.out.println("\n Усі автомобілі:");
        printTable(cars);
        // Збереження у текстовий файл
        saveToTextFile(cars, "src\\PR_10\\cars.txt");
        // Зчитування з текстового файлу
        List<CarRental> loadedCars = loadFromTextFile("src\\PR_10\\cars.txt");
        // Вивід зчитаних
        System.out.println("\n Автомобілі, зчитані з файлу:");
        printTable(loadedCars);
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
    // Збереження в текстовий файл
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
    // Зчитування з текстового файлу
    private static List<CarRental> loadFromTextFile(String filename) {
        List<CarRental> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.add(CarRental.fromTextLine(line));
            }
            System.out.println("\n Дані зчитано з файлу: " + filename);
        } catch (IOException e) {
            System.err.println(" Помилка читання з файлу: " + e.getMessage());
        }
        return result;
    }
}
