/** Baekjoon Online Judge
*   Problem No. 10989
*   수 정렬하기 3 
*   Writed by Rush.K
*   Using Counting Sort 
*/

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P10989 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // Scanner 대신 BufferedReader 사용하기! 속도 매우 빠름 
		StringBuilder sb = new StringBuilder(); // StringBuilder 사용해서 출력 속도 높이기 
		
		int[] numbers = new int[10001];
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) numbers[Integer.parseInt(br.readLine())]++;
		for (int i = 0; i < numbers.length; i++) {
			for (int j = 0; j < numbers[i]; j++) {
				sb.append(i + "\n");
			}
		}
		System.out.println(sb);
	}
}
