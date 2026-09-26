class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startR = 0;
        int startC = 0;

        // Give every litter a bit number
        int[][] litterId = new int[m][n];

        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int litterCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startR = i;
                    startC = j;
                }

                if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        // No litter
        if (litterCount == 0) {
            return 0;
        }

        int fullMask = (1 << litterCount) - 1;

        /*
         * best[r][c][mask]
         *
         * Maximum energy with which we have reached
         * (r,c) after collecting 'mask'.
         *
         * If we reach the same state with less/equal energy,
         * it is useless.
         */
        int[][][] best =
            new int[m][n][1 << litterCount];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(best[i][j], -1);
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();

        // row, col, mask, remainingEnergy
        queue.offer(new int[]{
            startR, startC, 0, energy
        });

        best[startR][startC][0] = energy;

        int moves = 0;

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        while (!queue.isEmpty()) {

            int size = queue.size();

            // All states at this level use 'moves' moves
            while (size-- > 0) {

                int[] cur = queue.poll();

                int r = cur[0];
                int c = cur[1];
                int mask = cur[2];
                int e = cur[3];

                if (mask == fullMask) {
                    return moves;
                }

                for (int d = 0; d < 4; d++) {

                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // Outside grid
                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n) {
                        continue;
                    }

                    // Obstacle
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    // One move costs one energy
                    int newEnergy = e - 1;

                    if (newEnergy < 0) {
                        continue;
                    }

                    int newMask = mask;

                    char cell = classroom[nr].charAt(nc);

                    // Collect litter
                    if (cell == 'L') {
                        int id = litterId[nr][nc];
                        newMask |= (1 << id);
                    }

                    // Reset energy
                    if (cell == 'R') {
                        newEnergy = energy;
                    }

                    /*
                     * We already reached this position with
                     * the same collected litter and MORE energy.
                     */
                    if (best[nr][nc][newMask] >= newEnergy) {
                        continue;
                    }

                    best[nr][nc][newMask] = newEnergy;

                    queue.offer(new int[]{
                        nr, nc, newMask, newEnergy
                    });
                }
            }

            moves++;
        }

        return -1;
    }
}