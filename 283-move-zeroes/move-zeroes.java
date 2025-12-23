class Solution {
    public void moveZeroes(int[] nums) {
        int f=0;

        for(int i=0;i<nums.length;i++){
            if (nums[i] != 0) {
                nums[f++] = nums[i];
            }
        }
         while (f < nums.length) {
            nums[f++] = 0;
        }
    }
}