package battleship;

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
    public static final int BORDER_MIN = 7;
    public static final int BORDER_MAX = 10;
    
    Player player1;
    Player player2;
    Player currentPlayer;
    
    public int width;
    public int height;
    
    Scanner typing;
    
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
    public static boolean isBorderLegal(int width, int height) {
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
    public void playGame() {
        this.initialGame();
    }
    
    
    /**
     * Initial game... Player 1 and Player 2 add 10 ships 
     * (includes 1 FLATTOP, 2 DREADNOUGHT, 3 BATTLECRUISER, 4 MINESWEEPER) 
     */
    public void initialGame() {
        int i = 0; // count for player, "0" is player 1 and "1" is player 2 
        System.out.println("---------------- INITIAL GAME -----------------");
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
        System.out.println("---------------- END INITIAL GAME -----------------");
    }
    
    public boolean putShips() {
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
                if (this.checkFormat(input).equals("true")) {
                    String[] format = input.split(" ");
                    coordinate = this.convertToCoordinate(format[0]);
                    dir = this.convertToDirection(format[1]);
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
                    System.out.println(this.checkFormat(input));
                }
            }
            
            shipCount++;
        }
        if (shipCount == 10) {
            result = true;
        }
        return result;
    }
    
    /**
     * Check the format is if legal or not
     */
    public String checkFormat(String args) {
        
        String result = "true";
        String showMsg = "Error : ";
        String[] subStr = args.split(" ");
        /* 
         * Check the args' length should be 4 or 10 (with "reset")
         */
        if (args.length() != 4 && args.length() != 10) {
            showMsg += "expected: input length is 4 or 10 (with \"reset\") but was " + args.length();
            return showMsg;
        }
        
        /*
         * Check the subStr's length should be 2 or 3 (with "reset")
         */ 
        if (subStr.length != 2 && subStr.length !=3) {
            showMsg += "expected: 2 or 3 parameters but was " + subStr.length + "parameters";
            return showMsg;
        }
        
        /*
         *  Check subStr[0] (coordinate's value if legal or not)
         */
        char[] xy = new char[2];
        // Check the subStr[0]'s length ,should be 2;
        if (subStr[0].length() != 2) {
            showMsg += "expected: 2 values in coordinate but was " + subStr[0].length() + " values in it";
            return showMsg;
        } 
        xy = subStr[0].toCharArray();
        int x = xy[0] - 65; // 'A' to int is 65;
        int y = Integer.parseInt(Character.toString(xy[1]));
        if (x < 0 || x >= this.width || y < 0 && y >= this.height) {
            showMsg += "expected: coordinate (x,y) in battle field but was out of the field";
            return showMsg;
        }
        
        /*
         * Check subStr[1] ( direction's value if legal or not)
         */
        String dir = subStr[1];
        if (!dir.equals("N") && !dir.equals("S") && !dir.equals("O") && !dir.equals("W")) {
            showMsg += "expected: direction is N, O, S and W but was " + dir;
            return showMsg;
        }
        
        /*
         * Check subStr[2] ( reset instruction)
         */
        if (subStr.length == 3) {
            if (!subStr[2].equals("RESET")) {
                showMsg += "expected: reset instruction is RESET but was " + subStr[2];
                return showMsg;
            }
        }
        return result;
    }
    
    /**
     * Convert to coordinate
     */
    public int[] convertToCoordinate(String args) {
        int[] coordinate = new int[2];
        char[] xy = args.toCharArray();
        coordinate[0] = xy[0] - 65;
        coordinate[1] = Integer.parseInt(Character.toString(xy[1]));
        return coordinate;
    }
    
    /**
     * Convert to direction
     */
    public Direction convertToDirection(String args) {
        Direction dir = null;
        switch(args) {
        case "O" : dir = Direction.EAST; break;
        case "S" : dir = Direction.SOUTH; break;
        case "N" : dir = Direction.NORTH; break;
        case "W" : dir = Direction.WEST; break;
        default : break;
        }
        return dir;
    }
}
