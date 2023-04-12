package com.algorithm.kmp;

/**
 * (业务实现)
 *
 * @author NANDI_GUO
 * @date 2023/4/12 12:07
 */
public class Kmp {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABCDABDE";
        String str2 = "ABCDABD"; //子串
//        String str2 = "BBC"; //子串

        int[] next = kmpNext(str2);
        int index = kmpSearch(str1, str2, next);
         System.out.println(index);
    }

    public static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            //需要处理不相等时j的值
            while(j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j-1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()){
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取到一个字符串的部分匹配值表
    public static int[] kmpNext(String dest) {
        //创建一个数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0; //如果字符串的长度为1， 部分匹配之就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];// AAAB [0,1,2,2]
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
