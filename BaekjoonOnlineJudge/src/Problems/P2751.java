/** Baekjoon Online Judge
 *   Problem No. 2751
 *   수 정렬하기 2
 *   Writed by Rush.K
 *   카운팅 정렬
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2751 {
    public static int[] minusNumbers;
    public static int[] plusNumbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        minusNumbers = new int[1000001]; // 음의 정수 저장
        plusNumbers = new int[1000001]; // 양의 정수 저장

        for (int i = 0; i < N; i++) { // 각 input 에 맞는 배열에 저장
            String input = br.readLine();
            if (input.startsWith("-")) {
                minusNumbers[Integer.parseInt(input.replace("-", ""))] += 1;
            } else {
                plusNumbers[Integer.parseInt(input)] += 1;
            }
        }

        for (int i = minusNumbers.length - 1; i > 0; i--) { // 출력 결과값 저장
            for (int j = 0; j < minusNumbers[i]; j++) {
                sb.append("-" + i + "\n");
            }
        }

        for (int i = 0; i < plusNumbers.length; i++) { // 출력 결과값 저장
            for (int j = 0; j < plusNumbers[i]; j++) {
                sb.append(i + "\n");
            }
        }

        System.out.print(sb); // 결과 출력
    }
}
