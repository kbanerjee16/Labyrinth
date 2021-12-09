import java.util.ArrayList;
public class LabyrinthSolver {
	//add in line comments, take out main method, check work
	private static ArrayList<Integer> moves = new ArrayList<Integer>();
	private static final int up = 0;
	private static final int left = 2;
	private static final int right = 3;
	private static final int down = 1;
	private static boolean [][] moveTracker;
	
	public static boolean findSafeMove(int row, int col, Labyrinth l) {
		if((row == l.rows-1) && (col == l.cols-1)) {
			return true;
		}
	
		for(int count = 0; count < 4; count++){
			if(count == 0){
				if(isSafe(row-1, col, l)) {
					makeMove(row-1, col, up);
					if(findSafeMove(row-1, col, l))
						return true;
					goBack(row-1, col);
				}
			}
			if(count == 1) {
				if(isSafe(row+1, col, l)) {
					makeMove(row+1, col, down);
					if(findSafeMove(row+1, col, l))
						return true;
					goBack(row+1, col);
				}
			}
			if(count == 2) {
				if(isSafe(row, col-1, l)) {
					makeMove(row, col-1, left);
					if(findSafeMove(row, col-1, l))
						return true;
					goBack(row, col-1);
				}
			}
			if(count == 3) {
				if(isSafe(row, col+1, l)) {
					makeMove(row, col+1, right);
					if(findSafeMove(row, col+1, l))
						return true;
					goBack(row, col+1);
				}
			}
				
		}
		return false;
	}
	
	public static boolean isSafe(int row, int col, Labyrinth l) {
		return (l.isValid(row, col)) && (l.isStone(row, col)) && (!alrBeenHere(row, col));
	}
	
	public static void makeMove(int row, int col, int direction) {
		moves.add(direction);
		moveTracker[row][col] = true;
	}
	
	public static void goBack(int row, int col) {
		moveTracker[row][col] = false;
		moves.remove(moves.size()-1);
	}
	
	public static int[] solve(Labyrinth l) {
		moveTracker = new boolean [l.rows][l.cols];
		moveTracker[0][0] = true;
		findSafeMove(0, 0, l);
		int [] finalMoves = new int [moves.size()];
		for(int i = 0; i < moves.size(); i++) {
			finalMoves[i] = moves.get(i);
		}
		return finalMoves;
	}
	
	public static boolean alrBeenHere(int row, int col) {
		return moveTracker[row][col];
	}
	
	public static void main(String[] args) {
		Labyrinth l = new Labyrinth(4, 4);
		l.printGrid();
		System.out.println(l.solves(solve(l)));
	}
	
}