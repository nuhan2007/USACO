import java.util.*;
import java.io.*;
import java.util.StringTokenizer;

public class Moos {
	public static void main(String[] args) throws IOException {
		String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", 
		"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int f = Integer.parseInt(st.nextToken());
		String s = br.readLine();
		HashMap<String, Integer> initCounts = new HashMap<String, Integer>();
		for(int i=0; i< n-2; i++) {
			if(s.charAt(i) != s.charAt(i+1) && s.charAt(i+1) == s.charAt(i+2)) {
				String sub = s.substring(i, i+3);
				if(initCounts.containsKey(sub)) {
					initCounts.put(sub, initCounts.get(sub) + 1);
				}
				else initCounts.put(sub, 1);
			}
		}

		//pw.println(initCounts);

		TreeSet<String> ans = new TreeSet<String>();
		HashMap<String, Integer> temp = new HashMap<String, Integer>(initCounts);
		for(String str : temp.keySet()) {
			if(initCounts.get(str) >= f) {
				ans.add(str);
				initCounts.remove(str);
			}
		}

		temp = new HashMap<String, Integer>(initCounts);
		if(f >= 2) {
			for(int i=0; i<n-2; i++) {
				String sub = s.substring(i, i+3);
				for(String str : temp.keySet()) {
					boolean affectsPrev = false;
					int toBeChanged = -1;
					boolean canBeAdded = false;
					if((sub.charAt(0) != str.charAt(0) && sub.substring(1).equals(str.substring(1)))) {
						canBeAdded = true;
						toBeChanged = i;
					}
					else if (sub.charAt(0) == str.charAt(0) && sub.charAt(1) == str.charAt(1) && sub.charAt(2) != str.charAt(2)) {
						canBeAdded = true;
						toBeChanged = i+1;
					}
					else if(sub.charAt(0) == str.charAt(0) && sub.charAt(1) != str.charAt(1) && sub.charAt(2) == str.charAt(2)) {
						canBeAdded = true;
						toBeChanged = i+2;
					}

					if(s.substring(Math.max(0, toBeChanged-2), Math.min(n-1, toBeChanged+4)).indexOf(str) >= 0) {
						affectsPrev = true;
					}

					if(!affectsPrev && canBeAdded) {
						initCounts.put(str, initCounts.getOrDefault(str, 0) + 1);
						if(initCounts.get(str) >= f) {
							ans.add(str);
						}
					}
				}
			}
		}
		else if(f == 1) {
			for(int i=1; i<n-1; i++) {
				if(s.charAt(i) == s.charAt(i+1)) {
					for(String let : letters) {
						if(!let.equals(s.substring(i, i+1))) {
							ans.add(let + "" + s.substring(i, i+2));
						}
					}
				}
			}
		}

		ArrayList<String> res = new ArrayList<String>(ans);
		pw.println(res.size());
		for(int i=0; i<res.size(); i++) {
			pw.println(res.get(i));
		}
		pw.close();
	}
}
