/** Baekjoon Online Judge
 *   Problem No. 11723
 *   집합
 *   Writed by Rush.K
 *   비트마스킹 이용
 */


/**
 * 알아둬야할 상식!
 * 쉬프트 연산자 (<<, >>)
 * 1 << n : 1뒤에 n개의 0이 있는 수 (1을 왼쪽으로 n비트 이동)
 * 1 >> n : 1앞에 n개의 0이 있는 수 (1을 오른쪽으로 n비트 이동)
 *
 * &, |, ^ : AND, OR, XOR (연산 우선순위가 낮으므로 괄호쳐서 사용하기)
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P11723 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        int bitMask = 0 << 20;
        String readInput;
        String[] input = new String[2];


        for (int i = 0; i < M; i++) {
            int number = 0;
            readInput = br.readLine();
            if (readInput.contains(" ")) {
                input = readInput.split(" ");
                number = 1 << Integer.parseInt(input[1]);
            } else {
                input = new String[1];
                input[0] = readInput;
            }

            switch (input[0]) {
                case "add": // 추가는 OR연산
                    if ((bitMask & number) == 0) {
                        bitMask |= number;
                    }
                    break;
                case "remove": // 삭제는 단순 빼기사용, if문 제거하고 &= ~ 로 사용 가능
                    if ((bitMask & number) != 0) {
                        bitMask -= number;
                    }
                    break;
                case "check": // 체크
                    if ((bitMask & number) != 0) {
                        sb.append(1 + "\n");
                    } else {
                        sb.append(0 + "\n");
                    }
                    break;
                case "toggle": // 스위칭은 XOR연산
                    bitMask ^= number;
                    break;
                case "all": // 모든 비트 켜기
                    bitMask = (1 << 21) - 2;
                    break;
                case "empty": // 모든 비트 끄기
                    bitMask = 0;
                    break;
            }
        }

        System.out.println(sb);
    }
}
