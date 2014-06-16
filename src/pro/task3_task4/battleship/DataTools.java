package battleship;


/**
 * This class is responsible for processing input's data and convert from String to coordinate 
 * and direction
 * @author Le Wang
 * @version 1.0
 */
public final class DataTools {
    
    /** 
     * Private constructor to avoid object generation. 
     */
    private DataTools() {
        
    }
    
    /**
     *
     * Check the initial format is if legal or not
     * @param args input String
     * @param width battle field's width
     * @param height battle field's height
     * @return if occur Error , return error message
     */
    public static String checkInitialFormat(String args, int width, int height) {
        String[] subStr = args.split(" ");
        String showMsg = "Error : ";
        /* 
         * Check the args' length should be 4 or 5 (with "reset")
         */
        if (args.length() != 4 && args.length() != 5) {
            showMsg += "expected: input length is 4 or 5 (with \"reset\") but was " + args.length();
            return showMsg;
        }
        
        /*
         * Check the subStr's length should be 2 or 1 (this is a instruction "reset" or "undo" or "quit")
         */ 
        if (subStr.length != 2 && subStr.length != 1) {
            showMsg += "expected: 1 or 2 parameters but was " + subStr.length + " parameters";
            return showMsg;
        }
        
        /*
         *  Check subStr[0] (coordinate's value if legal or not)
         */
        if (subStr.length == 2) {
            char[] xy = new char[2];
            // Check the subStr[0]'s length ,should be 2;
            if (subStr[0].length() != 2) {
                showMsg += "expected: 2 values in coordinate but was " + subStr[0].length() + " values in it";
                return showMsg;
            } 
            xy = subStr[0].toCharArray();
            int x = xy[0] - 65; // 'A' to int is 65;
            int y = Integer.parseInt(Character.toString(xy[1]));
            if (x < 0 || x >= width || y < 0 && y >= height) {
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
        }
        
        /*
         * Check subStr[0] ( reset and quit instruction)
         */
        if (subStr.length == 1) {
            if (!subStr[0].equals("RESET") && !subStr[0].equals("QUIT")) {
                showMsg += "expected: reset or quit instruction but was " + subStr[0];
                return showMsg;
            } 
        }
        return "true";
    }

    /**
     * Check the throw bomb's format is if legal or not
     * @param args input String
     * @return if occur Error , return error message
     */
    public static String checkPlayFormat(String args) {
        String str = args;
        String showMsg = "Error : ";
        /* 
         * Check the args' length should be 2 or 5 ("print") or 4 ("quit")
         */
        if (args.length() != 2 && args.length() != 5 && args.length() != 4) {
            showMsg += "expected: input length is 2 or 2 or 5 (\"print\") or 4 (\"quit\") but was " + args.length();
            return showMsg;
        }
        
        if (str.length() == 5) {
            if (!str.equals("PRINT")) {
                showMsg += "expected: print instruction is PRINT but was " + str;
                return showMsg;
            }
        }
        
        if (str.length() == 4) {
            if (!str.equals("QUIT")) {
                showMsg += "expected: print instruction is PRINT but was " + str;
                return showMsg;
            }
        }   
        return "true";
    }

    /**
     * Convert to coordinate
     * @param args input coordinate's String
     * @return convert to integer
     */
    public static int[] convertToCoordinate(String args) {
        int[] coordinate = new int[2];
        char[] xy = args.toCharArray();
        coordinate[0] = xy[0] - 65;
        coordinate[1] = Integer.parseInt(Character.toString(xy[1]));
        return coordinate;
    }
    
    /**
     * Convert to direction
     * @param args input direction's String
     * @return convert to Direction
     */
    public static Direction convertToDirection(String args) {
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
