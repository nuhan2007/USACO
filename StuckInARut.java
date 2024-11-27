//December 2020 Silver Problem 3
import java.util.*;
import java.io.*;
public class StuckInARut
{
    static int[] xCoords;
    static int[] yCoords;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		List<Integer> eastCows = new ArrayList<Integer>();
		List<Integer> northCows = new ArrayList<Integer>();
		int n = Integer.parseInt(br.readLine());
		xCoords = new int[n];
		yCoords = new int[n];
		
		for(int i=0; i<n; i++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    char c = st.nextToken().charAt(0);
		    if(c == 'E') {
		        eastCows.add(i);
		    }
		    else northCows.add(i);
		    xCoords[i] = Integer.parseInt(st.nextToken());
		    yCoords[i] = Integer.parseInt(st.nextToken());
		}
		
		eastCows.sort(Comparator.comparingInt(j -> yCoords[j]));
		northCows.sort(Comparator.comparingInt(j -> xCoords[j]));
		
		
		boolean[] stopped = new boolean[n];
		int[] amountStopped = new int[n];
		for(int E: eastCows) {
		    for(int N: northCows) {
		        if((!stopped[E] && !stopped[N])
		        && xCoords[E] < xCoords[N] 
		        && yCoords[E] > yCoords[N])
		        {
		           if(xCoords[N] - xCoords[E] > yCoords[E] - yCoords[N]) {
		               stopped[E] = true;
		               amountStopped[N] +=  (1 + amountStopped[E]);
		           }
		           else if(xCoords[N] - xCoords[E] < yCoords[E] - yCoords[N]) {
		               stopped[N] = true;
		               amountStopped[E] += (1 + amountStopped[N]);
		           }
		        }
		    }
		}
		
		for(int num : amountStopped) pw.println(num);
		pw.close();

	}
}