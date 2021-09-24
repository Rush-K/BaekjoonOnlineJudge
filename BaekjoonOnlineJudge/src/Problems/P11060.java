/** Baekjoon Online Judge
 *   Problem No. 11060
 *   점프 점프
 *   Writed by Rush.K
 *   Using Dynamic Programming Method
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P11060 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] jumpZone = new int[N]; // 점프 정보 배열
        int[] result = new int[N]; // 점프 횟수 배열

        String[] input = br.readLine().split(" ");

        for (int i = 0 ; i < N; i++) {
            jumpZone[i] = Integer.parseInt(input[i]);
            result[i] = Integer.MAX_VALUE;
        }

        result[0] = 0;

        for (int i = 0; i < N; i++) {
            if (result[i] == Integer.MAX_VALUE) continue;
            for (int j = i + 1; j <= ((i + jumpZone[i]) >= N ? N - 1 : (i + jumpZone[i])); j++) { // 점프 가능한 범위에서 최솟값 업데이트
                if (i == 0) result[j] = 1;
                else result[j] = Math.min(result[j], result[i] + 1);
            }
        }

        System.out.println(result[N - 1] == Integer.MAX_VALUE ? -1 : result[N - 1]); // 결과 출력
    }
}
