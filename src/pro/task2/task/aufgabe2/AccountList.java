package task.aufgabe2;

public class AccountList {
    Account[] accountList = new Account[5];
    
    public AccountList() {
        for (int i = 0; i < 5; i++) {
            accountList[i] = new Account("Max" + i,
                                         "DE6821050170001234567" + i,
                                         "123" + i, 200 + i);
        }
    }
    
            
    /*
    accountList[0] = new Account("Max1",
                                 "DE68210501700012345678",
                                 "1234",100);
    accountList[1] = new Account("Max2",
                                 "DE68210501700014345678",
                                 "1234",150);
    accountList[2] = new Account("Max3",
                                 "DE68210504500014345678",
                                 "1234",200);
    accountList[3] = new Account("Max4",
                                 "DE67219501700014345678",
                                 "1234",250);
    accountList[4] = new Account("Max5",
                                 "DE68210501700014345876",
                                 "1234",300);
    */
   
}
