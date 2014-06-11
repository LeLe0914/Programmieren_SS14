package battleship;

public class Player {
    
    private Sea sea;
    private PlayerID id;
    
    public Player(PlayerID id, Sea sea) {
        this.setId(id);
        this.setSea(sea);
    }
    
    
            
    public Sea getSea() {
        return sea;
    }



    public void setSea(Sea sea) {
        this.sea = sea;
    }



    public PlayerID getId() {
        return id;
    }



    public void setId(PlayerID id) {
        this.id = id;
    }



    public enum PlayerID {
        ONE, TWO ;
    }

}
