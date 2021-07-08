/** Baekjoon Online Judge
*   Problem No. 10844
*   ���� ��� �� 
*   Writed by Rush.K
*   Using Dynamic Programming Method
*/

package Problems;

import java.util.Scanner;

public class P10844 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		
		long[][] result = new long[101][10];
		for (int i = 1; i < 10; i++) result[1][i] = 1;
		for (int i = 2; i < 101; i++) {
			for (int j = 1; j < 10; j++) {
				if (j == 1) {
					result[i][j] = (result[i - 1][9] + result[i - 1][2]) % 1000000000;
				} else if (j == 9) {
					result[i][j] = result[i - 1][8];
				} else {
					result[i][j] = result[i - 1][j - 1] + result[i - 1][j + 1] % 1000000000;
				}
			}
		}
		long value = 0;
		for (int i = 0; i < 10; i++) {
			value += result[N][i];
		}
		System.out.println(value % 1000000000);
	}
}
