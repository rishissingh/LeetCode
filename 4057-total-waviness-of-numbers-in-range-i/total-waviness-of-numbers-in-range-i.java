class Solution {
    public int totalWaviness(int num1, int num2) {
        int totalWavinessCount = 0;
        
        for (int i = num1; i <= num2; i++) {
            
            if (i < 100) {
                continue;
            }
            
            totalWavinessCount += calculateWaviness(i);
        }
        
        return totalWavinessCount;
    }
    
    private int calculateWaviness(int num) {
        String s = Integer.toString(num);
        int waviness = 0;
        int n = s.length();
        
        for (int j = 1; j < n - 1; j++) {
            char curr = s.charAt(j);
            char prev = s.charAt(j - 1);
            char next = s.charAt(j + 1);
            
            if (curr > prev && curr > next) {
                waviness++;
            }
         
            else if (curr < prev && curr < next) {
                waviness++;
            }
        }
        
        return waviness;
    }
}