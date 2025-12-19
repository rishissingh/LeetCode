class Solution {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        // Initialize disjoint sets to keep track of groups
        int[] groups = new int[n];
        // List to store the result
        List<Integer> result = new ArrayList<>();
        // List to store temporary persons involved in a meeting
        List<Integer> temp = new ArrayList<>();

        // Initialize each person to be in their own group initially
        for (int i = 0; i < n; ++i) 
            groups[i] = i;
        // The first person is considered to be in group 0
        groups[firstPerson] = 0;

        // Sort meetings based on their time
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[2], b[2]));

        // Process meetings
        int i = 0;
        while (i < meetings.length) {
            int currentTime = meetings[i][2];
            temp.clear();
            // Process all meetings happening at the current time
            while (i < meetings.length && meetings[i][2] == currentTime) {
                int group1 = find(groups, meetings[i][0]);
                int group2 = find(groups, meetings[i][1]);
                // Merge the groups of persons involved in the meeting
                groups[Math.max(group1, group2)] = Math.min(group1, group2);
                // Add involved persons to the temporary list
                temp.add(meetings[i][0]);
                temp.add(meetings[i][1]);
                ++i;
            }
            // Update groups of persons in the temporary list
            for (int j = 0; j < temp.size(); ++j) {
                if (find(groups, temp.get(j)) != 0) {
                    groups[temp.get(j)] = temp.get(j);
                }
            }
        }

        // Find all people who have no conflicting meetings with the first person
        for (int j = 0; j < n; ++j) {
            if (find(groups, j) == 0) 
                result.add(j);
        }

        return result;
    }

    // Find operation of the disjoint set data structure
    private int find(int[] groups, int index) {
        while (index != groups[index]) 
            index = groups[index];
        return index;
    }
}