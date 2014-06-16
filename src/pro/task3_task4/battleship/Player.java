package battleship;


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
     * @param one play's id
     * @param sea battle field
     */
    public Player(PlayerID one, Sea sea) {
        this.setId(one);
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

}
