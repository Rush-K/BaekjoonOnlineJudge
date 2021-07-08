/** Baekjoon Online Judge
*   Problem No. 11728
*   배열 합치기 
*   Writed by Rush.K
*   Using Merge Sort
*/

package Problems;

import java.util.Arrays;
import java.util.Scanner;

public class P11728_2 {

	public static int[] result;
	
	public static void MergeSort(int[] arr, int top, int bottom) {
		if (bottom < top) {
			int middle = (top + bottom) / 2;
			MergeSort(arr, middle, bottom);
			MergeSort(arr, top, middle + 1);
			Merge(arr, top, middle, bottom);
		}
	}
	
	public static void Merge(int[] arr, int top, int middle, int bottom) {
		int bottom_ = bottom;
		int top_ = middle + 1;
		int buf = bottom;
		
		while (top_ <= top && bottom_ <= middle) {
			if (arr[top_] > arr[bottom_]) {
				result[buf++] = arr[bottom_++];
			} else {
				result[buf++] = arr[top_++];
			}
		}
		
		if (bottom_ > middle) {
			for (int i = top_; i <= top; i++) {
				result[buf++] = arr[i];
			}
		} else {
			for (int i = bottom_; i <= middle; i++) {
				result[buf++] = arr[i];
			}
		}
		
		for (int i = bottom; i <= top; i++) {
			arr[i] = result[i];
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		int[] arr = new int[N + M];
		for (int i = 0; i < N + M; i++) arr[i] = scanner.nextInt();
		result = new int[N + M];
		
		MergeSort(arr, N + M - 1, 0);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) sb.append(arr[i] + " ");
		System.out.println(sb); // output
	}
}

