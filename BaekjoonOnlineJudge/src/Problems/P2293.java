/** Baekjoon Online Judge
*   Problem No. 2293
*   동전 1
*   Writed by Rush.K
*   Using Dynamic Programming Method
*/

package Problems;

import java.util.Arrays;
import java.util.Scanner;

public class P2293 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); // input
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		
		int[] coins = new int[n];
		for (int i = 0 ; i < n; i++) coins[i] = scanner.nextInt();
		
		Arrays.sort(coins);
		
		int[] result = new int[k + 1];		
		
		result[0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = coins[i]; j <= k; j++) {
				result[j] += result[j - coins[i]];
			}
		}
		System.out.println(result[k]); // output
	}
}
