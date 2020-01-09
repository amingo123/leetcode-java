package _3LongestSubstringWithoutRepeatingCharacters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _3LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));//3
        System.out.println(lengthOfLongestSubstring("bbbbb"));//1
        System.out.println(lengthOfLongestSubstring("pwwkew"));//3
        System.out.println(lengthOfLongestSubstring("dvdf"));//3
        System.out.println(lengthOfLongestSubstring(" "));//1
        System.out.println(lengthOfLongestSubstring("abba"));//2
    }

    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        final char[] chars = s.toCharArray();
        int currentmax = 0,max = 0;
        for (int i = 0, j = 0,num = 0; i < chars.length; i++) {
            if(hashMap.containsKey(chars[i]))
            {
                num = hashMap.get(chars[i]);
                j = Math.max(j,num);
                if(currentmax > max) {
                    max = currentmax;
                }
                hashMap.put(chars[i], i);
                currentmax = i - j;
            }
            else
            {
                hashMap.put(chars[i], i);
                currentmax++;
            }
        }
        return Math.max(max,currentmax);
    }

        public static int lengthOfLongestSubstring1(String s) {
            HashMap<Character, Integer> hashMap = new HashMap<>();
            final char[] chars = s.toCharArray();
            int max = 0;
            int num;
            for (int i = 0; i < chars.length; i++) {
                if(hashMap.containsKey(chars[i]))
                {
                    num = hashMap.get(chars[i]);
                    if(hashMap.size() > max)
                    {
                        max = hashMap.size();
                    }
                    hashMap.clear();
                    i = num;
                }
                else
                {
                    hashMap.put(chars[i], i);
                }

            }
            return Math.max( max,hashMap.size());
        }
}
