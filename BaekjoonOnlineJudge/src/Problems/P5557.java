/** Baekjoon Online Judge
*   Problem No. 5557
*   1학년 
*   Writed by Rush.K
*   Using Dynamic Programming Method (2 - dimension Array)
*/

package Problems;

import java.util.Scanner;

public class P5557 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int[] Num = new int[N + 1];
		for (int i = 1; i <= N; i++) Num[i] = scanner.nextInt(); // input 
		
		long[][] result = new long[21][N + 1];
		
		result[Num[1]][1] = 1;
		for (int j = 1; j <= N - 2; j++) {
			for (int i = 0; i <= 20; i++) {
				if (result[i][j] >= 1) {
					if (i - Num[j + 1] >= 0) result[i - Num[j + 1]][j + 1] += result[i][j];
					if (i + Num[j + 1] <= 20) result[i + Num[j + 1]][j + 1] += result[i][j];
				}
			}
		}
		System.out.println(result[Num[N]][N - 1]); // output 
	}
}
