class Solution {
    static class Node {
        long val;
        int id; // Original index to maintain leftmost priority
        Node prev, next;
        boolean removed = false;

        Node(long val, int id) { 
            this.val = val; 
            this.id = id;
        }
    }

    static class Pair implements Comparable<Pair> {
        long sum;
        int leftId;
        Node left;
        long leftVal, rightVal;

        Pair(Node left) {
            this.left = left;
            this.sum = left.val + left.next.val;
            this.leftId = left.id;
            this.leftVal = left.val;
            this.rightVal = left.next.val;
        }

        @Override
        public int compareTo(Pair other) {
            if (this.sum != other.sum) {
                return Long.compare(this.sum, other.sum);
            }
            // Tie-breaker: choose the leftmost pair (smallest original index)
            return Integer.compare(this.leftId, other.leftId);
        }
    }
    public int minimumPairRemoval(int[] nums) {
      int n = nums.length;
        if (n < 2) return 0;

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(nums[i], i);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int violations = 0;

        for (int i = 0; i < n; i++) {
            if (i > 0) nodes[i].prev = nodes[i - 1];
            if (i < n - 1) {
                nodes[i].next = nodes[i + 1];
                if (nodes[i].val > nodes[i + 1].val) {
                    violations++;
                }
                pq.add(new Pair(nodes[i]));
            }
        }

        int operations = 0;
        while (violations > 0 && !pq.isEmpty()) {
            Pair top = pq.poll();
            Node L = top.left;
            Node R = L.next;

            // Lazy deletion check: verify the pair is still valid
            if (L.removed || R == null || R.removed || L.val != top.leftVal || R.val != top.rightVal) {
                continue;
            }

            // Before merging, remove the local violations this pair/neighbors might have
            if (L.prev != null && L.prev.val > L.val) violations--;
            if (L.val > R.val) violations--;
            if (R.next != null && R.val > R.next.val) violations--;

            // Merge: update L.val, bypass R
            L.val = L.val + R.val;
            L.next = R.next;
            if (R.next != null) R.next.prev = L;
            R.removed = true;
            operations++;

            // Recalculate violations for the new neighbors
            if (L.prev != null && L.prev.val > L.val) violations++;
            if (L.next != null && L.val > L.next.val) violations++;

            // Add new possible pairs to the heap
            if (L.prev != null) pq.add(new Pair(L.prev));
            if (L.next != null) pq.add(new Pair(L));
        }

        return operations;
    }
}