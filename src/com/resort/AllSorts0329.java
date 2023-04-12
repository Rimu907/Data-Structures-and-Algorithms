package com.resort;

import java.util.Arrays;

import static com.nd.sort.MergeSort.merge;


/**
 * (业务实现)
 *
 * @author NANDI_GUO
 * @date 2023/3/29 16:19
 */
public class AllSorts0329 {
    public static void main(String[] args) {
        int arr[] = {4, 6, 8, 5, 9, 10};
        int[] temp = new int[arr.length];
//        heapSort(arr);
//        redixSort(arr);
        mergeSort(arr, 0, arr.length - 1, temp);
//        quickSort(arr, 0, arr.length - 1);
//        shellSort(arr);
//        insertSort(arr);
//        selectSort(arr);
//        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //堆排序
    //1.先从最后非叶子节点排大顶堆
    //2.首位换位置，尾部是最大元素可排除在外
    //3.重复向后排
    private static void heapSort(int[] arr) {
        int temp;
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            buildHeap(arr, i, arr.length);
        }
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            buildHeap(arr, 0, j);
        }
    }

    //左右先比，调指针，父子在比换位置。循环跳出赋值。
    private static void buildHeap(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k + 1] > arr[k]) {
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k; //i不再指向父节点 指向右节点
            }
        }
        arr[i] = temp; //右节点赋值为初始父节点值
    }

    //基数排序
    //1.先有桶数组0123456789，每个容量得能装下数组中所有数
    //2.与每个桶的统计数组
    //3.遍历次数与最大数的几位有关
    //4.装桶循环几次，最后从桶拿出来
    private static void redixSort(int arr[]) {
        int buckets[][] = new int[10][arr.length];
        int bcount[] = new int[10];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        max = String.valueOf(max).length();

        for (int j = 1; j < Math.pow(10, max); j *= 10) {
            for (int k = 0; k < arr.length; k++) {
                int digit = arr[k] / j % 10;
                buckets[digit][bcount[digit]] = arr[k];
                bcount[digit]++;
            }
            //遍历每个桶中的每个元素
            int temp = 0;
            for (int l = 0; l < bcount.length; l++) {
                if (bcount[l] != 0) {
                    for (int m = 0; m < bcount[l]; m++) {
                        arr[temp++] = buckets[l][m];
                    }
                }
                bcount[l] = 0;
            }
        }
    }

    //归并排序
    //1. 先递归分解
    //2. 再合并，合并是回溯过程
    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid, temp);
        mergeSort(arr, mid + 1, right, temp);
        merges(arr, left, mid, right, temp);
    }

    private static void merges(int[] arr, int left, int mid, int right, int[] temp) {
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

    //快速排序
    //1.定义基准数
    //2.双指针 从右向左 找比基准数小 从左向右 找比基准数大
    private static void quickSort(int arr[], int left, int right) {
        if (left >= right) {
            return;
        }

        int base = arr[left];
        int i = left;
        int j = right;
        int temp;

        while (i < j) {
            while (i < j && arr[j] >= base) {
                j--;
            }
            while (i < j && arr[i] <= base) {
                i++;
            }
            //停下的就是ij交换
            if (arr[i] > arr[j]) {
                temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        //跳出循环 ij撞在一起, 基准数与指针所指换位
        arr[left] = arr[i];
        arr[i] = base;
        //第一轮结束，基准数左右开始递归
        quickSort(arr, left, i - 1);
        quickSort(arr, j + 1, right);
    }

    //希尔排序 {4}{6, 8, 5, 9} 待插1 位置0
    //把无序表插入有序表
    private static void shellSort(int arr[]) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int index = i - gap;
                int insertVal = arr[i];
                while (index >= 0 && insertVal < arr[index]) {
                    arr[index + gap] = arr[index];
                    index -= gap;
                }
                arr[index + gap] = insertVal;
            }
        }
    }

    //插入排序 {4}{6, 8, 5, 9}
    private static void insertSort(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            int index = i - 1;
            int insertVal = arr[i];
            while (index >= 0 && insertVal < arr[index]) {
                arr[index + 1] = arr[index];
                index--;
            }
            arr[index + 1] = insertVal;
        }
    }

    //选择排序 先指 后换
    private static void selectSort(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            int temp;
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

    //冒泡排序
    private static void bubbleSort(int[] arr) {
        int temp;
        boolean flag;
        for (int i = 0; i < arr.length - 1; i++) {
            flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    flag = true;
                }
            }
            if (!flag){
                break;
            }
        }
    }
}
