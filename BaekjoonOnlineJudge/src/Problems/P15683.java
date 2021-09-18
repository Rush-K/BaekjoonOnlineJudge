/** Baekjoon Online Judge
 *   Problem No. 15683
 *   감시
 *   Writed by Rush.K
 *   브루트포스(dfs) 이용
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class CCTV {
    public int cctvX;
    public int cctvY;
    public int direction;

    public CCTV(int _cctvX, int _cctvY, int _direction) {
        this.cctvX = _cctvX;
        this.cctvY = _cctvY;
        this.direction = _direction;
    }
}
public class P15683 {
    public static int N;
    public static int M;
    public static int[][] map;
    public static int[][] visited;
    public static ArrayList<ArrayList<CCTV>> cctvList;
    public static int answer;

    public static void updateMap(CCTV cctv, boolean backUp) { // 해당 cctv로 감시 가능한 영역 업데이트
        if (backUp) visited[cctv.cctvX][cctv.cctvY]--;
        else visited[cctv.cctvX][cctv.cctvY]++;

        if ((cctv.direction & (1 << 3)) != 0) { // North
            for (int i = cctv.cctvX - 1; i >= 0; i--) {
                if (map[i][cctv.cctvY] == 6) break;
                else {
                    if (backUp) visited[i][cctv.cctvY]--;
                    else visited[i][cctv.cctvY]++;
                }
            }
        }
        if ((cctv.direction & (1 << 2)) != 0) { // East
            for (int i = cctv.cctvY + 1; i < M; i++) {
                if (map[cctv.cctvX][i] == 6) break;
                else {
                    if (backUp) visited[cctv.cctvX][i]--;
                    else visited[cctv.cctvX][i]++;
                }
            }
        }
        if ((cctv.direction & (1 << 1)) != 0) { // South
            for (int i = cctv.cctvX + 1; i < N; i++) {
                if (map[i][cctv.cctvY] == 6) break;
                else {
                    if (backUp) visited[i][cctv.cctvY]--;
                    else visited[i][cctv.cctvY]++;
                }
            }
        }
        if ((cctv.direction & 1) != 0) { // West
            for (int i = cctv.cctvY - 1; i >= 0; i--) {
                if (map[cctv.cctvX][i] == 6) break;
                else {
                    if (backUp) visited[cctv.cctvX][i]--;
                    else visited[cctv.cctvX][i]++;
                }
            }
        }
    }

    public static void dfs(int depth) { // dfs
        if (depth == cctvList.size()) {
            int result = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visited[i][j] == 0 && map[i][j] == 0) result++;
                }
            }
            answer = Math.min(answer, result);
            return;
        }

        for (int i = 0; i < cctvList.get(depth).size(); i++) {
            updateMap(cctvList.get(depth).get(i), false);
            dfs(depth + 1);
            updateMap(cctvList.get(depth).get(i), true);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        ArrayList<CCTV> initialCCTVList = new ArrayList<>();

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        cctvList = new ArrayList<ArrayList<CCTV>>();

        map = new int[N][M];
        visited = new int[N][M];
        answer = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input[j]);
                CCTV cctv;
                switch(map[i][j]) {
                    case 1:
                        cctvList.add(new ArrayList<CCTV>());
                        cctv = new CCTV(i, j, 1);
                        cctvList.get(cctvList.size() - 1).add(cctv);
                        cctv = new CCTV(i, j, 1 << 1);
                        cctvList.get(cctvList.size() - 1).add(cctv);
                        cctv = new CCTV(i, j, 1 << 2);
                        cctvList.get(cctvList.size() - 1).add(cctv);
                        cctv = new CCTV(i, j, 1 << 3);
                        cctvList.get(cctvList.size() - 1).add(cctv);
                        break;
                    case 2:
                        cctvList.add(new ArrayList<CCTV>());
                        cctv = new CCTV(i, j, (1 << 2) | 1);
                        cctvList.get(cctvList.size() - 1).add(cctv);
                        cctv = new CCTV(i, j, (1 << 3) | (1 << 1));
                        cctvList.get(cctvList.size() - 1).add(cctv);
                        break;
                    case 3:
                        cctvList.add(new ArrayList<CCTV>());
                        cctv = new CCTV(i, j, (1 << 3) | (1 << 2));
                        cctvList.get(cctvList.size() - 1).add(cctv);
                        cctv = new CCTV(i, j, (1 << 2) | (1 << 1));
                        cctvList.get(cctvList.size() - 1).add(cctv);
                        cctv = new CCTV(i, j, (1 << 1) | 1);
                        cctvList.get(cctvList.size() - 1).add(cctv);
                        cctv = new CCTV(i, j, (1 << 3) | 1);
                        cctvList.get(cctvList.size() - 1).add(cctv);
                        break;
                    case 4:
                        cctvList.add(new ArrayList<CCTV>());
                        cctv = new CCTV(i, j, (1 << 3) | (1 << 2) | (1 << 1));
                        cctvList.get(cctvList.size() - 1).add(cctv);
                        cctv = new CCTV(i, j, (1 << 2) | (1 << 1) | 1);
                        cctvList.get(cctvList.size() - 1).add(cctv);
                        cctv = new CCTV(i, j, (1 << 1) | 1 | (1 << 3));
                        cctvList.get(cctvList.size() - 1).add(cctv);
                        cctv = new CCTV(i, j, (1 << 3) | 1 | (1 << 2));
                        cctvList.get(cctvList.size() - 1).add(cctv);
                        break;
                    case 5:
                        cctv = new CCTV(i, j, (1 << 3) | (1 << 2) | (1 << 1) | 1);
                        initialCCTVList.add(cctv);
                        break;
                }
            }
        }

        for (int i = 0; i < initialCCTVList.size(); i++) updateMap(initialCCTVList.get(i), false); // 5번 카메라는 미리 업데이트 해둠
        dfs(0);
        System.out.println(answer);
    }
}
