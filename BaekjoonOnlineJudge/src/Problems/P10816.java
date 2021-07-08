/** Baekjoon Online Judge
*   Problem No. 10816
*   숫자 카드 2
*   Writed by Rush.K
*   Using Divide & Conquer (Binary Search)
*/

package Problems;

import java.util.Arrays;
import java.util.Scanner;

public class P10816 {
	public static int[] Ns;
	public static int[] Ns_num;
	
	public static int Divide(int find, int top, int bottom) {
		if (find <= Ns[top] && find >= Ns[bottom]) {
			int middle = (top + bottom) / 2;
			if (find < Ns[middle]) {
				return Divide(find, middle, bottom);
			} else if (find > Ns[middle]) {
				if (top - middle == 1) {
					if (Ns[top] != find && Ns[middle] != find)
						return 0;
					else
						return Ns_num[middle];
				} else {
						return Divide(find, top, middle);
				}
			} else {
				return Ns_num[middle];
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); // input
		int N = scanner.nextInt();
		Ns = new int[N];
		for (int i = 0; i < N; i++) Ns[i] = scanner.nextInt();
		Ns_num = new int[N];
		int M = scanner.nextInt();
		int[] Ms = new int[M];
		for (int i = 0; i < M; i++) Ms[i] = scanner.nextInt();
		
		Arrays.sort(Ns);
		
		int sor = 1;
		for (int i = 1; i < N; i++) {
			if (Ns[i] == Ns[i - 1]) {
				sor++;
				if (i == N - 1) {
					for (int j = i - sor + 1; j <= i; j++) {
						Ns_num[j] = sor;					
					}
				}
			} else {
				if (i == N - 1) {
					Ns_num[i] = sor;
				}
				if (sor > 1) {
					for (int j = i - sor; j < i; j++) {
						Ns_num[j] = sor;
					}
					sor = 1;
				} else {
					Ns_num[i - 1] = 1;
					sor = 1;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < M; i++) {
			int temp = Divide(Ms[i], N - 1, 0);
			sb.append(temp + " ");
		}
		
		System.out.println(sb); // output
	}
}
