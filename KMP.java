class Solution {
  public int deleteString(String s) {
      int n = s.length();
      int[] dp = new int[n];
      dp[n-1] = 1;
      for (int i = n - 2; i >= 0; i--) { //O(N)
          dp[i] = 1;
          int[] prefix = calcPrefixLen(s.substring(i, n)); // O(N)
          for (int j = 1; j < prefix.length; j++) { // O(N)
              if (j % 2 == 0 && j / 2 == prefix[j]) {
                  dp[i] = Math.max(dp[i], 1 + dp[i + prefix[j]]);
              }
          }
      }
      
      return dp[0];
  }
  
// Acknowledgement: https://bitbucket.org/StableSort/play/src/master/src/com/stablesort/stringmatch/KnuthMorrisPratt.java
  public int[] calcPrefixLen(String pattern) {
  int patternLen = pattern.length();
  int[] ar = new int[patternLen + 1];
  ar[0] = -1;
  ar[1] = 0;

  int prefixLen = 0;
  int i = 1;
  
  while (i < patternLen) {			
    if (pattern.charAt(prefixLen) == pattern.charAt(i)) { 
      prefixLen++;
      i++;
      ar[i] = prefixLen;
      
    } else if (prefixLen > 0) {
      prefixLen = ar[prefixLen]; // note  that we do not increment i here
      
    } else {
      i++;
      ar[i] = 0; // 'prefixLen' reached 0, so save that into ar[] and move forward
    }
  }
  
  return ar;
}
}