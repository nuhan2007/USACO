import java.io.*;
import java.util.*;
public class OutOfSorts {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        Entry[] entries = new Entry[n];
        for(int i=0; i<n; i++) {
            entries[i] = new Entry(Integer.parseInt(br.readLine()), i);
        }
        Arrays.sort(entries);
        int ans = 1;
        for(int i=0; i<n; i++) {
            ans = Math.max(ans, 1 + entries[i].index - i);
        }
        pw.println(ans);
        pw.close();
        
    }
    static class Entry implements Comparable<Entry>{
        int val;
        int index;
        public Entry(int val, int index) {
            this.val = val;
            this.index = index;
        }
        public int compareTo(Entry other) {
            if(this.val == other.val) return this.index - other.index;
            return this.val - other.val;
        }
    }
}
