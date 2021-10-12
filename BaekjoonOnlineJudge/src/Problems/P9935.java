/** Baekjoon Online Judge
 *   Problem No. 9935
 *   문자열 폭발
 *   Writed by Rush.K
 *   Stack 이용
 */

/**
 *  replace 함수는 메모리를 많이 잡아먹는다!
 *  StringBuilder : 문자열 연산 시, 새로운 객체를 생성하지 않음! 적극 사용 권장!
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        char[] bomb = br.readLine().toCharArray();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length; i++) {
            stack.push(input[i]);
            if (stack.size() >= bomb.length) { // 폭발 문자열 길이가 되면, 폭발 문자열인지 확인하고 처리함
                boolean canPop = false;
                for (int j = 0; j < bomb.length; j++) {
                    if (stack.get(stack.size() - bomb.length + j) != bomb[j]) break;
                    if (j == bomb.length - 1) canPop = true;
                }

                if (canPop) {
                    for (int j = 0; j < bomb.length; j++) stack.pop();
                }
            }
        }

        StringBuilder result = new StringBuilder(); // 적극 권장!

        for (int i = 0; i < stack.size(); i++) result.append(stack.get(i));

        if (result.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(result);
        }
    }
}
