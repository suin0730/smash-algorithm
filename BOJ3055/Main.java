package DAY01.BOJ3055;


import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static final int[] DX = {-1, 1, 0, 0};
	static final int[] DY = {0, 0, -1, 1};
	static int R, C, time;
	static char[][] map;
	static Queue<int[]> queue;
	static Queue<int[]> wqueue;

	public static void main(String[] args) throws FileNotFoundException {

//		System.setIn(new FileInputStream("src/DAY01/BOJ3055/input.txt"));
		Scanner sc = new Scanner(System.in);

		// 받은 입력 넣기
		R = sc.nextInt();
		C = sc.nextInt();
		map = new char[R][C];
		queue = new LinkedList<int[]>();
		wqueue = new LinkedList<int[]>();
		sc.nextLine();
		for (int i = 0; i < R; i++) {
			map[i] = sc.nextLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'S') {
					queue.add(new int[]{i, j});
				} else if (map[i][j] == '*') {
					wqueue.add(new int[]{i, j});
				}
			}
		}
		System.out.println(bfs() ? time : "KAKTUS");
	}

	static boolean bfs() {
		// 1. 큐에서 꺼내옴
		while (!queue.isEmpty()) {
			time++;
			int size = wqueue.size();
			for (int i = 0; i < size; i++) {
				int[] current = wqueue.poll();
				for (int j = 0; j < 4; j++) {
					int nx = current[0] + DX[j];
					int ny = current[1] + DY[j];
					if (!isBoundary(nx, ny) || map[nx][ny] == 'X') continue;
					if (map[nx][ny] == '*' || map[nx][ny] == 'D') continue;
					// 5.1. 		체크인 - 물
					map[nx][ny] = '*';

					// 6.1. 		큐에 넣음 - 물
					wqueue.add(new int[]{nx, ny});
				}
			}

			size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] current = queue.poll();

				// 3. 연결된 곳을 순회
				for (int j = 0; j < 4; j++) {

					int nx = current[0] + DX[j];
					int ny = current[1] + DY[j];

					// 4. 	갈 수 있는가?
					if (!isBoundary(nx, ny) || map[nx][ny] == 'X') continue;
					if (map[nx][ny] == '*' || map[nx][ny] == 'S') continue;

					// 2. 목적지인가?
					if (map[nx][ny] == 'D') return true;

					// 5.2. 		체크인 - 고슴도치
					map[nx][ny] = 'S';
					// 6.2. 		큐에 넣음 - 고슴도치
					queue.add(new int[]{nx, ny});
				}
			}
		}
		return false;
	}

	static boolean isBoundary(int nx, int ny) {
		if (nx < 0 || nx >= R || ny < 0 || ny >= C) return false;
		return true;
	}
}


