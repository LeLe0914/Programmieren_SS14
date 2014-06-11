package battleship;

public class StringTest {
    
    public static void main (String args[]) {
        String str = "d4 w reset";
        String newStr = str.toUpperCase();
        String[] format = newStr.split(" ");
        int a = "Z".toCharArray()[0];
        int b = "B".toCharArray()[0];
        System.out.println(a + "  "+  b);
        char[] coordinate = format[0].toCharArray();
        System.out.println((int) (coordinate[0] - 65));
        System.out.println(str.length()); // 4 or 10
    }

}
