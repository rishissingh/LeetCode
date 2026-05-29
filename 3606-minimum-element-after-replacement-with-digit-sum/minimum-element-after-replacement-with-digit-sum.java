class Solution {
    public int minElement(int[] nums) {
      int minVal = Integer.MAX_VALUE;
        
        for (int num : nums) {
            int sum = 0;
            while (num > 0) {
                sum += num % 10;
                num /= 10;
            }
            if (sum < minVal) {
                minVal = sum;
            }
        }
        
        return minVal;  
    }
}