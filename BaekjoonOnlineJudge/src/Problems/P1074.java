/** Baekjoon Online Judge
 *   Problem No. 1074
 *   Z
 *   Writed by Rush.K
 *   분할 정복
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1074 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int r = Integer.parseInt(input[1]);
        int c = Integer.parseInt(input[2]);

        int answer = 0;
        while (N > 1) { // 2 * 2 배열이 될때까지 분할 정복 수행
            int sideLength = (int)Math.pow(2, N);
            if (r >= sideLength / 2) {
                if (c >= sideLength / 2) { // 4분면
                    answer += 3 * (int)Math.pow(sideLength / 2, 2);
                    r -= (int)Math.pow(2, N - 1);
                    c -= (int)Math.pow(2, N - 1);
                } else { // 3분면
                    answer += 2 * (int)Math.pow(sideLength / 2, 2);
                    r -= (int)Math.pow(2, N - 1);
                }
            } else {
                if (c >= sideLength / 2) { // 2분면
                    answer += (int)Math.pow(sideLength / 2, 2);
                    c -= (int)Math.pow(2, N - 1);
                }
            }
            N--;
        }
        if (r == 0 && c == 0) {
            answer += 1;
        } else if (r == 0 && c == 1) {
            answer += 2;
        } else if (r == 1 && c == 0) {
            answer += 3;
        } else if (r == 1 && c == 1) {
            answer += 4;
        }

        System.out.println(answer - 1);
    }
}
