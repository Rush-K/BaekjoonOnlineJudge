package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P12886 {
    public static HashSet<String> hs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        hs = new HashSet<String>();

        int sum = 0;
        int[] stones = new int[3];

        for (int i = 0; i < input.length; i++) {
            stones[i] = Integer.parseInt(input[i]);
            sum += stones[i];
        }

        if (sum % 3 != 0) {
            System.out.println(0);
            return;
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(stones);
        int hashSize = 0;

        while (!q.isEmpty()) {
            int[] stonelist = q.poll();

            if (stonelist[0] == stonelist[1] && stonelist[1] == stonelist[2]) {
                System.out.println(1);
                return;

            } else {
                for (int i = 0; i < 2; i++) {
                    for (int j = i + 1; j < 3; j++) {
                        if (stonelist[i] != stonelist[j]) {
                            if (stonelist[i] > stonelist[j]) {
                                if (2 * stonelist[j] <= 500) {
                                    int[] newlist = stonelist.clone();
                                    newlist[i] = newlist[i] - newlist[j];
                                    newlist[j] = 2 * newlist[j];
                                    hs.add(Arrays.toString(newlist));
                                    if (hashSize != hs.size()) {
                                        hashSize = hs.size();
                                        q.add(newlist);
                                    }
                                }
                            } else {
                                if (2 * stonelist[i] <= 500) {
                                    int[] newlist = stonelist.clone();
                                    newlist[i] = 2 * newlist[i];
                                    newlist[j] = newlist[j] - newlist[i];
                                    hs.add(Arrays.toString(newlist));
                                    if (hashSize != hs.size()) {
                                        hashSize = hs.size();
                                        q.add(newlist);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(0);
    }
}
