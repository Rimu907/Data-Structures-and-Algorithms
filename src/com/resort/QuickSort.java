package com.resort;

import java.util.Arrays;

/**
 * (业务实现)
 * 双指针 基准数 左右换位 基准数换位
 *
 * @author NANDI_GUO
 * @date 2023/3/30 13:07
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 1, 5, 3, 2};
        System.out.println(Arrays.toString(arr));
        quickSort05(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left;
        int j = right;
        int base = arr[left];
        int temp;
        while (i < j) {
            while (i < j && arr[j] >= base) {
                j--;
            }
            while (i < j && arr[i] <= base) {
                i++;
            }
            if (arr[i] >= arr[j]) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[left] = arr[i];
        arr[i] = base;
        quickSort(arr, i + 1, right);
        quickSort(arr, left, j - 1);
    }

    private static void quickSort02(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }

        int i = left;
        int j = right;
        int base = arr[left];
        int temp;

        while (i < j) {
            while (i < j && arr[j] >= base) {
                j--;
            }
            while (i < j && arr[i] <= base) {
                i++;
            }
            if (arr[i] > arr[j]) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[left] = arr[i];
        arr[i] = base;

        quickSort(arr, j + 1, right);
        quickSort(arr, left, i - 1);
    }

    private static void quickSort03(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }
        int i = left;
        int j = right;
        int base = arr[left];
        int temp;

        while (i < j) {
            while (i < j && arr[j] >= base) {
                j--;
            }
            while (i < j && arr[i] <= base) {
                i++;
            }
            if (arr[i] > arr[j]) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[left] = arr[i];
        arr[i] = base;

        quickSort03(arr, i + 1, right);
        quickSort03(arr, left, i - 1);
    }

    private static void quickSort04(int[] arr, int left, int right) {
        if (left > right) {
            return;
        }

        int temp;
        int base = arr[left];
        int i = left;
        int j = right;

        while (i < j) {
            while (i < j && arr[j] >= base) {
                j--;
            }
            while (i < j && arr[i] <= base) {
                i++;
            }
            if (arr[i] > arr[j]) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[left] = arr[i];
        arr[i] = base;

        quickSort04(arr, i + 1, right);
        quickSort04(arr, left, i - 1);
    }

    private static void quickSort05(int[] arr, int left, int right) {
        if (left > right){
            return;
        }

        int temp;
        int base = arr[left];
        int i = left;
        int j = right;

        while (i < j) {
            while (i < j && arr[j] >= base) {
                j--;
            }
            while (i < j && arr[i] <= base) {
                i++;
            }
            if (arr[i] > arr[j]) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[left] = arr[i];
        arr[i] = base;

        quickSort05(arr, left, i - 1);
        quickSort05(arr, i + 1, right);
    }
}
