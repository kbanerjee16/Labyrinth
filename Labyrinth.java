public class Labyrinth {
    public final int rows;
    public final int cols;
    private UF tracker;
    private int destination;
    public static final int[] UP = {-1,0};
    public static final int[] DOWN = {1,0};
    public static final int[] LEFT = {0,-1};
    public static final int[]  RIGHT = {0,1};
    private boolean[][] grid;
    
    /**
    * Constructs a random labyrinth with specified width and height.
    * @param x the width (in grid squares) of the maze.
    * @param y the height (in grid squares) of the maze.
    * @return a new Labyrinth object of the specified dimensions.
    */
    public Labyrinth(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new boolean[rows][cols];
        this.tracker = new UF(rows * cols);
        this.destination = rows * cols - 1;
        
        build();
    }
    
    //Constructs the labyrinth
    private void build() {
        //Allow access to the starting and ending squares.
        grid[0][0] = true;
        grid[rows-1][cols-1] = true;
        
        while(!tracker.find(0 , destination)) {
           int n = (int) (Math.random() * this.rows);
           int m = (int) (Math.random() * this.cols);
           grid[n][m] = true;
           link(n,m);
        }  
    }
    
    //Converts grid coordinates to "absolute" coordinates used by the tracker.
    private int toAbs(int row, int col) {
        return row * cols + col;
    }
    
    private int toAbs(int[] loc) throws IllegalArgumentException {
        validateLoc(loc);
        return toAbs(loc[0], loc[1]);
    }
    
    //Ensure locations given as array of length 2 is formatted correctly.
    private void validateLoc(int[] loc) throws IllegalArgumentException {
        if (loc.length != 2) {
            throw new IllegalArgumentException("Location parameters must have exactly two arguments");
        }
    }
    
    /**
    * Check if the given row and column is on the grid.
    * @param row a potential row number
    * @param col a potential col number
    * @return {@code true} if the given row and column is on the grid. 
    */
    public boolean isValid(int row, int col) {
        return row >= 0 && row < this.rows && col >= 0 && col < this.cols;
    }
    
    /*
    * Check if a potential grid square is actually on the grid with coordinates
    * given as an array of length 2;
    */
    private boolean isValid(int[] loc) throws IllegalArgumentException {
        validateLoc(loc);
        return isValid(loc[0], loc[1]);
    }
    
    //Link a given grid square to adjacent stone (not lava) sites.
    private void link(int row, int col) {
        for (int[] direction : new int[][]{UP, DOWN, LEFT, RIGHT}) {
            int neighborRow = row + direction[0];
            int neighborCol = col + direction[1];
            if (isValid(neighborRow, neighborCol) && isStone(neighborRow, neighborCol)) {
                tracker.union(toAbs(row, col), toAbs(neighborRow, neighborCol));
            }
        }
    }
    
    /**
    * Checks if a given grid square is stone (or lava).
    * @param row the row of the grid square to check.
    * @param col the column of the grid square to check.
    * @return {@code true} if the given square is stone, {@code false} if lava.
    */
    public boolean isStone(int row, int col) {
        return grid[row][col];
    }
    
    private boolean isStone(int[] loc) throws IllegalArgumentException {
        validateLoc(loc);
        return isStone(loc[0], loc[1]);
    }
    
    /**
    * Checks if a given set of instructions accurately solves the labyrinth.
    * @param solution an array of integers representing the directions to move
    * from one square to the next. With 0 = up, 1 = down, 2 = left, and 3 = right.
    * @return {@code true if the given solution is correct}, otherwise {@code false}.
    */
    public boolean solves(int[] solution) {
        int[] currentSquare = {0,0};
        int[][] directions = {UP, DOWN, LEFT, RIGHT};
        for (int i = 0; i < solution.length; i++) {
            currentSquare = nextSquare(currentSquare, directions[solution[i]]);
            if (isStone(currentSquare) && isValid(currentSquare)) {
                return false;
            }
        }
        return toAbs(currentSquare[0],currentSquare[1]) == destination;
    }
    
    //Determine the next square from a given location and a direction to move.
    private int[] nextSquare(int[] loc, int[] direction) throws IllegalArgumentException {
        if (direction.length != 2) throw new IllegalArgumentException("direction and loc params must have exactly two elements.");
        return new int[] {loc[0] + direction[0], loc[1] + direction[1]};
    }
    
    //Print the grid.
    private void printGrid() {
        System.out.println();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j]) {
                    System.out.print(" S ");
                } else {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }
    
    //Some testing code.
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage is java Labyrinth [rows] [columns]");
            return;
        }
        
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        
        Labyrinth l = new Labyrinth(a,b);
        l.printGrid();
    }
    
    //Private Union-Find class to assist with random maze construction.
    private class UF {
        private int[] id;
        private int[] size;
        
        UF(int n){
            id = new int[n];
            size = new int[n];
            
            for(int i = 0; i < n; i++) {
                id[i] = i; 
                size[i] = 1;
            }
        }
        
        int rootOf(int i){
            while(id[i] != i){
                id[i] = id[id[i]];
                i = id[i];
            }
            
            return i;
        }
        
        void union(int p, int q){
            int pRoot = rootOf(p);
            int qRoot = rootOf(q);
            
            if(pRoot == qRoot) return;
            
            if(size[pRoot] < size[qRoot]){
                id[pRoot] = qRoot;
                size[qRoot] += size[pRoot];
            }
            else{
                id[qRoot] = pRoot;
                size[pRoot] += qRoot;
            }
        }
        
        boolean find(int p, int q){
            return rootOf(p) == rootOf(q);
        }
    }

}