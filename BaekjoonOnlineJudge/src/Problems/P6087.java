/** Baekjoon Online Judge
 *   Problem No. 6087
 *   레이저 통신
 *   Writed by Rush.K
 *   Dijkstra, BFS
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class C { // 위치 클래스
    int x;
    int y;
    int mirror;
    int[] direction;

    public C(int _x, int _y) {
        this.x = _x;
        this.y = _y;
        this.mirror = 0;
        this.direction = new int[]{0, 0};
    }

    public C(int _x, int _y, int _mirror, int[] _direction) {
        this.x = _x;
        this.y = _y;
        this.mirror = _mirror;
        this.direction = _direction.clone();
    }
}
public class P6087 {
    public static char[][] map;
    public static int[][] visited;

    public static boolean correctnessDirection(int[] dir, int[] dir_two) { // 방향 일치 여부 반환
        if (dir[0] == dir_two[0] && dir[1] == dir_two[1]) {
            return true;
        }
        return false;
    }

    public static void razerPathFinder(C start, C end) {
        PriorityQueue<C> pq = new PriorityQueue<>(new Comparator<C>() { // 거울을 최소로 사용한 경로부터 탐색하기 위한 우선순위 큐
            @Override
            public int compare(C o1, C o2) {
                return o1.mirror > o2.mirror ? 1 : -1;
            }
        });
        visited[start.x][start.y] = 0;

        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int i = 0; i < direction.length; i++) {
            try {
                if (map[start.x + direction[i][0]][start.y + direction[i][1]] != '*') {
                    visited[start.x + direction[i][0]][start.y + direction[i][1]] = 0;
                    pq.add(new C(start.x + direction[i][0], start.y + direction[i][1], 0, direction[i]));
                }
            } catch (Exception e) {}
        }

        while (!pq.isEmpty()) { // BFS 탐색
            C pos = pq.poll();

            if (pos.x == end.x && pos.y == end.y) {
                return;
            }

            for (int i = 0; i < direction.length; i++) {
                try {
                    if (map[pos.x + direction[i][0]][pos.y + direction[i][1]] != '*') {
                        if (correctnessDirection(direction[i], pos.direction)) { // 같은 방향성
                            if (visited[pos.x + direction[i][0]][pos.y + direction[i][1]] == Integer.MAX_VALUE || visited[pos.x + direction[i][0]][pos.y + direction[i][1]] >= pos.mirror) { // 거울 최솟값 업데이트
                                visited[pos.x + direction[i][0]][pos.y + direction[i][1]] = pos.mirror;
                                pq.add(new C(pos.x + direction[i][0], pos.y + direction[i][1], pos.mirror, direction[i]));
                            }
                        } else { // 다른 방향성
                            if (visited[pos.x + direction[i][0]][pos.y + direction[i][1]] == Integer.MAX_VALUE || visited[pos.x + direction[i][0]][pos.y + direction[i][1]] >= pos.mirror + 1) { // 거울 최솟값 업데이트
                                visited[pos.x + direction[i][0]][pos.y + direction[i][1]] = pos.mirror + 1;
                                pq.add(new C(pos.x + direction[i][0], pos.y + direction[i][1], pos.mirror + 1, direction[i]));
                            }
                        }
                    }
                } catch (Exception e) {}
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int W = Integer.parseInt(input[0]);
        int H = Integer.parseInt(input[1]);
        map = new char[H][W];
        visited = new int[H][W];
        int[][] cPosition = new int[2][2];
        int cNum = 0;

        for (int i = 0; i < H; i++) {
            char[] mapInput = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                map[i][j] = mapInput[j];
                visited[i][j] = Integer.MAX_VALUE;
                if (map[i][j] == 'C') {
                    cPosition[cNum][0] = i;
                    cPosition[cNum][1] = j;
                    map[i][j] = '.';
                    cNum++;
                }
            }
        }

        razerPathFinder(new C(cPosition[0][0], cPosition[0][1]), new C(cPosition[1][0], cPosition[1][1]));
        System.out.println(visited[cPosition[1][0]][cPosition[1][1]]);
    }
}
