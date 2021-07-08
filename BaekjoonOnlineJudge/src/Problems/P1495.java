/** Baekjoon Online Judge
*   Problem No. 1495
*   기타리스트 
*   Writed by Rush.K
*   Using Dynamic Programming Method (2 - dimension Array)
*/

package Problems;

import java.util.Scanner;

public class P1495 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int S = scanner.nextInt();
		int M = scanner.nextInt();
		int[] V = new int[N + 1];
		for (int i = 1; i <= N; i++) V[i] = scanner.nextInt(); // input 
		
		int[][] result = new int[1001][N + 1];
		result[S][0] = 1;
		for (int j = 0; j <= N - 1; j++) {
			for (int i = 0; i <= M; i++) {
				if (result[i][j] == 1) {
					if (i - V[j + 1] >= 0) result[i - V[j + 1]][j + 1] = 1;
					if (i + V[j + 1] <= M) result[i + V[j + 1]][j + 1] = 1;
				}
			}
		}
		
		for (int i = M; i >= 0; i--) { // output 
			if (result[i][N] == 1) {
				System.out.println(i);
				break;
			}
			if (i == 0) {
				System.out.println(-1);
				break;
			}
		}
	}

}
