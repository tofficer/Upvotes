//Tyler Officer
import java.io.*;
import java.util.*;

public class New_Upvotes {
	public static void main(String[] args) throws Exception {
        	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        	List<String> lines = new ArrayList<>();
        	String line = null;
        	while((line = br.readLine()) != null) {
        		lines.add(line);
		}
		br.close();

		String[] arr = lines.get(0).split("\\s+");

		int n = Integer.valueOf(arr[0]);
		int k = Integer.valueOf(arr[1]);

		arr = lines.get(1).split("\\s+");
		int[] upvotes = new int[n];

		for (int i = 0; i < n; i++) upvotes[i] = Integer.valueOf(arr[i]);

		getPatterns(upvotes, n, k);
	}

	public static void getPatterns(int[] upvotes, int n, int k) {
		if  (k == 1) {
			for (int i = 0; i < n; i++) System.out.println(0);
			return;
		}

		int[] table = new int[n-1];

		for (int i = 1; i < n; i++) {
			if (upvotes[i] < upvotes[i-1]) table[i-1] = -1;
			else if (upvotes[i] > upvotes[i-1]) table[i-1] = 1;
		}

		int[] window = new int[n+1-k];
		
		for (int add_length = k-1; add_length >= 1; add_length--) {
			int num = 0;
			int start = 0;
			int[] temp_arr = new int[table.length-1];

			for (int i = 0; i < table.length; i++) {
				if (i != table.length-1) {
					if (table[i] == 0){
						temp_arr[i] = table[i+1];
					}
					else {
						int sum = table[i]+table[i+1];
						if (sum == 1 || sum == 2 || sum == -1 || sum == -2) temp_arr[i] = table[i];
						else temp_arr[i] = 5;
					}
				}

				int temp = table[i];
				if (temp == 5) temp = 0;
				num += temp;

				if (i < add_length-1) continue;

				window[start] += num;

				if (add_length == 1) System.out.println(window[start]);

				temp = table[start];
				if (temp == 5) temp = 0;
				num -= temp;

				start++;
			}

			table = temp_arr;
		}
	}
}
