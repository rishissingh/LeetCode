class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        long maxSide = -1;
        long mod = 1_000_000_007;

        // Add boundaries to the fence arrays
        int[] h = Arrays.copyOf(hFences, hFences.length + 2);
        h[h.length - 2] = 1;
        h[h.length - 1] = m;
        
        int[] v = Arrays.copyOf(vFences, vFences.length + 2);
        v[v.length - 2] = 1;
        v[v.length - 1] = n;

        // Sort to easily calculate distances
        Arrays.sort(h);
        Arrays.sort(v);

        // Store all possible horizontal gaps in a Set
        Set<Integer> hGaps = new HashSet<>();
        for (int i = 0; i < h.length; i++) {
            for (int j = i + 1; j < h.length; j++) {
                hGaps.add(h[j] - h[i]);
            }
        }

        // Check all possible vertical gaps
        for (int i = 0; i < v.length; i++) {
            for (int j = i + 1; j < v.length; j++) {
                int gap = v[j] - v[i];
                if (hGaps.contains(gap)) {
                    maxSide = Math.max(maxSide, (long) gap);
                }
            }
        }

        return maxSide == -1 ? -1 : (int) ((maxSide * maxSide) % mod);
    }
}