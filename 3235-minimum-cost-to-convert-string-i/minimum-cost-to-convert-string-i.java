class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        long[][] dist = new long[26][26];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }
        for (int i = 0; i < cost.length; i++) {
            int u = original[i] - 'a';
            int v = changed[i] - 'a';
            dist[u][v] = Math.min(dist[u][v], (long) cost[i]);
        }
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
        long totalCost = 0;
        for (int i = 0; i < source.length(); i++) {
            int sChar = source.charAt(i) - 'a';
            int tChar = target.charAt(i) - 'a';
            
            if (sChar == tChar) continue;

            long pathCost = dist[sChar][tChar];
            if (pathCost >= Integer.MAX_VALUE) {
                return -1; 
            }
            totalCost += pathCost;
        }

        return totalCost;
    }
}