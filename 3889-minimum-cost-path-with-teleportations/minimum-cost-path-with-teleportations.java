class Solution {
    public int minCost(int[][] grid, int k) {
       int m = grid.length;
        int n = grid[0].length;
        int INF = 1_000_000_000;

        // dp[ki][r][c] = min cost to reach (r, c) using exactly ki teleports
        int[][][] dp = new int[k + 1][m][n];
        for (int i = 0; i <= k; i++) {
            for (int r = 0; r < m; r++) Arrays.fill(dp[i][r], INF);
        }

        // Starting point: cost is 0
        dp[0][0][0] = 0;

        for (int ki = 0; ki <= k; ki++) {
            // 1. Process Normal Moves (Down and Right) for the current layer ki
            // This is O(m * n)
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (dp[ki][r][c] >= INF) continue;
                    
                    // Move Down
                    if (r + 1 < m) {
                        dp[ki][r + 1][c] = Math.min(dp[ki][r + 1][c], dp[ki][r][c] + grid[r + 1][c]);
                    }
                    // Move Right
                    if (c + 1 < n) {
                        dp[ki][r][c + 1] = Math.min(dp[ki][r][c + 1], dp[ki][r][c] + grid[r][c + 1]);
                    }
                }
            }

            // 2. Prepare for the next layer (ki + 1) via Teleportation
            if (ki < k) {
                // minCostForValue[v] stores the best cost to reach any cell with grid value v
                int[] minCostForValue = new int[10001];
                Arrays.fill(minCostForValue, INF);
                
                for (int r = 0; r < m; r++) {
                    for (int c = 0; c < n; c++) {
                        if (dp[ki][r][c] < INF) {
                            int val = grid[r][c];
                            minCostForValue[val] = Math.min(minCostForValue[val], dp[ki][r][c]);
                        }
                    }
                }

                // Suffix Minimum: suffixMin[v] = min cost to reach a cell with value >= v
                // This allows O(1) lookup for the teleportation rule grid[x][y] <= grid[i][j]
                for (int v = 9999; v >= 0; v--) {
                    minCostForValue[v] = Math.min(minCostForValue[v], minCostForValue[v + 1]);
                }

                // Apply teleportation to all cells for the next layer
                for (int r = 0; r < m; r++) {
                    for (int c = 0; c < n; c++) {
                        int targetVal = grid[r][c];
                        // We can teleport to (r, c) if there was a cell in layer ki with value >= targetVal
                        dp[ki + 1][r][c] = Math.min(dp[ki + 1][r][c], minCostForValue[targetVal]);
                    }
                }
            }
        }

        // Find the minimum cost at the target cell across all possible teleport counts
        int result = INF;
        for (int i = 0; i <= k; i++) {
            result = Math.min(result, dp[i][m - 1][n - 1]);
        }

        return result >= INF ? -1 : result; 
    }
}