package task.aufgabeA;
public enum ShipType {
    /**
     * The Minesweeper ship-type
     */
    MINESWEEPER {
        @Override
        public int getLength() {
            return 1;
        }
    }, 
    
    /**
     * The Battlecruise ship-type
     */
    BATTLECRUISER {
        @Override
        public int getLength() {
            return 2;
        }
    }, 
    
    /**
     * The Dreadnought ship-type
     */
    DREADNOUGHT {
        @Override
        public int getLength() {
            return 3;
        }
    }, 
    
    /**
     * The Flattop ship-type
     */
    FLATTOP {
        @Override
        public int getLength() {
            return 4;
        }
    };
    
    /**
     * Returns the ship-length of each ship-type
     * @return the length of the ship
     */
    public abstract int getLength();   
}
