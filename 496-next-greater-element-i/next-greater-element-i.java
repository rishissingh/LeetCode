class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
         int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            int currentVal = nums1[i];
            int nextGreater = -1;
            
            int j = 0;
            while (j < nums2.length && nums2[j] != currentVal) {
                j++;
            }
            for (int k = j + 1; k < nums2.length; k++) {
                if (nums2[k] > currentVal) {
                    nextGreater = nums2[k];
                    break;
                }
        
            }
            
            ans[i] = nextGreater;
        }
        
        return ans;

    }
}