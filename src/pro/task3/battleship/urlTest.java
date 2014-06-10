package battleship;

import java.io.File;

public class urlTest {
    
    public static void main(String args[]) {
        urlTest n = new urlTest();
        n.URL();
    }
    
    public void URL() {
       java.net.URL url = getClass().getResource("TestOutput");
       File f  = new File("TestOutput");
       System.out.println(url);
       System.out.println(f.getPath());
       System.out.println(f.getAbsolutePath());
       System.out.println(getClass().getResource("").getPath());
    }

}
