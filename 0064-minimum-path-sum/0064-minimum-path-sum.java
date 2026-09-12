class Solution {
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int [][] dp = new int[n][m];

        for(int i = 0; i < n;i++){
            for(int j = 0; j <m; j++){
                if(i == 0 && j==0){
                    dp[i][j] = grid[0][0];
                }
                else{
                    int up =grid[i][j]; 
                    int left = grid[i][j];
                    if(i>0) up += dp[i-1][j];
                    else up += (int)1e9;
                    if(j>0) left += dp[i][j-1];
                    else left += (int)1e9;
                    dp[i][j] = Math.min(up, left);
                }
            }
        }
        return dp[n-1][m-1];
    
    }
}

/* 
MEMOIZATION SOL

    // Function to calculate minimum path sum with memoization
    public int minPath(int i, int j,
            int[][] grid, int[][] dp) {

        // If we are at (0,0), return that cell's value
        if (i == 0 && j == 0)
            return grid[0][0];

        // If out of bounds, return large number
        if (i < 0 || j < 0)
            return (int) 1e9;

        // If already computed, return from dp
        if (dp[i][j] != -1)
            return dp[i][j];

        // Compute path by going up
        int up = grid[i][j] +
                 minPath(i - 1, j, grid, dp);

        // Compute path by going left
        int left = grid[i][j] +
                   minPath(i, j - 1, grid, dp);

        // Store the minimum in dp and return
        return dp[i][j] = Math.min(up, left);
    }

    // Main function that initializes dp and calls helper
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        // Create dp table initialized with -1
        int[][] dp = new int[n][m];
        for (int[] row : dp)
            Arrays.fill(row, -1);

        // Start from bottom-right corner
        return minPath(n - 1, m - 1, grid, dp);
    }
}


*/