/** Baekjoon Online Judge
 *   Problem No. 10830
 *   행렬 제곱
 *   Writed by Rush.K
 *   modular, 분할 정복
 */

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Matrix { // 행렬 클래스
    long[][] value;

    public Matrix(int row, int col) { // 항등 행렬 생성자
        this.value = new long[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == j) this.value[i][j] = 1L;
                else this.value[i][j] = 0;
            }
        }
    }

    public Matrix(int row, int col, int zero) { // 모든 요소의 값이 zero인 행렬 생성자
        this.value = new long[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                this.value[i][j] = zero;
            }
        }
    }

    public Matrix mul(Matrix mat) { // 행렬의 곱 연산
        Matrix answer = new Matrix(this.value.length, this.value.length, 0);

        for (int i = 0; i < this.value.length; i++) {
            for (int j = 0; j < this.value.length; j++) {
                for (int k = 0; k < this.value.length; k++) {
                    answer.value[i][j] += this.value[i][k] * mat.value[k][j];
                }
            }
        }

        return answer;
    }

    public Matrix convert(int p) { // 행렬 요소들을 p로 나누었을 때 나머지 값으로 변환
        for (int i = 0; i < this.value.length; i++) {
            for (int j = 0; j < this.value.length; j++) {
                this.value[i][j] = this.value[i][j] % 1000;
            }
        }

        return this;
    }

    public String toString() { // 행렬 출력
        String print = "";
        for (int i = 0; i < this.value.length; i++) {
            print += Arrays.toString(this.value[i]).replace("[", "").replace("]", "").replace(",", "") + "\n";
        }

        return print;
    }
}
public class P10830 {
    public static Matrix A;
    public static int p;

    public static Matrix pow(int N, Matrix base, long B) { // 거듭제곱의 분할 정복
        Matrix mat = new Matrix(N, N);

        while (B > 0) {
            if (B % 2 == 1) {
                mat = mat.mul(base).convert(p);
            }

            base = base.mul(base).convert(p);
            B /= 2;
        }

        return mat;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        long B = Long.parseLong(input[1]);

        p = 1000;
        A = new Matrix(N, N);

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                A.value[i][j] = Long.parseLong(input[j]);
            }
        }

        System.out.print(pow(N, A, B).toString()); // 값 출력

    }
}
