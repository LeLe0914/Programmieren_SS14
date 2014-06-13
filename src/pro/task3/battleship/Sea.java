package battleship;

/**
 * @author Sebastian Muenzner
 * @version 1.0
 * 
 * Models the rectangular board of squares for the battle ship game.
 */
public class Sea {
    /**
     * Width of board.
     */
    private final int width;
    /**
     * Height of board.
     */
    private final int height;

    /**
     * True if a bomb has been dropped over the square given by the entry's
     * coordinate. Array organization is as in board.
     */
    private final boolean[][] bombed;

    /**
     * Number represents length of ship in field.
     */
    private final int[][] ships;

    /**
     * Creates a new empty board of the given size with all squares water. At
     * most maxShipCount many ships can be set on this board.
     * 
     * @param width
     *            Width of board
     * @param height
     *            Height of board
     */
    public Sea(final int width, final int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException();
        }
        this.width = width;
        this.height = height;
        bombed = new boolean[this.width][this.height];
        ships = new int[this.width][this.height];
    }

    /**
     * Set a ship of the given type with the stern at the given position in the
     * given direction. If this is impossible, false is returned.
     * 
     * @param shipType
     *            type of ship
     * @param direction
     *            direction of ship on board
     * @param x
     *            x-coordinate on board
     * @param y
     *            y-coordinate on board
     * @return true, if ship is successfully added
     */
    public boolean addShip(final ShipType shipType, final Direction direction,
            final int x, final int y) {
        boolean squaresFree = false;

        /*
         * If space is left, computes the step length in x and y coordinates
         * when moving from the stern of the ship to the bow. Then checks if all
         * occupied squares are free and on the board. If so, adds the ship
         */
        int dx = 0;
        int dy = 0;

        switch (direction) {
        case EAST:
            dx = 1;
            break;

        case NORTH:
            dy = -1;
            break;

        case WEST:
            dx = -1;
            break;

        case SOUTH:
            dy = 1;
            break;

        default:
            assert false : "Illegal direction: " + direction;
            break;
        }

        squaresFree = true;
        for (int posIdx = 0; posIdx < shipType.getLength(); posIdx++) {
            int tmpX = x + posIdx * dx;
            int tmpY = y + posIdx * dy;

            if (!isPositionOnBoard(tmpX, tmpY) || (ships[tmpX][tmpY] != 0)
                    || startBombing()) {
                squaresFree = false;
            }
        }

        if (squaresFree) {
            for (int i = 0; i < shipType.getLength(); i++) {
                ships[x + i * dx][y + i * dy] = shipType.getLength();
            }
        }

        return squaresFree;
    }

    /**
     * Drop a bomb on the squared given by its position. Returns true if the
     * square has not yet been bombed and a ship is hit.
     * 
     * @param x
     *            x-coordinate on board
     * @param y
     *            y-coordinate on board
     * @return true, if ship is successfully bombed
     */
    public boolean dropBomb(final int x, final int y) {
        boolean hit = false;

        if (isPositionOnBoard(x, y)) {
            hit = (!bombed[x][y] && ships[x][y] != 0);
            bombed[x][y] = true;
        }
        return hit;
    }

    /**
     * Returns a string representation of the board from the attackers point of
     * view. Only water and bombs are shown.
     * 
     * @return string representation of board from enemy's perspective
     */
    public String toStringWithBombs() {
        String s = "";
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (!bombed[x][y]) {
                    s += ".";
                } else if (ships[x][y] == 0) {
                    s += "O";
                } else {
                    s += "X";
                }
            }
            s += "\n";
        }
        return s;
    }

    /**
     * Returns a string representation of the board from the players point of
     * view. Only water and the ships are shown.
     * 
     * @return string representation of board from player's perspective
     */
    public String toStringWithShips() {
        String s = "";

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (ships[x][y] == 0) {
                    s += ".";
                } else {
                    s += ships[x][y];
                }
            }
            s += "\n";
        }

        return s;
    }

    /**
     * Returns true if all ships have been completely sunk.
     * 
     * @return true, if all ships are sunken
     */
    public boolean allShipsSunk() {

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (ships[x][y] != 0 && !bombed[x][y]) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Checks whether a position is valid.
     * 
     * @param x
     *            x-coordinate on board
     * @param y
     *            y-coordinate on board
     * @return true, if ship is successfully bombed
     */
    private boolean isPositionOnBoard(final int x, final int y) {
        return (x >= 0) && (x < width) && (y >= 0) && (y < height);
    }

    /**
     * Looks up if a bomb has already been dropped, returns true in case of
     * starting.
     * 
     * @return true, if bombing already started
     */
    private boolean startBombing() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (bombed[x][y]) {
                    return true;
                }
            }
        }
        return false;

    }
}
