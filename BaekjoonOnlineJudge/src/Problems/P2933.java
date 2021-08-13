/** Baekjoon Online Judge
 *   Problem No. 2933
 *   미네랄
 *   Writed by Rush.K
 *   시뮬레이션, BFS 이용
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class MineralPosition { // 미네랄 위치 클래스
    int x;
    int y;

    public MineralPosition(int _x, int _y) {
        this.x = _x;
        this.y = _y;
    }
}

class Cluster { // 클러스터 클래스
    ArrayList<MineralPosition> minerals; // 클러스터에 속하는 미네랄
    int top; // 클러스터 상단 x값
    int bottom; // 클러스터 하단 x값

    public Cluster() {
        this.minerals = new ArrayList<>();
        this.top = 0;
        this.bottom = 0;
    }

    public void add(MineralPosition mineral) {
        this.minerals.add(mineral);
        this.top = Math.min(this.top, mineral.x);
        this.bottom = Math.max(this.bottom, mineral.x);
    }
}

public class P2933 {
    public static char[][] map;
    public static boolean[][] visited;

    public static Cluster findCluster(int R, int C, MineralPosition mineral) { // 클러스터 찾기 : BFS 이용
        Queue<MineralPosition> cluster = new LinkedList<>();
        Cluster result = new Cluster();

        result.add(mineral);
        cluster.add(mineral);
        visited[mineral.x][mineral.y] = true;

        while (!cluster.isEmpty()) {
            MineralPosition mp = cluster.poll();
            MineralPosition newMp;
            try { // 왼쪽 미네랄 살피기
                if (map[mp.x][mp.y - 1] == 'x' && visited[mp.x][mp.y - 1] == false) {
                    newMp = new MineralPosition(mp.x, mp.y - 1);
                    visited[mp.x][mp.y - 1] = true;
                    result.add(newMp);
                    cluster.add(newMp);
                }
            } catch (Exception e) {}

            try { // 오른쪽 미네랄 살피기
                if (map[mp.x][mp.y + 1] == 'x' && visited[mp.x][mp.y + 1] == false) {
                    newMp = new MineralPosition(mp.x, mp.y + 1);
                    visited[mp.x][mp.y + 1] = true;
                    result.add(newMp);
                    cluster.add(newMp);
                }
            } catch (Exception e) {}

            try { // 위쪽 미네랄 살피기
                if (map[mp.x - 1][mp.y] == 'x' && visited[mp.x - 1][mp.y] == false) {
                    newMp = new MineralPosition(mp.x - 1, mp.y);
                    visited[mp.x - 1][mp.y] = true;
                    result.add(newMp);
                    cluster.add(newMp);
                }
            } catch (Exception e) {}

            try { // 아래쪽 미네랄 살피기
                if (map[mp.x + 1][mp.y] == 'x' && visited[mp.x + 1][mp.y] == false) {
                    newMp = new MineralPosition(mp.x + 1, mp.y);
                    visited[mp.x + 1][mp.y] = true;
                    result.add(newMp);
                    cluster.add(newMp);
                }
            } catch (Exception e) {}
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        ArrayList<Integer> stickList = new ArrayList<>();
        int R = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);
        int N;
        boolean direction = false; // false : 왼 -> 오, true : 오 -> 왼

        map = new char[R][C];

        for (int i = 0 ; i < R; i++) {
            char[] mapInput = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = mapInput[j];
            }
        }

        N = Integer.parseInt(br.readLine());
        input = br.readLine().split(" ");
        for (String s : input) stickList.add(Integer.parseInt(s));

        while (!stickList.isEmpty()) { // 작업 시작
            PriorityQueue<Cluster> clusters = new PriorityQueue<>(new Comparator<Cluster>() {
                @Override
                public int compare(Cluster o1, Cluster o2) {
                    return o1.bottom > o2.bottom ? -1 : 1;
                }
            }); // 바닥에 가까운 클러스터 부터 정렬한 큐

            int stick = stickList.remove(0); // 막대 가져오기

            if (direction == false) { // 막대 던지기
                for (int i = 0; i < C; i++) {
                    if (map[R - stick][i] == 'x') {
                        map[R - stick][i] = '.';
                        break;
                    }
                }
            } else {
                for (int i = C - 1; i >= 0; i--) {
                    if (map[R - stick][i] == 'x') {
                        map[R - stick][i] = '.';
                        break;
                    }
                }
            }

            visited = new boolean[R][C];

            for (int i = 0; i < R; i++) { // 클러스터 찾아서 큐에 추가
                for (int j = 0; j < C; j++) {
                    if (map[i][j] == 'x' && visited[i][j] == false) {
                        clusters.add(findCluster(R, C, new MineralPosition(i, j)));
                    }
                }
            }

            while (!clusters.isEmpty()) {
                Cluster cluster = clusters.poll();

                if (cluster.bottom == R - 1) { // 바닥에 붙은 클러스터는 PASS
                    continue;
                } else { // 바닥에 붙지 않은 클러스터는 한 칸씩 내리며 확인
                    boolean isMove = true;
                    for (MineralPosition mp : cluster.minerals) map[mp.x][mp.y] = '.';

                    while (isMove && cluster.bottom != R - 1) {
                        for (MineralPosition mp : cluster.minerals) {
                            if (map[mp.x + 1][mp.y] == 'x') {
                                isMove = false;
                                break;
                            }
                        }

                        if (isMove) {
                            for (int i = 0; i < cluster.minerals.size(); i++) {
                                cluster.minerals.get(i).x += 1;
                            }
                            cluster.top += 1;
                            cluster.bottom += 1;
                        }
                    }

                    for (MineralPosition mp : cluster.minerals) map[mp.x][mp.y] = 'x';
                }
            }

            direction = !direction; // 던지는 방향 바꾸기
        }

        for (int i = 0; i < R; i++) { // 결과 출력
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
