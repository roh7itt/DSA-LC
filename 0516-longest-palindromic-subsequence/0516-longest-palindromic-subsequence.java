class Solution {
    public int longestPalindromeSubseq(String s) {
        String t = new StringBuilder(s).reverse().toString();
        return lcs(s, t);
    }
    private int lcs(String s1, String s2){
        int n = s1.length(), m = s2.length();
        int[] prev = new int[m + 1]; // Use m+1 since j goes up to m
        int[] cur = new int[m + 1];
        
        // No need for explicit zero-initialization loops (lines 10-11)
        
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    cur[j] = 1 + prev[j-1];
                } else {
                    cur[j] = Math.max(prev[j], cur[j-1]);
                }
            }
            // Important: Copy cur to prev for the next iteration
            for(int k = 0; k <= m; k++){
                prev[k] = cur[k];
            }
            // Optional: Reset cur if needed, but it gets overwritten anyway
        }
        return cur[m];
        }
}