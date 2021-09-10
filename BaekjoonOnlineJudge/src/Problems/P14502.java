/** Baekjoon Online Judge
 *   Problem No. 14502
 *   연구소
 *   Writed by Rush.K
 *   Combination(DFS), BFS 이용
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


class Virus {
    int x;
    int y;

    public Virus(int _x, int _y) {
        this.x = _x;
        this.y = _y;
    }
}

public class P14502 {
    public static int N;
    public static int M;
    public static int[][] map;
    public static int result;

    public static int findSafetyZone() { // 바이러스를 퍼뜨린 후, 안전 지역 갯수 구하기
        Queue<Virus> q = new LinkedList<>();
        int[][] workingMap = new int[N][M];
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int answer = 0;

        for (int i =0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                workingMap[i][j] = map[i][j];
                if (workingMap[i][j] == 2) {
                    Virus virus = new Virus(i,j);
                    q.add(virus);
                }
            }
        }

        while (!q.isEmpty()) { // BFS : 바이러스 퍼뜨리기
            Virus virus = q.poll();
            for (int i = 0; i < directions.length; i++) {
                try {
                    if (workingMap[virus.x - directions[i][0]][virus.y - directions[i][1]] == 0) {
                        workingMap[virus.x - directions[i][0]][virus.y - directions[i][1]] = 2;
                        q.add(new Virus(virus.x - directions[i][0], virus.y - directions[i][1]));
                    }
                } catch (Exception e) {}
            }
        }

        for (int i = 0; i < N; i++) { // 안전 지역 갯수 구하기
            for (int j = 0; j < M; j++) {
                if (workingMap[i][j] == 0) {
                    answer++;
                }
            }
        }

        return answer; // 안전 지역 갯수 반환
    }
    public static void dfs(int startX, int startY, int newWalls) { // 임의의 3개의 벽 세우기
        if (newWalls == 3) { // 벽 3개가 건설된 경우 답 구해보기
            result = Math.max(result, findSafetyZone()); // 최댓값 도출
            return;
        }

        for (int i = startX; i < N; i++) {
            int j = startY;
            if (i > startX) j = 0;
            for (; j < M; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    newWalls++;
                    dfs(i, j, newWalls);
                    map[i][j] = 0;
                    newWalls--;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N][M];
        result = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        dfs(0, 0, 0);
        System.out.println(result == Integer.MIN_VALUE ? 0 : result);
    }
}
