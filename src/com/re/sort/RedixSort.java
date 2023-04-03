package com.re.sort;

import java.util.Arrays;
import java.util.Map;

/**
 * (业务实现)
 * 桶排序 最大位数 循环放 循环取
 *
 * @author NANDI_GUO
 * @date 2023/3/30 16:50
 */
public class RedixSort {
    public static void main(String[] args) {
        int[] arr = {6, 4, 1, 5, 3, 2};
        int[] temp = new int[arr.length];
        System.out.println(Arrays.toString(arr));
        redixSort3(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void redixSort(int[] arr) {
        int[][] buckets = new int[10][arr.length];
        int[] bcount = new int[10];

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        max = String.valueOf(max).length();

        for (int n = 1; n < Math.pow(10, max); n *= 10) {
            for (int i = 0; i < arr.length; i++) {
                int digit = arr[i] / n % 10;
                buckets[digit][bcount[digit]] = arr[i];
                bcount[digit]++;
            }

            int index = 0;
            for (int i = 0; i < bcount.length; i++) {
                if (bcount[i] != 0) {
                    for (int j = 0; j < bcount[i]; j++) {
                        arr[index++] = buckets[i][j];
                    }
                }
                bcount[i] = 0;
            }
        }
    }

    private static void redixSort2(int[] arr) {
        int[][] buckets = new int[10][arr.length];
        int[] bcount = new int[10];

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        max = String.valueOf(max).length(); //得到最大位数

        for (int i = 1; i < Math.pow(10, max); i *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int digit = arr[j] / i % 10;
                buckets[digit][bcount[digit]] = arr[j];
                bcount[digit]++;
            }
            int index = 0;
            for (int j = 0; j < bcount.length; j++) {
                if (bcount[j] != 0) {
                    for (int k = 0; k < bcount[j]; k++) {
                        arr[index++] = buckets[j][k];
                    }
                }
                bcount[j] = 0;
            }
        }
    }

    private static void redixSort3(int[] arr) {
        int[][] buckets = new int[10][arr.length];
        int[] bcount = new int[10];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        max = String.valueOf(max).length();

        for (int i = 1; i < Math.pow(10, max); i *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int digit = arr[j] / i % 10;
                buckets[digit][bcount[digit]] = arr[j];
                bcount[digit]++;
            }
            int index = 0;
            for (int j = 0; j < bcount.length; j++) {
                if (bcount[j] != 0) {
                    for (int k = 0; k < bcount[j]; k++) {
                        arr[index++] = buckets[j][k];
                    }
                }
                bcount[j] = 0;
            }
        }
    }

}
