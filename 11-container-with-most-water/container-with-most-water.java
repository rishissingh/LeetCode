class Solution {
    public int maxArea(int[] height) {
        int maxW=0;
        int l=0;
        int r=height.length -1;

        while(l<r){
        int w = r - l;
        int ch = Math.min(height[l],height[r]);
        int cw = w*ch;
        maxW = Math.max(maxW,cw);

        if(height[l]<height[r]){
            l++;
        }else{
            r--;
        }
        }
        return maxW;
    }
}