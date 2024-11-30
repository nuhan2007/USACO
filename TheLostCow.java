import java.io.*;
import java.util.*;
public class TheLostCow {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("lostcow.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lostcow.out")));
        int x = scan.nextInt();
        int y = scan.nextInt();
        int toMove = 1;
        int dir = 1;
        int dist = 0;
        int prev = x;
        int loc = x;
        while(x != y) {
            loc = x + toMove * dir;
            dist += Math.abs(prev - loc);
            toMove *= 2;

            if(loc > y && x < y) {
                dist -= (loc -y);
                break;
            }
            else if(loc < y && x > y) {
                dist -= (y-loc);
                break;
            }

            dir *= -1;
            prev = loc;
            
        }
        pw.println(dist);
        pw.close();
    }    
}
