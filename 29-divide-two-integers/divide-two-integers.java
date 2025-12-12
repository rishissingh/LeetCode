class Solution {
    public int divide(int dividend, int divisor) {
         // Handle overflow case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine sign
        boolean isNegative = (dividend < 0) ^ (divisor < 0);

        // Convert to long before abs to handle MIN_VALUE
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        int quotient = 0;

        while (a - b >= 0) {
            long curDivisor = b;
            int curQuotient = 1;

            while (a - (curDivisor << 1) >= 0) {
                curDivisor <<= 1;
                curQuotient <<= 1;
            }

            quotient += curQuotient;
            a -= curDivisor;
        }

        return isNegative ? -quotient : quotient;    
    }
}