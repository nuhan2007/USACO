import java.util.*;
import java.io.*;
public class WhyCrossTheRoad {
    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("helpcross.in"));
        //PrintWriter pw = new PrintWriter(System.out);
        PrintWriter pw = new PrintWriter("helpcross.out");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] chickens = new int[C];
        for(int i=0; i<C; i++) chickens[i] = Integer.parseInt(br.readLine());
        Cow[] cows = new Cow[N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            cows[i] = new Cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(cows);
        Arrays.sort(chickens);
        br.close();
        int numHelped = 0;
        int cowAt = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int c : chickens) {
            while(cowAt < N && cows[cowAt].start <= c) {
                pq.add(cows[cowAt].end);
                cowAt++;
            }
            while(!pq.isEmpty() && pq.peek() < c) {
                pq.remove();
            }
            if(!pq.isEmpty()) {
                numHelped++;
                pq.remove();
            }
        }

        pw.println(numHelped);
        pw.close();

    }
    static class Cow implements Comparable<Cow> {
        int start;
        int end;
        public Cow(int start, int end) {
            this.start = start;
            this.end = end;
        }
        public int compareTo(Cow other) {
            if(this.start == other.start) return this.end - other.end;
            return this.start - other.start;
        }
    }
}