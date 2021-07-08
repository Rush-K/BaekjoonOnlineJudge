/** Baekjoon Online Judge
*   Problem No. 11058
*   크리보드
*   Writed by Rush.K
*   Using Dynamic Programming Method
*/

package Problems;

import java.util.Scanner;

public class P11058 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		
		long[] result = new long[101];
		for (int i = 1; i <= 6; i++) result[i] = i;
		
		for (int i = 7; i <= N; i++) {
			result[i] = result[i - 1] + 1;
			for (int j = 0; j <= i - 3; j++) {
				result[i] = Math.max(result[i], result[j] * (i - j - 1));
			}
		}
		System.out.println(result[N]);
	}
}
