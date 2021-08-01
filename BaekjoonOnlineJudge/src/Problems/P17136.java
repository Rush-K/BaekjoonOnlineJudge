package Problems;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class Case {
	int[][] map;
	int[] squareCount;
	
	public Case(int[][] _map, int[] _squareCount) {
		map = new int[_map.length][_map.length];
		for (int i = 0; i < _map.length; i++) map[i] = Arrays.copyOf(_map[i], _map[i].length);
		squareCount = new int[_squareCount.length];
		squareCount = Arrays.copyOf(_squareCount, _squareCount.length);
	}
}
public class P17136 {
	public static boolean isClearMap(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[i][j] == 1) return false;
			}
		}
		return true;
	}
	public static boolean findSquare(int[][] map, int squareLength, int x, int y) {
		for (int i = x; i < x + squareLength; i++) {
			for (int j = y; j < y + squareLength; j++) {
				if (map[i][j] == 0) return false;
			}
		}
		return true;
	}
	
	public static void updateMap(int[][] map, int squareLength, int x, int y) {
		for (int i = x; i < x + squareLength; i++) {
			for (int j = y; j < y + squareLength; j++) {
				map[i][j] = 0;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Queue<Case> queue = new LinkedList<>();
		
		int answer = Integer.MAX_VALUE;
		int[] squareCount = new int[6];
		int[][] map = new int[10][10];
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				map[i][j] = scanner.nextInt();
			}
		}
		
		Case T = new Case(map, squareCount);
		Case tempT;
		
		queue.add(T);
		
		while (!queue.isEmpty()) {
			T = queue.poll();
			
			int ans = 0;
			for (int s : T.squareCount) ans += s;
			
			if (ans > answer) continue;
			
			if (isClearMap(T.map)) {
				answer = Math.min(answer, ans);
				break;
			}
			
			for (int j = 0; j < T.map.length; j++) {
				for (int k = 0; k <= T.map.length; k++) {
					for (int i = 5; i >= 1; i--) {
						if (T.squareCount[i] >= 5) continue;
						if (j + i - 1 >= T.map.length || k + i - 1 >= T.map.length) continue;
						if (findSquare(T.map, i, j, k)) {
							tempT = new Case(T.map, T.squareCount);
							updateMap(tempT.map, i, j, k);
							tempT.squareCount[i]++;
							queue.add(tempT);
						}
					}

				}
			}	
		}
		
		if (answer == Integer.MAX_VALUE) System.out.println("-1");
		else System.out.println(answer);
	}
}
