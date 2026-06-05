package PR_9;

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
    private int rentalPeriod; // у днях
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    // Конструктор для орендованих автомобілів
    public CarRental(String brand, String licensePlate, int year, boolean isRented, LocalDate rentalDate, int rentalPeriod) {
        this.brand = brand;
        this.licensePlate = licensePlate;
        this.year = year;
        this.isRented = isRented;
        this.rentalDate = isRented ? rentalDate : null;
        this.rentalPeriod = isRented ? rentalPeriod : 0;
    }
    // Конструктор для вільних автомобілів
    public CarRental(String brand, String licensePlate, int year) {
        this(brand, licensePlate, year, false, null, 0);
    }
    // Методи доступу
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
}
public class PR_9 {
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

        // Вивід орендованих автомобілів
        System.out.println("\n Орендовані автомобілі:");
        printTable(filterCars(cars, true));

        // Вивід вільних автомобілів
        System.out.println("\n Вільні автомобілі:");
        printTable(filterCars(cars, false));
        // Пошук автомобілів, що звільняться у квітні 2025 року
        System.out.println("\n Автомобілі, що звільняться у квітні 2025:");
        printTable(filterByReturnMonth(cars, 4, 2025));
    }
    // Метод для виводу таблиці
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
    // Метод для фільтрації за статусом оренди
    private static List<CarRental> filterCars(List<CarRental> cars, boolean rented) {
        List<CarRental> result = new ArrayList<>();
        for (CarRental car : cars) {
            if (car.isRented() == rented) {
                result.add(car);
            }
        }
        return result;
    }
    // Метод для пошуку авто, що звільняться у вказаному місяці
    private static List<CarRental> filterByReturnMonth(List<CarRental> cars, int month, int year) {
        List<CarRental> result = new ArrayList<>();
        for (CarRental car : cars) {
            LocalDate returnDate = car.getReturnDate();
            if (returnDate != null && returnDate.getYear() == year && returnDate.getMonthValue() == month) {
                result.add(car);
            }
        }
        return result;
    }
}
