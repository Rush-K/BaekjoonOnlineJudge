/** Baekjoon Online Judge
 *   Problem No. 17136
 *   색종이 붙이기
 *   Writed by Rush.K
 *   Using DFS Method
 */

package Problems;

import java.util.Scanner;

public class P17136 {
	public static int answer = Integer.MAX_VALUE; // 답
	public static int[] squareCount = new int[6]; // 종류별 색종이 갯수

	public static boolean isClearMap(int[][] map) { // 전부 색종이로 덮었는가?
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[i][j] == 1) return false;
			}
		}
		return true;
	}
	public static boolean findSquare(int[][] map, int squareLength, int x, int y) { // 색종이를 붙일 수 있는가?
		for (int i = x; i < x + squareLength; i++) {
			for (int j = y; j < y + squareLength; j++) {
				if (map[i][j] == 0) return false;
			}
		}
		return true;
	}
	
	public static void updateMap(int[][] map, int squareLength, int x, int y, int value) { // value : 0 -> 색종이 붙이기, 1 -> 색종이 떼기
		for (int i = x; i < x + squareLength; i++) {
			for (int j = y; j < y + squareLength; j++) {
				map[i][j] = value;
			}
		}
	}

	public static void dfs(int[][] map, int x, int y, int count) { // DFS 알고리즘
		if (count >= answer) return; // 최소 개수보다 많으면 탐색할 필요 없음

		if (x == map.length) { // 전체를 탐색 완료 했을 경우
			if (isClearMap(map)) answer = Math.min(answer, count); // 색종이로 알맞게 덮은 경우
			return;
		}

		if (y >= map.length) { // 좌표 y가 map을 벗어난 경우
			dfs(map, x + 1, 0, count);
			return;
		}

		if (map[x][y] == 1) {
			for (int k = 5; k >= 1; k--) { // 5x5 ~ 1x1 색종이를 붙여봄
				if (x + k - 1 >= map.length || y + k - 1 >= map.length) continue; // 색종이를 붙일 수 없는 경우
				if (findSquare(map, k, x, y) && squareCount[k] < 5) { // 색종이를 붙이는 경우
					updateMap(map, k, x, y, 0);
					squareCount[k]++;
					dfs(map, x, y + k, count + 1);
					squareCount[k]--;
					updateMap(map, k, x, y, 1);
				}
			}
		} else {
			dfs(map, x, y + 1, count);
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int[][] map = new int[10][10];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				map[i][j] = scanner.nextInt();
			}
		}

		dfs(map, 0, 0, 0);
		
		if (answer == Integer.MAX_VALUE) System.out.println("-1"); // 답이 없는 경우
		else System.out.println(answer);
	}
}
