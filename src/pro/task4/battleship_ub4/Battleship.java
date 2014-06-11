package battleship_ub4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import battleship.Direction;
import battleship.Sea;
import battleship.ShipType;
import battleship_ub4.Player.PlayerID;

/**
 * The class represents a Battleship
 * @author Le Wang
 * @version 1.0
 */
public final class Battleship {
    
    /**
     * Border's value should be in Interval [7,10]
     */
    private static final int BORDER_MIN = 7;
    private static final int BORDER_MAX = 10;
    
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    
    private int width;
    private int height;
    
    private String typing;
    
    /**
     * Battleship's Constructor
     * @param width sea's width
     * @param height sea's height
     */
    private Battleship(int width, int height) {
        if (isBorderLegal(width, height)) {
            this.width = width;
            this.height = height;
            player1 = new Player(PlayerID.ONE, new Sea(width, height));
            player2 = new Player(PlayerID.TWO, new Sea(width, height));
        } else {
            Terminal.printLine("Sorry, Values are illegal..should be in interval [7,10]!");
            Terminal.printLine("Starting Game Failed ! :(");
            Terminal.printLine("Please start game again !");
            System.exit(1);
        }
    }
    
    /**
     * Main - Method
     * @param args commander line arguments for setting width and height
     * @throws IOException 
     * @throws CloneNotSupportedException 
     */
    public static void main(String[] args) throws IOException {
        Battleship battelship = new Battleship(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        battelship.playGame();     
    }
    
    
    /**
     * This method is responsible for checking the border' value if legal or not
     * @param width border's width
     * @param height border's height
     * @return true or false
     */
    private boolean isBorderLegal(int width, int height) {
        if (width <= BORDER_MAX && width >= BORDER_MIN 
            && height >= BORDER_MIN && height <= BORDER_MAX) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Starting Game, build game's menu and listening player enter instruction
     * @throws IOException 
     * @throws CloneNotSupportedException 
     */
    private void playGame() throws IOException {
        Terminal.printLine("---------------- INITIAL GAME -----------------");
        this.initialGame();
        Terminal.printLine("---------------- END INITIAL GAME -----------------");
        
        Terminal.printLine("\n---------------- START PLAY GAME -----------------");
        this.dropBomb();
        Terminal.printLine("---------------- END PLAY GAME -----------------");
        
        Terminal.printLine("\n---------------- OUTPUT RESULT -----------------");
        Terminal.printLine("Winner is Player " + currentPlayer.getId());
        Terminal.printLine("\n---------------- PLAYER ONE -----------------");
        this.printBombField(player2);
        Terminal.printLine("\n---------------- PLAYER TWO -----------------");
        this.printBombField(player1);
        
        Terminal.printLine("GAME OVER \n  A output Text file is builded !");
        this.outputResultTxt();
    }
    
    
    /**
     * Initial game... Player 1 and Player 2 add 10 ships 
     * (includes 1 FLATTOP, 2 DREADNOUGHT, 3 BATTLECRUISER, 4 MINESWEEPER) 
     * @throws IOException 
     * @throws CloneNotSupportedException 
     */
    private void initialGame() throws IOException {
        int i = 0; // count for player, "0" is player 1 and "1" is player 2 
        while (i < 2) {
            // Show which player puts ships
            if (i == 0) {
                currentPlayer = player1;
                if (this.putShips()) {
                    Terminal.printLine("\nPlayer 1 has completed the putting ships \n"
                                     + "*******************************************\n");
                    i++;
                }   
            } else {
                currentPlayer = player2;
                if (this.putShips()) {
                    Terminal.printLine("\nPlayer 2 has completed the putting ships \n"
                                     + "*******************************************\n");
                    i++;
                }
            }
            
        }
    }
    
    /**
     * Put ships 
     * @return
     * @throws IOException 
     * @throws CloneNotSupportedException 
     */
    private boolean putShips() throws IOException {
        boolean result = false;
        // count ships and direction,which player wants to put
        int shipCount = 0;
        ShipType type = null;
        Direction dir;
        int[] coordinate;
        
        // Input tips and isAdd
        String maxX = Character.toString((char) (this.width + 65 - 1));
        boolean isAdd = false;
        
        // Some tips
        System.out.print("Tips:  Format of Position likes this \"D4 N\" \n"
                         + "       - D4 represents coordinate, D is column and 4 is row \n"
                         + "       - N is direction North \n"
                         + "       - W is direction West \n"
                         + "       - S is direction South \n"
                         + "       - O is direction East \n "
                         + "      \"reset\" , if format likes this \"D4 N rest\" , The battle field will be reset \n"
                         + "       MAX_X-AXIS IS " + maxX + "\n"
                         + "       MAX_Y-AXIS IS " + (this.height - 1) + "\n\n");
        // Core Part - Put ships
        Terminal.printLine("Player " + currentPlayer.getId() + " starts to putting ships :");
        while (shipCount < 10) {
            // 1. Set type is FLATTOP
            if (shipCount == 0) {
                //Terminal.printLine("Please input a coordinate for putting a FLattop :");
                type = ShipType.FLATTOP;
            }
            
            // 2. Set type is DREADNOUGHT
            if (shipCount < 3 && shipCount > 0) {
                //Terminal.printLine("Please input a coordinate for putting a DREADNOUGHT :");
                type = ShipType.DREADNOUGHT;
            }
            
            // 3. Set type is BATTLECRUISER
            if (shipCount < 6 && shipCount >= 3) {
                //Terminal.printLine("Please input a coordinate for putting a BATTLECRUISER :");
                type = ShipType.BATTLECRUISER;
            }
            
            // 4. Set type is MINESWEEPER
            if (shipCount < 10 && shipCount >= 6) {
              //Terminal.printLine("Please input a coordinate for putting a MINESWEEPER :");
                type = ShipType.MINESWEEPER;
            }
            
            while (true) {
                typing = Terminal.readLine();
                String input = typing.toUpperCase();
                
                if (DataTools.checkInitialFormat(input, width, height).equals("true")) {
                    String[] format = input.split(" ");
                    if (format.length == 2) {
                        coordinate = DataTools.convertToCoordinate(format[0]);
                        dir = DataTools.convertToDirection(format[1]);
                        isAdd = currentPlayer.getSea().addShip(type, dir, coordinate[0], coordinate[1]);
                        if (isAdd == true) {
                            Terminal.printLine("Player " + currentPlayer.getId() + " puts " + type);
                            shipCount++;
                            break;
                        } else {
                            Terminal.printLine("Error : this position is illegal, please try again !");
                        }
                    }
                    
                    if (format.length == 1) {
                        if (format[0].equals("RESET")) {
                            currentPlayer.getSea().initialPlayField(this.width, this.height);
                            shipCount = 0;
                            break;
                        }
                        
                        if (format[0].equals("QUIT")) {
                            Terminal.printLine("Game Quit");
                            System.exit(0);
                        }
                    }
                    
                } else {
                    Terminal.printLine(DataTools.checkInitialFormat(input, width, height));
                }
            }
            
            
        }
        if (shipCount == 10) {
            result = true;
        }
        return result;
    }
    
    private void dropBomb() throws IOException {
        int[] coordinate;
        int x;
        int y;
        Player enemy = player2;
        currentPlayer = player1;
        
        Terminal.printLine("Tips : The format of throwing Bomb has two formats \n"
                         + "       1. \"D4\" represents coordinate. \n"
                         + "       2. \"D4 PRINT\", if you want to see bombing's result. \n");    
        while (!enemy.getSea().allShipsSunk()) {
            Terminal.printLine("Player " + currentPlayer.getId() + " starts to throw bomb !");
            typing = Terminal.readLine();
            String input = typing.toUpperCase();
            if (DataTools.checkPlayFormat(input) == "true") {
                // input coordinate
                if(input.length() == 2) {
                    coordinate = DataTools.convertToCoordinate(input);
                    x = coordinate[0];
                    y = coordinate[1];
                    if (enemy.getSea().bomb[x + y * height].getValue().equals(".")) {
                        if (enemy.getSea().dropBomb(x, y)) {
                            Terminal.printLine("Treffer \n");
                        } else {
                            Terminal.printLine("Wasser \n");
                            if (currentPlayer.getId().equals(PlayerID.ONE)) {
                                currentPlayer = player2;
                                enemy = player1;
                            } else {
                                currentPlayer = player1;
                                enemy = player2;
                            }
                        }     
                    } else {
                        Terminal.printLine("Error : this position is bombed, please try again !");
                    }
                }
                
                // input instructions
                if(input.length() == 5) {
                    this.printBombField(enemy);
                }
                
                if(input.length() == 4) {
                    Terminal.printLine("Game Quit");
                    System.exit(0);
                }
            } else {
                Terminal.printLine(DataTools.checkPlayFormat(input));
            }
        }
    }

    /**
     * Print Bomb field, player can see which field he hits a ship or not
     */
    private void printBombField(Player player) {
        Player currentPlayer = player;
        String[] result = currentPlayer.getSea().toStringWithBombs().split("\n");
        for (int i = 0; i < result.length; i++) {
            Terminal.printLine(result[i]);
        }
    }
    
    
    
    /**
     * When this game over or all ship are sunk, the method should build a final report
     */
    private void outputResultTxt() {
        try {
            String path = this.getClass().getResource("").getPath() + "/TestOutput.txt";
            BufferedWriter out = new BufferedWriter(new FileWriter(path));
            String[] bombs1 = player1.getSea().toStringWithBombs().split("\n");
            String[] ships1 = player1.getSea().toStringWithShips().split("\n");
            String[] bombs2 = player2.getSea().toStringWithBombs().split("\n");
            String[] ships2 = player2.getSea().toStringWithShips().split("\n");
            int size = bombs1.length;
            out.write(">>>>>>>>>>>>>>>>> Output Game's Result <<<<<<<<<<<<<<<<<<<<<");
            out.newLine();
            out.write("Winner is Player " + currentPlayer.getId());
            out.newLine();
            out.write("--------------------------------------------------------");
            out.newLine();
            out.write("Player 1 :");
            out.newLine();
            for (int i = 0; i < size; i++) {
                out.write(ships1[i] + "        ***        " + bombs2[i]);
                out.newLine();
            }
            out.write("--------------------------------------------------------");
            out.newLine();
            out.write("Player 2 :");
            out.newLine();
            for (int i = 0; i < size; i++) {
                out.write(ships2[i] + "        ***        " + bombs1[i]);
                out.newLine();
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

