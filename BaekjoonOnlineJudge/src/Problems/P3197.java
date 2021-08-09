/** Baekjoon Online Judge
 *   Problem No. 3197
 *   백조의 호수
 *   Writed by Rush.K
 *   BFS
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class SwanPosition {
    int x;
    int y;

    public SwanPosition(int _x, int _y) {
        this.x = _x;
        this.y = _y;
    }
}

public class P3197 {
    public static ArrayList<SwanPosition> swans; // 백조의 위치들
    public static char[][] map; // 지도
    public static Queue<SwanPosition> updateList; // 얼음 업데이트 해야할 목록
    public static Queue<SwanPosition> newPathList; // 백조 이동 범위 업데이트해야할 목록
    public static boolean[][] visited; // 백조 이동 기록

    public static void iceToWater(int x, int y) { // 얼음 -> 물로 바꾸기
        try {
            if (map[x - 1][y] == 'X') {
                map[x - 1][y] = '.';
                updateList.add(new SwanPosition(x - 1, y));
            }
        } catch (Exception e) {}

        try {
            if (map[x + 1][y] == 'X') {
                map[x + 1][y] = '.';
                updateList.add(new SwanPosition(x + 1, y));
            }
        } catch (Exception e) {}

        try {
            if (map[x][y - 1] == 'X') {
                map[x][y - 1] = '.';
                updateList.add(new SwanPosition(x, y - 1));
            }
        } catch (Exception e) {}

        try {
            if (map[x][y + 1] == 'X') {
                map[x][y + 1] = '.';
                updateList.add(new SwanPosition(x, y + 1));
            }
        } catch (Exception e) {}
    }

    public static boolean isMeetingComplete(int R, int C) { // 두 마리의 백조가 만났는가? : BFS
        Queue<SwanPosition> q = new LinkedList<>();

        while (!newPathList.isEmpty()) q.add(newPathList.poll());

        while (!q.isEmpty()) {
            SwanPosition swan = q.poll();
            boolean isPath = false;

            if (swan.x == swans.get(1).x && swan.y == swans.get(1).y) return true;

            try {
                if (map[swan.x - 1][swan.y] != 'X' && visited[swan.x - 1][swan.y] == false) {
                    q.add(new SwanPosition(swan.x - 1, swan.y));
                    visited[swan.x - 1][swan.y] = true;
                    isPath = true;
                }
            } catch (Exception e) {}

            try {
                if (map[swan.x + 1][swan.y] != 'X' && visited[swan.x + 1][swan.y] == false) {
                    q.add(new SwanPosition(swan.x + 1, swan.y));
                    visited[swan.x + 1][swan.y] = true;
                    isPath = true;
                }
            } catch (Exception e) {}

            try {
                if (map[swan.x][swan.y - 1] != 'X' && visited[swan.x][swan.y - 1] == false) {
                    q.add(new SwanPosition(swan.x, swan.y - 1));
                    visited[swan.x][swan.y - 1] = true;
                    isPath = true;
                }
            } catch (Exception e) {}

            try {
                if (map[swan.x][swan.y + 1] != 'X' && visited[swan.x][swan.y + 1] == false) {
                    q.add(new SwanPosition(swan.x, swan.y + 1));
                    visited[swan.x][swan.y + 1] = true;
                    isPath = true;
                }
            } catch (Exception e) {}

            if (isPath == false) {
                newPathList.add(swan);
            }
        }

        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int answer = 0;

        int R = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);

        swans = new ArrayList<>();
        map = new char[R][C];
        updateList = new LinkedList<>();
        newPathList = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            char[] mapInput = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = mapInput[j];

                if (map[i][j] == '.') {
                    updateList.add(new SwanPosition(i, j));
                } else if (map[i][j] == 'L') {
                    updateList.add(new SwanPosition(i, j));
                    swans.add(new SwanPosition(i, j));
                    map[i][j] = '.';
                }
            }
        }

        newPathList.add(swans.get(0));
        visited = new boolean[R][C];

        while (!isMeetingComplete(R, C)) { // 백조들이 만날 때 까지
            int update = updateList.size();
            while (update-- != 0) { // 얼음 업데이트
                SwanPosition sp = updateList.poll();
                iceToWater(sp.x, sp.y);
            }
            answer++;
        }

        System.out.println(answer);
    }
}
