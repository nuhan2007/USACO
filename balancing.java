import java.util.*;
import java.io.*;
public class balancing {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        Cow[] cows = new Cow[n];
        HashSet<Integer> vFences = new HashSet<Integer>();
        HashSet<Integer> hFences = new HashSet<Integer>();
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            cows[i] = new Cow(x,y);
            vFences.add(y+1);
            hFences.add(x+1);
        }
        
        int minMax = b;
        for(int v : vFences) {
            for(int h : hFences) {
                int topLeft = 0;
                int topRight = 0;
                int bottomLeft = 0;
                int bottomRight = 0;
                for(Cow c : cows) {
                    if(c.x > h) {
                        if(c.y > v) {
                            topRight++;
                        } else {
                            bottomRight++;
                        }
                    }
                    else {
                        if(c.y > v) {
                            topLeft++;
                        } else {
                            bottomLeft++;
                        }
                    }
                }
                int curMin = Collections.max(Arrays.asList(topLeft, topRight, bottomLeft, bottomRight));
                minMax = Math.min(curMin, minMax);
            }
        }
        
        br.close();
        pw.println(minMax);
        pw.close();
    }
    
    static class Cow {
        int x;
        int y;
        public Cow(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}