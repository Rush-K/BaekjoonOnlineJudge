/** Baekjoon Online Judge
 *   Problem No. 1655
 *   MooTube (Silver)
 *   Writed by Rush.K
 *   Heap 이용
 */

// 왜 분할 정복으로 했을 땐 시간 초과일까? 같은 O(nlogn) 인 것 같은데..

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;


public class P1655 {
    public static PriorityQueue<Integer> ascendingQueue; // 최소 힙
    public static PriorityQueue<Integer> descendingQueue; // 최대 힙

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        ascendingQueue = new PriorityQueue<>(new Comparator<Integer>() { // 최소 힙 구현
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 > o2 ? 1 : -1;
            }
        });

        descendingQueue = new PriorityQueue<>(new Comparator<Integer>() { // 최대 힙 구현
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 > o2 ? -1 : 1;
            }
        });



        for (int i = 0; i < N; i++) {
            int K = Integer.parseInt(br.readLine());
            if (ascendingQueue.size() == descendingQueue.size()) { // 두 힙의 사이즈가 같을 경우 (짝수일 경우)
                if (ascendingQueue.peek() == null && descendingQueue.peek() == null) descendingQueue.add(K); // 두 힙이 비어있을 때, 최대 힙에 삽입
                else { // 두 힙이 비어있지 않을 경우 상황에 맞게 삽입 ( 최소 힙이 한개 더 많도록 함)
                    if (ascendingQueue.peek() >= K) descendingQueue.add(K);
                    else {
                        ascendingQueue.add(K);
                        descendingQueue.add(ascendingQueue.poll());
                    }
                }
            } else { // 두 힙의 사이즈가 다를 경우 (홀수일 경우)
                if (ascendingQueue.peek() == null) { // 최소 힙이 비어있을 경우
                    if (descendingQueue.peek() < K) ascendingQueue.add(K);
                    else {
                        descendingQueue.add(K);
                        ascendingQueue.add(descendingQueue.poll());
                    }
                } else { // 두 힙이 비어있지 않을 경우 상황에 맞게 삽입 ( 두 힙의 크기가 같아지도록 함)
                    if (ascendingQueue.peek() < K) {
                        ascendingQueue.add(K);
                    } else {
                        descendingQueue.add(K);
                        ascendingQueue.add(descendingQueue.poll());
                    }
                }
            }
            sb.append(descendingQueue.peek() + "\n"); // 결과 삽입
        }

        System.out.print(sb); // 결과 출력
    }
}

/* 분할 정복 사용한 코드

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class P1655 {
    public static ArrayList<Integer> tree;

    public static void divideConquer(int K, int start, int end) {
        int root = 0;
        while (start != end) {
            root = (end + start + 1) / 2;
            if (K > tree.get(root)) {
                start = root + 1;
            } else {
                end = root - 1;
            }
        }

        if (tree.get(start) < K) tree.add(start + 1,K);
        else tree.add(start, K);
        return;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int root = 0;
        tree = new ArrayList<>();

        int K = Integer.parseInt(br.readLine());
        tree.add(K);
        sb.append(tree.get(root) + "\n");

        for (int i = 1; i < N; i++) {
            K = Integer.parseInt(br.readLine());
            divideConquer(K, 0, tree.size() - 1);
            if (tree.size() % 2 == 0) root = tree.size() / 2 - 1;
            else root = tree.size() / 2;
            sb.append(tree.get(root) + "\n");
        }

        System.out.print(sb);
    }
}


 */