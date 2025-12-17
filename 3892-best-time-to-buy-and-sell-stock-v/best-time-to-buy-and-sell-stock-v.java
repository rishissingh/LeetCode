class Solution {
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        long NEG = Long.MIN_VALUE / 2;

        long[] flat = new long[k + 1];
        long[] longPos = new long[k + 1];
        long[] shortPos = new long[k + 1];

        Arrays.fill(longPos, NEG);
        Arrays.fill(shortPos, NEG);

        for (int price : prices) {
            long[] newFlat = Arrays.copyOf(flat, k + 1);
            long[] newLong = Arrays.copyOf(longPos, k + 1);
            long[] newShort = Arrays.copyOf(shortPos, k + 1);

            for (int t = 0; t <= k; t++) {

                // Open a normal buy
                newLong[t] = Math.max(newLong[t], flat[t] - price);

                // Open a short sell
                newShort[t] = Math.max(newShort[t], flat[t] + price);

                if (t > 0) {
                    // Close a normal transaction (sell)
                    newFlat[t] = Math.max(newFlat[t], longPos[t - 1] + price);

                    // Close a short transaction (buy back)
                    newFlat[t] = Math.max(newFlat[t], shortPos[t - 1] - price);
                }
            }

            flat = newFlat;
            longPos = newLong;
            shortPos = newShort;
        }

        long ans = 0;
        for (int t = 0; t <= k; t++) {
            ans = Math.max(ans, flat[t]);
        }
        return ans;
    }
}