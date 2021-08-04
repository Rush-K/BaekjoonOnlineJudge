/** Baekjoon Online Judge
 *   Problem No. 1202
 *   보석 도둑
 *   Writed by Rush.K
 *   Greedy Algorithm, Priority Queue 이용
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Jewel {
    int weight;
    int price;

    public Jewel(int _weight, int _price) {
        weight = _weight;
        price = _price;
    }
}
public class P1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Jewel[] jewels;
        int[] bags;
        PriorityQueue<Integer> waitingJewels;

        Jewel jewel;
        int N, K, jewelIndex;
        long answer = 0;

        String[] input;

        input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        jewels = new Jewel[N];

        for (int i = 0; i < N; i++) { // 보석 정보 받아오기
            input = br.readLine().split(" ");
            jewels[i] = new Jewel(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        }

        Arrays.sort(jewels, new Comparator<Jewel>() { // 무게 오름차순으로 보석 정렬
            @Override
            public int compare(Jewel o1, Jewel o2) {
                return o1.weight > o2.weight ? 1 : -1;
            }
        });

        bags = new int[K];

        for (int i = 0; i < K; i++) bags[i] = Integer.parseInt(br.readLine()); // 가방 무게 가져오기
        Arrays.sort(bags); // 가방 무게 오름차순으로 정렬


        waitingJewels = new PriorityQueue<>(new Comparator<Integer>() { // 보석 대기 우선수위 큐 (가격 내림차순)
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 > o2 ? -1 : 1;
            }
        });

        jewelIndex = 0;

        for (int i = 0; i < bags.length; i++) { // 가방 무게보다 낮은 보석들을 보석 대기 우선순위 큐로 옮김
            while (jewelIndex < N && bags[i] >= jewels[jewelIndex].weight) {
                waitingJewels.add(jewels[jewelIndex++].price);
            }
            if (!waitingJewels.isEmpty()) answer += waitingJewels.poll(); // 큐 HEAD 위치한 가격 값을 더함 ( = 가방 무게에 알맞으면서 가장 높은 가격)
        }

        System.out.println(answer); // 값 출력
    }
}

