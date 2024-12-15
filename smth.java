
import java.io.*;
import java.util.*;
public class smth {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        long t = Long.parseLong(br.readLine());
        while(t -- > 0) {
            char[] digits = br.readLine().toCharArray();
            int firstDig = Character.getNumericValue(digits[0]);
            int len = digits.length;
            long ans = 0;
            for(int i=1; i<len-1; i++) {
                ans += (5 * Long.parseLong(stringOfOnes(i)));
            }

            if(firstDig > 4) {
                ans += 5 * Long.parseLong(stringOfOnes(len-1));
            }
            else if(firstDig == 4) {
                long toAdd = Math.max(0, (Long.parseLong(new String(digits)) - 4 * Integer.parseInt(stringOfOnes(len))));
                ans += toAdd;
            }

            pw.println(ans);
            
        }
        pw.close();
    }

    static String stringOfOnes(int x) {
        StringBuilder res = new StringBuilder();
        for(int i=0; i<x; i++) {
            res = res.append("1");
        }
        return new String(res);
    }
 }
