package task.aufgabe2;

public class Transfer {
    
    Account remitter;
    Account receiver;
    AccountList al = new AccountList(); // Simulate bank-database for test transfer function
    float money;
    
    public Transfer(Account remitter, Account receiver, float money) {
        this.remitter = remitter;
        this.receiver = receiver;
        this.money = money;
    }
    
    public void excute() {
        if (checkAccount(remitter, receiver)) {
            remitter.drawMoney(money);
            receiver.saveMoney(money);
            // Print receipt
            System.out.println("----------Tranfer Success----------");
            System.out.println(" Receiver        : " + receiver.getOwner());
            System.out.println(" Receiver's iban : " + receiver.getiban());
            System.out.printf(" Sum of Money    : € %.2f \n", money);
            System.out.println("***********************************");
            System.out.println(" Remitter        : " + remitter.getOwner());
            System.out.println(" Remitter's iban : " + remitter.getiban());
            System.out.printf(" Status          : € %.2f \n", remitter.showStatus());
            
        } else {
            System.out.println("Transfer is failed !");
        }
    }
    
    public boolean checkAccount(Account remitter, Account receiver) {
        // check the remitter and receiver exist in Bank-Database or not.
        if (isExistAccount(remitter) && isExistAccount(receiver)) {
            // check the remitter's password right or not
            if (isCorrectPassword(remitter)) {
                return true;
            } else {
                return false;
            }       
        }
        return false;
    }
    
    public boolean isExistAccount(Account a) {
        
        for (int i = 0; i < al.accountList.length; i++) {
            if (al.accountList[i].getiban().equals(a.getiban()) 
                && al.accountList[i].getOwner().equals(a.getOwner())) {
                return true;
            }
        }
        System.out.println("Sorry, the " + a.getOwner() + " is not exist !");
        return false;
    }
    
    public boolean isCorrectPassword(Account a) {
        Account tempAccount = null;
        for (int i = 0; i < al.accountList.length; i++) {
            if (al.accountList[i].getiban().equals(a.getiban())
               && al.accountList[i].getOwner().equals(a.getOwner())) {
                tempAccount = al.accountList[i];
            }
        }
        
        if (tempAccount.getPassword().equals(a.getPassword())) {
            return true;
        }
        System.out.println("Your password is error!!");
        return false;
    }
    
public static void main(String args[]) {
        
        Account remitter;
        Account receiver;
        Transfer transfer;
        // Test1 : remitter is not exist in Bank-Database
        remitter = new Account("LeLe",
                               "DE68210501700012345678",
                               "1234", 100);
        receiver = new Account("Max0",
                               "DE68210501700012345670",
                               "1230", 100);
        transfer = new Transfer(remitter, receiver, 50);
        System.out.println("Test1 : remitter is not exist in Bank-Database");
        transfer.excute();
        System.out.println();
        
        // Test2 : remitter is exist but input error password;
        remitter = new Account("Max1",
                               "DE68210501700012345671",
                               "1238", 101);
        receiver = new Account("Max0",
                               "DE68210501700012345670",
                               "1230", 100);
        transfer = new Transfer(remitter, receiver, 50);
        System.out.println("Test2 : remitter is exist but input error password");
        transfer.excute();
        System.out.println();
        
        // Test3 : transfer success
        remitter = new Account("Max1",
                               "DE68210501700012345671",
                               "1231", 101);
        receiver = new Account("Max0",
                               "DE68210501700012345670",
                               "1230", 100);
        transfer = new Transfer(remitter, receiver, 50);
        System.out.println("Test3 : transfer success");
        transfer.excute();
        System.out.println();
    }

}
