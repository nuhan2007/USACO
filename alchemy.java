import java.util.*;
import java.io.*;
public class alchemy
{
    
    static int[][] recipes;
    static int[] have;
    static int n,k;
    
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       n = Integer.parseInt(br.readLine());
       StringTokenizer st = new StringTokenizer(br.readLine());
       have = new int[n];
       recipes = new int[n][];
       for(int i=0; i<n; i++) {
           have[i] = Integer.parseInt(st.nextToken());
       }
       
       k = Integer.parseInt(br.readLine());
       for(int i=0; i<k; i++) {
           st = new StringTokenizer(br.readLine());
           int L = Integer.parseInt(st.nextToken())-1;
           int M = Integer.parseInt(st.nextToken());
           recipes[L] = new int[M];
           for(int j=0; j<M; j++) {
               recipes[L][j] = Integer.parseInt(st.nextToken())-1;
           }
       }
       
       int ans = 0;
       while(canMake(n-1)) {
           ans++;
       }
       System.out.println(ans);
       
       
    }
    
    static boolean canMake(int want) {
        if(have[want] > 0) {
            have[want]--;
            return true;
        }
        
        if(recipes[want] == null) return false;
        for(int metal : recipes[want]) if(!canMake(metal)) return false;
        return true;
    }
}