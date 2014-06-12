package task.battleship;

import battleship.Sea;

/**
 * The class represents 
 * @author Le Wang
 * @version 1.0
 */
public class Player {
    
    private Sea sea;
    private PlayerID id;
    
    /**
     * Constructor of Player
     * @param id play's id
     * @param sea battle field
     */
    public Player(PlayerID id, Sea sea) {
        this.setId(id);
        this.setSea(sea);
    }
    
    /**
     * Get-Method for sea
     * @return sea
     */
    public Sea getSea() {
        return sea;
    }
    
    /**
     * Set-Method for sea
     * @param sea battle field
     */
    public void setSea(Sea sea) {
        this.sea = sea;
    }

    /**
     * Get-Method for player's id
     * @return player's id
     */
    public PlayerID getId() {
        return id;
    }

    /**
     * Set-Method for player's id
     * @param id player's id
     */
    public void setId(PlayerID id) {
        this.id = id;
    }

    /**
     * The enum PlayID
     * @author Le Wang
     * @version 1.0
     */
    public enum PlayerID {
        /**
         * Player1 is PlayerID.ONE
         */
        ONE,
        
        /**
         * Player 2 is PlayerID.TWO
         */
        TWO;
    }

}
