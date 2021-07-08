/** Baekjoon Online Judge
*   Problem No. 2294
*   동전 2
*   Writed by Rush.K
*   Using Dynamic Programming Method
*/

package Problems;

import java.util.Arrays;
import java.util.Scanner;

public class P2294 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		
		int[] coins = new int[n];
		for (int i = 0; i < n; i++) coins[i] = scanner.nextInt();
		Arrays.sort(coins);
		
		int[] result = new int[k + 1];
		for (int i = 1; i < k + 1; i++) result[i] = 2016112098;
		
		for (int i = 1 ; i < k + 1; i++) {
			for (int j = 0; j < n; j++) {
				if (i - coins[j] >= 0)
					result[i] = Math.min(result[i - coins[j]] + 1, result[i]);
			}
		}
		
		for (int i = 1; i < k + 1; i++) 
			if (result[i] == 2016112098) result[i] = -1;

		System.out.println(result[k]);
	}
}
