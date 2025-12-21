class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        int deletion = 0;
        boolean[] sorted = new boolean[n];
        for(int col = 0; col < m; col++){
            boolean badCol = false;
            for(int row = 0; row < n-1; row++){
                if(!sorted[row]){
                    char uppar = strs[row].charAt(col);
                    char lower = strs[row+1].charAt(col);
                    if(uppar > lower){
                        badCol = true;
                        break;
                    }
                }
            }
            if(badCol){
                deletion++;
                continue;
            }
            for(int row = 0; row < n-1; row++){
                if(!sorted[row]){
                    char uppar = strs[row].charAt(col);
                    char lower = strs[row+1].charAt(col);
                    if(uppar < lower){
                        sorted[row] = true;
                    }
                }
            }
        }
        return deletion;
    }
}