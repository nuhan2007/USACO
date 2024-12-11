// Source: https://usaco.guide/general/io
import java.util.*;
import java.io.*;
import java.util.StringTokenizer;

public class MadeUp {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)-1).toArray();
		int[] B = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)-1).toArray();
		int[] C = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)-1).toArray();

		HashMap<Integer, Integer> mapA = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> mapBC = new HashMap<Integer, Integer>();

		for(int i=0; i<n; i++) {
			if(mapA.containsKey(A[i])) {
				mapA.put(A[i], mapA.get(A[i]) + 1);
			}
			else {
				mapA.put(A[i], 1);
			}
		}

		for(int j=0; j<n; j++) {
			int c = C[j];
			if(c < B.length && mapBC.containsKey(B[c])) {
				mapBC.put(B[c], mapBC.get(B[c]) + 1);
			}
			else if (c < B.length) {
				mapBC.put(B[c], 1);
			}
		}
		
		int ans = 0;
		for(Map.Entry<Integer, Integer> entry : mapA.entrySet()) {
			int key = entry.getKey();
			if(mapBC.containsKey(key)) {
				ans += mapBC.get(key) * entry.getValue();
			}
		}

		System.out.println(ans);
	}
}
