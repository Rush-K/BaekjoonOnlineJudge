/** Baekjoon Online Judge
 *   Problem No. 2193
 *   이친수
 *   Writed by Rush.K
 *   피보나치 수열
 */

package Problems;

import java.util.Scanner;

public class P2193 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        long[] pinaryNumber = new long[N + 1];

        if (N == 1) {
            System.out.println(1);
            return;
        }

        pinaryNumber[1] = 1;
        pinaryNumber[2] = 1;
        for (int i = 3; i <= N; i++) {
            pinaryNumber[i] = pinaryNumber[i - 1] + pinaryNumber[i - 2];
        }
        System.out.println(pinaryNumber[N]);
    }
}
