class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        
        for (int i = 0; i < n; i++) {
            int p = nums.get(i);
            
            if (p == 2) {
                ans[i] = -1;
            } else {
                int temp = p;
                int bitToFlip = 0;
           
                for (int b = 0; b < 31; b++) {
                    if (((p >> b) & 1) == 1) {
                        bitToFlip = b;
                    } else {
                        break;
                    }
                }
                
                ans[i] = p ^ (1 << bitToFlip);
            }
        }
        
        return ans;
    }
}