class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        HashMap<Integer,Integer> map= new HashMap<>();
        int[] c = Arrays.copyOf(nums,nums.length);
        Arrays.sort(c);
        int j=0;
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(c[i])){
                j++;
                continue;
            }
            map.put(c[i],j++);
        }
        int k=0;
        for(int i : nums){
            if(map.containsKey(i)){
                nums[k++]=map.get(i);
            }
        }
        return nums;
    }
}