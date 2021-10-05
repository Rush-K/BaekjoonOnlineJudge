/** Baekjoon Online Judge
 *   Problem No. 1911
 *   흙길 보수하기
 *   Writed by Rush.K
 *   Greedy Algorithm 이용
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

class Pool { // 물 웅덩이 클래스
    int start;
    int end;

    public Pool(int _start, int _end) {
        this.start = _start;
        this.end = _end;
    }
}

public class P1911 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int L = Integer.parseInt(input[1]);
        PriorityQueue<Pool> pq = new PriorityQueue<>(new Comparator<Pool>() { // 물 웅덩이 오름차순 정렬
            @Override
            public int compare(Pool o1, Pool o2) {
                return o1.start > o2.start ? 1 : -1;
            }
        });

        int result = 0;
        int pointer = -1; // 널빤지 설치가 완료된 가장 최근 위치

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            pq.add(new Pool(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }

        while (!pq.isEmpty()) {
            Pool pool = pq.poll();

            if (pool.end <= pool.start) continue; // 확인할 필요 없으므로 Pass
            if (pool.end <= pointer + 1) continue; // 확인할 필요 없으므로 Pass
            if (pool.start <= pointer) pool.start = pointer + 1;

            if ((pool.end - pool.start) % L == 0) { // 널빤지가 딱 맞아 떨어질 때
                result += (pool.end - pool.start) / L;
                pointer = pool.end - 1;
            } else { // 널빤지가 삐져나오는 경우
                result += (pool.end - pool.start) / L;
                result += 1;
                pointer = pool.end - 1 + L - ((pool.end - pool.start) % L);
            }
        }

        System.out.println(result); // 결과 출력
    }
}
