class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();

        int[] dp = new int[m];
        int maxKeep = 1;

        // Each column alone can be kept
        for (int j = 0; j < m; j++) {
            dp[j] = 1;
        }

        for (int j = 0; j < m; j++) {
            for (int i = 0; i < j; i++) {

                boolean valid = true;

                // Check all rows
                for (int row = 0; row < n; row++) {
                    if (strs[row].charAt(i) > strs[row].charAt(j)) {
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
            maxKeep = Math.max(maxKeep, dp[j]);
        }

        return m - maxKeep;
    }
}