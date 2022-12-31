import java.util.*;

public class Dungeon1 {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Enter dimensions of the dungeon (row col): ");
		int[] dim = new int[2];
		dim[0] = in.nextInt();
		dim[1] = in.nextInt();
		
		System.out.print("Enter position of player (row col): ");
		int[] player = new int[2];
		player[0] = in.nextInt() - 1;
		player[1] = in.nextInt() - 1;
		
		System.out.print("Enter position of gold (row col): ");
		int[] gold = new int[2];
		gold[0] = in.nextInt() - 1;
		gold[1] = in.nextInt() - 1;
		System.out.println(new Dungeon1().numberOfSteps(dim, player, gold));

		in.close();

	}
	
	public int numberOfSteps(int[] dim, int[] player, int[] gold) {
		
		int n = dim[0];
		int m = dim[1];
		
		int[] rowInc = {1,0,-1,0};
		int[] colInc = {0,1,0,-1};
		
		Deque<int[]> q = new ArrayDeque<>();
		Set<String> visited = new HashSet<>();
		
		if (player[0] == gold[0] && player[1] == gold[1]) {
			return 0;
		}
		
		q.add(player);
		
		int count = 1;
		int next = 0;
		int level = 1;

		while (!q.isEmpty()) {

			
			int[] curr = q.poll();
			visited.add(curr[0]+","+curr[1]);
			count--;
			
			for (int i=0; i<4; i++) {
				
				int row = curr[0] + rowInc[i];
				int col = curr[1] + colInc[i];
				
				if (row >= n || row < 0 || col >= m || col < 0 || visited.contains(String.valueOf(row+","+col))) {
					continue;
				}
				
				if (row == gold[0] && col == gold[1]) {
					return level;
				}
				
//				if (visited.contains(String.valueOf(row+","+col))) {
//					continue;
//				}
				q.add(new int[] {row,col});
				next++;
				
			}
			
			if (count == 0) {
				count = next;
				next = 0;
				level++;
			}
			
		}
		
		return -1;
		
	}

}
