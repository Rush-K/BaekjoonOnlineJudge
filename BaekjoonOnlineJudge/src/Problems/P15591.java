/** Baekjoon Online Judge
 *   Problem No. 15591
 *   MooTube (Silver)
 *   Writed by Rush.K
 *   Graph BFS
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Relation { // 특정 노드와 연결되는 노드 정보
    int node;
    int usado;

    public Relation(int _node, int _usado) {
        this.node = _node;
        this.usado = _usado;
    }
}

public class P15591 {
    public static ArrayList<ArrayList<Relation>> network; // 인접리스트로 그래프 구현

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Queue<Relation> bfsQ = new LinkedList<>();
        int N, Q, answer;
        int[][] questions;
        String[] input;

        input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        Q = Integer.parseInt(input[1]);

        network = new ArrayList<ArrayList<Relation>>();
        for (int i = 0; i <= N; i++) network.add(new ArrayList<Relation>());

        for (int i = 0; i < N - 1; i++) {
            input = br.readLine().split(" ");
            network.get(Integer.parseInt(input[0])).add(new Relation(Integer.parseInt(input[1]), Integer.parseInt(input[2])));
            network.get(Integer.parseInt(input[1])).add(new Relation(Integer.parseInt(input[0]), Integer.parseInt(input[2])));
        }

        questions = new int[Q][2];

        for (int i = 0; i < Q; i++) {
            input = br.readLine().split(" ");
            questions[i][0] = Integer.parseInt(input[0]);
            questions[i][1] = Integer.parseInt(input[1]);
        }

        for (int i = 0; i < Q; i++) { // 결과 만들기
            answer = 0;
            boolean[] visited = new boolean[N + 1]; // 노드 방문 여부 배열
            visited[questions[i][1]] = true; // 첫 노드 방문
            for (Relation rel : network.get(questions[i][1])) bfsQ.add(rel); // 첫 노드에서 연결되는 노드들 Queue에 추가

            while (!bfsQ.isEmpty()) {
                Relation rel = bfsQ.poll();
                if (visited[rel.node] == false) { // 아직 방문하지 않은 노드일 경우
                    visited[rel.node] = true;
                    if (rel.usado >= questions[i][0]) answer++; // USADO가 K 이상일 경우 답 추가

                    for (Relation r : network.get(rel.node)) { // 아직 방문하지 않은 파생 노드 추가
                        if (visited[r.node] == false) {
                            bfsQ.add(new Relation(r.node, Math.min(r.usado, rel.usado))); // usado는 최소가 되는 것으로 추가
                        }
                    }
                }
            }
            sb.append(answer + "\n");
        }

        System.out.print(sb);
    }
}
