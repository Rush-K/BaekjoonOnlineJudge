/** Baekjoon Online Judge
*   Problem No. 1912
*   연속합
*   Writed by Rush.K
*   Dynamic Programming
 */

/**
 * 너무 깊게 생각하지 말자! dp는 항상 간단한 법
 */
package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class P1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");

        int[] initial = new int[n + 1];
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            initial[i] = Integer.parseInt(input[i - 1]);
        }

        dp[1] = initial[1];
        int result = initial[1];

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1] + initial[i], initial[i]); // 계속 더했던 수열보다 새로 시작한 수를 비교하여 dp 업데이트
            result = Math.max(result, dp[i]);
        }

        System.out.println(result);
    }
}
