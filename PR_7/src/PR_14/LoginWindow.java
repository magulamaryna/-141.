package PR_14;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {

    private JTextField loginField;

    private JPasswordField passwordField;

    private JPasswordField confirmField;

    private JButton loginButton;

    private JButton registerButton;

    private JButton cancelButton;

    private boolean registerMode = false;

    private UserManager manager;

    public LoginWindow() {

        manager = new UserManager();

        setTitle("Вхід до системи");

        setSize(450, 250);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initialize();
    }

    private void initialize() {

        JPanel panel = new JPanel();

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,10,10,10));

        panel.setLayout(
                new GridLayout(5,2,10,10));

        panel.add(new JLabel("Логін:"));

        loginField = new JTextField();

        panel.add(loginField);

        panel.add(new JLabel("Пароль:"));

        passwordField = new JPasswordField();

        panel.add(passwordField);

        panel.add(new JLabel("Підтвердження пароля:"));

        confirmField = new JPasswordField();

        confirmField.setVisible(false);

        panel.add(confirmField);

        loginButton = new JButton("Увійти");

        registerButton =
                new JButton("Реєстрація");

        cancelButton =
                new JButton("Скасувати");

        panel.add(loginButton);

        panel.add(registerButton);

        panel.add(cancelButton);

        add(panel);

        loginButton.addActionListener(
                e -> login());

        registerButton.addActionListener(
                e -> register());

        cancelButton.addActionListener(
                e -> System.exit(0));
    }

    private void login() {

        String login =
                loginField.getText();

        String password =
                new String(
                        passwordField.getPassword());

        if(manager.checkUser(login,password)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Вхід успішний");

            GameWindow window =
                    new GameWindow();

            window.setVisible(true);

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Неправильний логін або пароль");
        }
    }

    private void register() {

        if(!registerMode) {

            registerMode = true;

            setTitle("Реєстрація");

            confirmField.setVisible(true);

            loginButton.setVisible(false);

            revalidate();

            repaint();

            return;
        }

        String login =
                loginField.getText();

        String pass1 =
                new String(
                        passwordField.getPassword());

        String pass2 =
                new String(
                        confirmField.getPassword());

        if(!pass1.equals(pass2)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Паролі не співпадають");

            return;
        }

        manager.addUser(login, pass1);

        JOptionPane.showMessageDialog(
                this,
                "Користувач зареєстрований");

        registerMode = false;

        setTitle("Вхід до системи");

        confirmField.setVisible(false);

        loginButton.setVisible(true);

        passwordField.setText("");

        confirmField.setText("");
    }
}