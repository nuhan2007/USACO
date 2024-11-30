import java.io.*;
import java.util.*;
public class Censoring {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("censor.in"));
        PrintWriter pw = new PrintWriter("censor.out");
        String s = br.readLine();
        String toRemove = br.readLine();
        StringBuilder ans = new StringBuilder();
        for(char c : s.toCharArray()) {
            ans.append(c);
            if(ans.length() >= toRemove.length() && ans.substring(ans.length() - toRemove.length()).equals(toRemove)) {
                ans.replace(ans.length() - toRemove.length(), ans.length(), "");
            }
         }
        pw.println(ans);
        pw.close();
    }    
}
