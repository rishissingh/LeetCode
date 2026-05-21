class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        
        if (s.length() < p.length()) {
            return result;
        }

        Map<Character, Integer> pMap = new HashMap<>();
        Map<Character, Integer> windowMap = new HashMap<>();

        for (char c : p.toCharArray()) {
            pMap.put(c, pMap.getOrDefault(c, 0) + 1);
        }

        int k = p.length();

        for (int i = 0; i < s.length(); i++) {
            char right = s.charAt(i);
            windowMap.put(right, windowMap.getOrDefault(right, 0) + 1);

            if (i >= k) {
                char left = s.charAt(i - k);
                windowMap.put(left, windowMap.get(left) - 1);
                if (windowMap.get(left) == 0) {
                    windowMap.remove(left);
                }
            }

            if (windowMap.equals(pMap)) {
                result.add(i - k + 1);
            }
        }

        return result;
    }
}