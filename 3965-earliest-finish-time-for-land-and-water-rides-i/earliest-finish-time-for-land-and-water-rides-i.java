class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int minFinishTime = Integer.MAX_VALUE;
        int n = landStartTime.length;
        int m = waterStartTime.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int landFirstFinish = landStartTime[i] + landDuration[i];
                int totalTimeLandFirst = Math.max(landFirstFinish, waterStartTime[j]) + waterDuration[j];
                minFinishTime = Math.min(minFinishTime, totalTimeLandFirst);

                int waterFirstFinish = waterStartTime[j] + waterDuration[j];
                int totalTimeWaterFirst = Math.max(waterFirstFinish, landStartTime[i]) + landDuration[i];
                minFinishTime = Math.min(minFinishTime, totalTimeWaterFirst);
            }
        }

        return minFinishTime;
    }
}