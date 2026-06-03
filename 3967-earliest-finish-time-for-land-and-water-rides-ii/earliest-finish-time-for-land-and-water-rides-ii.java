class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int n = landStartTime.length;
        int m = waterStartTime.length;

        int[][] rides = new int[n + m][3];
        for (int i = 0; i < n; i++) {
            rides[i][0] = landStartTime[i];
            rides[i][1] = landDuration[i];
            rides[i][2] = 0; 
        }
        for (int j = 0; j < m; j++) {
            rides[n + j][0] = waterStartTime[j];
            rides[n + j][1] = waterDuration[j];
            rides[n + j][2] = 1; 
        }

        Arrays.sort(rides, (a, b) -> Integer.compare(a[0], b[0]));

        int minLandDuration = Integer.MAX_VALUE;
        int minWaterDuration = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n + m; i++) {
            int start = rides[i][0];
            int duration = rides[i][1];
            int type = rides[i][2];

            if (type == 0) { 
                if (minWaterDuration != Integer.MAX_VALUE) {
                    ans = Math.min(ans, start + duration + minWaterDuration);
                }
                minLandDuration = Math.min(minLandDuration, duration);
            } else { 
                if (minLandDuration != Integer.MAX_VALUE) {
                    ans = Math.min(ans, start + duration + minLandDuration);
                }
                minWaterDuration = Math.min(minWaterDuration, duration);
            }
        }

        int minLandFinish = Integer.MAX_VALUE;
        int minWaterFinish = Integer.MAX_VALUE;

        for (int i = 0; i < n + m; i++) {
            int start = rides[i][0];
            int duration = rides[i][1];
            int type = rides[i][2];

            if (type == 0) {
                if (minWaterFinish != Integer.MAX_VALUE) {
                    ans = Math.min(ans, Math.max(start, minWaterFinish) + duration);
                }
            } else {
                if (minLandFinish != Integer.MAX_VALUE) {
                    ans = Math.min(ans, Math.max(start, minLandFinish) + duration);
                }
            }

            if (type == 0) {
                minLandFinish = Math.min(minLandFinish, start + duration);
            } else {
                minWaterFinish = Math.min(minWaterFinish, start + duration);
            }
        }

        return ans;
    }
}