/** Baekjoon Online Judge
*   Problem No. 11726
*   2 X n Ÿ�ϸ�
*   Writed by Rush.K
*   Finding recurrence formula
*/

package Problems;

import java.util.Scanner;

public class P11726 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt(); // input
		
		if (n < 1 || n > 1000) return;
		
		int[] result = new int[n + 1];
		result[0] = 1;
		result[1] = 1;
		for (int i = 2; i < n + 1; i++) {
			result[i] = (result[i - 1] + result[i - 2]) % 10007;
		}
		
		System.out.println(result[n]); // output
	}
}
