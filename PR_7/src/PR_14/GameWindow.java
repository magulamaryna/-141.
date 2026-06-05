package PR_14;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameWindow extends JFrame {

    private GamePanel gamePanel;

    public GameWindow() {

        setTitle("Морський бій");

        setSize(1000, 700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel = new GamePanel();

        add(gamePanel, BorderLayout.CENTER);

        createMenu();

        createToolBar();
    }

    private void createMenu() {

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem saveAsItem = new JMenuItem("Save As");
        JMenuItem exitItem = new JMenuItem("Exit");

        newItem.setAccelerator(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_N,
                        InputEvent.CTRL_DOWN_MASK));

        openItem.setAccelerator(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_O,
                        InputEvent.CTRL_DOWN_MASK));

        saveItem.setAccelerator(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_S,
                        InputEvent.CTRL_DOWN_MASK));

        exitItem.setAccelerator(
                KeyStroke.getKeyStroke(
                        KeyEvent.VK_Q,
                        InputEvent.CTRL_DOWN_MASK));

        newItem.addActionListener(
                e -> gamePanel.newGame());

        openItem.addActionListener(
                e -> gamePanel.loadGame());

        saveItem.addActionListener(
                e -> gamePanel.saveGame());

        saveAsItem.addActionListener(
                e -> gamePanel.saveGameAs());

        exitItem.addActionListener(
                e -> System.exit(0));

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu helpMenu = new JMenu("Help");

        JMenuItem aboutItem =
                new JMenuItem("About");

        aboutItem.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Морський бій\nВерсія 1.0\nJava Swing",
                    "About",
                    JOptionPane.INFORMATION_MESSAGE);

        });

        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

    private void createToolBar() {

        JToolBar toolBar = new JToolBar();

        JButton btnNew =
                new JButton("New");

        JButton btnOpen =
                new JButton("Open");

        JButton btnSave =
                new JButton("Save");

        JButton btnAbout =
                new JButton("About");

        btnNew.addActionListener(
                e -> gamePanel.newGame());

        btnOpen.addActionListener(
                e -> gamePanel.loadGame());

        btnSave.addActionListener(
                e -> gamePanel.saveGame());

        btnAbout.addActionListener(
                e -> JOptionPane.showMessageDialog(
                        this,
                        "Морський бій\nJava Swing"));

        toolBar.add(btnNew);
        toolBar.add(btnOpen);
        toolBar.add(btnSave);
        toolBar.addSeparator();
        toolBar.add(btnAbout);

        add(toolBar, BorderLayout.NORTH);
    }
}