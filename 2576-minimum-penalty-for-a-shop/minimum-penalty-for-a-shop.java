class Solution {
    public int bestClosingTime(String customers) {
         int n = customers.length();
        
        // Count total Y initially (closing at hour 0)
        int penalty = 0;
        for (char c : customers.toCharArray()) {
            if (c == 'Y') penalty++;
        }

        int minPenalty = penalty;
        int bestHour = 0;

        // Move closing hour from 1 to n
        for (int i = 0; i < n; i++) {
            if (customers.charAt(i) == 'Y') {
                penalty--; // less missed customer
            } else {
                penalty++; // extra open-empty hour
            }

            if (penalty < minPenalty) {
                minPenalty = penalty;
                bestHour = i + 1;
            }
        }

        return bestHour;
    }
}