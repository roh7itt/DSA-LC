class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = Integer.MAX_VALUE / 2;

        int[] best = new int[n];

        for (int i = 0; i < n; i++) {
            best[i] = INF;
        }

        int left = 0;
        int sum = 0;
        int answer = INF;
        int minLength = INF;

        for (int right = 0; right < n; right++) {

            sum += arr[right];

            // Shrink window if sum becomes too large
            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            // We found a subarray with sum = target
            if (sum == target) {

                int length = right - left + 1;

                // Combine with a previous non-overlapping subarray
                if (left > 0 && best[left - 1] != INF) {
                    answer = Math.min(
                        answer,
                        best[left - 1] + length
                    );
                }

                // Best single subarray ending at or before right
                minLength = Math.min(minLength, length);
            }

            // Carry forward the best subarray
            // found so far
            if (right == 0) {
                best[right] = minLength;
            } else {
                best[right] = Math.min(
                    best[right - 1],
                    minLength
                );
            }
        }

        return answer == INF ? -1 : answer;
    }
}