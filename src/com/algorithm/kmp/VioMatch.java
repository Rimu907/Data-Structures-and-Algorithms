package com.algorithm.kmp;

/**
 * (业务实现)
 *
 * @author NANDI_GUO
 * @date 2023/4/11 18:01
 */
public class VioMatch {
    public static void main(String[] args) {

    }

    public static int vioMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0;
        int j = 0;

        while (i < s1Len && j < s2Len) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }

        if (j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }
}
