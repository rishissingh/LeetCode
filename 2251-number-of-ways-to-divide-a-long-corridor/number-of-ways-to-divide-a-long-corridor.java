class Solution {
    public int numberOfWays(String corridor) {
        int mod = 1000000007;

        List<Integer> seats = new ArrayList<>();
        for (int i = 0; i < corridor.length(); i++) {
            if (corridor.charAt(i) == 'S') {
                seats.add(i);
            }
        }

        int totalSeats = seats.size();
        if (totalSeats < 2 || totalSeats % 2 != 0) return 0;
        if (totalSeats == 2) return 1;

        long ans = 1;

        for (int i = 2; i < totalSeats; i += 2) {
            int left = seats.get(i - 1);
            int right = seats.get(i);
            int plantsBetween = right - left - 1;
            ans = (ans * (plantsBetween + 1)) % mod;
        }
        return (int) ans;
    }
}