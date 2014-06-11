package battleship;

public class DataTools {
    
    /**
     * Check the initial format is if legal or not
     */
    public static String checkInitialFormat(String args, int width, int height) {
        String[] subStr = args.split(" ");
        String showMsg = "Error : ";
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
            showMsg += "expected: 2 or 3 parameters but was " + subStr.length + " parameters";
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
        
        /*
         * Check subStr[2] ( reset instruction)
         */
        if (subStr.length == 3) {
            if (!subStr[2].equals("RESET")) {
                showMsg += "expected: reset instruction is RESET but was " + subStr[2];
                return showMsg;
            }
        }
        return "true";
    }

    /**
     * Check the throw bomb's format is if legal or not
     */
    public static String checkPlayFormat(String args) {
        String[] subStr = args.split(" ");
        String showMsg = "Error : ";
        /* 
         * Check the args' length should be 2 or 8 (with "print")
         */
        if (args.length() != 2 && args.length() != 8) {
            showMsg += "expected: input length is 2 or 8 (with \"reset\") but was " + args.length();
            return showMsg;
        }
        
        /*
         * Check the subStr's length should be 1 or 2 (with "print")
         */ 
        if (subStr.length != 1 && subStr.length !=2) {
            showMsg += "expected: 1 or 2 parameters but was " + subStr.length + " parameters";
            return showMsg;
        }

        /*
         * Check subStr[2] ( print instruction)
         */
        if (subStr.length == 2) {
            if (!subStr[1].equals("PRINT")) {
                showMsg += "expected: print instruction is PRINT but was " + subStr[2];
                return showMsg;
            }
        }   
        return "true";
    }

    /**
     * Convert to coordinate
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
