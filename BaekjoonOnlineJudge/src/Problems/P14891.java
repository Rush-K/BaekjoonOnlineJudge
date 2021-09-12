/** Baekjoon Online Judge
 *   Problem No. 14891
 *   톱니바퀴
 *   Writed by Rush.K
 *   비트마스킹, Queue 이용
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Gear {
    int gearNumber; // 톱니바퀴 번호
    int pastGearNumber; // 영향을 주는 톱니바퀴 번호
    int pastGearRotateDirection; // 영향을 주는 톱니바퀴 회전방향

    public Gear(int _gearNumber, int _pastGearNumber, int _pastGearRotateDirection) {
        this.gearNumber = _gearNumber;
        this.pastGearNumber = _pastGearNumber;
        this.pastGearRotateDirection = _pastGearRotateDirection;
    }
}

public class P14891 {
    public static int rotate(int gear) { // 시계 방향 회전
        if (gear % 2 == 1) {
            gear >>= 1;
            gear |= 1 << 7;
        } else {
            gear >>= 1;
        }

        return gear;
    }

    public static int reverseRotate(int gear) { // 반시계 방향 회전
        if ((gear & (1 << 7)) == 0) {
            gear <<= 1;
        } else {
            gear -= (1 << 7);
            gear <<= 1;
            gear += 1;
        }

        return gear;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Gear> q = new LinkedList<>();
        int[] Gears = {0, 0, 0, 0, 0}; // 톱니바퀴 정보 저장
        boolean[] rotatedState; // 톱니바퀴 회전 여부 저장
        String gearInfo;

        for (int i = 1; i <= 4; i++) { // 각 톱니바퀴 정보 저장 (비트마스킹)
            gearInfo = br.readLine();
            int value = 0;
            for (int j = 0; j < gearInfo.length(); j++) {
                value += Integer.parseInt(gearInfo.substring(j, j + 1)) << (gearInfo.length() - 1 - j);
            }
            Gears[i] = value;
        }

        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            String[] input = br.readLine().split(" ");
            int gearNumber = Integer.parseInt(input[0]);
            int rotateDirection = Integer.parseInt(input[1]);

            if (rotateDirection == 1) { // 시계 방향
                Gears[gearNumber] = rotate(Gears[gearNumber]);
            } else if (rotateDirection == -1) { // 반시계 방향
                Gears[gearNumber] = reverseRotate(Gears[gearNumber]);
            }

            rotatedState = new boolean[]{false, false, false, false, false};
            rotatedState[gearNumber] = true;

            if (gearNumber - 1 >= 1) {
                q.add(new Gear(gearNumber - 1, gearNumber, rotateDirection));
            }
            if (gearNumber + 1 <= 4) {
                q.add(new Gear(gearNumber + 1, gearNumber, rotateDirection));
            }

            while (!q.isEmpty()) { // 영향받은 톱니바퀴들 정보 업데이트 (Queue)
                Gear gn = q.poll();

                int pastGearValue = gn.pastGearRotateDirection == 1 ? reverseRotate(Gears[gn.pastGearNumber]) : rotate(Gears[gn.pastGearNumber]);
                int firstComparater;
                int secondComparater;

                if (gn.pastGearNumber > gn.gearNumber) { // 업데이트 해야할 톱니바퀴가 해당 톱니바퀴에 영향을 준 톱니바퀴 왼쪽에 위치할 경우
                    firstComparater = (Gears[gn.gearNumber] & (1 << 5)) == 0 ? 0 : 1;
                    secondComparater = (pastGearValue & (1 << 1)) == 0 ? 0 : 1;
                } else { // 업데이트 해야할 톱니바퀴가 해당 톱니바퀴에 영향을 준 톱니바퀴의 오른쪽에 위치할 경우
                    firstComparater = (Gears[gn.gearNumber] & (1 << 1)) == 0 ? 0 : 1;
                    secondComparater = (pastGearValue & (1 << 5)) == 0 ? 0 : 1;
                }

                if (firstComparater != secondComparater) {
                    if (gn.pastGearRotateDirection == -1) {
                        Gears[gn.gearNumber] = rotate(Gears[gn.gearNumber]);
                        rotatedState[gn.gearNumber] = true;

                        if (gn.gearNumber - 1 >= 1 && rotatedState[gn.gearNumber - 1] == false) {
                            q.add(new Gear(gn.gearNumber - 1, gn.gearNumber, 1));
                        }
                        if (gn.gearNumber + 1 <= 4 && rotatedState[gn.gearNumber + 1] == false) {
                            q.add(new Gear(gn.gearNumber + 1, gn.gearNumber, 1));
                        }

                    } else {
                        Gears[gn.gearNumber] = reverseRotate(Gears[gn.gearNumber]);
                        rotatedState[gn.gearNumber] = true;

                        if (gn.gearNumber - 1 >= 1 && rotatedState[gn.gearNumber - 1] == false) {
                            q.add(new Gear(gn.gearNumber - 1, gn.gearNumber, -1));
                        }
                        if (gn.gearNumber + 1 <= 4 && rotatedState[gn.gearNumber + 1] == false) {
                            q.add(new Gear(gn.gearNumber + 1, gn.gearNumber, -1));
                        }
                    }
                }
            }
        }
        int result = 0;
        for (int i = 1, j = 1; i <= 4; i++, j = j * 2) { // 12시 방향 값에 따른 점수 산출
            if ((Gears[i] & (1 << 7)) != 0) {
                result += j;
            }
        }

        System.out.println(result); // 점수 출력
    }
}
