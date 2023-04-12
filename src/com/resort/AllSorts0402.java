package com.resort;

import com.nd.sort.Bubble;

import java.util.Arrays;

/**
 * (业务实现)
 *
 * @author NANDI_GUO
 * @date 2023/4/2 15:11
 */
public class AllSorts0402 {
    public static void main(String[] args) {
        int[] arr = {3, 4, 1, 2, 6, 8, 7, 9, 0, 5};
        int[] temp = new int[arr.length];
        System.out.println(Arrays.toString(arr));
//        bubbleSort(arr);
//        selectSort(arr);
//        insertSort(arr);
//        shellSorts(arr);
//        quickSort(arr, 0, arr.length - 1);
//        mergeSort(arr, 0, arr.length - 1, temp);
//        redixSort(arr);
        heapSort(arr);
        System.out.println("排序后: " + Arrays.toString(arr));
    }

    private static void heapSort(int[] arr) {
        int temp;
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            getHeap(arr, i, arr.length);
        }
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            getHeap(arr, 0, j);
        }
    }

    private static void getHeap(int[] arr, int i, int length) {
        int head = arr[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] > head) {
                arr[i] = arr[k];
                i = k;
            }
        }
        arr[i] = head;
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

        for (int i = 1; i < Math.pow(10, max); i *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int num = arr[j] / i % 10;
                buckets[num][bcount[num]] = arr[j];
                bcount[num]++;
            }
            int temp = 0;
            for (int j = 0; j < bcount.length; j++) {
                if (bcount[j] != 0) {
                    for (int k = 0; k < bcount[j]; k++) {
                        arr[temp++] = buckets[j][k];
                    }
                }
                bcount[j] = 0;
            }
        }
    }

    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
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

    private static void quickSort(int[] arr, int left, int right) {
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

        quickSort(arr, i + 1, right);
        quickSort(arr, left, j - 1);
    }

    private static void shellSorts(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int insertVal = arr[i];
                int sortedP = i - gap;
                while (sortedP >= 0 && arr[sortedP] > insertVal) {
                    arr[sortedP + gap] = arr[sortedP];
                    sortedP -= gap;
                }
                arr[sortedP + gap] = insertVal;
            }
        }
    }

    private static void insertSort(int[] arr) {
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

    private static void selectSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            if (min != i) {
                temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;
            }
        }
    }

    private static void bubbleSort(int[] arr) {
        int temp;
        boolean flag;
        for (int i = 0; i < arr.length - 1; i++) {
            flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                return;
            }
        }
    }
}
