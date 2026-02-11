class Solution {
    public int numberOfRoutes(String[] grid, int d) {
          int n = grid.length;
        int m = grid[0].length();
        long MOD = 1_000_000_007;

        // dp[j][0] = reached via vertical move, dp[j][1] = reached via horizontal move
        long[][] dp = new long[m][2];

        // Precompute reach limits
        int hReach = d;
        int vReach = (d >= 1) ? (int) Math.sqrt(d * d - 1) : -1;

        // 1. Initialize Bottom Row (Row n-1)
        for (int j = 0; j < m; j++) {
            if (grid[n - 1].charAt(j) == '.') {
                dp[j][0] = 1;
            }
        }

        // Handle horizontal moves within the bottom row
        long[] pref = new long[m + 1];
        for (int j = 0; j < m; j++) pref[j + 1] = (pref[j] + dp[j][0]) % MOD;

        for (int j = 0; j < m; j++) {
            if (grid[n - 1].charAt(j) == '.') {
                int L = Math.max(0, j - hReach);
                int R = Math.min(m - 1, j + hReach);
                long total = (pref[R + 1] - pref[L] + MOD) % MOD;
                dp[j][1] = (total - dp[j][0] + MOD) % MOD;
            }
        }

        // 2. Iterate Upwards
        for (int i = n - 2; i >= 0; i--) {
            long[][] nextDp = new long[m][2];
            
            // Vertical Transitions (from row i+1 to i)
            long[] prefPrev = new long[m + 1];
            for (int j = 0; j < m; j++) {
                prefPrev[j + 1] = (prefPrev[j] + dp[j][0] + dp[j][1]) % MOD;
            }

            for (int j = 0; j < m; j++) {
                if (grid[i].charAt(j) == '.') {
                    if (vReach >= 0) {
                        int L = Math.max(0, j - vReach);
                        int R = Math.min(m - 1, j + vReach);
                        nextDp[j][0] = (prefPrev[R + 1] - prefPrev[L] + MOD) % MOD;
                    }
                }
            }

            // Horizontal Transitions (within current row i)
            long[] prefCurr = new long[m + 1];
            for (int j = 0; j < m; j++) {
                prefCurr[j + 1] = (prefCurr[j] + nextDp[j][0]) % MOD;
            }

            for (int j = 0; j < m; j++) {
                if (grid[i].charAt(j) == '.') {
                    int L = Math.max(0, j - hReach);
                    int R = Math.min(m - 1, j + hReach);
                    long total = (prefCurr[R + 1] - prefCurr[L] + MOD) % MOD;
                    nextDp[j][1] = (total - nextDp[j][0] + MOD) % MOD;
                }
            }
            dp = nextDp;
        }

        // 3. Collect Results from Top Row
        long ans = 0;
        for (int j = 0; j < m; j++) {
            ans = (ans + dp[j][0] + dp[j][1]) % MOD;
        }

        return (int) ans;
    }
}