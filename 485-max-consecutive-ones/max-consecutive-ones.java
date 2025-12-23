class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
         int c = 0;
        int m = 0;

        for (int num : nums) {
            if (num == 1) {
                c++;
                m = Math.max(m, c);
            } else {
                c = 0;
            }
        }
        return m;
    }
}