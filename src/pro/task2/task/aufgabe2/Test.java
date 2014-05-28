package task.aufgabe2;

public class Test {
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
