/** Baekjoon Online Judge
 *   Problem No. 11401
 *   이항 계수 3
 *   Writed by Rush.K
 *   modular, 페르마의 소정리
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** 꼭 알아야 할 정리!
 *
 * 1. modular 정리들
 *  - (a + b) mod q = (a mod q + b mod q) mod q
 *  - (a - b) mod q = (a mod q - b mod q) mod q
 *  - (a * b) mod q = (a mod q * b mod q) mod q
 *  - a mod q 의 modular inverse = a * b mod q = 1 일때, b mod q의 값 (a, b는 서로소)
 *  - 큰 수의 modular 계산은 분할 정복 이용
 *
 * 2. 페르마의 소정리
 *  - A^p mod p ≡ A (mod p) (p = 소수, A와 p는 서로소)
 *
 * + TIP
 *  - 1000000007 은 10억 이상의 가장 작은 소수
 *
 *  Reference : https://st-lab.tistory.com/241
 */

public class P11401 {
    public static long p = 1000000007;

    public static long factorial(int number) { // 팩토리얼 + modular
        long answer = 1;
        for (int i = 1; i <= number; i++) {
            answer = (answer * i) % p;
        }
        return answer;
    }

    public static long fermatLittleTheorem(int N, int K) { // 페르마의 소정리
        return (factorial(N) % p) * pow((factorial(K) * factorial(N - K) % p), p - 2) % p;
    }

    public static long pow(long base, long exp) { // 분할 정복
        long result = 1;

        while (exp > 0) {
            if (exp % 2 == 1) { // 지수가 홀수일 경우
                result *= base;
                result %= p;
            }

            base = (base * base) % p;
            exp /= 2;
        }

        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        System.out.println(fermatLittleTheorem(N, K));
    }
}
