/** Baekjoon Online Judge
 *   Problem No. 2482
 *   색상환
 *   Writed by Rush.K
 *   Dynamic Programming
 */

package Problems;

import java.util.Scanner;

public class P2482 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();

        long[][] map = new long[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            map[i][0] = 1;
            map[i][1] = i;
        }

        for (int i = 3; i <= N; i++) {
            for (int j = 2; j <= (i + 1) / 2; j++) {
                map[i][j] = (map[i - 2][j - 1] + map[i - 1][j]) % 1000000003;
            }
        }

        long result = (map[N - 3][K - 1] + map[N - 1][K]) % 1000000003; // 하나의 색을 선택했을 때, 안했을 때의 경우의 수를 더한 값이 답
        System.out.println(result);
    }
}
