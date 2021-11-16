public class Labyrinth {
    public final int width;
    public final int height;
    private UF tracker;
    private int destination;
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int  RIGTH = 3;
    private boolean[] board;
    
    /**
    * Constructs a random labyrinth with specified width and height.
    * @param x the width (in grid squares) of the maze.
    * @param y the height (in grid squares) of the maze.
    * @return a new Labyrinth object of the specified dimensions.
    */
    public Labyrinth(int x, int y) {
        this.width = x;
        this.height = y;
        this.board = new boolean[width][height];
        this.tracker = new UF(width * height);
        this.destination = width * height - 1;
        
        build();
    }
    
    //Constructs the labyrinth
    private void build() {
        //Allow access to the starting and ending squares.
        board[0] = true;
        board[height * width - 1] = true;
        
        while(!tracker.find(0 , destination)) {
            int r = Math.random() * destination;
            board[r] = true;
            
        }
        
    }
    
    private int toAbs(int row, int col) {
        return row * width + col;
    }
    
    private boolean solves(int[] instructions) {
        
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