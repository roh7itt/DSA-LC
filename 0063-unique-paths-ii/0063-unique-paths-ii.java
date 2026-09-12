class Solution {
    int mod = (int)(1e9+7);
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid[0].length;    
        int n = obstacleGrid.length;
        int[][] dp = new int[n][m];
        return func(m, n, obstacleGrid, dp);
    }    
    private int func(int m, int n, int[][] obstacleGrid, int[][] dp) {
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                    continue;
                }

                int up = 0;
                int left = 0;
                if (i > 0)
                    up = dp[i - 1][j];
                if (j > 0)
                    left = dp[i][j - 1];

                dp[i][j] = up + left;
            }
        }
        return dp[n - 1][m - 1];
    }

}

/*
int r = obstacleGrid.length;
        int c = obstacleGrid[0].length;

        int[][]dp = new int[r][c];
       // Arrays.fill(dp , 0);

        for(int i = 0 ; i<r ;i++)
        {
            for(int j = 0 ; j<c ;j++)
            {
                if(obstacleGrid[i][j] == 1) dp[i][j] = 0;
                else if(i == 0 && j==0) dp[i][j] = 1;
                else if(i==0) dp[i][j] = dp[i][j-1];
                else if(j==0) dp[i][j] = dp[i-1][j];
                else  dp[i][j]= dp[i][j-1] + dp[i-1][j];
             
            }
        }

        return dp[r-1][c-1];
 */