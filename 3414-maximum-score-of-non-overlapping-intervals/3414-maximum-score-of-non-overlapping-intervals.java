class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        // Step 1: attach original index and sort by end time
        int[][] arr = new int[n][4];   // [start, end, weight, originalIndex]
        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);  // start
            arr[i][1] = intervals.get(i).get(1);  // end
            arr[i][2] = intervals.get(i).get(2);  // weight
            arr[i][3] = i;                         // original index
        }

        // sort by end time; tie-break by smaller original index
        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) return a[1] - b[1];
            return a[3] - b[3];
        });

        // Step 2: DP
        // dp[k][i] = {maxWeight, lexSmallestIndices} picking exactly k intervals from first i
        // use long[] to store: [totalWeight, idx1, idx2, idx3, idx4] for k=1..4
        int K = 4;

        // dp[k] = best result picking exactly k intervals so far
        // each entry: long[2] = {totalWeight, encoded indices (as bitmask is too big)}
        // instead store: long totalWeight and int[] indices for each k

        long[] dpWeight = new long[K + 1];
        int[][] dpIdx   = new int[K + 1][K];
        Arrays.fill(dpWeight, Long.MIN_VALUE);
        dpWeight[0] = 0;
        Arrays.fill(dpIdx[0], Integer.MAX_VALUE);

        // for each interval in sorted order
        // we maintain best[k] = best solution using exactly k intervals

        // Use arrays of size n for each k
        long[][] best       = new long[K + 1][n + 1];
        int[][][]bestIdx    = new int[K + 1][n + 1][K];

        for (long[] row : best) Arrays.fill(row, Long.MIN_VALUE);
        best[0][0] = 0;
        for (int[] r : bestIdx[0]) Arrays.fill(r, Integer.MAX_VALUE);

        for (int i = 1; i <= n; i++) {
            int start  = arr[i - 1][0];
            int end    = arr[i - 1][1];
            int weight = arr[i - 1][2];
            int idx    = arr[i - 1][3];

            // find last interval that ends before current starts (binary search)
            int lo = 0, hi = i - 1, p = 0;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (arr[mid][1] < start) {
                    p = mid + 1;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }

            for (int k = 0; k <= K; k++) {
                // option 1: skip interval i
                best[k][i]    = best[k][i - 1];
                bestIdx[k][i] = bestIdx[k][i - 1].clone();

                // option 2: take interval i (need k-1 from first p intervals)
                if (k >= 1 && best[k - 1][p] != Long.MIN_VALUE) {
                    long newWeight = best[k - 1][p] + weight;
                    int[] newIdx   = buildIdx(bestIdx[k - 1][p], idx, k - 1);

                    if (newWeight > best[k][i] ||
                       (newWeight == best[k][i] && isSmaller(newIdx, bestIdx[k][i]))) {
                        best[k][i]    = newWeight;
                        bestIdx[k][i] = newIdx;
                    }
                }
            }
        }

        // find best answer across all k from 1 to K
        long   ansWeight = Long.MIN_VALUE;
        int[]  ansIdx    = new int[K];
        Arrays.fill(ansIdx, Integer.MAX_VALUE);

        for (int k = 1; k <= K; k++) {
            if (best[k][n] == Long.MIN_VALUE) continue;
            if (best[k][n] > ansWeight ||
               (best[k][n] == ansWeight && isSmaller(bestIdx[k][n], ansIdx))) {
                ansWeight = best[k][n];
                ansIdx    = bestIdx[k][n].clone();
            }
        }

        // collect valid indices and sort
        List<Integer> result = new ArrayList<>();
        for (int idx : ansIdx) {
            if (idx != Integer.MAX_VALUE) result.add(idx);
        }
        Collections.sort(result);

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // build new index array by inserting newIdx into existing, keeping sorted
    static int[] buildIdx(int[] prev, int newIdx, int prevK) {
        int K      = 4;
        int[] res  = new int[K];
        Arrays.fill(res, Integer.MAX_VALUE);
        int count  = 0;
        for (int i = 0; i < K && prev[i] != Integer.MAX_VALUE; i++) {
            res[count++] = prev[i];
        }
        res[count] = newIdx;
        Arrays.sort(res, 0, count + 1);
        return res;
    }

    // compare two index arrays lexicographically
    static boolean isSmaller(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return a[i] < b[i];
        }
        return false;
    }
}