class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), res);
        return res;
    }
      private void backtrack(int idx, int[] nums, List<Integer> temp, List<List<Integer>> res) {
        if (idx == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }

        backtrack(idx + 1, nums, temp, res);

        temp.add(nums[idx]);
        backtrack(idx + 1, nums, temp, res);
        temp.remove(temp.size() - 1);
    }
}