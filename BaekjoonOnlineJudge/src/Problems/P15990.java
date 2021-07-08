/** Baekjoon Online Judge
*   Problem No. 15990
*   1, 2, 3 ���ϱ� 5
*   Writed by Rush.K
*   Using Dynamic Programming Method
*/

package Problems;

import java.util.Scanner;

public class P15990 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();
		long[][] result = new long[4][100001];
		
		result[1][1] = 1;
		result[2][2] = 1;
		result[1][3] = 1;
		result[2][3] = 1;
		result[3][3] = 1;
		
		for (int i = 4; i < 100001; i++) {
			result[1][i] = result[2][i - 1] + result[3][i - 1] % 1000000009;
			result[2][i] = result[1][i - 2] + result[3][i - 2] % 1000000009;
			result[3][i] = result[1][i - 3] + result[2][i - 3] % 1000000009;
		}
		
		StringBuilder print = new StringBuilder();
		
		for (int i = 0; i < T; i++) {
			int tmp = scanner.nextInt();
			print.append((result[1][tmp] + result[2][tmp] + result[3][tmp]) % 1000000009 + "\n");
		}
		
		System.out.println(print);
	}

}
