package com.re.sort;

import java.util.Arrays;

/**
 * (业务实现)
 *
 * @author NANDI_GUO
 * @date 2023/3/31 10:19
 */
public class AllSorts0331 {
    public static void main(String[] args) {
        int arr[] = {4, 6, 8, 5, 9, 3, 1, 2, 7};
        System.out.println(Arrays.toString(arr));
        int[] temp = new int[arr.length];
//        bubbleSort(arr);
//        selectSort(arr);
//        insertSort(arr);
//        shellSort(arr);
//        quickSort(arr, 0, arr.length - 1);
//        mergeSort(arr, 0, arr.length - 1, temp);
//        redixSort(arr);
        heapSort(arr);
        System.out.println("排序结果: " + Arrays.toString(arr));
    }

    //大顶堆，换位置，大顶堆
    private static void heapSort(int[] arr) {
        int temp;
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
    }

    private static void adjustHeap(int[] arr, int i, int length) {
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

        for (int i = 1; i < Math.pow(10, max); i++) {
            for (int j = 0; j < arr.length; j++) {
                int num = arr[j] / i % 10;
                buckets[num][bcount[num]] = arr[j];
                bcount[num]++;
            }
            //取
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

    //递归分 回溯合
    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    //{1,2,3}{4,5,6}
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
            if (arr[i] > arr[j]) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[left] = arr[i];
        arr[i] = base;

        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }

    private static void shellSort(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i += gap) {
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
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
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
                break;
            }
        }
    }

}
