/** Baekjoon Online Judge
*   Problem No. 1463
*   1�� �����
*   Writed by Rush.K
*   Using BFS by QUEUE
*/

package Problems;

import java.util.LinkedList;
import java.util.Scanner;

class Number { // ��
	int N;
	int result;
	
	public Number(int n, int res) {
		N = n;
		result = res;
	}
}

public class P1463 {

	public static void main(String[] args) {
		//input 
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		
		if (num < 1 || num > 1000000) { // ������ ���� ������ ���α׷� ����
			return ;
		}

		Number N = new Number(num, 0); // ���� Number Ŭ������ �̿��� �ʱ�ȭ
		
		int result = Integer.MAX_VALUE; // ��� ��
		LinkedList<Number> queue = new LinkedList<>(); // BFS�� ���� QUEUE
		queue.add(N); // ù �� ���� 
		
		while (!queue.isEmpty()) { // ť�� �� ������ �ݺ�
			Number Start = queue.pollFirst(); // ���� ������
			if (Start.N == 1) { // ���� 1�� ��
				result = Start.result;
				break;
			}
			if (Start.N % 3 == 0) { // ���� 3���� ������ ������ ��
				Number temp = new Number(Start.N / 3, Start.result + 1);
				if (temp.N == 1) {
					result = temp.result;
					break;
				} else {
					queue.add(temp);
				}
			}
			if (Start.N % 2 == 0) { // ���� 2�� ������ ������ ��
				Number temp = new Number(Start.N / 2, Start.result + 1);
				if (temp.N == 1) {
					result = temp.result;
					break;
				} else {
					queue.add(temp);
				}
			}
			// ������ 1�� �� ��
			Number temp = new Number(Start.N - 1, Start.result + 1);
			if (temp.N == 1) {
				result = temp.result;
				break;
			} else {
				queue.add(temp);
			}
		}
		//output
		System.out.println(result);
	}
}
