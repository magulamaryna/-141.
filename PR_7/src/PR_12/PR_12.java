package PR_12;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;
public class PR_12 extends JFrame {
    private DefaultListModel<CarRental> listModel = new DefaultListModel<>();
    private JList<CarRental> carList = new JList<>(listModel);
    private JTextField brandField = new JTextField(10);
    private JTextField plateField = new JTextField(10);
    private JTextField yearField = new JTextField(4);
    private JCheckBox rentedBox = new JCheckBox("Орендовано");
    private JTextField dateField = new JTextField(8); // формат dd.MM.yyyy
    private JTextField periodField = new JTextField(4);
    private final String FILE_TO_READ = "src/PR_11/cars.txt";
    private final String FILE_TO_SAVE = "src/PR_12/cars.txt";
    public PR_12() {
        setTitle("Прокат авто");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(3, 4, 10, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Введіть дані"));

        inputPanel.add(new JLabel("Марка:"));
        inputPanel.add(brandField);
        inputPanel.add(new JLabel("Номер:"));
        inputPanel.add(plateField);
        inputPanel.add(new JLabel("Рік:"));
        inputPanel.add(yearField);
        inputPanel.add(rentedBox);
        inputPanel.add(new JLabel(""));
        inputPanel.add(new JLabel("Дата оренди (dd.MM.yyyy):"));
        inputPanel.add(dateField);
        inputPanel.add(new JLabel("Період (днів):"));
        inputPanel.add(periodField);
        add(inputPanel, BorderLayout.NORTH);
        carList.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(carList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Список авто"));
        add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton(" Додати");
        JButton saveButton = new JButton(" Зберегти");
        JButton removeButton = new JButton(" Видалити");
        JButton loadButton = new JButton(" Зчитати");
        buttonPanel.add(addButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(loadButton);  // Додали кнопку Зчитати
        add(buttonPanel, BorderLayout.SOUTH);
        rentedBox.addActionListener(e -> toggleRentalFields());
        addButton.addActionListener(e -> addCar());
        saveButton.addActionListener(e -> saveList());
        removeButton.addActionListener(e -> removeSelected());
        loadButton.addActionListener(e -> loadList()); // Натискання на Зчитати
        toggleRentalFields();
        setVisible(true);
    }
    private void toggleRentalFields() {
        boolean rented = rentedBox.isSelected();
        dateField.setEnabled(rented);
        periodField.setEnabled(rented);
    }
    private void addCar() {
        try {
            String brand = brandField.getText().trim();
            String plate = plateField.getText().trim();
            int year = Integer.parseInt(yearField.getText().trim());
            boolean isRented = rentedBox.isSelected();
            CarRental car;
            if (isRented) {
                LocalDate date = LocalDate.parse(dateField.getText().trim(), CarRental.DATE_FORMAT);
                int period = Integer.parseInt(periodField.getText().trim());
                car = new CarRental(brand, plate, year, true, date, period);
            } else {
                car = new CarRental(brand, plate, year);
            }
            listModel.addElement(car);
            clearFields();
        } catch (NumberFormatException | DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, " Некоректне введення: " + ex.getMessage(), "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void clearFields() {
        brandField.setText("");
        plateField.setText("");
        yearField.setText("");
        dateField.setText("");
        periodField.setText("");
        rentedBox.setSelected(false);
    }

    private void saveList() {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_TO_SAVE))) {
            List<CarRental> cars = Collections.list(listModel.elements());
            cars.sort(CarRental::compareTo);
            for (CarRental car : cars) {
                out.println(car.toTextLine());
            }
            JOptionPane.showMessageDialog(this, " Дані збережено у файл: " + FILE_TO_SAVE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, " Помилка збереження: " + e.getMessage(), "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void loadList() {
        File file = new File(FILE_TO_READ);
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, " Файл для зчитування не знайдено: " + FILE_TO_READ, "Помилка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            listModel.clear(); // Очищуємо список перед завантаженням
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    CarRental car = CarRental.fromTextLine(line);
                    listModel.addElement(car);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this,
                            "Пропущено некоректний рядок " + lineNumber + ": " + ex.getMessage(),
                            "Помилка формату", JOptionPane.WARNING_MESSAGE);
                }
            }
            JOptionPane.showMessageDialog(this, " Дані успішно зчитані з файлу.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, " Помилка читання файлу: " + e.getMessage(), "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void removeSelected() {
        int index = carList.getSelectedIndex();
        if (index >= 0) {
            listModel.remove(index);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PR_12::new);
    }
}
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
    public LocalDate getReturnDate() {
        return isRented ? rentalDate.plusDays(rentalPeriod) : null;
    }
    @Override
    public String toString() {
        String periodStr = isRented ? String.format("%3d", rentalPeriod) : "  0";
        String rentDateStr = isRented ? rentalDate.format(DATE_FORMAT) : "------------";
        return String.format("| %-10s | %-8s | %-4d | %-8s | %-12s | %s днів |",
                brand, licensePlate, year,
                isRented ? "Так" : "Ні",
                rentDateStr,
                periodStr);
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
        if (parts.length < 6) throw new IllegalArgumentException("Мало частин у рядку");
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

