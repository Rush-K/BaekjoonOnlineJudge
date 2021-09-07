/** Baekjoon Online Judge
 *   Problem No. 20922
 *   겹치는 건 싫어
 *   Writed by Rush.K
 *   투 포인터 이용
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P20922 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        input = br.readLine().split(" ");

        int[] bufferCount = new int[100001]; // 수열 내 각 수의 갯수를 위한 배열
        int firstPointer = 0;
        int secondPointer = 0;
        int result = 0;

        while (secondPointer != N) {
            int number = Integer.parseInt(input[secondPointer]);
            if (++bufferCount[number] > K) { // 해당 수가 K개 이상일 경우
                result = Math.max(result, secondPointer - firstPointer);
                for (int i = firstPointer; i < secondPointer; i++) { // 해당 수가 K개 이하가 되도록 first 포인터 업데이트
                    if (Integer.parseInt(input[i]) == number) {
                        firstPointer = i + 1;
                        break;
                    }
                }
                bufferCount[number]--;
            }
            secondPointer++;
            result = Math.max(result, secondPointer - firstPointer);
        }

        System.out.println(result);
    }
}
