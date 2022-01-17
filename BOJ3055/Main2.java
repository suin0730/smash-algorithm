package DAY01.BOJ3055;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main2 {

	static final int[] MX = {-1, 1, 0, 0};
	static final int[] MY = {0, 0, -1, 1};
	static int R, C;
	static char[][] map;
	static int[][] dp;
	static Queue<Point> queue;
	static boolean foundAnswer;

	public static void main(String[] args) throws FileNotFoundException {

		System.setIn(new FileInputStream("src/DAY01/BOJ3055/input.txt"));
		Scanner sc = new Scanner(System.in);

		// 받은 입력 넣기
		R = sc.nextInt();
		C = sc.nextInt();

		map = new char[R][C];
		dp = new int[R][C];
		queue = new LinkedList<>();

		Point st = null;
		sc.nextLine();
		for (int r = 0; r < R; r++) {
			map[r] = sc.nextLine().toCharArray();
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 'S') {
					st = new Point(r, c, 'S');
				} else if (map[r][c] == '*') {
					queue.add(new Point(r, c, '*'));
				}
			}
		}
		queue.add(st);

		while(!queue.isEmpty()){
			// 1. 큐에서 꺼내옴 -> S, ., D, *...
			Point p = queue.poll();
			// 2. 목적지인가? -> D
			if(p.type == 'D'){
				System.out.println(dp[p.y][p.x]);
				foundAnswer = true;
				break;
			}
			// 3. 연결된 곳을 순회 -> 좌, 우, 위, 아래
			for(int i=0;i<4;i++){
				int ty = p.y+MY[i];
				int tx = p.x+MX[i];

				// 4. 갈수있는가?
				if(isBoundary(ty, tx)){
					// 4.1. (고슴도치)
					if(p.type == '.' || p.type=='S'){
						if((map[ty][tx] == '.'||map[ty][tx]=='D') && dp[ty][tx]==0){
							// 5.1. 체크인 -> 시간 기록
							dp[ty][tx] = dp[p.y][p.x]+1;
							// 6.1. 큐에 넣음
							queue.add(new Point(ty, tx, map[ty][tx]));
						}
					}
					// 4.2. (물)
					else if(p.type== '*'){
						if(map[ty][tx]=='.'||map[ty][tx]=='S'){
							// 5.2. 체크인 -> 지도에 * 표기
							map[ty][tx] = '*';
							// 6.2. 큐에 넣음
							queue.add(new Point(ty, tx, '*'));
						}
					}
				}
			}
		}
		if(!foundAnswer){
			System.out.println("KAKTUS");
		}
	}

	static boolean isBoundary(int nx, int ny) {
		return nx >= 0 && nx < R && ny >= 0 && ny < C;
	}
}

class Point {
	int y;
	int x;
	char type;

	public Point(int y, int x, char type) {
		super();
		this.y = y;
		this.x = x;
		this.type = type;
	}
}
