import java.io.*;
import java.util.*;
public class cownomics
{
    
    static final Map<Character, Integer> GENOME_ID = Map.ofEntries(Map.entry('A', 0), Map.entry('T', 1) , Map.entry('C', 2), Map.entry('G', 3));
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cownum = Integer.parseInt(st.nextToken());
        int genomeLen = Integer.parseInt(st.nextToken());
        int triplets = 0;
        
        int[][] spotted = new int[cownum][genomeLen];
        for(int i=0; i<cownum; i++) {
            String g = br.readLine();
            for(int j=0; j<genomeLen; j++) {
                spotted[i][j] = GENOME_ID.get(g.charAt(j));
            }
        }
        
        int[][] plain = new int[cownum][genomeLen];
        for(int s=0; s<cownum; s++) {
            String g = br.readLine();
            for(int j=0; j<genomeLen; j++) {
                plain[s][j] = GENOME_ID.get(g.charAt(j));
            }
        }
        
        for(int a=0; a<genomeLen; a++) {
            for(int b=a+1; b<genomeLen; b++) {
                for(int c=b+1; c<genomeLen; c++) {
                    int[] spotted_ids = new int[64];
                    
                    for(int sc = 0; sc < cownum; sc++) {
                        int total = spotted[sc][a] * 16 + spotted[sc][b] * 4 + spotted[sc][c];
                        spotted_ids[total] = 1;
                    }
                    
                    boolean validTriple = true;
                    for(int pc = 0; pc < cownum; pc++) {
                        int total = plain[pc][a] * 16 + plain[pc][b] * 4 + plain[pc][c];
                        if(spotted_ids[total] == 1) {
                            validTriple = false;
                            break;
                        }
                    }
                    
                    triplets += validTriple ? 1 : 0;
                }
            }
        }
        
        br.close();
        pw.println(triplets);
        pw.close();
    }
}