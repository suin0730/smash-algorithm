package DAY01.BOJ1759;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int L, C;
	static char data[];
	static List<String> result = new ArrayList<>();

	public static void main(String[] args) throws FileNotFoundException {
//		System.setIn(new FileInputStream("src/DAY01/BOJ1759/input.txt"));
		Scanner sc = new Scanner(System.in);

		// 받은 입력 넣기
		L = sc.nextInt();
		C = sc.nextInt();

		data = new char[C];
		for (int i = 0; i < C; i++) {
			data[i] = sc.next().charAt(0);
		}
		Arrays.sort(data);

//		dfs(0, 0, 0, -1, "");

		for (int i = 0; i < C; i++) {
			if (data[i] == 'a' || data[i] == 'e' || data[i] == 'i' || data[i] == 'o' || data[i] == 'u') {
				// 5. 		간다 - dfs(next) -> 모음
				dfs(1, 0, 1, i, String.valueOf(data[i]));
			} else {
				// 5. 		간다 - dfs(next) -> 자음
				dfs(1, 1, 0, i, String.valueOf(data[i]));
			}

		}

		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}

	}

	static void dfs(int length, int ja, int mo, int current, String pwd) {
		// 1. 체크인 - 생략 가능
		// 2. 목적지인가? - 길이 + 자모개수
		if (length == L) {
			if (ja >= 2 && mo >= 1) {
				result.add(pwd);
			}
		} else {
			// 3. 연결된 곳을 순회 - 나보다 오른쪽에 있는 알파벳
			for (int nextIndex = current + 1; nextIndex < data.length; nextIndex++) {
				char nextData = data[nextIndex];
				// 4. 	갈 수 있는가? - 정렬해서 오른쪽만 보며 됨
				if (nextData == 'a' || nextData == 'e' || nextData == 'i' || nextData == 'o' || nextData == 'u') {
					// 5. 		간다 - dfs(next) -> 모음
					dfs(length + 1, ja, mo + 1, nextIndex, pwd + nextData);
				} else {
					// 5. 		간다 - dfs(next) -> 자음
					dfs(length + 1, ja + 1, mo, nextIndex, pwd + nextData);
				}
			}
		}

		// 6. 체크아웃 - 생략

	}
}
