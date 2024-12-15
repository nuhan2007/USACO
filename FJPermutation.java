import java.util.*;

public class FJPermutation {
    public static void solve() {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine());
        String[] input = scanner.nextLine().split(" ");
        int[] H = new int[N];
        for (int i = 0; i < N; i++) {
            H[i] = Integer.parseInt(input[i]);
        }
        if (H[N - 1] != 1) {
            System.out.println(-1);
            return;
        }
        boolean[] remaining = new boolean[N + 1];
        Arrays.fill(remaining, true);
        remaining[0] = false; // 0 is not used
        for (int h : H) {
            if (!remaining[h]) {
                System.out.println(-1);
                return;
            }
            remaining[h] = false;
        }
        List<Integer> ends = new ArrayList<>();
        for (int x = 1; x <= N; x++) {
            if (remaining[x]) {
                ends.add(x);
            }
        }
        int[] ans = new int[N];
        Arrays.fill(ans, -1);
        int l = 0, r = N - 1;
        ans[l] = ends.get(0);
        ans[r] = ends.get(1);
        for (int i = 2; i < ends.size(); i++) {
            int h = H[i - 1];
            if (ans[l] > ans[r]) {
                l++;
                ans[l] = h;
            } else {
                r--;
                ans[r] = h;
            }
        }
        System.out.println(Arrays.toString(ans).replaceAll("[\\[\\],]", ""));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < T; i++) {
            solve();
        }
    }
}

