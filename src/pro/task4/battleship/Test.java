package battleship;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import battleship.Sea.Coordinate;

/**
 * Test class for testing game
 * @author Le Wang
 * @version 1.0
 */
public class Test {
    
    /**
     * Initial a play field
     */
    private Sea sea = new Sea(10, 10);
    private Scanner scPosition;
    private Scanner scPress;
    private String gameMode = null;
    
    /**
     * Main method for running the whole project
     * @param args a parameter
     */
    public static void main(String args[]) {
        Test test = new Test();
        test.playGame();  
    }
    
    /**
     * Initial game... add 10 ships (includes 1 FLATTOP, 2 DREADNOUGHT, 3 BATTLECRUISER
     * 4 MINESWEEPER) 
     * @return false or true
     */
    private boolean initialGame() {
        int[] count = {0, 0, 0, 0};
        int total = 0;
        int x;
        int y;
        Direction dir = null;
        while (total != 10) {
            // Random directions and position
            switch ((int) (Math.random() * 3)) {
            case 0: dir = Direction.EAST; break;
            case 1: dir = Direction.NORTH; break;
            case 2: dir = Direction.SOUTH; break;
            case 3: dir = Direction.WEST; break;
            default : break;
            }
            x = (int) (Math.random() * (this.sea.getWidth() - 1));
            y = (int) (Math.random() * (this.sea.getHeight() - 1));
            
            // Add one FLATTOP
            if (count[3] < 1) {
                if (this.sea.addShip(ShipType.FLATTOP, dir, x, y)) {
                    count[3] = count[3] + 1;
                }
            }
            
            // Add two DREADNOUGHT
            if (count[2] < 2) {
                if (this.sea.addShip(ShipType.DREADNOUGHT, dir, x, y)) {
                    count[2] = count[2] + 1;
                }
            }
            
            // Add three BATTLECRUISER
            if (count[1] < 3) {
                if (this.sea.addShip(ShipType.BATTLECRUISER, dir, x, y)) {
                    count[1] = count[1] + 1;
                }
            }
            
            if (count[0] < 4) {
                if (this.sea.addShip(ShipType.MINESWEEPER, dir, x, y)) {
                    count[0] = count[0] + 1;
                }
            }
            
            total = count[0] + count[1] + count[2] + count[3];
        }
        if (total == 10) {
            System.out.println("Game initial success :)");
            return true;
        } else {
            System.out.println("Game initial failed :(");
            return false;
        }
    }

    /**
     * Starting Game, build game's menu and listening player enter instruction
     */
    private void playGame() {
        System.out.println("Game initialing.......");
        this.initialGame(); 
        System.out.println("Game starting...........");
        System.out.println("> Select Game Mode :");
        System.out.println("  > 1. Normal Mode");
        System.out.println("  > 2. Intellligence Mode");
        System.out.println("  > 3. Exit Game");
        System.out.println("> Please chose mode :)");
        while (true) {
            this.scPress = new Scanner(System.in);
            String input = this.scPress.next();
           
            if (input.equals("1")) {
                this.normalMode();
            }
            else if (input.equals("2")) {
                this.intelligenceMode();
            }
            else if (input.equals("3")) {
                System.out.println("Game Exit !");
                break;
            }
            else {
                System.out.println("Illegal choose, please try again!");
            }
        }      
        System.exit(0);
    }
    
    /**
     * Player can input a coordinate, which he wants to drop a bomb in here
     * @return Coordinate
     */
    private Coordinate inputPosition() {
        int x = 0;
        int y = 0;
        String[] str;
        String input;
        while (true) {
            System.out.println("Please input a Coordinate for droping Bomb !");
            System.out.println("Enter position （wie 9,8) : ");
            scPosition = new Scanner(System.in);
            input = scPosition.next().toUpperCase();
            if (input.equals("Q")) {
                x = -1;
                y = -1;
                break;
            } else {
                str = input.split(",");
                try {
                    x = Integer.parseInt(str[0]);
                    y = Integer.parseInt(str[1]);
                    if (x >= 0 && x < sea.getWidth() && y >= 0 && y < sea.getHeight()) {
                        break;
                    } else {
                        System.out.println("Input value of x or y is illegal");
                        System.out.println("Please try again !");
                        System.out.println();
                    }   
                    
                } catch (NumberFormatException e) {
                    System.out.println("The format of position is illegal. ");
                    System.out.println("The right format should be \"3,8\"");
                    System.out.println();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("The format of position is illegal. ");
                    System.out.println("The right format should be \"3,8\"");
                    System.out.println();
                }
            }
        }
        return sea.new Coordinate(x, y);
    }
    
    /**
     * Print Bomb field, player can see which field he hits a ship or not
     */
    private void printBombField() {

        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                System.out.print(sea.bomb[x + y * sea.getHeight()].getValue() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    /**
     * This method is for intelligence mode, if a player input a coordinate, it can scan 3*3
     * area, if it detects a ship, it can record its coordinate for prepare dropping bomb
     * @param position center point
     * @return bombard suggestion
     */
    private String radar(Coordinate position) {
        String bombardSuggestion = "Position|";
        int x = position.getX();
        int y = position.getY();
        // Scan North-West if detect a ship , add its coordinate to bombardSuggestion
        if ((x - 1) >= 0 
            && (y - 1) >= 0 
            && !this.sea.seaPosition[(x - 1) + (y - 1) * this.sea.getWidth()].getValue().equals(".")) {
            bombardSuggestion += (x - 1) + "," + (y - 1) + "|";
        }
        
        // Scan North if detect a ship , add its coordinate to bombardSuggestion
        if ((y - 1) >= 0 && !this.sea.seaPosition[(x) + (y - 1) * this.sea.getWidth()].getValue().equals(".")) {
            bombardSuggestion += x + "," + (y - 1) + "|";
        }
        
        // Scan North-East if detect a ship , add its coordinate to bombardSuggestion
        if ((x + 1) < sea.getWidth() 
            && (y - 1) >= 0 
            && !this.sea.seaPosition[(x + 1) + (y - 1) * this.sea.getWidth()].getValue().equals(".")) {
            bombardSuggestion += (x + 1) + "," + (y - 1) + "|";
        }
        
        // Scan West if detect a ship , add its coordinate to bombardSuggestion
        if ((x - 1) >= 0 && !this.sea.seaPosition[(x - 1) + (y) * this.sea.getWidth()].getValue().equals(".")) {
            bombardSuggestion += (x - 1) + "," + y + "|";
        }
        
        // Scan Center if detect a ship , add its coordinate to bombardSuggestion
        if (!this.sea.seaPosition[(x) + (y) * this.sea.getWidth()].getValue().equals(".")) {
            bombardSuggestion += x + "," + y + "|";
        }
        
        // Scan East if detect a ship , add its coordinate to bombardSuggestion
        if ((x + 1) < sea.getWidth() 
            && !this.sea.seaPosition[(x + 1) + (y) * this.sea.getWidth()].getValue().equals(".")) {
            bombardSuggestion += (x + 1) + "," + y + "|";
        }
        
        // Scan South-West if detect a ship , add its coordinate to bombardSuggestion
        if ((x - 1) >= 0 
            && (y + 1) < sea.getHeight() 
            && !this.sea.seaPosition[(x - 1) + (y + 1) * this.sea.getWidth()].getValue().equals(".")) {
            bombardSuggestion += (x - 1) + "," + (y + 1) + "|";
        }
        
        // Scan South if detect a ship , add its coordinate to bombardSuggestion
        if ((y + 1) < sea.getHeight() 
            && !this.sea.seaPosition[(x) + (y + 1) * this.sea.getWidth()].getValue().equals(".")) {
            bombardSuggestion += x + "," + (y + 1) + "|";
        }
        
        // Scan North if detect a ship , add its coordinate to bombardSuggestion
        if ((x + 1) < sea.getWidth() 
            && (y + 1) < sea.getHeight() 
            && !this.sea.seaPosition[(x + 1) + (y + 1) * this.sea.getWidth()].getValue().equals(".")) {
            bombardSuggestion += (x + 1) + "," + (y + 1) + "|";
        }
        return bombardSuggestion;
    }
    
    /**
     * Normal Mode, without radar.... drop bomb one by one
     */
    private void normalMode() {
        boolean isDropBomb;
        int count = 0;
        gameMode = "Normal Mode";
        System.out.println("----------- NORAML MODE -----------");
        System.out.println("Tip: Press \"Q\" for quit Game");
        while (true) {
            Coordinate dropBomb = this.inputPosition();
            int x = dropBomb.getX();
            int y = dropBomb.getY();
            if (x != -1 && y != -1) {
                if (sea.bomb[x + y * sea.getWidth()].getValue().equals(".")) {
                    isDropBomb = sea.dropBomb(x, y);
                    count++;
                    this.showInfo(count, isDropBomb, x, y);
                    this.printBombField();
                  
                }
                if (this.sea.allShipsSunk()) {
                    System.out.println("All ships are sunk ! Good game!");
                    System.out.println("A output Text file is builded !");
                    this.outputResultTxt();
                    break;
                }
            } else {
                if (this.sea.allShipsSunk()) {
                    System.out.println("All ships are sunk ! Good game!");
                    System.out.println("A output Text file is builded !");
                    this.outputResultTxt();
                    break;
                }
                System.out.println("Game Over !!!");
                System.out.println("A output Text file is builded !");
                this.outputResultTxt();
                break;
            }
        }  
        
    }

    /**
     * Intelligence Mode.... with radar. Player can easy win game with radar's help
     */
    private void intelligenceMode() {
        int count = 0; 
        gameMode = "Intelligence Mode";
        System.out.println("----------- INTELLIGENCE MODE -----------");
        System.out.println("Tip: Press \"Q\" for quit Game");
        while (true) {
            Coordinate scanPosition = this.inputPosition();
            if (scanPosition.getX() != -1 && scanPosition.getY() != -1) {
                // Open the radar and scanning the center with scan radius 1
                String bombardSuggestion = this.radar(scanPosition);
                String[] detectPosition = bombardSuggestion.split("\\|");
                int x = 0;
                int y = 0;
                boolean isDropBomb;
                if (detectPosition.length == 1) {
                    System.out.println("Radar does not detect any ships in this distric !");
                    System.out.println();
                } else {
                    System.out.println("Radar detects ships in this distric as follow coordinate !");
                    System.out.println(bombardSuggestion);
                    for (int i = 1; i < detectPosition.length; i++) {
                        String[] bombPosition = detectPosition[i].split(",");
                        x = Integer.parseInt(bombPosition[0]);
                        y = Integer.parseInt(bombPosition[1]);
                        if (sea.bomb[x + y * sea.getWidth()].getValue().equals(".")) {
                            isDropBomb = sea.dropBomb(x, y);
                            count++;
                            this.showInfo(count, isDropBomb, x, y);
                        } 
                    }
                    this.printBombField();
                } 
                if (this.sea.allShipsSunk()) {
                    System.out.println("All ships are sunk ! Good game!");
                    System.out.println("A output Text file is builded !");
                    this.outputResultTxt();
                    break;
                }     
            } else {
                if (this.sea.allShipsSunk()) {
                    System.out.println("All ships are sunk ! Good game!");
                    System.out.println("A output Text file is builded !");
                    this.outputResultTxt();
                    break;
                }
                System.out.println("Game Over !!!");
                System.out.println("A output Text file is builded !");
                this.outputResultTxt();
                break;
            }
        }
    }
    
    /**
     * Show this point's information , if a bomb is dropping
     * @param count the count of bomb   
     * @param isDropBomb strike or not
     * @param x x-axis
     * @param y y-axis
     */
    private void showInfo(int count, boolean isDropBomb, int x, int y) {
        String str = "Number " + count 
                + " Bomb; Position [" + x + ", " + y + "]";
        if (isDropBomb == true) {
            str = str + "; This Bomb does hits a ship !";
            System.out.println(str);
            
        } else {
            str = str + "; This Bomb does not hit a ship !";
            System.out.println(str);
            
        }  
        
    }
    
    /**
     * When this game over or all ship are sunk, the method should build a final report
     */
    private void outputResultTxt() {
        try {
            String path = this.getClass().getResource("").getPath() + "/TestOutput.txt";
            BufferedWriter out = new BufferedWriter(new FileWriter(path));
            String[] bombs = this.sea.toStringWithBombs().split("\n");
            String[] ships = this.sea.toStringWithShips().split("\n");
            int size = ships.length;
            out.write(">>>>>>>>>>>>>>>>> Output Game's Result <<<<<<<<<<<<<<<<<<<<<");
            out.newLine();
            out.write("GAME MODE : " + this.gameMode);
            out.newLine();
            if (sea.allShipsSunk()) {
                out.write("Congratulations !! All ship are sunk !!");
                out.newLine();
            }
            for (int i = 0; i < size; i++) {
                out.write(ships[i] + "        ***        " + bombs[i]);
                out.newLine();
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
