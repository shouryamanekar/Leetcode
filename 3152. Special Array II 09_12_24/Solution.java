class Solution {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] parityDifference = new int[n];
        
        // Precompute a parity difference array
        for (int i = 1; i < n; i++) {
            // Parity difference of adjacent numbers: 1 if different parity, 0 if same parity
            parityDifference[i] = parityDifference[i - 1] + 
                                  ((nums[i - 1] % 2) != (nums[i] % 2) ? 1 : 0);
        }

        boolean[] result = new boolean[queries.length];

        // Evaluate each query
        for (int i = 0; i < queries.length; i++) {
            int from = queries[i][0];
            int to = queries[i][1];
            if (to == from) {
                // A single element subarray is trivially special
                result[i] = true;
            } else {
                // Check if the number of adjacent pairs with different parity equals (to - from)
                result[i] = (parityDifference[to] - parityDifference[from]) == (to - from);
            }
        }

        return result;
    }
}