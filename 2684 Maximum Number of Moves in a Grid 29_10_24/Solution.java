class Solution {
    private int[][] memo;
    private int rows, cols;

    public int maxMoves(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        memo = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            Arrays.fill(memo[i], -1);
        }
        
        int maxMoves = 0;
        for (int row = 0; row < rows; row++) {
            maxMoves = Math.max(maxMoves, dfs(grid, row, 0));
        }
        
        return maxMoves;
    }
    
    private int dfs(int[][] grid, int row, int col) {
        if (col == cols - 1) return 0; 
        
        if (memo[row][col] != -1) return memo[row][col];
        
        int maxSteps = 0;
        int currVal = grid[row][col];
        
        int[][] directions = {{-1, 1}, {0, 1}, {1, 1}};
        
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            
            if (newRow >= 0 && newRow < rows && newCol < cols && grid[newRow][newCol] > currVal) {
                maxSteps = Math.max(maxSteps, 1 + dfs(grid, newRow, newCol));
            }
        }
        
        memo[row][col] = maxSteps; 
        return maxSteps;
    }
}
