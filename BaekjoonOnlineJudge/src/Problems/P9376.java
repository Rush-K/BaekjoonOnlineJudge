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
    public static int[][] visited;

    public static int prisonerMoving(LocationInPrison firstPrisoner, LocationInPrison secondPrisoner) {
        PriorityQueue<LocationInPrison> q = new PriorityQueue<>(new Comparator<LocationInPrison>() {
            @Override
            public int compare(LocationInPrison o1, LocationInPrison o2) {
                if (o1.doors.size() == o2.doors.size()) {
                    return map[o1.x][o1.y] == '.' ? -1 : 1;
                }
                return o1.doors.size() > o2.doors.size() ? 1 : -1;
            }
        });

        ArrayList<LocationInPrison> pathList = new ArrayList<>();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int firstOpenedDoors = Integer.MAX_VALUE;

        visited = new int[h][w];
        q.add(firstPrisoner);
        visited[firstPrisoner.x][firstPrisoner.y] = 1;

        while (!q.isEmpty()) {
            LocationInPrison lip = q.poll();
            if (lip.x == 0 || lip.x == h - 1 || lip.y == 0 || lip.y == w - 1) {
                pathList.add(lip);
                continue;
            }

            for (int i = 0; i < directions.length; i++) {
                try {
                    if (map[lip.x - directions[i][0]][lip.y - directions[i][1]] != '*' && visited[lip.x - directions[i][0]][lip.y - directions[i][1]] == 0) {
                        if (map[lip.x - directions[i][0]][lip.y - directions[i][1]] == '#') {
                            visited[lip.x - directions[i][0]][lip.y - directions[i][1]] = 1;
                            q.add(new LocationInPrison(lip.x - directions[i][0], lip.y - directions[i][1], lip.doors, new Door(lip.x - directions[i][0], lip.y - directions[i][1])));

                        } else {
                            visited[lip.x - directions[i][0]][lip.y - directions[i][1]] = 1;
                            q.add(new LocationInPrison(lip.x - directions[i][0], lip.y - directions[i][1], lip.doors));
                        }
                    }
                } catch (Exception e) { System.out.println(e); }
            }
        }

        for (LocationInPrison temp : pathList) {
            visited = new int[h][w];
            for (Door l : temp.doors) {
                visited[l.x][l.y] = 2;
                map[l.x][l.y] = '.';
            }
            int secondOpenedDoors = secondPrisonerMoving(secondPrisoner);
            firstOpenedDoors = Math.min(firstOpenedDoors, temp.doors.size() + secondOpenedDoors);

            for (Door l : temp.doors) map[l.x][l.y] = '#';
        }

        return firstOpenedDoors;
    }

    public static int secondPrisonerMoving(LocationInPrison prisoner) {
        PriorityQueue<LocationInPrison> q = new PriorityQueue<>(new Comparator<LocationInPrison>() {
            @Override
            public int compare(LocationInPrison o1, LocationInPrison o2) {
                if (o1.doors.size() == o2.doors.size()) {
                    return map[o1.x][o1.y] == '.' ? -1 : 1;
                }
                return o1.doors.size() > o2.doors.size() ? 1 : -1;
            }
        });

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int secondOpenedDoors = Integer.MAX_VALUE;

        q.add(prisoner);
        visited[prisoner.x][prisoner.y] = 1;

        while (!q.isEmpty()) {
            LocationInPrison lip = q.poll();
            if (lip.x == 0 || lip.x == h - 1 || lip.y == 0 || lip.y == w - 1 || visited[lip.x][lip.y] == 2) {
                secondOpenedDoors = Math.min(secondOpenedDoors, lip.doors.size());
                continue;
            }

            for (int i = 0; i < directions.length; i++) {
                try {
                    if (map[lip.x - directions[i][0]][lip.y - directions[i][1]] != '*' && visited[lip.x - directions[i][0]][lip.y - directions[i][1]] != 1) {
                        if (map[lip.x - directions[i][0]][lip.y - directions[i][1]] == '#') {
                            visited[lip.x - directions[i][0]][lip.y - directions[i][1]] = 1;
                            q.add(new LocationInPrison(lip.x - directions[i][0], lip.y - directions[i][1], lip.doors, new Door(lip.x - directions[i][0], lip.y - directions[i][1])));
                        } else {
                            if (visited[lip.x - directions[i][0]][lip.y - directions[i][1]] == 2) {
                                q.add(new LocationInPrison(lip.x - directions[i][0], lip.y - directions[i][1], lip.doors));
                            } else {
                                visited[lip.x - directions[i][0]][lip.y - directions[i][1]] = 1;
                                q.add(new LocationInPrison(lip.x - directions[i][0], lip.y - directions[i][1], lip.doors));
                            }
                         }
                    }
                } catch (Exception e) { }
            }
        }

        return secondOpenedDoors;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[][] prisoners = new int[2][2];
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String[] input = br.readLine().split(" ");
            h = Integer.parseInt(input[0]);
            w = Integer.parseInt(input[1]);
            map = new char[h][w];
            int prisonerNumber = 0;
            int answer = 0;

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

            answer = Math.min(prisonerMoving(new LocationInPrison(prisoners[0][0], prisoners[0][1]), new LocationInPrison(prisoners[1][0], prisoners[1][1])),
                    prisonerMoving(new LocationInPrison(prisoners[1][0], prisoners[1][1]), new LocationInPrison(prisoners[0][0], prisoners[0][1])));


            sb.append(answer + "\n");
        }
        System.out.print(sb);
    }
}
