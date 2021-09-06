/** Baekjoon Online Judge
 *   Problem No. 1238
 *   파티
 *   Writed by Rush.K
 *   Dijkstra 알고리즘 이용
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class Road { // 간선 클래스
    int destination;
    int time;
    int totalTime;

    public Road(int _destination, int _time) {
        this.destination = _destination;
        this.time = _time;
        this.totalTime = 0;
    }

    public Road(int _destination, int _time, int _totalTime) {
        this.destination = _destination;
        this.time = _time;
        this.totalTime = _totalTime;
    }
}

public class P1238 {
    public static ArrayList<ArrayList<Road>> map;
    public static boolean[] visited;
    public static PriorityQueue<Road> pq;

    public static int dijkstra(int N, int start, int X) { // start -> X 최단거리 탐색
        int sum = 0;

        pq = new PriorityQueue<>(new Comparator<Road>() { // 가중치 합이 가장 적은것 부터 정렬
            @Override
            public int compare(Road o1, Road o2) {
                return o1.totalTime > o2.totalTime ? 1 : -1;
            }
        });

        visited = new boolean[N + 1];
        visited[start] = true;

        for (Road road : map.get(start)) {
            Road newRoad = new Road(road.destination, road.time);
            newRoad.totalTime += road.time;
            pq.add(newRoad);
        }

        while(!pq.isEmpty()) {
            Road road = pq.poll();

            visited[road.destination] = true;

            if (road.destination == X) {
                sum += road.totalTime;
                break;
            }

            for (Road nextRoad : map.get(road.destination)) {
                if (visited[nextRoad.destination] != true) {
                    Road newRoad = new Road(nextRoad.destination, nextRoad.time, road.totalTime + nextRoad.time);
                    pq.add(newRoad);
                }
            }
        }
        return sum;
    }

    public static int bfs(int start, int X, int N) {
        int result = 0;

        result += dijkstra(N, start, X);
        result += dijkstra(N, X, start);

        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int X = Integer.parseInt(input[2]);

        map = new ArrayList<ArrayList<Road>>();

        for (int i = 0; i <= N; i++) {
            map.add(new ArrayList<Road>());
        }

        for (int i = 0 ; i < M; i++) {
            input = br.readLine().split(" ");
            Road newRoad = new Road(Integer.parseInt(input[1]), Integer.parseInt(input[2]), N);
            map.get(Integer.parseInt(input[0])).add(newRoad);
        }

        int result = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            if (i == X) {
                continue;
            } else{
                int value = bfs(i, X, N);
                result = Math.max(result, value); // 가장 오래 걸리는 사람 추출
            }
        }

        System.out.println(result);
    }
}
