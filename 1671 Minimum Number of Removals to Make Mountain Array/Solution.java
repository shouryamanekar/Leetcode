class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] lis = new int[n];
        int[] lds = new int[n];
        
        for (int i = 0; i < n; i++) {
            lis[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }
        
        for (int i = n - 1; i >= 0; i--) {
            lds[i] = 1;
            for (int j = n - 1; j > i; j--) {
                if (nums[i] > nums[j]) {
                    lds[i] = Math.max(lds[i], lds[j] + 1);
                }
            }
        }
        
        int minRemovals = Integer.MAX_VALUE;
        for (int i = 1; i < n - 1; i++) {
            if (lis[i] > 1 && lds[i] > 1) {
                int removals = n - (lis[i] + lds[i] - 1);
                minRemovals = Math.min(minRemovals, removals);
            }
        }
        
        return minRemovals;
    }
}