package com.resort;

import java.util.Arrays;

/**
 * (业务实现)
 * 先分后合
 *
 * @author NANDI_GUO
 * @date 2023/3/30 13:52
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {6, 4, 1, 5, 3, 2, 213, 154, 32, 523, 6436, -12312, 0};
        int[] temp = new int[arr.length];
        System.out.println(Arrays.toString(arr));
        mergeSort06(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid, temp);
        mergeSort(arr, mid + 1, right, temp);
        merge(arr, left, mid, right, temp);
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //声明左右指针
        int i = left;
        int j = mid + 1;
        int pointer = 0;
        //当左指针没到中，右指针没到右
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[pointer++] = arr[i++];
            } else {
                temp[pointer++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[pointer++] = arr[i++];
        }
        while (j <= right) {
            temp[pointer++] = arr[j++];
        }

        pointer = 0;
        while (left <= right) {
            arr[left++] = temp[pointer++];
        }
    }

    private static void mergeSort02(int[] arr, int left, int right, int[] temp) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort02(arr, left, mid, temp);
        mergeSort02(arr, mid + 1, right, temp);
        merge02(arr, left, mid, right, temp);
    }

    private static void merge02(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int p = 0;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[p++] = arr[i++];
            } else {
                temp[p++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[p++] = arr[i++];
        }
        while (j <= right) {
            temp[p++] = arr[j++];
        }

        for (int k = 0; k < p; k++) {
            arr[left + k] = temp[k];
        }
    }

    private static void mergeSort03(int[] arr, int left, int right, int[] temp) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort03(arr, left, mid, temp);
        mergeSort03(arr, mid + 1, right, temp);
        merge03(arr, left, mid, right, temp);

    }

    private static void merge03(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int p = 0;

        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[p++] = arr[i++];
            } else {
                temp[p++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[p++] = arr[i++];
        }
        while (j <= right) {
            temp[p++] = arr[j++];
        }
        for (int k = 0; k < p; k++) {
            arr[left + k] = temp[left];
        }
    }

    private static void mergeSort04(int[] arr, int left, int right, int[] temp) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort04(arr, left, mid, temp);
        mergeSort04(arr, mid + 1, right, temp);
        merge04(arr, left, mid, right, temp);
    }

    private static void merge04(int[] arr, int left, int mid, int right, int[] temp) {
        int p = 0;
        int i = left;
        int j = mid + 1;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[p++] = arr[i++];
            } else {
                temp[p++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[p++] = arr[i++];
        }

        while (j <= right) {
            temp[p++] = arr[j++];
        }

        for (int k = 0; k < p; k++) {
            arr[left + k] = temp[k];
        }
    }

    private static void mergeSort05(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort05(arr, left, mid, temp);
            mergeSort05(arr, mid + 1, right, temp);
            merger05(arr, left, mid, right, temp);
        }
    }

    private static void merger05(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int p = 0;

        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[p++] = arr[i++];
            } else {
                temp[p++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[p++] = arr[i++];
        }
        while (j <= right) {
            temp[p++] = arr[j++];
        }
        for (int k = 0; k < p; k++) {
            arr[left + k] = temp[k];
        }
    }

    private static void mergeSort06(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort06(arr, left, mid, temp);
            mergeSort06(arr, mid + 1, right, temp);
            merge06(arr, left, mid, right, temp);
        }
    }

    private static void merge06(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int p = 0;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]){
                temp[p++] = arr[i++];
            }else {
                temp[p++] = arr[j++];
            }
        }

        while(i <= mid){
            temp[p++] = arr[i++];
        }
        while(j <= right){
            temp[p++] = arr[j++];
        }

        for (int k = 0; k < p; k++) {
            arr[left+k] = temp[k];
        }
    }

}
