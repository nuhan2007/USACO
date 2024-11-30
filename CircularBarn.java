import java.io.*;
import java.util.*;
public class CircularBarn {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cbarn.in"));
        PrintWriter pw = new PrintWriter("cbarn.out");
        int n = Integer.parseInt(br.readLine());
        int[] rooms = new int[n];
        for(int i=0; i<n; i++) {
            rooms[i] = Integer.parseInt(br.readLine());
        }
        int best = (int)1e9;
        for(int i=0; i<n; i++) {
            int curSum = 0;
            for(int j=1; j<n; j++) {
                curSum += rooms[(i+j) % n] * j;
            }
            best = Math.min(best, curSum);
        }
        pw.println(best);
        pw.close();
    }    
}
