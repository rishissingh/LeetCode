class Solution {
    class SegmentTree {
        int n;
        int[] tree;

        public SegmentTree(int n) {
            this.n = n;
            this.tree = new int[4 * n];
        }

        public void update(int node, int start, int end, int idx, int val) {
            if (start == end) {
                tree[node] = val;
                return;
            }
            int mid = start + (end - start) / 2;
            if (idx <= mid) {
                update(2 * node, start, mid, idx, val);
            } else {
                update(2 * node + 1, mid + 1, end, idx, val);
            }
            tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
        }

        public int query(int node, int start, int end, int l, int r) {
            if (r < start || end < l) {
                return 0;
            }
            if (l <= start && end <= r) {
                return tree[node];
            }
            int mid = start + (end - start) / 2;
            int p1 = query(2 * node, start, mid, l, r);
            int p2 = query(2 * node + 1, mid + 1, end, l, r);
            return Math.max(p1, p2);
        }
    }

    public List<Boolean> getResults(int[][] queries) {
        int maxCoord = 0;
        for (int[] q : queries) {
            maxCoord = Math.max(maxCoord, q[1]);
        }
        maxCoord = Math.min(50000, maxCoord);

        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);
        obstacles.add(maxCoord + 1);

        SegmentTree st = new SegmentTree(maxCoord + 2);

        List<Boolean> results = new ArrayList<>();

        for (int[] q : queries) {
            int type = q[0];
            if (type == 1) {
                int x = q[1];
                if (x > maxCoord) continue;

                Integer prev = obstacles.floor(x);
                Integer next = obstacles.ceiling(x);

                obstacles.add(x);

                st.update(1, 0, maxCoord + 1, x, x - prev);
                st.update(1, 0, maxCoord + 1, next, next - x);
            } else {
                int x = q[1];
                int sz = q[2];

                if (sz > x) {
                    results.add(false);
                    continue;
                }

                int maxAvailable = st.query(1, 0, maxCoord + 1, 0, x);

                Integer floorObstacle = obstacles.floor(x);
                int lastGap = x - floorObstacle;
                maxAvailable = Math.max(maxAvailable, lastGap);

                results.add(maxAvailable >= sz);
            }
        }

        return results;
    }
}