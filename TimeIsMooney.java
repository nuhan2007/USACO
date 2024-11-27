//2020 January Gold Problem 1
import java.io.*;
import java.util.*;
public class TimeIsMooney {
    public static void main(String[] args) throws IOException {
        final int T_max = 1001;
        BufferedReader br = new BufferedReader(new FileReader("time.in"));
        PrintWriter pw = new PrintWriter("time.out");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] values = new int[n];
        List<List<Integer>> adj = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) values[i] = Integer.parseInt(st.nextToken());
        for(int i=0; i<n; i++) adj.add(new ArrayList<>());
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken()) - 1;
            int n2 = Integer.parseInt(st.nextToken()) - 1;
            adj.get(n1).add(n2);
        }

        long[][] dp = new long[T_max][n];
        for(int i=0; i<n; i++) Arrays.fill(dp[i], -1);

        long best = 0;
        dp[0][0] = 0;
        for(int t=0; t<T_max-1; t++) {
            for(int i=0; i<n; i++) {
                if(dp[t][i] != -1) {
                    for(int neighbor : adj.get(i)) {
                        dp[t+1][neighbor] = Math.max(dp[t+1][neighbor], dp[t][i] + values[neighbor]);
                    }
                }
            }
            best = Math.max(best, dp[t][0] - t*t*c);
        }
        pw.println(best);
        pw.close();
    }
}