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
	private boolean [][] moveTracker;
	
	public LabyrinthSolver(int r, int c) {
		this.maze = new Labyrinth(r, c);
		this.rows = r;
		this.cols = c;
		moveTracker = new boolean [r][c];
		moves.add(0);
	}
	public boolean findSafeMove(int row, int col, Labyrinth maze) {
		if((row == rows-1) && (col == cols-1)) {
			System.out.println(moves.size());
			return true;
		}
		count = 0;
		while(count < 4){
			if(count == 0){
				row -= 1;
				if(isSafe(row, col, up) {
					if(findSafeMove(row, col, maze)) {
						moves.add(up);
						moveTracker[row][col] = true;
						return true;
					}
				}
				goBack(row, col, direction);
			}
			if(count == 1) {
				row += 1;
				if(isSafe(row, col, down)) {
					if(findSafeMove(row, col, maze)) {
						moves.add(down);
						moveTracker[row][col] = true;
					}
				}
			}
			if(count == 2) {
				col += 1;
				recursiveCall(row, col, right);
			}
			if(count == 3) {
				col -= 1;
				recursiveCall(row, col, left);
			}
				
			count++;
		}
		return false;
	}
	
	public void isSafe(int row, int col, int direction) {
		if((maze.isValid(row, col)) && (maze.isStone(row, col)) && (!alrBeenHere(row, col))) {
			moveTracker[row][col] = true;
			findSafeMove(row, col, maze);
			goBack(row, col, direction);
		}
	}
	
	public void goBack(int row, int col, int direction) {
		moves.remove(moves.size-1);
		moveTracker[row][col] = false;
	}
	
	public void solve() {
		findSafeMove(0, 0, maze);
	}
	
	public void printSolution() {
		for(int i = 0; i < moves.size(); i++) {
			System.out.println(moves.get(i));
		}
	}
	
	public void printMaze() {
		maze.printGrid();
	}
	
	public boolean alrBeenHere(int row, int col) {
		if(moveTracker[row][col])
			return true;
		return false;
	}
	
	public static void main(String[] args) {
		LabyrinthSolver test = new LabyrinthSolver(5, 5);
		test.printMaze();
		test.solve();
	}
	
}