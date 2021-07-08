/** Baekjoon Online Judge
*   Problem No. 11066
*   파일 합치기 
*   Writed by Rush.K
*   Using Dynamic Programming Method
*/

package Problems;

import java.util.Scanner;

class TestCase {
	int N;
	int[] info;
	int[][] result;
	
	public TestCase(int N_, int[] info_) {
		N = N_;
		info = info_;
		result = new int[N + 1][N + 1];
	}
}

public class P11066 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();
		
		TestCase[] testcases = new TestCase[T];
		for (int i = 0 ; i < T; i++) {
			int N = scanner.nextInt();
			int[] info = new int[N + 1];
			for (int j = 1; j <= N; j++) info[j] = scanner.nextInt();
			testcases[i] = new TestCase(N, info);
		} // input 
		
		for (int i = 0; i < T; i++) { // 테스트 케이스 갯수만큼 실행 
			int[][] sum = new int[testcases[i].N + 1][testcases[i].N + 1]; // i ~ j 까지의 합 배열 
			
			for (int t = 0; t <= testcases[i].N; t++) { // sum 배열을 구현하기 위한 Dynamic Method
				for (int x = 1; x <= testcases[i].N - t; x++) {
					int y = x + t;
					if (t == 0) sum[x][y] = testcases[i].info[x];
					else if (t == 1) sum[x][y] = testcases[i].info[x] + testcases[i].info[y];
					else sum[x][y] = sum[x][y - 1] + sum[y][y];
				}	
			}
			
			for (int x = 1; x <= testcases[i].N; x++) { // result 배열을 구현하기 위한 초기화 
				for (int y = x; y <= testcases[i].N; y++) {
					if (x == y) {
						testcases[i].result[x][y] = testcases[i].info[x];
					} else if (y == x + 1) {
						testcases[i].result[x][y] = testcases[i].info[x] + testcases[i].info[y];
					} else {
						testcases[i].result[x][y] = 99999999;
					}
				}
			}
			
			for (int t = 2; t <= testcases[i].N; t++) { // result 배열을 구현하기 위한 Dynamic Method 
				for (int x = 1; x <= testcases[i].N - t; x++) {
					int y = x + t;
					for (int k = x; k < y; k++) {
						if (k == x) {
							testcases[i].result[x][y] = Math.min(testcases[i].result[x][y],
									sum[x][y] + testcases[i].result[k + 1][y]);	
						} else if (k + 1 == y) {
							testcases[i].result[x][y] = Math.min(testcases[i].result[x][y],
									sum[x][y] + testcases[i].result[x][k]);	
						} else {
							testcases[i].result[x][y] = Math.min(testcases[i].result[x][y],
									sum[x][y] + testcases[i].result[x][k] + testcases[i].result[k + 1][y]);				
						}
					}
				}	
			}
			System.out.println(testcases[i].result[1][testcases[i].N]); // output
		}
	}
}
