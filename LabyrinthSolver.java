import java.util.ArrayList;
public class LabyrinthSolver {
	private Labyrinth maze;
	private int rows;
	private int cols;
	private ArrayList<Integer> moves = new ArrayList<Integer>();
	private int up = 0;
	private int left = 3;
	private int right = 2;
	private int down = 1;
	private int count = 0;
	private int [][] moveTracker;
	
	public LabyrinthSolver(int r, int c) {
		this.maze = new Labyrinth(r, c);
		this.rows = r;
		this.cols = c;
		moveTracker = new int[r][c];
	}
	public boolean findSafeMove(int row, int col, Labyrinth maze) {
		if((row == rows-1) && (col == cols-1)) {
			for(int i = 0; i < moves.size(); i++) {
				System.out.println(moves.get(i));
			}
			return true;
			//leave 
		}
		//loops through 4 times once for each direction
		//have you been there before
		//method encapsulation for the if statements
		//if next one is a solution return true
		//remove froms olution array and clear array that keeps track of whre you have been (already been here)
		
		while(count < 4){
			if(count == 0){
				row += 1;
				if((maze.isValid(row, col)) && (maze.isStone(row, col))
					&& (!alrBeenHere(row, col))) {
					recursiveCall(up);
				}
			}
			if(count == 1) {
				row -= 1;
				if((maze.isValid(row, col)) && (maze.isStone(row, col))) {
					moves.add(down);
					findSafeMove(row, col, maze);
					goBack(row, col, down);
				}
			}
			if(count == 2) {
				col += 1;
				if((maze.isValid(row, col)) && (maze.isStone(row, col))) {
					moves.add(right);
					findSafeMove(row, col, maze);
					goBack(row, col, right);
				}
			}
			if(count == 3) {
				col -= 1;
				if((maze.isValid(row, col)) && (maze.isStone(row, col))) {
					moves.add(left);
					findSafeMove(row, col, maze);
					goBack(row, col, left);
				}
			}
				
			count++;
		}
	}
	
	public void recursiveCall(int direction) {
		moves.add(direction);
		findSafeMove(row, col, maze);
		goBack(row, col, direction);
	}
	
	public void goBack(int row, int col, int direction) {
		moves.remove(direction);
	}
	
	public void solve() {
		findSafeMove(0, 0, maze);
	}
	
	public boolean alrBeenHere(int row, int col) {
		if(moveTracker[row][col])
			return true;
		return false;
	}
	
	public static void main(String[] args) {
		//LabyrinthSolver test = new LabyrinthSolver(5, 5);
		//test.solve();
		System.out.print("hello");
	}
	
}