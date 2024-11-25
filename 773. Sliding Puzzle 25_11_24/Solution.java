class Solution {
    public int slidingPuzzle(int[][] board) {
        // Target state for the puzzle
        String target = "123450";
        
        // Convert the initial board into a single string representation
        StringBuilder start = new StringBuilder();
        for (int[] row : board) {
            for (int num : row) {
                start.append(num);
            }
        }
        
        // Possible moves for each position in the 1D representation
        int[][] directions = {
            {1, 3},    // 0 can swap with 1, 3
            {0, 2, 4}, // 1 can swap with 0, 2, 4
            {1, 5},    // 2 can swap with 1, 5
            {0, 4},    // 3 can swap with 0, 4
            {1, 3, 5}, // 4 can swap with 1, 3, 5
            {2, 4}     // 5 can swap with 2, 4
        };
        
        // BFS setup
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(start.toString());
        visited.add(start.toString());
        
        int moves = 0; // Number of moves
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                
                // Check if target state is reached
                if (current.equals(target)) {
                    return moves;
                }
                
                // Find the index of '0' (empty space)
                int zeroIndex = current.indexOf('0');
                
                // Explore all valid moves
                for (int swapIndex : directions[zeroIndex]) {
                    String nextState = swap(current, zeroIndex, swapIndex);
                    
                    if (!visited.contains(nextState)) {
                        visited.add(nextState);
                        queue.offer(nextState);
                    }
                }
            }
            
            // Increment moves after processing the current level
            moves++;
        }
        
        // If no solution is found
        return -1;
    }
    
    // Helper function to swap characters in a string
    private String swap(String state, int i, int j) {
        char[] chars = state.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }
}
