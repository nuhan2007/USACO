import java.io.*;
import java.util.*;

public class Triangles {
	static class Fence {
		int x;
		int y;
		// terminology: anchor point = vertex of the right angle in a right
		// triangle sum of heights of all triangles that use this fence as an
		// anchor point
		long heightsum;
		// sum of the bases of all triangles that use this fence as an anchor
		// point
		long basesum;
	}

	static class Pair implements Comparable<Pair> {
		int first, second;

		public Pair(int x, int y) {
			first = x;
			second = y;
		}

		public int compareTo(Pair x) {
			if (this.first == x.first) return this.second - x.second;
			return this.first - x.first;
		}
	}

	static final int MOD = (int)1e9 + 7;
	static final int MAX_C = (int)1e4;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("triangles.in"));
		PrintWriter pw = new PrintWriter("triangles.out");
		StringTokenizer st = new StringTokenizer(in.readLine());

		int n = Integer.parseInt(st.nextToken());
		Fence[] fences = new Fence[n];
		// all possible x coordinates of the fences (+1 to account for 0)
		ArrayList<Pair>[] xcoords = new ArrayList[2 * MAX_C + 1];
		// all possible y coordinates of the fences
		ArrayList<Pair>[] ycoords = new ArrayList[2 * MAX_C + 1];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			fences[i] = new Fence();
			fences[i].x = Integer.parseInt(st.nextToken());
			fences[i].y = Integer.parseInt(st.nextToken());
			// we add MAX_C to make all of our coordinates positive
			// so that we won't have negative indices
			if (xcoords[fences[i].x + MAX_C] == null)
				xcoords[fences[i].x + MAX_C] = new ArrayList<>();
			if (ycoords[fences[i].y + MAX_C] == null)
				ycoords[fences[i].y + MAX_C] = new ArrayList<>();

			xcoords[fences[i].x + MAX_C].add(new Pair(fences[i].y, i));
			ycoords[fences[i].y + MAX_C].add(new Pair(fences[i].x, i));
		}

		for (int i = 0; i <= 2 * MAX_C; i++) {
			if (xcoords[i] != null) {
				// cur is the value of the current s_i
				long cur = 0;
				// sort all y positions of all points w/ the same x pos
				Collections.sort(xcoords[i]);
				/*
				 * then we compute the value s_1 of this set:
				 * the sum of the heights of all the triangles that
				 * have their anchor point at (i, xcoords[i][0].first)
				 */
				for (int j = 1; j < xcoords[i].size(); j++) {
					cur += xcoords[i].get(j).first - xcoords[i].get(0).first;
				}
				fences[xcoords[i].get(0).second].heightsum = cur;
				// then we compute the rest of the s_i for this set
				for (int j = 1; j < xcoords[i].size(); j++) {
					cur += (2 * j - xcoords[i].size()) *
					       (xcoords[i].get(j).first - xcoords[i].get(j - 1).first);
					fences[xcoords[i].get(j).second].heightsum = cur;
				}
			}
		}

		// we do the sums of bases in exactly the same way
		for (int i = 0; i <= 2 * MAX_C; i++) {
			if (ycoords[i] != null) {
				long cur = 0;
				Collections.sort(ycoords[i]);
				for (int j = 1; j < ycoords[i].size(); j++) {
					cur += ycoords[i].get(j).first - ycoords[i].get(0).first;
				}
				fences[ycoords[i].get(0).second].basesum = cur;
				for (int j = 1; j < ycoords[i].size(); j++) {
					cur += (2 * j - ycoords[i].size()) *
					       (ycoords[i].get(j).first - ycoords[i].get(j - 1).first);
					fences[ycoords[i].get(j).second].basesum = cur;
				}
			}
		}

		// finally we compute the total area
		int totalArea = 0;
		for (int i = 0; i < n; i++) {
			totalArea += fences[i].heightsum * fences[i].basesum % MOD;
			totalArea %= MOD;
		}
		pw.println(totalArea);
		pw.close();
	}
}
