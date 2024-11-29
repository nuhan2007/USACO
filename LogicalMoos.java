import java.util.*;
import java.io.*;

public class LogicalMoos {
    static final int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = scanner.next();
        }
        int[] group = new int[n];
        int group_no = 0;
        for (int i = 0; i < n; i++) {
            if (s[i].equals("or")) {
                group_no++;
            } else {
                if (i % 2 == 0) {
                    group[i] = group_no;
                }
            }
        }
        int[] first_false = new int[group_no + 1];
        int[] last_false = new int[group_no + 1];
        Arrays.fill(first_false, INF);
        Arrays.fill(last_false, -1);
        for (int i = 0; i < n; i += 2) {
            int g = group[i];
            if (s[i].equals("false")) {
                if (first_false[g] == INF) {
                    first_false[g] = i;
                }
                last_false[g] = i;
            }
        }
        int total_first_true = INF, total_last_true = -1;
        for (int i = 0; i <= group_no; i++) {
            if (first_false[i] == INF) {
                if (total_first_true == INF) {
                    total_first_true = i;
                }
                total_last_true = i;
            }
        }
        while (q-- > 0) {
            int l = scanner.nextInt() - 1;
            int r = scanner.nextInt() - 1;
            String t = scanner.next();
            // we only need one true for whole statement to be true
            int l_group = group[l], r_group = group[r];
            if (total_first_true < l_group || total_last_true > r_group) {
                System.out.print(t.equals("true") ? 'Y' : 'N');
                continue;
            }
            if (t.equals("true")) {
                System.out.print(first_false[l_group] < l || last_false[r_group] > r ? 'N' : 'Y');
            } else {
                System.out.print('Y');
            }
        }
        scanner.close();
    }
}