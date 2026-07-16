class Solution {
    public long gcdSum(int[] nums) {
       int n = nums.length;
        int[] p = new int[n];

        int m = 0;
        for (int i = 0; i < n; i++) {
            m = Math.max(m, nums[i]);
            p[i] = gcd(nums[i], m);
        }

        Arrays.sort(p);

        long r = 0;
        int i = 0, j = n - 1;
        while (i < j) {
            r += gcd(p[i], p[j]);
            i++;
            j--;
        }

        return r;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a; 
    }
}