/** Baekjoon Online Judge
*   Problem No. 10422
*   괄호  
*   Writed by Rush.K
*   Using Dynamic Programming Method, remainder(*)
*/

package Problems;

import java.util.Scanner;

public class P10422 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();

		long[] L = new long[5001];
		L[0] = 1;
		L[2] = 1;
		for (int i = 4; i <= 5000; i++) {
			if (i % 2 == 1) L[i] = 0;
			else {
				for (int j = 0; j < i / 2; j++) {
					L[i] += (L[2 * j] * L[(i - 2) - 2 * j]) % 1000000007;
				}
				L[i] %= 1000000007;
			}
		}
		
		int[] TestCase = new int[T];
		for (int i = 0; i < T; i++) TestCase[i] = scanner.nextInt();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) sb.append(L[TestCase[i]] + "\n");
		System.out.print(sb);
	}
}
