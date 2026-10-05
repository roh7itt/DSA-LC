class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];

        // Empty string matches empty pattern
        dp[0][0] = true;

        // Empty string can be matched by all '*' pattern
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                char sChar = s.charAt(i - 1);
                char pChar = p.charAt(j - 1);

                // Normal character or '?'
                if (sChar == pChar || pChar == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }

                // '*'
                else if (pChar == '*') {

                    // '*' matches empty
                    // OR
                    // '*' matches current character
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }

        return dp[m][n];


/*        int n = s.length(), m= p.length();
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        for(int j = 1; j<= m; j++){
            dp[0][j]= false;
        }
        for(int i = 1;i<n; i++){
            boolean flag = true;
            for(int ii=1; ii <= i ; ii++){
                if(s.charAt(ii-1)!='*'){
                    flag = false;
                    break;
                }
            }
            dp[i][0]= flag;
        }
        for(int i = 1; i <= n;i++){
            for(int j = 1; j<= m; j++){
                if(s.charAt(i-1)==p.charAt(j-1)||s.charAt(i-1)=='?'){
                    dp[i][j]=dp[i-1][j-1];
                }else if(s.charAt(i-1)=='*'){
                    dp[i][j]=dp[i-1][j]||dp[i][j-1];
                }else{
                    dp[i][j]=false;
                }
            }
        }
        return dp[n][m]; */
    }
}