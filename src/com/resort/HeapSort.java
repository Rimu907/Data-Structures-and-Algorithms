package com.resort;

import java.util.Arrays;

/**
 * (业务实现)
 * 先搞大顶堆，在搞剩下的
 * 左右比，父子比
 * 首位换位置
 *
 * @author NANDI_GUO
 * @date 2023/3/30 17:25
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {6, 4, 1, 5, 3, 2, 213, 154, 32, 523, 6436, -12312, 0};
        int[] temp = new int[arr.length];
        System.out.println(Arrays.toString(arr));
        heapSort03(arr);
        System.out.println(Arrays.toString(arr));
    }

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
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k++) {
            if (k + 1 < length && arr[k + 1] > arr[k]) {
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            }
        }
        arr[i] = temp;
    }

    private static void heapSort02(int[] arr) {
        int temp;
        //初次搞大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap02(arr, i, arr.length);
        }
        //搞剩下的，数组头是最大，换到最后在排
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap02(arr, 0, j);
        }
    }

    private static void adjustHeap02(int[] arr, int i, int length) {
        int temp = arr[i]; //存头节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k + 1] > arr[k]) {
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k]; //头节点 = 最大的节点
                i = k; //指向最大的节点
            }
        }
        arr[i] = temp;
    }

    private static void heapSort03(int[] arr) {
        int temp;
        //大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap03(arr, i, arr.length);
        }
        //去尾排顶堆
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap03(arr, 0, j);
        }
    }

    private static void adjustHeap03(int[] arr, int i, int length) {
        int temp = arr[i];
        //左右比
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] > temp){
                arr[i] = arr[k];
                i = k;
            }
        }
        arr[i] = temp;
    }
}
