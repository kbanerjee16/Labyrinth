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
		//this.maze = new Labyrinth(r, c);
		//this.rows = r;
		//this.cols = c;
		moveTracker = new boolean [r][c];
		moveTracker[0][0] = true;
	}
	public boolean findSafeMove(int row, int col, Labyrinth maze) {
		if((row == maze.rows-1) && (col == maze.cols-1)) {
			printSolution();
			return true;
		}
		count = 0;
		while(count < 4){
			if(count == 0){
				if(isSafe(row-1, col)) {
					makeMove(row-1, col, up);
					if(findSafeMove(row-1, col, maze))
						return true;
					goBack(row-1, col);
				}
			}
			if(count == 1) {
				if(isSafe(row+1, col)) {
					makeMove(row+1, col, down);
					if(findSafeMove(row+1, col, maze))
						return true;
					goBack(row+1, col);
				}
			}
			if(count == 2) {
				if(isSafe(row, col+1)) {
					makeMove(row, col+1, right);
					if(findSafeMove(row, col+1, maze))
						return true;
					goBack(row, col+1);
				}
			}
			if(count == 3) {
				if(isSafe(row, col-1)) {
					makeMove(row, col-1, left);
					if(findSafeMove(row, col-1, maze))
						return true;
					goBack(row, col-1);
				}
			}
				
			count++;
		}
		return false;
	}
	
	public boolean isSafe(int row, int col) {
		return (maze.isValid(row, col)) && (maze.isStone(row, col)) && (!alrBeenHere(row, col));
	}
	
	public void makeMove(int row, int col, int direction) {
		moves.add(direction);
		moveTracker[row][col] = true;
	}
	
	public void goBack(int row, int col) {
		moveTracker[row][col] = false;
		moves.remove(moves.size()-1);
	}
	
	public void solve(Labyrinth l) {
		findSafeMove(0, 0, l);
		//return moves() don't print it
	}
	
	public void printSolution() {
		for(int i = 0; i < moves.size(); i++) {
			if(moves.get(i) == 0)
				System.out.println("up");
			if(moves.get(i) == 1)
				System.out.println("down");
			if(moves.get(i) == 2)
				System.out.println("right");
			if(moves.get(i) == 3)
				System.out.println("left");
		}
	}
	
	public void printMaze() {
		maze.printGrid();
	}
	
	public boolean alrBeenHere(int row, int col) {
		return moveTracker[row][col];
	}
	
	public static void main(String[] args) {
		LabyrinthSolver test = new LabyrinthSolver(7, 7);
		test.printMaze();
		test.solve();
	}
	
}
//make class static no constructor
//return solution don't print it