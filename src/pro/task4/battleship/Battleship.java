package battleship;

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
    
    /**
     * Battleship's Constructor
     * @param width sea's width
     * @param height sea's height
     */
    public Battleship(int width, int height) {
        if(Battleship.isBorderLegal(width, height)) {
            
        } else {
            System.out.println("Sorry, Values are illegal..should be in interval [7,10]!");
        }
    }
    
    /**
     * Main - Method
     * @param args commander line arguments for setting width and height
     */
    public static void main(String[] args) {
        
    }
    
    /**
     * This method is responsible for checking the border' value if legal or not
     * @param width border's width
     * @param height border's height
     * @return true or false
     */
    public static boolean isBorderLegal(int width, int height) {
        if (width < 11 && width > 6 && height > 6 && height < 11) {
            return true;
        } else {
            return false;
        }
    }

}
