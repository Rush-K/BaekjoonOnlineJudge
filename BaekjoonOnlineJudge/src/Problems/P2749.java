/** Baekjoon Online Judge
 *   Problem No. 2749
 *   피보나치 수 3
 *   Writed by Rush.K
 *   파사노 주기, modular 연산
 */

package Problems;

import java.util.Scanner;

/** 피사노 주기
 * 피보나치 수를 특정 수로 나눈 값이 일정한 주기를 가지는 것.
 * 피보나치 수를 K(=10^n)로 나눌 때, 15 * 10^(n - 1)의 주기를 가진다.
 */

public class P2749 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        int pisanoPeriod = 15 * (int)Math.pow(10, 5);
        long[] fibonacci = new long[pisanoPeriod];

        fibonacci[0] = 0;
        fibonacci[1] = 1;

        if (n == 0) {
            System.out.println(0);
            return;
        } else if (n == 1) {
            System.out.println(1);
            return;
        }

        for (int i = 2; i < pisanoPeriod; i++) { // 파사노 주기만큼 피보나치 수 배열 값 생성
            fibonacci[i] = (fibonacci[i - 1] % 1000000 + fibonacci[i - 2] % 1000000) % 1000000;
        }

        System.out.println(fibonacci[(int) (n % pisanoPeriod)]); // 해당 값 출력
    }
}
