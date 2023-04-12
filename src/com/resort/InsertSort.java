package com.resort;

import java.util.Arrays;

/**
 * (业务实现)
 * f有序表 w无序表 遍历无序表
 *
 * @author NANDI_GUO
 * @date 2023/3/30 12:04
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {6, 4, 1, 5, 3, 2};
        System.out.println(Arrays.toString(arr));
        insertSort05(arr);
        System.out.println(Arrays.toString(arr));
    }

    //{6}{4，1，5，3，2}
    private static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int p = i - 1;
            while (p >= 0 && arr[p] > insertVal) {
                arr[p + 1] = arr[p];
                p--;
            }
            arr[p + 1] = insertVal;
        }
    }

    private static void insertSort02(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int sortedP = i - 1;
            while (sortedP >= 0 && arr[sortedP] > insertVal) {
                arr[sortedP + 1] = arr[sortedP];
                sortedP--;
            }
            arr[sortedP + 1] = insertVal;
        }
    }

    private static void insertSort03(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int sortedP = i - 1;
            while (sortedP >= 0 && arr[sortedP] > insertVal) {
                arr[sortedP + 1] = arr[sortedP];
                sortedP--;
            }
            arr[sortedP + 1] = insertVal;
        }
    }

    private static void insertSort04(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int sortedP = i - 1;
            while (sortedP >= 0 && arr[sortedP] > insertVal) {
                arr[sortedP + 1] = arr[sortedP];
                sortedP--;
            }
            arr[sortedP + 1] = insertVal;
        }
    }

    private static void insertSort05(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int sortedP = i - 1;
            while (sortedP >= 0 && arr[sortedP] > insertVal) {
                arr[sortedP+1] = arr[sortedP];
                sortedP--;
            }
            arr[sortedP+1] = insertVal;
        }
    }
}
