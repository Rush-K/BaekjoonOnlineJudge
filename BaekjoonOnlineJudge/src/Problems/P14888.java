/** Baekjoon Online Judge
 *   Problem No. 14888
 *   연산자 끼워넣기
 *   Writed by Rush.K
 *   브루트포스(순열) 이용
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P14888 {
    public static int[] numbers;
    public static int[] operations;
    public static int maxResult;
    public static int minResult;

    public static int yield(int a, int operation, int b) { // 연산자 이용 결과 계산
        switch(operation) {
            case 0:
                return a + b;
            case 1:
                return a - b;
            case 2:
                return a * b;
            case 3:
                return a / b;
        }
        return 0;
    }

    public static void swap(int a, int b) {
        int temp = operations[a];
        operations[a] = operations[b];
        operations[b] = temp;
    }

    public static void permutation(int depth, int r) { // 순열
        if (depth == r) {
            int result = 0;
            for (int i = 0; i < operations.length; i++) {
                if (i == 0) result = yield(numbers[0], operations[0], numbers[1]);
                else result = yield(result, operations[i], numbers[i + 1]);
            }
            maxResult = Math.max(maxResult, result);
            minResult = Math.min(minResult, result);
            return;
        }

        for (int i = depth; i < operations.length; i++) {
            swap(depth, i);
            permutation(depth + 1, r);
            swap(depth, i);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        numbers = new int[N];
        operations = new int[N - 1];
        maxResult = Integer.MIN_VALUE;
        minResult = Integer.MAX_VALUE;

        String[] input = br.readLine().split(" ");

        for (int i = 0; i < input.length; i++) {
            numbers[i] = Integer.parseInt(input[i]);
        }

        input = br.readLine().split(" ");

        int index = 0;
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < Integer.parseInt(input[i]); j++) {
                operations[index++] = i;
            }
        }

        permutation(0, N - 1);
        System.out.println(maxResult);
        System.out.println(minResult);
    }
}
