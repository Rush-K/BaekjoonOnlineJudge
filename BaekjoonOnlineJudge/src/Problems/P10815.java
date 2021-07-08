/** Baekjoon Online Judge
*   Problem No. 10815
*   숫자 카드 
*   Writed by Rush.K
*   Using Divide & Conquer (Binary Search)
*/

package Problems;

import java.util.Arrays;
import java.util.Scanner;

public class P10815 {

	public static int[] Ns;
	
	public static int Divide(int find, int top, int bottom) {
		if (find <= Ns[top] && find >= Ns[bottom]) {
			int middle = (top + bottom) / 2;
			if (find == Ns[middle]) {
				return 1;
			} else if (find < Ns[middle]) {
				return Divide(find, middle, bottom);
			} else {
				if (top - middle == 1) {
					if (Ns[top] != find && Ns[middle] != find)
						return 0;
					else
						return 1;
				} else {
					return Divide(find, top, middle);
				}
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt(); 
		Ns = new int[N];
		for (int i = 0; i < N; i++) {
			 Ns[i] = scanner.nextInt();
		}
		Arrays.sort(Ns);

		int M = scanner.nextInt();
		int[] Ms = new int[M];
		for (int i = 0; i < M; i++) Ms[i] = scanner.nextInt();
		
		StringBuilder print = new StringBuilder();
		for (int i = 0; i < M; i++) {
			if (Divide(Ms[i], N - 1, 0) == 1) {
				print.append("1 ");	
			} else {
				print.append("0 ");
			}
		}
		System.out.println(print); // output
	}
}
