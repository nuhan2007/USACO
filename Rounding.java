import java.io.*;
import java.util.*;
public class Rounding {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        long t = Long.parseLong(br.readLine());
        while(t -- > 0) {
            char[] digits = br.readLine().toCharArray();
            int firstDig = digits[0] - '0';
            int len = digits.length;
            long ans = 0;
            
            for(int i=1; i<len-1; i++) {
                ans += stringOfOnes(i) * 5;
            }

            if(firstDig > 4) {
                ans += 5 * stringOfOnes(len - 1);
            }
            else if(firstDig == 4) {
                long toAdd = Math.max(0, Long.parseLong(new String(digits)) - 4 * stringOfOnes(len));
                ans += toAdd;
            }

            pw.println(ans);
            
        }
        pw.close();
    }

    static long stringOfOnes(int length) {
        long result = 0;
        for (int i = 0; i < length; i++) {
            result = result * 10 + 1;
        }
        return result;
    }
}
