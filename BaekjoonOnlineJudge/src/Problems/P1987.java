/** Baekjoon Online Judge
 *   Problem No. 1987
 *   알파벳
 *   Writed by Rush.K
 *   DFS 이용
 */

/**
 * BFS는 메모리 초과!
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1987 {
    public static int answer;
    public static int R;
    public static int C;
    public static boolean[] visited;
    public static int[][] map;
    public static int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void dfs(int x, int y, int result) { // DFS 탐색

        for (int i = 0; i < direction.length; i++) {
            try {
                if (!visited[map[x + direction[i][0]][y + direction[i][1]] - 65]) {
                    visited[map[x + direction[i][0]][y + direction[i][1]] - 65] = true;
                    dfs(x + direction[i][0], y + direction[i][1], result + 1);
                    visited[map[x + direction[i][0]][y + direction[i][1]] - 65] = false;
                }
            } catch (Exception e) {
            }
        }

        answer = Math.max(answer, result);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        R = Integer.parseInt(input.split(" ")[0]);
        C = Integer.parseInt(input.split(" ")[1]);
        map = new int[R][C];
        answer = Integer.MIN_VALUE;
        visited = new boolean[26];

        for (int i = 0; i < R; i++) { // 알파벳을 정수형으로 치환하여 비교
            input = br.readLine();
            char[] charInput = input.toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = (int) charInput[j];
            }
        }
        visited[map[0][0] - 65] = true;

        dfs(0,0,1);

        System.out.println(answer);
    }
}
