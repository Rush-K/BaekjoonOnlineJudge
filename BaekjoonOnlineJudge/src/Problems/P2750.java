/** Baekjoon Online Judge
*   Problem No. 2750 
*   수 정렬하기  
*   Writed by Rush.K
*   Using PriorityQueue  
*/

package Problems;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class P2750 {
	public static void main(String[] args) { // PriorityQueue 사용, Array.sort가 가장 빠르긴함 
		Scanner scanner = new Scanner(System.in);
		PriorityQueue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1.compareTo(o2);
			}
			
		});
		
		int N = scanner.nextInt();
		for (int i = 0; i < N; i++) q.add(scanner.nextInt());
		while (!q.isEmpty()) System.out.println(q.poll());
	}
}
