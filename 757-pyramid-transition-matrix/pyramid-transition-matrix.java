class Solution {
    Map<String, List<Character>> map = new HashMap<>();

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        // Step 1: Build the mapping
        for (String s : allowed) {
            String key = s.substring(0, 2);
            char val = s.charAt(2);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(val);
        }
        // Step 2: Start DFS
        return dfs(bottom);
    }

    private boolean dfs(String bottom) {
        // Base case: pyramid completed
        if (bottom.length() == 1) return true;

        // Generate all possible next rows
        List<String> nextRows = new ArrayList<>();
        buildNextRow(bottom, 0, new StringBuilder(), nextRows);

        // Try each next row
        for (String next : nextRows) {
            if (dfs(next)) return true;
        }
        return false;
    }

    private void buildNextRow(String bottom, int index,
                              StringBuilder current, List<String> result) {

        if (index == bottom.length() - 1) {
            result.add(current.toString());
            return;
        }

        String key = bottom.substring(index, index + 2);
        if (!map.containsKey(key)) return;

        for (char c : map.get(key)) {
            current.append(c);
            buildNextRow(bottom, index + 1, current, result);
            current.deleteCharAt(current.length() - 1); // backtrack
        }
    }
}