class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        // Base case: dp[0] = 0
        dp[0] = 0;

        // Loop through all amounts from 1 to amount
        for (int i = 1; i <= amount; i++) {
            // Try each coin
            for (int coin : coins) {
                // If coin can be used
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                    // Update dp[i] with minimum coins
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }

        // If dp[amount] is still infinity, return -1
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}

/*
RECURSIVE 

public int coinChange(int[] coins, int amount) {
        // Creating dp array initialized with -2
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -2);

        // Calling helper function
        return helper(coins, amount, dp);
    }

    // Helper recursive function
    private int helper(int[] coins, int rem, int[] dp) {
        // If remaining amount is zero
        if (rem == 0) return 0;

        // If remaining amount is negative
        if (rem < 0) return -1;

        // If already computed
        if (dp[rem] != -2) return dp[rem];

        // Initialize minimum with large value
        int mini = Integer.MAX_VALUE;

        // Try every coin
        for (int coin : coins) {
            // Recursive call
            int res = helper(coins, rem - coin, dp);

            // If result is valid
            if (res >= 0 && res < mini)
                mini = 1 + res;
        }

        // Store result in dp
        dp[rem] = (mini == Integer.MAX_VALUE) ? -1 : mini;
        return dp[rem];
    }


    SPACE OPTIMISATION

    public int minimumElements(int[] arr, int T) {
        int n = arr.length;

        // Create two arrays for space optimization
        int[] prev = new int[T + 1];
        int[] cur = new int[T + 1];

        // Initialize the base case for the first element
        for (int i = 0; i <= T; i++) {
            if (i % arr[0] == 0)
                prev[i] = i / arr[0];
            else
                prev[i] = (int) 1e9;
        }

        // Loop through the rest of the elements
        for (int ind = 1; ind < n; ind++) {
            for (int target = 0; target <= T; target++) {

                // Option 1: Do not take current element
                int notTake = prev[target];

                // Option 2: Take current element if possible
                int take = (int) 1e9;
                if (arr[ind] <= target)
                    take = 1 + cur[target - arr[ind]];

                // Store the minimum of both choices
                cur[target] = Math.min(notTake, take);
            }

            // Update previous row for next iteration
            prev = cur.clone();
        }

        // Extract the final answer from DP
        int ans = prev[T];
        if (ans >= 1e9)
            return -1;
        return ans;
 */