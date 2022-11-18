package com.nd.sort;

import java.util.Arrays;

/**
 * 基数排序1(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/11/18 18:07
 */
public class RedixSort {
    public static void main(String[] args) {
        int arr[] = {53,3,542,748,14,214};
        redixsort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void redixsort(int[] arr){
        int max = arr[0];
        for (int n: arr) {
            if (n > max){
                max = n;
            }
        }
        int maxLen = String.valueOf(max).length();

        int[][]buckets = new int[10][arr.length];
        //为了记录每个桶中实际存放了多少个数据，我们定义一个一维数组来记录各个桶中的有效数据
        int[] beCounts = new int[10]; //每个桶中存放的个数 beCounts[0] 就是第一个桶中每次放入数据的个数
        for (int i = 1, n = 1; i <= maxLen; i++, n*=10) {

            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j]/n%10; //每个元素的个位数
                buckets[digitOfElement][beCounts[digitOfElement]] = arr[j]; //放进桶0的第0个位置
                beCounts[digitOfElement]++; //根据桶进行叠加
            }

            //取数据放回原来的数组
            int index = 0;
            for (int k = 0; k < beCounts.length; k++) { //遍历每个桶，取每个桶中的数据
                if (beCounts[k] != 0){
                    for (int l = 0; l < beCounts[k]; l++) { //遍历每个桶中的每个数据，size就是每个桶中的个数，也就是beCounts[k]
                        arr[index++] = buckets[k][l];
                    }
                }
                beCounts[k]=0;
            }
        }
    }
}
