package _5LongestPalindromicSubstring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _5LongestPalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));//bab
        System.out.println(longestPalindrome("aa"));//aa
        System.out.println(longestPalindrome("cbbd"));//bb
        System.out.println(longestPalindrome("ccc"));//ccc
        System.out.println(longestPalindrome("aba"));//aba
        System.out.println(longestPalindrome("abcba"));//abcba
        System.out.println(longestPalindrome("aaaa"));//aaaa
        System.out.println(longestPalindrome("aaaaa"));//aaaaa
        System.out.println(longestPalindrome("abacab"));//bacab

    }

    public static String longestPalindrome(String s) {
        if (s == null) return null;
        if (s.length() <= 1) return s;
        HashMap<Character, Integer> hashMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        StringBuilder sbTemp = new StringBuilder();
        final char[] chars = s.toCharArray();
        boolean PalindromicModel = false;
        for (int i = 0, num = -1; i < chars.length; i++) {
            if (PalindromicModel) {
                if (num>=0 && chars[i] == chars[num] ) {
                    sbTemp.append(chars[i]);
                    if(sbTemp.length()%2 ==0 && sbTemp.length()< s.length()) {
                        sbTemp.insert(0, chars[num]);
                    }
                    num = i - 1;
                } else {
                    if (sbTemp.length() > sb.length()) {
                        sb.append(sbTemp);
                    }
                    sbTemp = new StringBuilder();
                    PalindromicModel = false;
                    num = -1;
                }
                hashMap.put(chars[i], i);
            } else {
                if (hashMap.containsKey(chars[i])) {
                    if ((i - 2 >= 0) && chars[i] == chars[i - 2]) {
                        PalindromicModel = true;
                        num = i - 3;
                        sbTemp.append(chars[i - 2]);
                        sbTemp.append(chars[i - 1]);
                        sbTemp.append(chars[i]);
                    } else if (chars[i] == chars[i - 1]) {
                        PalindromicModel = true;
                        num = i - 1;
                        sbTemp.append(chars[i]);
                        sbTemp.append(chars[i - 1]);
                    }
                }
                hashMap.put(chars[i], i);
            }
        }
        if (sb.length() == 0) {
            if (sbTemp.length() == 0) {
                return String.valueOf(s.charAt(0));
            } else {
                return sbTemp.toString();
            }
        } else {
            return sb.toString();
        }
    }
}
