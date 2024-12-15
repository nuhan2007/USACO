import java.util.*;
import java.io.*;
import java.util.StringTokenizer;

public class Cheese {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		boolean[][][] cheese = new boolean[n][n][n];
		int ans = 0;

		for(int i=0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			cheese[x][y][z] = true;

			int contiguousX = 0;
			int contiguousY = 0;
			int contiguousZ = 0;
			int k = 0;
			while(k < n) {
				while(k < n && cheese[k][y][z] == true) {
					contiguousX++;
					k++;
				}
				ans += Math.max(0, contiguousX - n + 1);
				contiguousX = 0;
				k++;
			}
			//finished X

			k = 0;
			while(k < n) {
				while(k < n && cheese[x][k][z] == true) {
					contiguousY++;
					k++;
				}
				ans += Math.max(0, contiguousY - n + 1);
				contiguousY = 0;
				k++;
			}
				//finished Y
			k = 0;
			while(k < n) {
				while(k < n && cheese[x][y][k] == true) {
					contiguousZ++;
					k++;
				}
				ans += Math.max(0, contiguousZ - n + 1);
				contiguousZ = 0;
				k++;
			}
			//finished Z

			pw.println(ans);

		}
		pw.close();
	}
}
