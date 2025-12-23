class Solution {
    public int removeDuplicates(int[] nums) {
        int unique=0;
        for(int j=1;j<nums.length;j++){
            if(nums[j]!=nums[unique]){
                unique++;
                nums[unique]=nums[j];
            }
            
        }
        return unique + 1;
    }
}