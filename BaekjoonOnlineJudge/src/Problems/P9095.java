/** Baekjoon Online Judge
*   Problem No. 9095
*   1, 2, 3 ���ϱ�
*   Writed by Rush.K
*   Finding recurrence formula
*/

package Problems;

import java.util.Scanner;

public class P9095 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); // input
		int T = scanner.nextInt();
		
		int[] input = new int[T];
		
		int[] result = new int[12];
		result[1] = 1;
		result[2] = 2;
		result[3] = 4;
		for (int j = 4; j < 12; j++) result[j] = result[j - 1] + result[j - 2] + result[j - 3];

		for (int i = 0; i < T; i++) {
			input[i] = scanner.nextInt();
		}
		
		for (int i = 0; i < T; i++) { // output
			System.out.println(result[input[i]]);
		}
	}
}
