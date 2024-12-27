import java.util.*;

class BinaryIndexedTree {
    private static final int INFINITY = 1 << 30;
    private int size;
    private int[] tree;

    public BinaryIndexedTree(int size) {
        this.size = size;
        tree = new int[size + 1];
        Arrays.fill(tree, INFINITY);
    }

    public void update(int x, int v) {
        while (x <= size) {
            tree[x] = Math.min(tree[x], v);
            x += x & -x;
        }
    }

    public int query(int x) {
        int minimum = INFINITY;
        while (x > 0) {
            minimum = Math.min(minimum, tree[x]);
            x -= x & -x;
        }
        return minimum == INFINITY ? -1 : minimum;
    }
}

class Solution {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int n = heights.length;
        int m = queries.length;

        for (int i = 0; i < m; ++i) {
            if (queries[i][0] > queries[i][1]) {
                queries[i] = new int[] {queries[i][1], queries[i][0]};
            }
        }

        Integer[] indices = new Integer[m];
        for (int i = 0; i < m; ++i) {
            indices[i] = i;
        }

        Arrays.sort(indices, (i, j) -> queries[j][1] - queries[i][1]);

        int[] sortedHeights = heights.clone();
        Arrays.sort(sortedHeights);

        int[] results = new int[m];
        BinaryIndexedTree tree = new BinaryIndexedTree(n);
        int j = n - 1;

        for (int index : indices) {
            int left = queries[index][0], right = queries[index][1];
            while (j > right) {
                int position = n - Arrays.binarySearch(sortedHeights, heights[j]) + 1;
                tree.update(position, j);
                --j;
            }

            if (left == right || heights[left] < heights[right]) {
                results[index] = right;
            } else {
                int position = n - Arrays.binarySearch(sortedHeights, heights[left]);
                results[index] = tree.query(position);
            }
        }

        return results;
    }
}