/** Baekjoon Online Judge
 *   Problem No. 1644
 *   소수의 연속합
 *   Writed by Rush.K
 *   에라토스테네스의 체 이용
 */

package Problems;

import java.util.ArrayList;
import java.util.Scanner;

public class P1644 {
    public static int answer = 0;

    public static int[] arrayOfPrimeNumber(int N) { // 에라토스테네스의 체를 통한 소수 배열 반환
        ArrayList<Integer> result = new ArrayList<>();
        boolean[] numberList = new boolean[N + 1];

        int root = (int) Math.sqrt(N);

        for (int i = 2; i <= root; i++) {
            if (numberList[i] == false) {
                for (int j = i; i * j <= N; j++) {
                    numberList[i * j] = true;
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            if (numberList[i] == false) result.add(i);
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    public static int sum(int[] primeNumbers, int startIndex, int Count, int N) { // startIndex 부터 Count 개 만큼 더한 값 반환
        int sum = 0;

        for (int i = startIndex; i < startIndex + Count; i++) {
            if (sum > N) return -1;
            sum += primeNumbers[i];
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] primeNumbers = arrayOfPrimeNumber(N); // 소수들 받아오기
        boolean[] visited = new boolean[primeNumbers.length];

        for (int i = 1; i <= primeNumbers.length; i++) {
            for (int j = 0; j < primeNumbers.length - i + 1; j++) {
                int resultSum = sum(primeNumbers, j, i, N);
                if (resultSum == N) answer++; // 값이 N과 일치하는 경우
                else if (resultSum == -1) break; // 값이 N을 초과하는 경우
            }
        }

        System.out.println(answer); // 최종 경우의 수 반환
    }
}
