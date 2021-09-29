/** Baekjoon Online Judge
 *   Problem No. 9461
 *   파도반 수열
 *   Writed by Rush.K
 *   Dynamic Programming 이용
 */

package Problems;

import java.util.Scanner;

public class P9461 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        long[] K = new long[101];
        K[1] = 1;
        K[2] = 1;
        K[3] = 1;
        K[4] = 2;

        for (int i = 5; i < K.length; i++) K[i] = K[i - 1] + K[i - 5];

        int T = scanner.nextInt();

        for (int i = 0; i < T; i++) {
            int k = scanner.nextInt();
            sb.append(K[k] + "\n");
        }

        System.out.println(sb);
    }
}
