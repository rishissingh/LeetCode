class Solution {
    public List<Long> mergeAdjacent(int[] nums) {
         List<Long> stack = new ArrayList<>();

        for (int num : nums) {
            stack.add((long) num);

            while (stack.size() >= 2) {
                int n = stack.size();

                if (stack.get(n - 1).equals(stack.get(n - 2))) {
                    long val = stack.remove(n - 1);
                    stack.remove(n - 2);
                    stack.add(val * 2);
                } else {
                    break;
                }
            }
        }

        return stack;
    }
}