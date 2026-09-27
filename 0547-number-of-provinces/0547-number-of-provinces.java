class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int[] visited = new int[n];
        int provinces = 0;
        for(int city = 0; city < n; city++){
            if(visited[city]==0){
                provinces++;
                dfs(isConnected, city, visited);
            }
        }
        return provinces;
    }
    private void dfs(int[][] isConnected, int city, int[] visited){
        visited[city]=1;
        int n = isConnected.length;
        for(int nCity = 0; nCity < n; nCity++){
            if (isConnected[city][nCity] == 1 && visited[nCity] == 0) dfs(isConnected, nCity, visited);
        }
    }
}