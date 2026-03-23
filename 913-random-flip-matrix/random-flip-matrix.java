class Solution {
       int rows, cols;
    int total;
    Map<Integer, Integer> map;
    Random rand;
    public Solution(int m, int n) {
        rows = m;
        cols = n;
        total = m * n;
        map = new HashMap<>();
        rand = new Random();
    }
    
    public int[] flip() {
       int r = rand.nextInt(total);  
        total--;
        
        int index = map.getOrDefault(r, r);
        
        map.put(r, map.getOrDefault(total, total));
        
        return new int[]{index / cols, index % cols};
 
    }
    
    public void reset() {
        map.clear();
        total = rows * cols;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(m, n);
 * int[] param_1 = obj.flip();
 * obj.reset();
 */