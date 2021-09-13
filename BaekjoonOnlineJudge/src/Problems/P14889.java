/** Baekjoon Online Judge
 *   Problem No. 14889
 *   스타트와 링크
 *   Writed by Rush.K
 *   브루트포스 이용
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P14889 {
    public static int[][] scores;
    public static boolean[] visited;
    public static long result;

    public static long sumScore() { // 두 팀의 점수 차이 산출
        long sum = 0;
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                for (int j = i + 1; j < visited.length; j++) {
                    if (visited[j]) {
                        sum += (scores[i][j] + scores[j][i]);
                    }
                }
            } else {
                for (int j = i + 1; j < visited.length; j++) {
                    if (!visited[j]) {
                        sum -= (scores[i][j] + scores[j][i]);
                    }
                }
            }
        }
        return Math.abs(sum);
    }

    public static void dfs(int start, int r) {
        if (r == 0) {
            result = Math.min(result, sumScore()); // 점수 차이가 가장 적은 것 업데이트
            return;
        }

        for (int i = start; i < visited.length; i++) { // 백트래킹
            if (!visited[i]) {
                visited[i] = true;
                dfs(i, r - 1);
                visited[i] = false;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        visited = new boolean[N];
        scores = new int[N][N];
        result = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < input.length; j++) {
                scores[i][j] = Integer.parseInt(input[j]);
            }
        }

        dfs(0, N / 2);
        System.out.println(result);

    }
}
