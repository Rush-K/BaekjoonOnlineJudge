/** Baekjoon Online Judge
 *   Problem No. 14499
 *   주사위 굴리기
 *   Writed by Rush.K
 *   시뮬레이션
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Dice { // 주사위 클래스
    int x;
    int y;
    int[] development;

    public Dice(int _x, int _y) {
        this.x = _x;
        this.y = _y;
        development = new int[6]; // 0 : 윗면, 1 : 앞면, 2 : 아랫면, 3 : 뒷면, 4 : 왼쪽면, 5 : 오른쪽면
    }

    public boolean roll(int direction, int N, int M) { // 주사위 굴리기
        int[] dir = {0, 1, -1, -1, 1};

        if (direction > 2) { // 맵을 벗어나는 행위면 false
            if (this.x + dir[direction] >= N || this.x + dir[direction] < 0) return false;
        } else {
            if (this.y + dir[direction] >= M || this.y + dir[direction] < 0) return false;
        }

        int temp;

        switch(direction) {
            case 1: // east
                // 0, 5, 2, 4 => 4, 0, 5, 2
                temp = this.development[0];
                this.development[0] = this.development[4];
                this.development[4] = this.development[2];
                this.development[2] = this.development[5];
                this.development[5] = temp;
                this.y++;
                break;
            case 2: // west
                // 0, 5, 2, 4 => 5, 2, 4, 0
                temp = this.development[0];
                this.development[0] = this.development[5];
                this.development[5] = this.development[2];
                this.development[2] = this.development[4];
                this.development[4] = temp;
                this.y--;
                break;
            case 3: // north
                // 0, 3, 2, 1 -> 1, 0, 3, 2
                temp = this.development[0];
                this.development[0] = this.development[1];
                this.development[1] = this.development[2];
                this.development[2] = this.development[3];
                this.development[3] = temp;
                this.x--;
                break;
            case 4: // south
                // 0, 3, 2, 1 -> 3, 0, 1, 2
                temp = this.development[0];
                this.development[0] = this.development[3];
                this.development[3] = this.development[2];
                this.development[2] = this.development[1];
                this.development[1] = temp;
                this.x++;
                break;
        }
        return true;
    }
}

public class P14499 {
    public static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        Dice dice = new Dice(Integer.parseInt(input[2]), Integer.parseInt(input[3]));
        int K = Integer.parseInt(input[4]);

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) map[i][j] = Integer.parseInt(input[j]);
        }

        input = br.readLine().split(" ");

        for (int i = 0; i < K; i++) {
            boolean rollResult = dice.roll(Integer.parseInt(input[i]), N, M);
            if (rollResult) {
                if (map[dice.x][dice.y] == 0) { // 위치한 곳이 0일 때
                    map[dice.x][dice.y] = dice.development[2];
                } else { // 위치한 곳이 0이 아닐 때
                    dice.development[2] = map[dice.x][dice.y];
                    map[dice.x][dice.y] = 0;
                }
                sb.append(dice.development[0] + "\n");
            }
        }

        System.out.println(sb);
    }
}
