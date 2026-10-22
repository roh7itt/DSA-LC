class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = binarySearch(nums, target, true);
        int last = binarySearch(nums, target, false);
        return new int[]{first, last};
        
    }
    private int binarySearch(int[] nums, int target, boolean findFirst){
        int low = 0, high = nums.length-1; 
        int result = -1;
        while(low <= high){
            int mid = low + (high-low)/2;
            if(nums[mid]==target){
                result = mid;
                if(findFirst == true){
                    high = mid-1;
                }
                else{
                    low = mid+1;
                }
            }
            else if(nums[mid]<target){
                low = mid+1;
            }
            else{
                high = mid-1;
            }
        }
        return result;
    }
}