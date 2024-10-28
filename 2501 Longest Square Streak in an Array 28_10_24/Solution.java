class Solution {
    public int longestSquareStreak(int[] nums) {
        Arrays.sort(nums);
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int maxStreak = -1;

        for (int num : nums) {
            if (!numSet.contains(num)) continue; 
            
            int count = 1;
            int current = num;
            numSet.remove(num); 
            
            while (numSet.contains(current * current)) {
                current *= current;
                numSet.remove(current); 
                count++;
            }

            if (count >= 2) {
                maxStreak = Math.max(maxStreak, count);
            }
        }

        return maxStreak;
    }
}