package _942.DIStringMatch;

import java.util.Arrays;

public class _942DIStringMatch {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] a = s.diStringMatch("DDI");
        System.out.println(Arrays.toString(a));
    }

    static class Solution {
        public int[] diStringMatch(String S) {
            int N = S.length();
            int[] a = new int[N+1];
            int ICount = 0;
            int DCount = N;
            for (int i = 0; i < N; i++) {
                if(S.charAt(i) == 'I')
                {
                    a[i] = ICount;
                    ICount++;
                }
                else
                {
                    a[i] = DCount;
                    DCount--;
                }
            }
            if(S.charAt(N-1) == 'I')
            {
                a[N] = ICount;
            }
            else
            {
                a[N] = DCount;
            }
            return a;
        }
    }
}
