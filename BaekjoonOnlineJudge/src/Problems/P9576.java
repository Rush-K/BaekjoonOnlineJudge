/** Baekjoon Online Judge
 *   Problem No. 9576
 *   책 나눠주기
 *   Writed by Rush.K
 *   네트워크 유량 - 이분 매칭 이용
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class P9576 {
    public static int[] studentMatchBook;
    public static int[] bookMatchStudent;

    public static boolean dfs(ArrayList<ArrayList<Integer>> edges, int student, boolean[] visited) { // 이분 매칭을 위한 dfs 알고리즘
        if (visited[student]) return false;
        visited[student] = true;

        for (Integer book : edges.get(student)) {
            if (bookMatchStudent[book] == Integer.MAX_VALUE || dfs(edges, bookMatchStudent[book], visited)) { // 중요*
                bookMatchStudent[book] = student;
                studentMatchBook[student] = book;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T;
        int N, M;
        int answer;
        boolean[] visited;

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            ArrayList<ArrayList<Integer>> edges = new ArrayList<ArrayList<Integer>>(); // 그래프 표현을 위한 인접 리스트
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);

            studentMatchBook = new int[M];
            bookMatchStudent = new int[N + 1];
            answer = 0;

            for (int j = 0; j <= N; j++) bookMatchStudent[j] = Integer.MAX_VALUE;

            for (int j = 0; j < M; j++) edges.add(new ArrayList<Integer>());

            for (int j = 0; j < M; j++) {
                input = br.readLine().split(" ");
                for (int k = Integer.parseInt(input[0]); k <= Integer.parseInt(input[1]); k++) edges.get(j).add(k);
            }

            for (int j = 0; j < M; j++) { // 이분 매칭
                visited = new boolean[M];
                if (dfs(edges, j, visited)) answer++;
            }

            sb.append(answer + "\n");
        }

        System.out.println(sb);
    }
}
