package PR_14;

import java.io.Serializable;

public class GameState implements Serializable {

    private static final long serialVersionUID = 1L;

    public int[][] player1Field;
    public int[][] player2Field;

    public int currentPlayer;

    public int shipsPlaced;

    public boolean placingShips;
}

