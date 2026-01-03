class Solution {
    public int numOfWays(int n) {
      long MOD = 1_000_000_007;

        // For first row
        long aba = 6; // patterns like A B A
        long abc = 6; // patterns like A B C

        for (int i = 2; i <= n; i++) {
            long newAba = (3 * aba + 2 * abc) % MOD;
            long newAbc = (2 * aba + 2 * abc) % MOD;

            aba = newAba;
            abc = newAbc;
        }

        return (int)((aba + abc) % MOD);  
    }
}