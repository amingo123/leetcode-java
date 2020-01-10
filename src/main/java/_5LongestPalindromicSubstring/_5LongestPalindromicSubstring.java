package _5LongestPalindromicSubstring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _5LongestPalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));
    }

    public static String longestPalindrome(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        List<Character> result = new ArrayList<>();
        List<Character> resultTemp = new ArrayList<>();
        final char[] chars = s.toCharArray();
        boolean PalindromicModel = false;
        for (int i = 1, j = 0,num = -1; i < chars.length; i++) {
            hashMap.put(chars[i], i);
            if(PalindromicModel)
            {

            }
            else
            {
                if(hashMap.containsKey(chars[i]))
                {
                    if(chars[i] == chars[i-1]) {
                        PalindromicModel = true;
                        num = i - 1;
                        resultTemp.add(chars[i]);
                    }
                }
            }
        }
        return ";";
    }
}
