package PR_14;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class GamePanel extends JPanel {

    private static final int SIZE = 8;
    private static final int SHIPS = 10;

    private JButton[][] buttons;

    private int[][] player1Field;
    private int[][] player2Field;

    private JLabel infoLabel;

    private JLabel killedLabel;
    private JLabel aliveLabel;

    private int killed = 0;
    private int alive = 10;

    private int currentPlayer = 1;

    private int shipsPlaced = 0;

    private boolean placingShips = true;

    public GamePanel() {

        setLayout(new BorderLayout());

        player1Field = new int[SIZE][SIZE];
        player2Field = new int[SIZE][SIZE];

        createBoard();
    }

    private void createBoard() {

        JPanel board = new JPanel();

        board.setLayout(new GridLayout(SIZE, SIZE));

        buttons = new JButton[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {

            for (int j = 0; j < SIZE; j++) {

                JButton btn = new JButton();

                btn.setPreferredSize(
                        new Dimension(60, 60));

                final int x = i;
                final int y = j;

                btn.addActionListener(
                        e -> cellClick(x, y));

                buttons[i][j] = btn;

                board.add(btn);
            }
        }

        infoLabel = new JLabel(
                "Гравець 1: розставте "
                        + SHIPS + " кораблів");

        killedLabel =
                new JLabel("Вбито: 0");

        aliveLabel =
                new JLabel("Залишилось: 10");

        JPanel stats = new JPanel();

        stats.add(killedLabel);
        stats.add(aliveLabel);

        add(stats, BorderLayout.NORTH);

        add(infoLabel, BorderLayout.SOUTH);

        add(board, BorderLayout.CENTER);
    }

    private void cellClick(int x, int y) {

        if (placingShips) {

            placeShip(x, y);

        } else {

            makeMove(x, y);
        }
    }

    private void placeShip(int x, int y) {

        int[][] field =
                currentPlayer == 1
                        ? player1Field
                        : player2Field;

        if (field[x][y] == 1) {
            return;
        }

        field[x][y] = 1;

        buttons[x][y].setText("🚢");

        shipsPlaced++;

        if (shipsPlaced == SHIPS) {

            if (currentPlayer == 1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Передайте комп'ютер гравцю 2");

                clearBoard();

                currentPlayer = 2;

                shipsPlaced = 0;

                infoLabel.setText(
                        "Гравець 2: розставте "
                                + SHIPS + " кораблів");

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Розстановка завершена.\nПочинається гра.");

                clearBoard();

                currentPlayer = 1;

                placingShips = false;

                infoLabel.setText(
                        "Хід гравця 1");
            }
        }
    }

    private void makeMove(int x, int y) {

        int[][] enemyField =
                currentPlayer == 1
                        ? player2Field
                        : player1Field;

        JButton btn = buttons[x][y];

        if (!btn.isEnabled()) {
            return;
        }

        if (enemyField[x][y] == 1) {

            btn.setText("💥");

            enemyField[x][y] = 2;

        } else {

            btn.setText("•");
        }

        btn.setEnabled(false);

        if (checkWinner()) {
            return;
        }

        currentPlayer =
                currentPlayer == 1 ? 2 : 1;

        JOptionPane.showMessageDialog(
                this,
                "Передайте хід іншому гравцю");

        clearBoard();

        infoLabel.setText(
                "Хід гравця "
                        + currentPlayer);
    }

    private boolean checkWinner() {

        int[][] enemyField =
                currentPlayer == 1
                        ? player2Field
                        : player1Field;

        int aliveShips = 0;

        for (int i = 0; i < SIZE; i++) {

            for (int j = 0; j < SIZE; j++) {

                if (enemyField[i][j] == 1) {

                    aliveShips++;
                }
            }
        }

        if (aliveShips == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Переміг гравець "
                            + currentPlayer);

            return true;
        }

        killed++;

        killedLabel.setText(
                "Вбито: " + killed);

        alive = aliveShips;

        aliveLabel.setText(
                "Залишилось: " + alive);

        return false;
    }

    private void clearBoard() {

        for (int i = 0; i < SIZE; i++) {

            for (int j = 0; j < SIZE; j++) {

                buttons[i][j].setText("");

                buttons[i][j].setEnabled(true);
            }
        }
    }

    public void newGame() {

        player1Field = new int[SIZE][SIZE];
        player2Field = new int[SIZE][SIZE];

        currentPlayer = 1;

        shipsPlaced = 0;

        placingShips = true;

        killed = 0;
        alive = 10;

        killedLabel.setText("Вбито: 0");
        aliveLabel.setText("Залишилось: 10");

        clearBoard();

        infoLabel.setText(
                "Гравець 1: розставте "
                        + SHIPS + " кораблів");
    }

    public void saveGame() {

        try {

            GameState state = new GameState();

            state.player1Field = player1Field;
            state.player2Field = player2Field;

            state.currentPlayer = currentPlayer;

            state.shipsPlaced = shipsPlaced;

            state.placingShips = placingShips;

            ObjectOutputStream out =
                    new ObjectOutputStream(
                            new FileOutputStream(
                                    "game.dat"));

            out.writeObject(state);

            out.close();

            JOptionPane.showMessageDialog(
                    this,
                    "Гру збережено");

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Помилка збереження");
        }
    }

    public void saveGameAs() {

        JFileChooser chooser =
                new JFileChooser();

        int result =
                chooser.showSaveDialog(this);

        if (result ==
                JFileChooser.APPROVE_OPTION) {

            File file =
                    chooser.getSelectedFile();

            try {

                GameState state =
                        new GameState();

                state.player1Field =
                        player1Field;

                state.player2Field =
                        player2Field;

                state.currentPlayer =
                        currentPlayer;

                state.shipsPlaced =
                        shipsPlaced;

                state.placingShips =
                        placingShips;

                ObjectOutputStream out =
                        new ObjectOutputStream(
                                new FileOutputStream(
                                        file));

                out.writeObject(state);

                out.close();

                JOptionPane.showMessageDialog(
                        this,
                        "Гру збережено");

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    public void loadGame() {

        JFileChooser chooser =
                new JFileChooser();

        int result =
                chooser.showOpenDialog(this);

        if (result ==
                JFileChooser.APPROVE_OPTION) {

            try {

                File file =
                        chooser.getSelectedFile();

                ObjectInputStream in =
                        new ObjectInputStream(
                                new FileInputStream(
                                        file));

                GameState state =
                        (GameState)
                                in.readObject();

                player1Field =
                        state.player1Field;

                player2Field =
                        state.player2Field;

                currentPlayer =
                        state.currentPlayer;

                shipsPlaced =
                        state.shipsPlaced;

                placingShips =
                        state.placingShips;

                in.close();

                clearBoard();

                JOptionPane.showMessageDialog(
                        this,
                        "Гру завантажено");

            } catch (Exception e) {

                e.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Помилка відкриття");
            }
        }
    }
}