// Source: https://usaco.guide/general/io

import java.io.*;
import java.util.StringTokenizer;

public class BreedCounting {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("bcount.in"));
		PrintWriter pw = new PrintWriter("bcount.out");
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		int[] pfix1 = new int[n+1];
		int[] pfix2 = new int[n+1];
		int[] pfix3 = new int[n+1];
		for(int i=1; i<=n; i++) {
			int cow = Integer.parseInt(br.readLine());
			pfix1[i] = pfix1[i-1];
			pfix2[i] = pfix2[i-1];
			pfix3[i] = pfix3[i-1];
			if(cow == 1) {
				pfix1[i]++;
			}
			else if(cow == 2) {
				pfix2[i]++;
			}
			else pfix3[i]++;
		}
		for(int i=0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken())-1;
			int r = Integer.parseInt(st.nextToken());
			int p1 = pfix1[r] - pfix1[l];
			int p2 = pfix2[r] - pfix2[l];
			int p3 = pfix3[r] - pfix3[l];
			pw.println(p1 + " " + p2 + " " + p3);
		}
		
		pw.close();
	}
}
