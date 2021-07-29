/** Baekjoon Online Judge
*   Problem No. 1697
*   숨바꼭질 
*   Writed by Rush.K
*   Using BFS  
*/

package Problems;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Position {
	int pos;
	int count;
	
	public Position(int _pos) {
		pos = _pos;
		count = 0;
	}
	
	public Position(int _pos, int _count) {
		pos = _pos;
		count = _count;
	}
}

public class P1697 {

	public static void main(String[] args) { // BFS 사용 
		Scanner scanner = new Scanner(System.in);
		Queue<Position> q = new LinkedList<>();
		
		int N = scanner.nextInt();
		int K = scanner.nextInt();
		boolean[] visited = new boolean[100001];
		
		Position pos = new Position(N);
		Position tempPos;
		
		visited[N] = true;
		q.add(pos);

		while ((pos = q.poll()).pos != K) { // 뒷 좌표, 앞 좌표, 2배 좌표 가능한 것 큐 삽입 
			if (pos.pos - 1 >= 0 && visited[pos.pos - 1] == false) {
				visited[pos.pos - 1] = true;
				tempPos = new Position(pos.pos - 1, pos.count + 1);
				q.add(tempPos);
			}
			if (pos.pos + 1 <= 100000 && visited[pos.pos + 1] == false) {
				visited[pos.pos + 1] = true;
				tempPos = new Position(pos.pos + 1, pos.count + 1);
				q.add(tempPos);
			}
			if (2 * pos.pos <= 100000 && visited[2 * pos.pos] == false) {
				visited[2 * pos.pos] = true;
				tempPos = new Position(2 * pos.pos, pos.count + 1);
				q.add(tempPos);
			}
		}
		System.out.println(pos.count); // 결과 출력 
	}
}
