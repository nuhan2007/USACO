//2020 US Open Bronze Problem 3
import java.util.*;
import java.io.*;
public class CowntactTracing {

    static boolean[] cowEndsInfected;
    static int n, t, maxN, maxT;
    static int[] cowX;
    static int[] cowY;
    
    static boolean consistentWithData(int patientZero, int k) {
        boolean[] infectedCows = new boolean[maxN+1];
        Arrays.fill(infectedCows, false);
        infectedCows[patientZero] = true;
        int[] numHandShakes = new int[maxN+1];
        for(int i=0; i<=maxT; i++) {
            int c1 = cowX[i];
            int c2 = cowY[i];

            if(infectedCows[c1]) numHandShakes[c1]++;
            if(infectedCows[c2]) numHandShakes[c2]++;
            if(numHandShakes[c1] <= k && infectedCows[c1]) infectedCows[c2] = true;
            if(numHandShakes[c2] <= k && infectedCows[c2]) infectedCows[c1] = true;
        }

        for(int j=1; j<=n; j++) {
            if(infectedCows[j] != cowEndsInfected[j]) return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException{
        maxN = 100;
        maxT = 250;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        String s = br.readLine();
        cowEndsInfected = new boolean[n+1];
        for(int i=1; i<=n; i++) {
            if(s.charAt(i-1) == '1') cowEndsInfected[i] = true;
            else cowEndsInfected[i] = false;
        }
        cowX = new int[maxT+1];
        cowY = new int[maxT+1];
        for(int i=0; i<t; i++) {
            st = new StringTokenizer(br.readLine());
            int tt = Integer.parseInt(st.nextToken());
            cowX[tt] = Integer.parseInt(st.nextToken());
            cowY[tt] = Integer.parseInt(st.nextToken());
        }

        boolean[] possibleI = new boolean[maxN+1];
        boolean[] possibleK = new boolean[maxT+2];
        for(int i=1; i<=n; i++) {
            for(int k=0; k<maxT; k++) {
                if(consistentWithData(i, k));
                possibleI[i] = true;
                possibleK[k] = true;
            }
        }

        int numPatientZero = 0;
        int lowerK = Integer.MAX_VALUE;
        int upperK = Integer.MIN_VALUE;

        for(int i=0; i<maxN; i++) {
            if(possibleI[i]) numPatientZero++;
        }
        for(int k=0; k<=maxT; k++) {
            if(possibleK[k]) {
                lowerK = k;
                break;
            }
        }
        for(int k=maxT+1; k>=0; k--) {
            if(possibleK[k]) {
                upperK = k;
                break;
            }
        }

        System.out.print(numPatientZero + " " + lowerK + " ");
        if(upperK == maxT+1) System.out.print("Infinity");
        else System.out.print(upperK);

    }
}
