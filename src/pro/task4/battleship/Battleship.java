package battleship;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import battleship.Player.PlayerID;

/**
 * The class represents a Battleship
 * @author Le Wang
 * @version 1.0
 */
public class Battleship {
    
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
    
    private Scanner typing;
    
    /**
     * Battleship's Constructor
     * @param width sea's width
     * @param height sea's height
     */
    public Battleship(int width, int height) {
        if(Battleship.isBorderLegal(width, height)) {
            this.width = width;
            this.height = height;
            player1 = new Player(PlayerID.ONE, new Sea(width, height));
            player2 = new Player(PlayerID.TWO, new Sea(width, height));
        } else {
            System.out.println("Sorry, Values are illegal..should be in interval [7,10]!");
            System.out.println("Starting Game Failed ! :(");
            System.out.println("Please start game again !");
            System.exit(1);
        }
    }
    
    /**
     * Main - Method
     * @param args commander line arguments for setting width and height
     */
    public static void main(String[] args) {
        Battleship battelship = new Battleship(10, 10);
        battelship.playGame();
        
    }
    
    
    /**
     * This method is responsible for checking the border' value if legal or not
     * @param width border's width
     * @param height border's height
     * @return true or false
     */
    private static boolean isBorderLegal(int width, int height) {
        if (width <= BORDER_MAX && width >= BORDER_MIN 
            && height >= BORDER_MIN && height <= BORDER_MAX) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Starting Game, build game's menu and listening player enter instruction
     */
    private void playGame() {
        System.out.println("---------------- INITIAL GAME -----------------");
        this.initialGame();
        System.out.println("---------------- END INITIAL GAME -----------------");
        
        System.out.println("\n---------------- START PLAY GAME -----------------");
        this.dropBomb();
        System.out.println("---------------- END PLAY GAME -----------------");
        
        System.out.println("\n---------------- OUTPUT RESULT -----------------");
        System.out.println("Winner is Player " + currentPlayer.getId());
        System.out.println("\n---------------- PLAYER ONE -----------------");
        this.printBombField(player1);
        System.out.println("\n---------------- PLAYER TWO -----------------");
        this.printBombField(player2);
        
        System.out.println("GAME OVER \n  A output Text file is builded !");
        this.outputResultTxt();
    }
    
    
    /**
     * Initial game... Player 1 and Player 2 add 10 ships 
     * (includes 1 FLATTOP, 2 DREADNOUGHT, 3 BATTLECRUISER, 4 MINESWEEPER) 
     */
    private void initialGame() {
        int i = 0; // count for player, "0" is player 1 and "1" is player 2 
        while (i < 2) {
            // Show which player puts ships
            if (i == 0) {
                currentPlayer = player1;
                if(this.putShips()) {
                    System.out.println("\nPlayer 1 has completed the putting ships \n"
                                     + "*******************************************\n");
                    i++;
                }   
            } else {
                currentPlayer = player2;
                if(this.putShips()) {
                    System.out.println("\nPlayer 2 has completed the putting ships \n"
                                     + "*******************************************\n");
                    i++;
                }
            }
            
        }
    }
    
    /**
     * Put ships 
     * @return
     */
    private boolean putShips() {
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
        System.out.println("Player " + currentPlayer.getId() + " starts to putting ships :");
        reset: while (shipCount < 10) {
            // 1. Set type is FLATTOP
            if (shipCount == 0) {
                //System.out.println("Please input a coordinate for putting a FLattop :");
                type = ShipType.FLATTOP;
            }
            
            // 2. Set type is DREADNOUGHT
            if (shipCount < 3 && shipCount > 0) {
                //System.out.println("Please input a coordinate for putting a DREADNOUGHT :");
                type = ShipType.DREADNOUGHT;
            }
            
            // 3. Set type is BATTLECRUISER
            if (shipCount < 6 && shipCount >= 3) {
                //System.out.println("Please input a coordinate for putting a BATTLECRUISER :");
                type = ShipType.BATTLECRUISER;
            }
            
            // 4. Set type is MINESWEEPER
            if (shipCount < 10 && shipCount >= 6) {
              //System.out.println("Please input a coordinate for putting a MINESWEEPER :");
                type = ShipType.MINESWEEPER;
            }
            
            while (true) {
                typing = new Scanner(System.in);
                String input = typing.nextLine().toUpperCase();
                if (DataTools.checkInitialFormat(input, width, height).equals("true")) {
                    String[] format = input.split(" ");
                    coordinate = DataTools.convertToCoordinate(format[0]);
                    dir = DataTools.convertToDirection(format[1]);
                    if (format.length == 3) {
                        currentPlayer.getSea().initialPlayField(this.width, this.height);;
                        shipCount = 0;
                        break reset;
                    }
                    isAdd = currentPlayer.getSea().addShip(type, dir, coordinate[0], coordinate[1]);
                    if (isAdd == true) {
                        System.out.println("Player " + currentPlayer.getId() + " puts " + type);
                        break;
                    } else {
                        System.out.println("Error : this position is illegal, please try again !");
                    }
                } else {
                    System.out.println(DataTools.checkInitialFormat(input, width, height));
                }
            }
            
            shipCount++;
        }
        if (shipCount == 10) {
            result = true;
        }
        return result;
    }
    
    private void dropBomb() {
        int[] coordinate;
        int x;
        int y;
        Player enemy = player2;
        currentPlayer = player1;
        
        System.out.println("Tips : The format of throwing Bomb has two formats \n"
                         + "       1. \"D4\" represents coordinate. \n"
                         + "       2. \"D4 PRINT\", if you want to see bombing's result. \n");    
        while (!enemy.getSea().allShipsSunk(currentPlayer)) {
            System.out.println("Player " + currentPlayer.getId() + " starts to throw bomb !");
            typing = new Scanner(System.in);
            String input = typing.nextLine().toUpperCase();
            if (DataTools.checkPlayFormat(input) == "true") {
                String[] format = input.split(" ");
                coordinate = DataTools.convertToCoordinate(format[0]);
                x = coordinate[0];
                y = coordinate[1];
                if (!currentPlayer.getSea().isBombed(x, y)) {
                    if (enemy.getSea().dropBomb(currentPlayer.getSea(), x, y)) {
                        System.out.println("Treffer \n");
                        if (format.length == 2) {
                            this.printBombField(currentPlayer);
                        } 
                    } else {
                        System.out.println("Wasser \n");
                        if (format.length == 2) {
                            this.printBombField(currentPlayer);
                        }
                        
                        if (currentPlayer.getId().equals(PlayerID.ONE)) {
                            currentPlayer = player2;
                            enemy = player1;
                        } else {
                            currentPlayer = player1;
                            enemy = player2;
                        }
                    }     
                } else {
                    System.out.println("Error : this position is bombed, please try again !");
                }
                
                
            } else {
                System.out.println(DataTools.checkPlayFormat(input));
            }
        }
    }

    /**
     * Print Bomb field, player can see which field he hits a ship or not
     */
    private void printBombField(Player player) {
        Player currentPlayer = player;
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                System.out.print(player.getSea().bomb[x + y * currentPlayer.getSea().getHeight()].getValue() + " ");
            }
            System.out.println();
        }
        System.out.println();
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
            for (int i = 0; i < size; i++) {
                out.write(ships1[i] + "        ***        " + bombs1[i]);
                out.newLine();
            }
            out.write("--------------------------------------------------------");
            out.newLine();
            out.write("Player 2 :");
            for (int i = 0; i < size; i++) {
                out.write(ships2[i] + "        ***        " + bombs2[i]);
                out.newLine();
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

