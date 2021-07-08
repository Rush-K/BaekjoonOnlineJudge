/** Baekjoon Online Judge
*   Problem No. 11726
*   2 X n Ÿ�ϸ� 2
*   Writed by Rush.K
*   Finding recurrence formula
*/

package Problems;

import java.util.Scanner;

public class P11727 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); // input
		int N = scanner.nextInt();
		
		if (N < 0 && N > 1000) return ;
		
		int[] result = new int[N + 1];
		result[0] = 0;
		result[1] = 1;
		for (int i = 2; i < N + 1; i++) {
			if (i % 2 == 0) {
				result[i] = (result[i - 1] * 2 + 1) % 10007;
			} else if (i % 2 == 1) {
				result[i] = (result[i - 1] * 2 - 1) % 10007;
			}
		}
		System.out.println(result[N]); // output
	}
}  