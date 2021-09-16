/** Baekjoon Online Judge
 *   Problem No. 15486
 *   퇴사 2
 *   Writed by Rush.K
 *   Dynamic Programming 이용
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P15486 {
    public static void main(String[] args) throws IOException { // 이전까지 최대값을 갱신하며 비교
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 2];
        int maxValue = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            String[] input = br.readLine().split(" ");
            maxValue = Math.max(maxValue, dp[i]);

            if (i + Integer.parseInt(input[0]) <= N + 1) {
                dp[i + Integer.parseInt(input[0])] = Math.max(dp[i + Integer.parseInt(input[0])] ,maxValue + Integer.parseInt(input[1]));
            }
        }
        System.out.println(Math.max(maxValue, dp[N + 1]));
    }
}
