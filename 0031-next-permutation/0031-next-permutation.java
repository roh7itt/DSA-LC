class Solution {
    public void nextPermutation(int[] nums) {
        int ind =-1;
        int n = nums.length;
        for(int i = n-2; i>=0; i--){
            if(nums[i]<nums[i+1]){
                ind=i;
                break;
            }
        }
        if(ind==-1){
            reverse(nums, 0, n-1);
            return;
        }
        for(int i = n-1; i>ind; i--){
            if(nums[i]>nums[ind]){
                swap(nums, i, ind);
                break;
            }
        }
        reverse(nums, ind+1, n-1);
    }
    private void reverse(int[] nums, int start, int end){
        while(start<end){
            swap(nums, start, end);
            start++;
            end--;
        }
    }
    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

/*
    RECURSIVE SOLUTION
    
    BRUTE FORCE

    NOT RECOMMENDED SINCE TC- O(n!*n)


public List<Integer> nextPermutation(int[] nums) {
        // List to hold all permutations
        List<List<Integer>> all = new ArrayList<>();

        // Sort the input to start from the smallest
        Arrays.sort(nums);

        // Generate all permutations
        permute(nums, 0, all);

        // Convert array to list for comparison
        List<Integer> current = new ArrayList<>();
        for (int num : nums)
            current.add(num);

        // Find and return the next permutation
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).equals(current)) {
                if (i == all.size() - 1)
                    return all.get(0);
                return all.get(i + 1);
            }
        }

        return current;
    }

    // Backtracking permutation generator
    private void permute(int[] nums, int start, List<List<Integer>> all) {
        if (start == nums.length) {
            List<Integer> temp = new ArrayList<>();
            for (int num : nums) temp.add(num);
            all.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            swap(nums, i, start);
            permute(nums, start + 1, all);
            swap(nums, i, start);
        }
    }

    // Swap helper
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

 */