class Solution {
    public int change(int amount, int[] coins) {
/*        int n = coins.length;
        int[][] dp = new int[n][amount+1];
        
        for(int target = 0; target <= n; target++){
            if(target%coins[0]==0) dp[0][target] = 1;
        }
        for(int ind = 1; ind < n; ind++){
            for(int target = 0; target <= amount; target++){
                int notTake = dp[ind-1][target];
                int take = 0;
                if(coins[ind] <= target) take = dp[ind][target-coins[ind]];
                dp[ind][target] = take+notTake;
            }
        }
        return dp[n-1][amount]; */

        int n = coins.length;
        int dp[][] = new int[n][amount + 1];

        // Initialize base condition for the first element of the array
        for (int i = 0; i <= amount; i++) {
            if (i % coins[0] == 0)
                dp[0][i] = 1;
            // Else condition is automatically fulfilled, as dp array is initialized to zero
        }

        // Fill the dp array using dynamic programming
        for (int ind = 1; ind < n; ind++) {
            for (int target = 0; target <= amount; target++) {
                int notTaken = dp[ind - 1][target];

                int taken = 0;
                if (coins[ind] <= target)
                    taken = dp[ind][target - coins[ind]];

                dp[ind][target] = notTaken + taken;
            }
        }

        return dp[n - 1][amount];
    }
}

/*
public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int coin : coins){
            for(int i = coin; i <= amount; i++){
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
*/