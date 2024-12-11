import java.io.*;
import java.util.*;
public class Revegetation {

    static List<Integer>[] adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        adj = new ArrayList[m];
        for(int i=0; i<m; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            adj[a].add(b);
            adj[b].add(a);
        }

        int[] ans = new int[n];
        Arrays.fill(ans, 0);
        ans[0] = 1;
        for(int i=0; i<m; i++) {
            for(int edge : adj[i]) {
                if(ans[edge] <= 3 && ans[edge] == ans[i]) {
                    ans[Math.max(edge, i)] = ans[Math.min(edge, i)] + 1;
                }
                else if(ans[edge] == 0 && ans[i] != 1) {
                    ans[edge] = 1;
                }
                else ans[edge] = 2;
            }
        }

        for(int i=0; i<n; i++) {
            if(ans[i] == 0) ans[i] = 1;
            pw.print(ans[i]);
        }
        pw.close();

    }
}
