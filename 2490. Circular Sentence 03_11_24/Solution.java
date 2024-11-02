class Solution {
    public boolean isCircularSentence(String sentence) {
         int n = sentence.length();
        

        for (int i = 0; i < n; i++) {

            char currentChar = sentence.charAt(i);
            
            if (currentChar == ' ') {

                if (i > 0 && i < n - 1) {
                    if (sentence.charAt(i - 1) != sentence.charAt(i + 1)) {
                        return false;
                    }
                }
            }
        }
        
        if (sentence.charAt(0) != sentence.charAt(n - 1)) {
            return false; 
        }
        
        return true; 
    }
}