class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        if (rows == 1) return encodedText;

        int n = encodedText.length();
        int cols = n / rows;
        StringBuilder result = new StringBuilder();

        for (int j = 0; j < cols; j++) {
            int i = 0, k = j;
            
            while (i < rows && k < cols) {
                result.append(encodedText.charAt(i * cols + k));
                i++;
                k++;
            }
        }
        while (result.length() > 0 && result.charAt(result.length() - 1) == ' ') {
            result.deleteCharAt(result.length() - 1);
        }

        return result.toString();
    }
}