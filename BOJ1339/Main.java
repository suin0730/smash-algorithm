package DAY01.BOJ1339;

import java.io.FileNotFoundException;
import java.util.*;

public class Main {
	static int N;
	static Integer[] alph = new Integer[26];
	static List<String> word = new ArrayList<>();

	public static void main (String[] args) throws FileNotFoundException{
//		System.setIn(new FileInputStream("src/DAY01/BOJ1339/input.txt"));
		Scanner sc = new Scanner(System.in);

		// 받은 입력 넣기
		N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			word.add(String.valueOf(sc.next()));
		}
		for (int i = 0; i < 26; i++) {
			alph[i] = 0;
		}

		for (int i = 0; i < N; i++) {
			String s = word.get(i);
			int wordLen = s.length();
			int weight = 1;

			// 알파벳 위치에 따라 가중치 더해주기
			for (int j = wordLen - 1; j >= 0; j--) {
				alph[s.charAt(j) - 'A'] += weight;
				weight *= 10;
			}
		}

		Arrays.sort(alph, Collections.reverseOrder());

		int sum = 0;
		int num = 9;
		for (int i = 0; i < 26; i++) {
			//alph 빈도가 높은 것에 큰 num을 대입, 동시에 sum에 더해줌
			sum += alph[i] * num;
			num--;
		}
		System.out.println(sum);
	}
}
