class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
         // Sort in descending order
        Arrays.sort(happiness);
        
        long sum = 0;
        int n = happiness.length;
        
        for (int i = 0; i < k; i++) {
            int val = happiness[n - 1 - i] - i;
            if (val > 0) {
                sum += val;
            } else {
                break; // further values will be <= 0
            }
        }
        
        return sum;
    }
}