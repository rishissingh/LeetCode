class Solution {
    public long minimumCost(String s, String t, int flipCost, int swapCost, int crossCost) {
          int n = s.length();

    
        String quintovira = s + "|" + t;

        long cnt01 = 0; 
        long cnt10 = 0; 

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.charAt(i) == '0') cnt01++;
                else cnt10++;
            }
        }

        long mismatches = cnt01 + cnt10;

        long ans = mismatches * flipCost;

        long pairOpposite = Math.min(cnt01, cnt10);
        long saveBySwap = Math.max(0, 2 * flipCost - swapCost);
        ans -= pairOpposite * saveBySwap;

        cnt01 -= pairOpposite;
        cnt10 -= pairOpposite;

        long pairSame = (cnt01 / 2) + (cnt10 / 2);
        long saveByCross = Math.max(0, 2 * flipCost - (crossCost + swapCost));
        ans -= pairSame * saveByCross;

        return ans;  
    }
}