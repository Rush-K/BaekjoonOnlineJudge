/** Baekjoon Online Judge
*   Problem No. 11048
*   이동하기
*   Writed by Rush.K
*   Using Dynamic Programming Method
*/

package Problems;

import java.util.LinkedList;
import java.util.Scanner;

class Vertex {
	int x;
	int y;
	int numOfcandy;
	
	public Vertex(int x_, int y_, int noc) {
		x = x_;
		y = y_;
		numOfcandy = noc;
	}
}

public class P11048 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in); // input start
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		
		if (N < 1 || M > 1000) return ;
		
		Vertex[][] map = new Vertex[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int candy = scanner.nextInt();
				map[i][j] = new Vertex(i, j, candy);
			}
		} // input end
		
		int[][] result = new int[N][M];
		result[0][0] = map[0][0].numOfcandy;
		int i, j;
		
		for (i = 1; i < N; i++) result[i][0] += map[i][0].numOfcandy + result[i - 1][0];
		for (j = 1; j < M; j++) result[0][j] += map[0][j].numOfcandy + result[0][j - 1];

		for (i = 1; i < N; i++) {
			for (j = 1; j < M; j++) {
				result[i][j] += Math.max(Math.max(result[i - 1][j] + map[i][j].numOfcandy,
												  result[i][j - 1] + map[i][j].numOfcandy),
												  result[i - 1][j - 1] + map[i][j].numOfcandy);
			}
		}

		System.out.println(result[N - 1][M - 1]); // output
	}
}