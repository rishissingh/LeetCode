class Solution {

    class Pair {
        long cnt, sum;
        Pair(long cnt, long sum) {
            this.cnt = cnt;
            this.sum = sum;
        }
    }

    String s;
    Pair[][][][][] memo;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long x) {
        if (x <= 0) return 0;

        s = String.valueOf(x);
        int n = s.length();

        memo = new Pair[n][2][11][11][2];

        return dfs(0, 1, 10, 10, 0).sum;
    }

    private Pair dfs(int pos, int tight, int prev1, int prev2, int started) {

        if (pos == s.length()) {
            return new Pair(started == 1 ? 1 : 0, 0);
        }

        if (memo[pos][tight][prev1][prev2][started] != null) {
            return memo[pos][tight][prev1][prev2][started];
        }

        int limit = tight == 1 ? s.charAt(pos) - '0' : 9;

        long totalCnt = 0;
        long totalSum = 0;

        for (int d = 0; d <= limit; d++) {

            int ntight = (tight == 1 && d == limit) ? 1 : 0;

            if (started == 0 && d == 0) {

                Pair nxt = dfs(pos + 1, ntight, 10, 10, 0);

                totalCnt += nxt.cnt;
                totalSum += nxt.sum;

            } else {

                int add = 0;

                if (prev1 != 10 && prev2 != 10) {
                    if ((prev1 > prev2 && prev1 > d) ||
                        (prev1 < prev2 && prev1 < d)) {
                        add = 1;
                    }
                }

                Pair nxt = dfs(pos + 1, ntight, d, prev1, 1);

                totalCnt += nxt.cnt;
                totalSum += nxt.sum + add * nxt.cnt;
            }
        }

        return memo[pos][tight][prev1][prev2][started] =
                new Pair(totalCnt, totalSum);
    }
}