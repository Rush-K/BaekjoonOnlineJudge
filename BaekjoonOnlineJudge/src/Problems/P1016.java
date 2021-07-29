/** Baekjoon Online Judge
*   Problem No. 1016
*   제곱 ㄴㄴ수 
*   Writed by Rush.K
*   에라토스테네스의 체 변형  
*/

package Problems;

import java.util.Scanner;

public class P1016 {
	public static void main(String[] args) { // 에라토스테네스의 체 변형 알고리즘 
		Scanner scanner = new Scanner(System.in);
		int answer = 0;
		long min = scanner.nextLong();
		long max = scanner.nextLong();
		
		boolean[] numbers = new boolean[(int) (max - min) + 1];

		for (long i = 2; i * i <= max; i++) { // 몫을 index로 활용하여 제거해나감 
			long index = 0;
			long i_mul_i = i * i;
			if (min % i_mul_i == 0) {
				index = min / i_mul_i;
			} else {
				index = (min / i_mul_i) + 1;
			}
			for (long j = index; j * i_mul_i <= max; j++) {
				numbers[(int)(j * i_mul_i - min)] = true;
			}	
		}
		
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] == false) {
				answer++;
			}
		}
		System.out.println(answer);
	}
}
