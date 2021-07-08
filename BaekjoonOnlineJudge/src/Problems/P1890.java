/** Baekjoon Online Judge
*   Problem No. 1890
*   점프
*   Writed by Rush.K
*   Using Dynamic Programming Method
*/

package Problems;

import java.util.Scanner;

public class P1890 {
	public static int[][] map;
	public static long[][] result;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); // input
		int N = scanner.nextInt();
		
		if (N < 4 || N > 100) return ;
		
		map = new int[N][N];
		result = new long[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int jump = scanner.nextInt();
				if (jump < 0 || jump > 9) return;
				map[i][j] = jump;
			}
		}
		
		if (map[N - 1][N - 1] != 0) return;
		
		result[0][0] = 1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) continue;
				if (i + map[i][j] < N) result[i + map[i][j]][j] += result[i][j];
				if (j + map[i][j] < N) result[i][j + map[i][j]] += result[i][j];
			}
		}
		
		
		System.out.println(result[N - 1][N - 1]); // output
	}
}
