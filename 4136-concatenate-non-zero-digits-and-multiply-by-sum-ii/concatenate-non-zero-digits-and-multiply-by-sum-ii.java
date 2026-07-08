class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        long MOD = 1000000007;

        long[] pow10 = new long[n + 1];
        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        long[] prefVal = new long[n + 1];
        long[] prefSum = new long[n + 1];
        int[] nonZeroCount = new int[n + 1];

        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';
            if (digit != 0) {
                prefVal[i + 1] = (prefVal[i] * 10 + digit) % MOD;
                prefSum[i + 1] = prefSum[i] + digit;
                nonZeroCount[i + 1] = nonZeroCount[i] + 1;
            } else {
                prefVal[i + 1] = prefVal[i];
                prefSum[i + 1] = prefSum[i];
                nonZeroCount[i + 1] = nonZeroCount[i];
            }
        }

        int[] ans = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int l = queries[q][0];
            int r = queries[q][1];

            int count = nonZeroCount[r + 1] - nonZeroCount[l];
            if (count == 0) {
                ans[q] = 0;
                continue;
            }

            long totalSum = prefSum[r + 1] - prefSum[l];
            long x = (prefVal[r + 1] - (prefVal[l] * pow10[count]) % MOD + MOD) % MOD;

            ans[q] = (int) ((x * totalSum) % MOD);
        }

        return ans;
    }
}