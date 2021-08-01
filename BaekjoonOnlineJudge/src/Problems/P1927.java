/** Baekjoon Online Judge
 *   Problem No. 1927
 *   최소 힙
 *   Writed by Rush.K
 *   Using Priority Queue
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class P1927 {
    public static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>(new Comparator<Integer>() { // 오름차순 정렬되는 Priority Queue
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(br.readLine());
            if (number == 0) {
                if (pq.size() == 0) sb.append("0" + "\n"); // pq가 비어있을 경우
                else sb.append(pq.poll() + "\n");
            } else {
                pq.add(number);
            }
        }

        System.out.print(sb);
    }
}
