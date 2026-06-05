package PR_13;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
class CarEntry {
    String brand;
    String number;
    int year;
    boolean rented;
    String date;
    int period;
    public CarEntry(String brand, String number, int year, boolean rented, String date, int period) {
        this.brand = brand;
        this.number = number;
        this.year = year;
        this.rented = rented;
        this.date = date;
        this.period = period;
    }
    public String toDisplayString() {
        return String.format("Марка: %s | Номер: %s | Рік: %d | Орендовано: %s | Дата: %s | Період: %d днів",
                brand, number, year, rented ? "Так" : "Ні", date, period);
    }
    public String toFileString() {
        return String.join("|", brand, number, String.valueOf(year), String.valueOf(rented), date, String.valueOf(period));
    }
    public static CarEntry fromFileString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length != 6) return null;
        return new CarEntry(
                parts[0],
                parts[1],
                Integer.parseInt(parts[2]),
                Boolean.parseBoolean(parts[3]),
                parts[4],
                Integer.parseInt(parts[5])
        );
    }
}
public class PR_13 extends JFrame {
    private JTextArea brandArea;
    private JTextField numberField;
    private JSpinner yearSpinner, periodSpinner;
    private JCheckBox rentedCheck;
    private JComboBox<String> dateCombo;
    private DefaultListModel<String> listModel;
    private JList<String> carList;
    private java.util.List<CarEntry> dataList = new ArrayList<>();
    public PR_13() {
        super("Прокат авто");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        brandArea = new JTextArea(1, 15);
        brandArea.setLineWrap(true);
        JScrollPane brandScroll = new JScrollPane(brandArea);
        numberField = new JTextField(10);
        yearSpinner = new JSpinner(new SpinnerNumberModel(2020, 1980, 2030, 1));
        periodSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 365, 1));
        rentedCheck = new JCheckBox("Орендовано");
        dateCombo = new JComboBox<>();
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        for (int m = 1; m <= 12; m++) {
            int days = daysInMonth[m - 1];
            for (int d = 1; d <= days; d++) {
                dateCombo.addItem(String.format("%02d.%02d.2025", d, m));
            }
        }
        dateCombo.setEnabled(false);
        periodSpinner.setEnabled(false);
        rentedCheck.addItemListener(e -> {
            boolean selected = rentedCheck.isSelected();
            dateCombo.setEnabled(selected);
            periodSpinner.setEnabled(selected);
        });
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Марка:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(brandScroll, gbc);
        gbc.gridx = 2;
        inputPanel.add(new JLabel("Номер:"), gbc);
        gbc.gridx = 3;
        inputPanel.add(numberField, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Рік:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(yearSpinner, gbc);
        gbc.gridx = 2;
        inputPanel.add(rentedCheck, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(new JLabel("Дата оренди (dd.MM.yyyy):"), gbc);
        gbc.gridx = 1;
        inputPanel.add(dateCombo, gbc);
        gbc.gridx = 2;
        inputPanel.add(new JLabel("Період (днів):"), gbc);
        gbc.gridx = 3;
        inputPanel.add(periodSpinner, gbc);
        add(inputPanel, BorderLayout.NORTH);
        listModel = new DefaultListModel<>();
        carList = new JList<>(listModel);
        add(new JScrollPane(carList), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton(" Додати");
        JButton saveButton = new JButton(" Зберегти");
        JButton deleteButton = new JButton(" Видалити");
        JButton loadButton = new JButton(" Зчитати");
        addButton.addActionListener(e -> addEntry());
        saveButton.addActionListener(e -> saveWithChooser());
        deleteButton.addActionListener(e -> deleteSelected());
        loadButton.addActionListener(e -> loadFromChooser());
        buttonPanel.add(addButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(loadButton);
        add(buttonPanel, BorderLayout.SOUTH);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                saveToFile(new File("src\\PR_13\\cars.txt"));
            }
        });

        setVisible(true);
    }
    private void addEntry() {
        String brand = brandArea.getText().trim();
        String number = numberField.getText().trim();
        int year = (int) yearSpinner.getValue();
        boolean rented = rentedCheck.isSelected();
        String date = rented ? (String) dateCombo.getSelectedItem() : "-";
        int period = rented ? (int) periodSpinner.getValue() : 0;

        CarEntry entry = new CarEntry(brand, number, year, rented, date, period);
        dataList.add(entry);
        listModel.addElement(entry.toDisplayString());
    }
    private void deleteSelected() {
        int index = carList.getSelectedIndex();
        if (index != -1) {
            listModel.remove(index);
            dataList.remove(index);
        }
    }
    private void saveWithChooser() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            saveToFile(chooser.getSelectedFile());
        }
    }
    private void loadFromChooser() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            loadFromFile(chooser.getSelectedFile());
        }
    }
    private void saveToFile(File file) {
        try (PrintWriter pw = new PrintWriter(file)) {
            for (CarEntry entry : dataList) {
                pw.println(entry.toFileString());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Помилка збереження: " + e.getMessage());
        }
    }
    private void loadFromFile(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            listModel.clear();
            dataList.clear();
            String line;
            while ((line = br.readLine()) != null) {
                CarEntry entry = CarEntry.fromFileString(line);
                if (entry != null) {
                    dataList.add(entry);
                    listModel.addElement(entry.toDisplayString());
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Помилка зчитування: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PR_13::new);
    }
}

