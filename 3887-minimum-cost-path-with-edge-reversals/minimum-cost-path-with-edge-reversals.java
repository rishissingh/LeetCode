class Solution {
    public int minCost(int n, int[][] edges) {
        List<int[]>[] adj = new ArrayList[n];
        // adjacency list for incoming edges (to facilitate reversal)
        List<int[]>[] revAdj = new ArrayList[n];
        
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            revAdj[i] = new ArrayList<>();
        }
        
        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
            revAdj[e[1]].add(new int[]{e[0], e[2]});
        }
        
        // dist[i][0] = min cost to reach i without using switch AT node i
        // dist[i][1] = min cost to reach i and then having used the switch AT node i
        // Actually, since the switch is used "immediately", we just need to track 
        // the cost to reach a node.
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.offer(new long[]{0, 0}); // {node, current_cost}
        
        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            int u = (int) curr[0];
            long d = curr[1];
            
            if (d > dist[u]) continue;
            if (u == n - 1) return (int) d;
            
            // 1. Try normal edges
            for (int[] edge : adj[u]) {
                int v = edge[0];
                int w = edge[1];
                if (dist[v] > d + w) {
                    dist[v] = d + w;
                    pq.offer(new long[]{v, dist[v]});
                }
            }
            
            // 2. Try reversing an incoming edge (using switch at node u)
            // Note: The problem says "at most once" per node. 
            // This logic assumes once you move to 'v' via reversal, 
            // you are at a new node and can use its switch independently.
            for (int[] edge : revAdj[u]) {
                int v = edge[0]; // the original source is our new destination
                int w = edge[1];
                if (dist[v] > d + (2L * w)) {
                    dist[v] = d + (2L * w);
                    pq.offer(new long[]{v, dist[v]});
                }
            }
        }
        
        return dist[n - 1] == Long.MAX_VALUE ? -1 : (int) dist[n - 1];
    }
}