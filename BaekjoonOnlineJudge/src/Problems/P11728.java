/** Baekjoon Online Judge
*   Problem No. 11728
*   배열 합치기 
*   Writed by Rush.K
*   Using Arrays.sort
*/

package Problems;

import java.util.Arrays;
import java.util.Scanner;

public class P11728 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		int[] arr = new int[N + M];
		for (int i = 0; i < N + M; i++) arr[i] = scanner.nextInt();
		
		Arrays.sort(arr);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) sb.append(arr[i] + " ");
		System.out.println(sb); // output
	}
}
