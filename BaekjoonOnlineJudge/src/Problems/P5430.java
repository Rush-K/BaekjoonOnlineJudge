/** Baekjoon Online Judge
*   Problem No. 5430
*   AC 
*   Writed by Rush.K
*   Deque 이용 
*/

package Problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class P5430 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < testCase; i++) {
			String commands = br.readLine();
			int N = Integer.parseInt(br.readLine());
			Deque<Integer> array = new LinkedList<>();
			String[] list = br.readLine().replace("[", "").replace("]", "").split(",");
			boolean arrayDirection = false; // false : 왼 -> 오, true : 오 -> 왼  
			boolean isError = false; // D 연산 에러 여부 
			
			for (int j = 0; j < N; j++) array.add(Integer.parseInt(list[j])); // 배열 Deque에 삽입 
			
			for (int j = 0; j < commands.length(); j++) {
				switch (commands.substring(j, j + 1)) {
					case "R" : // 뒤집기 
						arrayDirection = !arrayDirection;
						break;
					case "D" : // 첫번째 수 삭제하기 
						if (arrayDirection) {
							if (array.isEmpty()) isError = true;
							else array.pollLast();
						} else {
							if (array.isEmpty()) isError = true;
							else array.pollFirst();
						}
						break;
				}
				
				if (isError) break;
			}
			
			if (isError) {
				sb.append("error" + "\n");
			} else {
				
				int[] answer = new int[array.size()];
				int index = 0;
				
				if (arrayDirection) {;
					while (!array.isEmpty()) answer[index++] = array.pollLast();
				} else {
					while (!array.isEmpty()) answer[index++] = array.pollFirst();
				}
				
				sb.append(Arrays.toString(answer).replace(" ", "") + "\n");
			} // 결과 sb에 저장 
			
		}
		
		System.out.print(sb); // 출력 
	}

}
