/** Baekjoon Online Judge
 *   Problem No. 11404
 *   플로이드
 *   Writed by Rush.K
 *   플로이드 알고리즘 이용
 */

package Problems;

import java.io.*;
import java.util.*;

public class P11404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == 0) {
                    if (i != j) map[i][j] = 1000000000;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            String[] input = br.readLine().split(" ");
            map[Integer.parseInt(input[0])][Integer.parseInt(input[1])] =
                    Math.min(map[Integer.parseInt(input[0])][Integer.parseInt(input[1])], Integer.parseInt(input[2]));
        }

        for (int k = 1; k <= n; k++) { // 플로이드 알고리즘 (k i j -> i k k j)
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == 0 || map[i][j] >= 1000000000) System.out.print(0 + " ");
                else System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
