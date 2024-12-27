class Solution {
    int n;

    boolean solve(int[] row, int[] col, int[][] sub, int cnt, char[][] board) {
        if (cnt == n * n) return true;
        int best = 9, x = -1, y = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '.') continue;
                int canFit = 0;
                for (int el = 1; el <= 9; el++) {
                    if ((row[i] & (1 << el)) != 0 || (col[j] & (1 << el)) != 0 || (sub[i / 3][j / 3] & (1 << el)) != 0) {
                        continue;
                    }
                    canFit++;
                }
                if (canFit < best) {
                    best = canFit;
                    x = i;
                    y = j;
                }
            }
        }
        if (x == -1 || y == -1) return false;
        for (int el = 1; el <= 9; el++) {
            if ((row[x] & (1 << el)) != 0 || (col[y] & (1 << el)) != 0 || (sub[x / 3][y / 3] & (1 << el)) != 0) {
                continue;
            }
            board[x][y] = (char) (el + '0');
            row[x] |= (1 << el);
            col[y] |= (1 << el);
            sub[x / 3][y / 3] |= (1 << el);
            if (solve(row, col, sub, cnt + 1, board)) return true;
            row[x] &= ~(1 << el);
            col[y] &= ~(1 << el);
            sub[x / 3][y / 3] &= ~(1 << el);
            board[x][y] = '.';
        }
        return false;
    }

    public void solveSudoku(char[][] board) {
        n = board.length;
        int[] row = new int[n];
        int[] col = new int[n];
        int[][] sub = new int[3][3];

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') continue;
                int x = board[i][j] - '0';
                row[i] |= (1 << x);
                col[j] |= (1 << x);
                sub[i / 3][j / 3] |= (1 << x);
                cnt++;
            }
        }

        solve(row, col, sub, cnt, board);
    }
}
