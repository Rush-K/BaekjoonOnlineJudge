package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Door {
    int x;
    int y;

    public Door(int _x, int _y) {
        this.x = _x;
        this.y = _y;
    }
}
class LocationInPrison {
    int x;
    int y;
    ArrayList<Door> doors;

    public LocationInPrison(int _x, int _y) {
        this.x = _x;
        this.y = _y;
        this.doors = new ArrayList<Door>();
    }

    public LocationInPrison(int _x, int _y, ArrayList<Door> _doors) {
        this.x = _x;
        this.y = _y;
        this.doors = new ArrayList<Door>();
        this.doors.addAll(_doors);
    }

    public LocationInPrison(int _x, int _y, ArrayList<Door> _doors, Door _door) {
        this.x = _x;
        this.y = _y;
        this.doors = new ArrayList<Door>();
        this.doors.addAll(_doors);
        this.doors.add(_door);
    }
}

public class P9376 {
    public static int h;
    public static int w;
    public static char[][] map;
    public static boolean[][] visited;
    public static int openedDoors;

    public static void prisonerMoving(LocationInPrison prisoner) {
        PriorityQueue<LocationInPrison> q = new PriorityQueue<>(new Comparator<LocationInPrison>() {
            @Override
            public int compare(LocationInPrison o1, LocationInPrison o2) {
                return map[o1.x][o1.y] == '.' ? -1 : 1;
            }
        });

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        q.add(prisoner);
        visited[prisoner.x][prisoner.y] = true;

        while (!q.isEmpty()) {
            LocationInPrison lip = q.poll();
            if (lip.x == 0 || lip.x == h - 1 || lip.y == 0 || lip.y == w - 1) {
                openedDoors += lip.doors.size();
                for (Door l : lip.doors) map[l.x][l.y] = '.';
                return;
            }

            for (int i = 0; i < directions.length; i++) {
                try {
                    if (map[lip.x - directions[i][0]][lip.y - directions[i][1]] != '*' && visited[lip.x - directions[i][0]][lip.y - directions[i][1]] == false) {
                        if (map[lip.x - directions[i][0]][lip.y - directions[i][1]] == '#') {
                            visited[lip.x - directions[i][0]][lip.y - directions[i][1]] = true;
                            q.add(new LocationInPrison(lip.x - directions[i][0], lip.y - directions[i][1], lip.doors, new Door(lip.x - directions[i][0], lip.y - directions[i][1])));

                        } else {
                            visited[lip.x - directions[i][0]][lip.y - directions[i][1]] = true;
                            q.add(new LocationInPrison(lip.x - directions[i][0], lip.y - directions[i][1], lip.doors));
                        }
                    }
                } catch (Exception e) {}
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] prisoners = new int[2][2];
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String[] input = br.readLine().split(" ");
            h = Integer.parseInt(input[0]);
            w = Integer.parseInt(input[1]);
            map = new char[h][w];
            int prisonerNumber = 0;

            for (int j = 0; j < h; j++) {
                char[] mapInput = br.readLine().toCharArray();
                for (int k = 0; k < w; k++) {
                    if (mapInput[k] == '$') {
                        prisoners[prisonerNumber][0] = j;
                        prisoners[prisonerNumber][1] = k;
                        prisonerNumber++;
                        map[j][k] = '.';
                    } else {
                        map[j][k] = mapInput[k];
                    }
                }
            }
            visited = new boolean[h][w];
            openedDoors = 0;
            prisonerMoving(new LocationInPrison(prisoners[0][0], prisoners[0][1]));
            for (int j = 0; j < h; j++) {
                for (int k = 0; k < w; k++) {
                    System.out.print(map[j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();


            visited = new boolean[h][w];
            prisonerMoving(new LocationInPrison(prisoners[1][0], prisoners[1][1]));
            for (int j = 0; j < h; j++) {
                for (int k = 0; k < w; k++) {
                    System.out.print(map[j][k] + " ");
                }
                System.out.println();
            }


            System.out.println(openedDoors);
        }
    }
}
