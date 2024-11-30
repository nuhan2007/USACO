import java.io.*;
import java.util.*;
public class FJActuallyFarms {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine()); 
        while(t -- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] h = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] p = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] ordByGoalHeight = new int[n];
            for(int i=0; i<n; i++) ordByGoalHeight[i] = i;
            ordByGoalHeight = Arrays.stream(ordByGoalHeight).boxed().sorted((i, j) -> p[j] - p[i]).mapToInt(i -> i).toArray();
            int ret = 0;

            for(int i=0; i+1<n; i++) {
                int smaller = ordByGoalHeight[i];
                int larger = ordByGoalHeight[i+1];
                if(h[smaller] >= h[larger] && a[smaller] < a[larger]) {
                    ret = Math.max(ret, ceilingDiv(h[smaller] - h[larger] + 1, a[larger] - a[smaller]));
                }
            }
            int[] trueHeights = new int[n];
            for(int i=0; i<n; i++) {
                trueHeights[i] = h[i] + a[i] * ret;
            }
            for(int i=0; i+1<n; i++) {
                int smaller = ordByGoalHeight[i];
                int larger = ordByGoalHeight[i+1];
                if(trueHeights[smaller] >= trueHeights[larger]) ret = -1;
            }
            pw.println(ret);
        }
        pw.close();
    }
    
    static int ceilingDiv(int a, int b) {
        return (a+b-1)/b;
    }
}
