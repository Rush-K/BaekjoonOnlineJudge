/** Baekjoon Online Judge
*   Problem No. 1920
*   수 찾기 
*   Writed by Rush.K
*   Using HashMap 
*/

package Problems;

import java.util.HashMap;
import java.util.Scanner;

public class P1920 {
	public static int[] solution(int[] numbers, int[] findNumbers) { // HashMap에 수 저장 후 탐색 
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		int[] answer = new int[findNumbers.length];
		
		for (int i = 0; i < numbers.length; i++) hashMap.put(numbers[i], 1);
		for (int i = 0; i < findNumbers.length; i++) {
			if (hashMap.get(findNumbers[i]) != null) answer[i] = 1;
		}
		
		return answer;
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		
		int[] numbers = new int[N];
		for (int i = 0; i < N; i++) numbers[i] = scanner.nextInt();
		
		int M = scanner.nextInt();
		
		int[] findNumbers = new int[M];
		for (int i = 0; i < M; i++) findNumbers[i] = scanner.nextInt();
		
		int[] answer = solution(numbers, findNumbers);
		for (int i = 0; i < answer.length; i++) System.out.println(answer[i]);
	}
}
