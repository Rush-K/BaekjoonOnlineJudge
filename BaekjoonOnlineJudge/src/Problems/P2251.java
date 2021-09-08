/** Baekjoon Online Judge
*   Problem No. 2251
*   물통
*   Writed by Rush.K
*   BFS 이용
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class P2251 {
    public static void main(String[] args) throws IOException {
        Queue<int[]> q = new LinkedList<>(); // BFS를 위한 Queue
        HashSet<String> log = new HashSet<>(); // 방문했던 물통 배열을 기록하는 HashSet
        HashSet<Integer> possibleC = new HashSet<>(); // 가능한 C 값 저장하는 HashSet
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int A = Integer.parseInt(input[0]);
        int B = Integer.parseInt(input[1]);
        int C = Integer.parseInt(input[2]);

        int[] waters = {0, 0, C};
        q.add(waters);

        while (!q.isEmpty()) {
            waters = q.poll();
            String waterslog = String.valueOf(waters[0]) + " " + String.valueOf(waters[1]) + " " + String.valueOf(waters[2]);

            if (log.contains(waterslog)) {
                continue;
            }

            if (waters[0] == 0) {
                possibleC.add(waters[2]);
            }

            if (waters[0] < A) { // A 물통 업데이트
                if (A - waters[0] <= waters[1]) {
                    int[] newWaters = {A, waters[1] - (A - waters[0]), waters[2]};
                    q.add(newWaters);
                } else {
                    if (waters[1] != 0) {
                        int[] newWaters = {waters[0] + waters[1], 0, waters[2]};
                        q.add(newWaters);
                    }
                }
                if (A - waters[0] <= waters[2]) {
                    int[] newWaters = {A, waters[1], waters[2] - (A - waters[0])};
                    q.add(newWaters);
                } else {
                    if (waters[2] != 0) {
                        int[] newWaters = {waters[0] + waters[2], waters[1], 0};
                        q.add(newWaters);
                    }
                }
            }

            if (waters[1] < B) { // B 물통 업데이트
                if (B - waters[1] <= waters[0]) {
                    int[] newWaters = {waters[0] - (B - waters[1]), B, waters[2]};
                    q.add(newWaters);
                } else {
                    if (waters[0] != 0) {
                        int[] newWaters = {0, waters[1] + waters[0], waters[2]};
                        q.add(newWaters);
                    }
                }
                if (B - waters[1] <= waters[2]) {
                    int[] newWaters = {waters[0], B, waters[2] - (B - waters[1])};
                    q.add(newWaters);
                } else {
                    if (waters[2] != 0) {
                        int[] newWaters = {waters[0], waters[1] + waters[2], 0};
                        q.add(newWaters);
                    }
                }
            }


            if (waters[2] < C) { // C 물통 업데이트
                if (C - waters[2] <= waters[0]) {
                    int[] newWaters = {waters[0] - (C - waters[2]), waters[1], C};
                    q.add(newWaters);
                } else {
                    if (waters[0] != 0) {
                        int[] newWaters = {0, waters[1], waters[2] + waters[0]};
                        q.add(newWaters);
                    }
                }
                if (C - waters[2] <= waters[1]) {
                    int[] newWaters = {waters[0], waters[1] - (C - waters[2]), C};
                    q.add(newWaters);
                } else {
                    if (waters[1] != 0) {
                        int[] newWaters = {waters[0], 0, waters[2] + waters[1]};
                        q.add(newWaters);
                    }
                }
            }

            log.add(String.valueOf(waterslog));
        }

        int[] result = possibleC.stream().mapToInt(i -> i).toArray();
        Arrays.sort(result); // 결과 오름차순
        System.out.println(Arrays.toString(result).replaceAll(",", "").replace("[", "").replace("]", ""));
    }
}
