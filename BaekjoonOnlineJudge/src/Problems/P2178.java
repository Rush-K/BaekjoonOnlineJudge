/** Baekjoon Online Judge
 *   Problem No. 2178
 *   미로 탐색
 *   Writed by Rush.K
 *   BFS
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Location {
    int x;
    int y;
    int num;

    public Location(int _x, int _y, int _num) {
        this.x = _x;
        this.y = _y;
        this.num = _num;
    }
}

public class P2178 {
    public static Queue<Location> q;
    public static int[][] map;

    public static void addLocation(Location loc) {
        map[loc.x][loc.y] = 0;
        q.add(loc);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        map = new int[N][M];

        char[] mapInput;
        for (int i = 0; i < N; i++) {
            mapInput = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(mapInput[j]));
            }
        }

        q = new LinkedList<>();

        Location loc = new Location(0, 0, 1);
        q.add(loc);

        while (!q.isEmpty()) { // BFS 이용 : 방문한 곳은 들리지 않음
            loc = q.poll();

            if (loc.x == N - 1 && loc.y == M - 1) break;

            if (loc.x == 0) {
                if (loc.y == 0) {
                    if (map[loc.x + 1][loc.y] == 1) addLocation(new Location(loc.x + 1, loc.y, loc.num + 1));
                    if (map[loc.x][loc.y + 1] == 1) addLocation(new Location(loc.x, loc.y + 1, loc.num + 1));
                } else if (loc.y == M - 1) {
                    if (map[loc.x][loc.y - 1] == 1) addLocation(new Location(loc.x, loc.y - 1, loc.num + 1));
                    if (map[loc.x + 1][loc.y] == 1) addLocation(new Location(loc.x + 1, loc.y, loc.num + 1));
                } else {
                    if (map[loc.x][loc.y - 1] == 1) addLocation(new Location(loc.x, loc.y - 1, loc.num + 1));
                    if (map[loc.x][loc.y + 1] == 1) addLocation(new Location(loc.x, loc.y + 1, loc.num + 1));
                    if (map[loc.x + 1][loc.y] == 1) addLocation(new Location(loc.x + 1, loc.y, loc.num + 1));
                }
            } else if (loc.x == N - 1) {
                if (loc.y == 0) {
                    if (map[loc.x - 1][loc.y] == 1) addLocation(new Location(loc.x - 1, loc.y, loc.num + 1));
                    if (map[loc.x][loc.y + 1] == 1) addLocation(new Location(loc.x, loc.y + 1, loc.num + 1));
                } else if (loc.y == M - 1) {
                    if (map[loc.x - 1][loc.y] == 1) addLocation(new Location(loc.x - 1, loc.y, loc.num + 1));
                    if (map[loc.x][loc.y - 1] == 1) addLocation(new Location(loc.x, loc.y - 1, loc.num + 1));
                } else {
                    if (map[loc.x][loc.y - 1] == 1) addLocation(new Location(loc.x, loc.y - 1, loc.num + 1));
                    if (map[loc.x][loc.y + 1] == 1) addLocation(new Location(loc.x, loc.y + 1, loc.num + 1));
                    if (map[loc.x - 1][loc.y] == 1) addLocation(new Location(loc.x - 1, loc.y, loc.num + 1));
                }
            } else {
                if (loc.y == 0) {
                    if (map[loc.x - 1][loc.y] == 1) addLocation(new Location(loc.x - 1, loc.y, loc.num + 1));
                    if (map[loc.x + 1][loc.y] == 1) addLocation(new Location(loc.x + 1, loc.y, loc.num + 1));
                    if (map[loc.x][loc.y + 1] == 1) addLocation(new Location(loc.x, loc.y + 1, loc.num + 1));
                } else if (loc.y == M - 1) {
                    if (map[loc.x - 1][loc.y] == 1) addLocation(new Location(loc.x - 1, loc.y, loc.num + 1));
                    if (map[loc.x + 1][loc.y] == 1) addLocation(new Location(loc.x + 1, loc.y, loc.num + 1));
                    if (map[loc.x][loc.y - 1] == 1) addLocation(new Location(loc.x, loc.y - 1, loc.num + 1));
                } else {
                    if (map[loc.x - 1][loc.y] == 1) addLocation(new Location(loc.x - 1, loc.y, loc.num + 1));
                    if (map[loc.x + 1][loc.y] == 1) addLocation(new Location(loc.x + 1, loc.y, loc.num + 1));
                    if (map[loc.x][loc.y - 1] == 1) addLocation(new Location(loc.x, loc.y - 1, loc.num + 1));
                    if (map[loc.x][loc.y + 1] == 1) addLocation(new Location(loc.x, loc.y + 1, loc.num + 1));
                }
            }
        }

        System.out.println(loc.num);
    }
}
