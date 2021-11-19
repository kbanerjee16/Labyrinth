public class LabyrinthSolver {
	private Labyrinth maze;
	private int rows;
	private int cols;
	private FINAL int up = row+1;
	private FINAL int left = col-1;
	private FINAL int right = col+1;
	private FINAL int down = row-1;
	
	public LabyrinthSolver(int r, int c) {
		this.maze = new Labyrinth(r, c);
		this.rows = r;
		this.cols = c;
	}
	public void findSafeMove(int row, int col, Labyrinth l) {
		if((row == rows-1) && (col == cols-1))
			show solution
			leave 
		//loops through 4 times once for each direction
		count = 0;
		while(count < 4){
			if(count = 0){
				row += 1;
				if((maze.isValid(row, col)) && (maze.isStone(row, col))) {
					//add solution to array
					findSafeMove(row, col, l);
					//go back
				}
			}
			if(count = 1) {
				row -= 1;
				if((maze.isValid(row, col)) && (maze.isStone(row, col))) {
					//add solution to array
					findSafeMove(row, col, l);
					//go back
				}
			}
			if(count = 2) {
				col += 1;
				if((maze.isValid(row, col)) && (maze.isStone(row, col))) {
					//add solution to array
					findSafeMove(row, col, l);
					//go back
				}
			}
			if(count = 3) {
				col -= 1;
				if((maze.isValid(row, col)) && (maze.isStone(row, col))) {
					//add solution to array
					findSafeMove(row, col, l);
					//go back
				}
			}
				
			if((maze.isStone(row+check, col)) && maze.isValid(row+check, col))) {
				//add to solution array
				row = row+check;
				col = col;
				findSafeMove(row, col, l);
				//go back
			}
		}
		foreach (x:{up, down, left, right})
			if(x is a valid move)
				make move x
				findSafeMove(newrow, newcol, l)
	}
	
	public int determineDirection(int row, int col, int direction) {
		if(direction == up)
			return row+1;
		if(direction == down)
			return row-1;
		if(direction == right)
			return col+1;
		if(direction == left)
			return col-1;
	}
}