class Solution {
    public long maxRunTime(int n, int[] batteries) {
        long total=0;
        for(int b:batteries) total += b;

        long low=0;
        long high=total/n;

        while(low<=high){
            long mid = low + (high - low)/2;
            if(canRun(mid, batteries,n)){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return high;
    }
    private boolean canRun(long T, int[] batteries, int  n){
        long sum=0;
        for(long b:batteries){
            sum+=Math.min(b,T);
            if(sum>=(long)n*T) return true;
        }
        return sum >= (long)n*T;
    }
}