/** Baekjoon Online Judge
*   Problem No. 15989
*   1, 2, 3 더하기 4
*   Writed by Rush.K
*   Using Dynamic Programming Method
*/

package Problems;

import java.util.Scanner;

public class P15989 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); // input
		int T = scanner.nextInt();
		
		int[][] result = new int[10001][4];
		
		result[1][1] = 1;
		result[1][2] = 0;
		result[1][3] = 0;
		
		result[2][1] = 1;
		result[2][2] = 1;
		result[2][3] = 0;
		
		result[3][1] = 1;
		result[3][2] = 1;
		result[3][3] = 1;
		
		for (int i = 4; i < 10001; i++) {
			result[i][1] = result[i - 1][1];
			result[i][2] = result[i - 2][1] + result[i - 2][2];
			result[i][3] = result[i - 3][1] + result[i - 3][2] + result[i - 3][3];
		}
		
		StringBuilder result_f = new StringBuilder();
		for (int i = 0; i < T; i++) {
			int temp = scanner.nextInt();
			result_f.append(result[temp][1] + result[temp][2] + result[temp][3] + "\n");
		}
		System.out.print(result_f); // output
	}

}