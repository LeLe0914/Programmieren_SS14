package task.aufgabe2;
/**
 * The class representation a bank account;
 * @author Le Wang
 *
 */
public class Account {
    
    private String owner;
    private String iban;
    private String password;
    private float accountStatus;
    
    public Account(String owner, String iban, String password, float accountStatus) {
        this.owner = owner;
        
        // Check the length of iban
        if (iban.length() < 23) {
            this.iban = iban;
        } else {
            throw new IllegalArgumentException("Error input format of iban !");
        }
        
        // Check the password is legal. password should build 4 numbers.(z.B. Sparkasse KA)
        if (this.isPasswordLegal(password)) {
            this.password = password;
        } else {
            throw new IllegalArgumentException("Error input format of Password !");
        }
        
        // Check the value is lager than 0;
        if (accountStatus > 0) {
            this.accountStatus = accountStatus; 
        } else {
            throw new IllegalArgumentException("Error accountStatus !");
        }
           
    }
    
    public void setPassword(String password) {
        if (this.isPasswordLegal(password)) {
            this.password = password;
        }
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public String getOwner() {
        return this.owner;
    }
    
    public String getiban() {
        return this.iban;
    }
    
    
    public float showStatus() {
        return this.accountStatus;
    }
    
    public void drawMoney(float money) {
        float tempStatus = this.accountStatus - money;
        if (tempStatus < 0) {
             throw new IllegalArgumentException("Not sufficient funds !");
        } else {
             this.accountStatus = tempStatus;
        }       
    }
    
    public void saveMoney(float money) {
       this.accountStatus = this.accountStatus + money;
    }
    
    public boolean isPasswordLegal(String password) {
        if (password.length() < 5) {
            for (int i = password.length(); --i >= 0;) {
                if (!Character.isDigit(password.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }      
    }
}
