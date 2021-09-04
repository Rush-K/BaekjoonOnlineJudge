/** Baekjoon Online Judge
 *   Problem No. 2470
 *   두 용액
 *   Writed by Rush.K
 *   Priority Queue 이용
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class P2470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int sum = Integer.MAX_VALUE;
        String[] liquids = br.readLine().split(" ");
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() { // 절댓값 기준 우선순위 큐
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.abs(o1) > Math.abs(o2) ? 1: -1;
            }
        });

        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(liquids[i]));
        }

        int firstAnswer = 0;
        int secondAnswer = 0;

        int firstliquid = pq.poll();
        int secondliquid = 0;
        while (!pq.isEmpty()) { // 인접한 수의 합을 비교함
            secondliquid = pq.poll();
            if (Math.abs(firstliquid + secondliquid) < sum) {
                firstAnswer = firstliquid;
                secondAnswer = secondliquid;
                sum = Math.abs(firstliquid + secondliquid);
            }
            firstliquid = secondliquid;
        }

        System.out.println(firstAnswer > secondAnswer ? secondAnswer + " " + firstAnswer : firstAnswer + " " + secondAnswer);
    }
}
