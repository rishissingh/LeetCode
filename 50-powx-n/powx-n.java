class Solution {
    public double myPow(double x, int n) {
        long N = n; // to handle Integer.MIN_VALUE
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        double ans = 1.0;

        while (N > 0) {
            if ((N & 1) == 1) {   // odd
                ans *= x;
            }
            x *= x;
            N >>= 1;             // N = N / 2
        }

        return ans;
    }
}