import java.io.*;
import java.util.*;

public class MeasuringTraffic {
	// An arbitrary number that's large compared to the ones in the problem.
	static final int LARGE = (int)1e9;

	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio("traffic");
		int numMiles = io.nextInt();
		String[] segmentType = new String[numMiles];
		int[] start = new int[numMiles];
		int[] end = new int[numMiles];

		for (int m = 0; m < numMiles; m++) {
			segmentType[m] = io.next();
			start[m] = io.nextInt();
			end[m] = io.nextInt();
		}

		// Set a large range that might as well be [0, infinity)
		int low = 0;
		int high = LARGE;

		for (int m = numMiles - 1; m >= 0; m--) {
			if (segmentType[m].equals("none")) {
				// Set a new range based on sensor reading.
				low = Math.max(low, start[m]);
				high = Math.min(high, end[m]);
			} else if (segmentType[m].equals("off")) {
				// Update range of possible traffic flows
				low += start[m];
				high += end[m];
			} else if (segmentType[m].equals("on")) {
				low -= end[m];
				high -= start[m];
				// Set to zero if low becomes negative
				low = Math.max(0, low);
			}
		}

		io.println(low + " " + high);

		low = 0;
		high = LARGE;

		// Process again, this time scanning the other way
		for (int m = 0; m < numMiles; m++) {
			if (segmentType[m].equals("none")) {
				low = Math.max(low, start[m]);
				high = Math.min(high, end[m]);
			} else if (segmentType[m].equals("on")) {
				low += start[m];
				high += end[m];
			} else if (segmentType[m].equals("off")) {
				low -= end[m];
				high -= start[m];
				low = Math.max(0, low);
			}
		}

		io.println(low + " " + high);
		io.close();
	}

	static class Kattio extends PrintWriter {
		private BufferedReader r;
		private StringTokenizer st;
		// standard input
		public Kattio() { this(System.in, System.out); }
		public Kattio(InputStream i, OutputStream o) {
			super(o);
			r = new BufferedReader(new InputStreamReader(i));
		}
		// USACO-style file input
		public Kattio(String problemName) throws IOException {
			super(problemName + ".out");
			r = new BufferedReader(new FileReader(problemName + ".in"));
		}
		// returns null if no more input
		public String next() {
			try {
				while (st == null || !st.hasMoreTokens())
					st = new StringTokenizer(r.readLine());
				return st.nextToken();
			} catch (Exception e) { }
			return null;
		}
		public int nextInt() { return Integer.parseInt(next()); }
		public double nextDouble() { return Double.parseDouble(next()); }
		public long nextLong() { return Long.parseLong(next()); }
	}
}