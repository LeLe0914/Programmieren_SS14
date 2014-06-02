package battleship;

/**
 * A class model a play field (Sea) and include logic of this play
 * @author Le Wang
 * @version 1.0
 */
public class Sea {
    /**
     * Limit the sea size
     */
    private static final int MAX_WIDTH = 100;
    private static final int MAX_HEIGHT = 100;
    
    /**
     * coordinate system is for ships
     */
    public Coordinate[] seaPosition;
    
    /**
     * another coordinate system is for bombs
     */
    public Coordinate[] bomb;
    
    /**
     * The play field's width and height
     */
    private int width;
    private int height;
    
    
    /**
     * The constructor builds new play field (width * height)... 
     * @param width from east to west
     * @param height from north to south
     */
    public Sea(int width, int height) {
        if (width > 0 && height > 0) {
            if (width <= MAX_WIDTH && height <= MAX_HEIGHT) {
                this.width = width;
                this.height = height;
                initialPlayField(width, height);
            } else {
                System.out.println("The area of field is too large !"
                        + "Width < 100 and Height < 100");
            } 
        } else {
            System.out.println("Width or Height should be larger than 0 !! :)");
        }  
    }
    
    /**
     * Initial a play field
     * @param width field's width
     * @param height field's height
     */
    public void initialPlayField(int width, int height) {
        seaPosition = new Coordinate[width * height];
        bomb = new Coordinate[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                seaPosition[x + y * height] = new Coordinate(x, y);
                bomb[x + y * height] = new Coordinate(x, y);
                seaPosition[x + y * height].setValue("."); 
                bomb[x + y * height].setValue(".");
            }
        }       
    }
    
    /**
     * The method can add a described ship
     * @param type type of ship
     * @param dir the ship's direction
     * @param x width
     * @param y height
     * @return false or true
     */
    public boolean addShip(ShipType type, Direction dir, int x, int y) {
        // Step 1 check whether the coordinate is legal or not
        boolean isAvailable = false;
        if (x >= 0 && x < width && y >= 0 && y < height) { 
            /* Step 2 check whether the place is available or not
             * Step 2.1 check whether the coordinate of the ship is out of boundary or not
             * Step 2.2 check whether the place of the ship takes up by other ship
             */
            int count;
            switch (dir) {
            case NORTH : if ((y - type.getLength()) >= -1) {
                count = 0;
                for (int i = 0; i < type.getLength(); i++) {
                    if (seaPosition[x + (y - i) * height].getValue().equals(".")) {
                        count++;
                    }
                }
                // Step 3 add a ship
                if (count == type.getLength()) {
                    for (int i = 0; i < type.getLength(); i++) {
                        seaPosition[x + (y - i) * height].setValue(Integer.toString(type.getLength()));
                    }
                    isAvailable = true;
                } 
            } break;
            
            case WEST :  if ((x - type.getLength() >= -1)) {
                count = 0;
                for (int i = 0; i < type.getLength(); i++) {
                    if (seaPosition[(x - i) + y * height].getValue().equals(".")) {
                        count++;
                    }
                }
                // Step 3 add a ship
                if (count == type.getLength()) {
                    for (int i = 0; i < type.getLength(); i++) {
                        seaPosition[(x - i) + y * height].setValue(Integer.toString(type.getLength()));
                    }
                    isAvailable = true;
                } 
            } break;
            
            case SOUTH : if ((y + type.getLength()) <= height) {
                count = 0;
                for (int i = 0; i < type.getLength(); i++) {
                    if (seaPosition[x + (y + i) * height].getValue().equals(".")) {
                        count++;
                    }
                }
                // Step 3 add a ship
                if (count == type.getLength()) {
                    for (int i = 0; i < type.getLength(); i++) {
                        seaPosition[x + (y + i) * height].setValue(Integer.toString(type.getLength()));
                    }
                    isAvailable = true;
                } 
            } break;
               
            case EAST :  if ((x + type.getLength()) <= width) {
                count = 0;
                for (int i = 0; i < type.getLength(); i++) {
                    if (seaPosition[(x + i) + y * height].getValue().equals(".")) {
                        count++;
                    }
                }
                // Step 3 add a ship
                if (count == type.getLength()) {
                    for (int i = 0; i < type.getLength(); i++) {
                        seaPosition[(x + i) + y * height].setValue(Integer.toString(type.getLength()));
                    }
                    isAvailable = true;
                } 
            } break; 
            
            default : break;
        }
        }
       return isAvailable; 
    }
    
    /**
     * The method implement a throw bomb action
     * @param x coordinate x-axis
     * @param y coordinate y-axis
     * @return false or true
     */
    public boolean dropBomb(int x, int y) {
        if (seaPosition[x + y * height].getValue().equals(".")) {
            bomb[x + y * height].setValue("O");
            return false;
        } else {
            bomb[x + y * height].setValue("X");
            return true;
        }   
    }
    
    /**
     * If all ships are sunk, the method returns true, if not, returns false
     * @return false or true;
     */
    public boolean allShipsSunk() {
        int count1 = 0; // count how many ships' positions in sea field
        int count2 = 0; // count how many times bomb hits the ship;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (!seaPosition[x + y * height].getValue().equals(".")) {
                    count1++;
                    if (bomb[x + y * height].getValue().equals("X")) {
                        count2++;
                    }
                }
            }
        }
        
        if (count1 == count2) {
            return true;
        } else {
            return false;
        }
    }
        
    
    /**
     * toString method()
     * @return stringWithShips
     */
    public String toStringWithShips() {
        String stringWithShips = null;
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if ((x + y * this.height) == 0) {
                    stringWithShips = seaPosition[x + y * this.height].getValue() + " ";
                } else {
                    stringWithShips += seaPosition[x + y * this.height].getValue() + " ";
                }
            }
            stringWithShips += ",";
        }
        return stringWithShips;
        
    }
    
    /**
     * toString method()    
     * @return stringWithBombs
     */
    public String toStringWithBombs() {
        String stringWithBombs = null;
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                if ((x + y * this.height) == 0) {
                    stringWithBombs = bomb[x + y * this.height].getValue() + " ";
                } else {
                    stringWithBombs += bomb[x + y * this.height].getValue() + " ";
                }
            }
            stringWithBombs += ",";
        }
        return stringWithBombs;
    }
    /**
     * Get method for width
     * @return width
     */
    public int getWidth() {
        return this.width;
    }
    
    /**
     * Get method for height
     * @return height
     */
    public int getHeight() {
        return this.height;
    }
    
   /**
    * This class represent a coordinate system.
    * Save end of ship's coordinate
    * @author Le Wang
    *
    */
    public class Coordinate {
        /**
         * x-axis and y-axis
         */
        private int x;
        private int y;
        
        private String value;
        
        /**
         * Initial the coordinate
         * @param x x-axis
         * @param y y-axis
         */
        public Coordinate(int x,  int y) {
            this.setX(x);
            this.setY(y);
        }
        
        private void setValue(String c) {
            this.value = c;
        }
        
        /**
         * Get method for getting this position's value
         * @return value
         */
        public String getValue() {
            return this.value;
        }

        /**
         * Get method for getting x-axis value
         * @return x
         */
        public int getX() {
            return x;
        }

        /**
         * Set method for setting x-axis value
         * @param x x-axis
         */
        public void setX(int x) {
            this.x = x;
        }

        /**
         * Get method for getting y-axis value
         * @return y
         */
        public int getY() {
            return y;
        }

        /**
         * Set method for setting y-axis value
         * @param y y-axis
         */
        public void setY(int y) {
            this.y = y;
        }
        
    }
}
