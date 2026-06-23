class Solution {
    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        long MOD = 1_000_000_007;

        long[] dpDown = new long[m];
        long[] dpUp = new long[m];

        for (int x = 0; x < m; x++) {
            dpDown[x] = 1;
            dpUp[x] = 1;
        }

        for (int i = 2; i <= n; i++) {
            long[] nextDown = new long[m];
            long[] nextUp = new long[m];

            long sumUp = 0;
            for (int y = 0; y < m; y++) {
                nextDown[y] = sumUp;
                sumUp = (sumUp + dpUp[y]) % MOD;
            }

            long sumDown = 0;
            for (int y = m - 1; y >= 0; y--) {
                nextUp[y] = sumDown;
                sumDown = (sumDown + dpDown[y]) % MOD;
            }

            dpDown = nextDown;
            dpUp = nextUp;
        }

        long ans = 0;
        for (int x = 0; x < m; x++) {
            ans = (ans + dpDown[x] + dpUp[x]) % MOD;
        }

        if (n == 1) {
            return m;
        }

        return (int) (ans % MOD);
    }
}