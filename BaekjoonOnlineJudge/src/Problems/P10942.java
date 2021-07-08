/** Baekjoon Online Judge
*   Problem No. 10942
*   Palindrome DP
*   Writed by Rush.K
*   Using Dynamic Programming Method
*/

package Problems;

import java.util.Scanner;

public class P10942 {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		if (N < 1 || N > 2000) return;
		
		int[] nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = scanner.nextInt();
			if (nums[i] > 100000) return;
		}
		
		int M = scanner.nextInt();
		if (M < 1 || M > 1000000) return ;
		
		int[][] Q = new int[M][2];
		for (int i = 0; i < M; i++) {
			Q[i][0] = scanner.nextInt();
			Q[i][1] = scanner.nextInt();
		}
		
		int[][] palindrome = new int[N][N];
		
		for (int i = 0; i < N; i++) palindrome[i][i] = 1; // 길이가 1인 문자열
		for (int i = 0; i < N - 1; i++) { // 길이가 2인 문자열
			if (nums[i] == nums[i + 1]) palindrome[i][i + 1] = 1;
		}
		for (int i = 2; i < N; i++) { // 길이가 3이상인 문자열
			for (int j = 0; j < N - i; j++) {
				int k = j + i;
				if (palindrome[j + 1][k - 1] == 1 && nums[j] == nums[k]) palindrome[j][k] = 1;
			}
		}

		StringBuilder result = new StringBuilder();
		
		for (int i = 0; i < M; i++) {
			if (palindrome[Q[i][0] - 1][Q[i][1] - 1] == 1) {
				result.append("1\n");
			} else {
				result.append("0\n");
			}
		}
		
		System.out.println(result);
	}
}