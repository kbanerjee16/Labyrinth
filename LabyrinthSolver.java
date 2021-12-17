import java.util.ArrayList;
public class LabyrinthSolver {
	
	//stores moves
	private static ArrayList<Integer> moves = new ArrayList<Integer>();
	
	//up, down, left and right
	private static final int up = 0;
	private static final int left = 2;
	private static final int right = 3;
	private static final int down = 1;
	
	//tracks which squares have been tried
	private static boolean [][] moveTracker;
	
	//the recursive method
	public static boolean findSafeMove(int row, int col, Labyrinth l) {
		//if solution has been found exit
		if((row == l.rows-1) && (col == l.cols-1)) {
			return true;
		}
		
		//loops through and checks if up, down, left, or right is the right move
		for(int count = 0; count < 4; count++) {
			//check up
			if(count == 0) {
				if(isSafe(row-1, col, l)) {
					makeMove(row-1, col, up);
					//if there is a safe square next to the square it is on then return true
					if(findSafeMove(row-1, col, l))
						return true;
					//if there is not a safe square next to the square it is on backtrack by removing the move it made
					moves.remove(moves.size()-1);
				}
			}
			if(count == 1) {
				//check down
				if(isSafe(row+1, col, l)) {
					makeMove(row+1, col, down);
					if(findSafeMove(row+1, col, l))
						return true;
					moves.remove(moves.size()-1);
				}
			}
			if(count == 2) {
				//check left
				if(isSafe(row, col-1, l)) {
					makeMove(row, col-1, left);
					if(findSafeMove(row, col-1, l))
						return true;
					moves.remove(moves.size()-1);
				}
			}
			if(count == 3) {
				//check right
				if(isSafe(row, col+1, l)) {
					makeMove(row, col+1, right);
					if(findSafeMove(row, col+1, l))
						return true;
					moves.remove(moves.size()-1);
				}
			}
				
		}
		//if there are no safe moves return false
		return false;
	}
	
	public static boolean alrBeenHere(int row, int col) {
		//checks if it has already tried this square
		return moveTracker[row][col];
	}
	
	public static boolean isSafe(int row, int col, Labyrinth l) {
		//checks if the square is in the maze, stone, and it has not already tried that square
		return (l.isValid(row, col)) && (l.isStone(row, col)) && (!alrBeenHere(row, col));
	}
	
	public static void makeMove(int row, int col, int direction) {
		//add the direction to the solving instructions and mark that it has been in this square
		moves.add(direction);
		moveTracker[row][col] = true;
	}
	
	public static int[] solve(Labyrinth l) {
		//initializes a double array the size of the labyrinth to store where it is checking
		moveTracker = new boolean [l.rows][l.cols];
		moveTracker[0][0] = true;
		//calls the recursive method to find a solution
		findSafeMove(0, 0, l);
		//creates and array of moves and transfers the values from the ArrayList to the array
		int [] finalMoves = new int [moves.size()];
		for(int i = 0; i < moves.size(); i++) {
			finalMoves[i] = moves.get(i);
		}
		//returns the solution as an array of integers
		return finalMoves;
	}
}