package com.nd.sort;

import java.util.Arrays;

import static com.nd.sort.HeapSort.adjustHeap;

/**
 * 复习所有排序(业务实现)
 * 注: 看数据想算法
 *
 * @author NANDI_GUO
 * @date 2023/3/27 18:35
 */
public class AllSortReview {
    public static void main(String[] args) {
        int[] arr = {4, 2, 6, 3, 1, 5, 66, 77, 123};
//        quickSort(arr, 0, arr.length - 1);
        int temp[] = new int[arr.length];
//        mergeSort(0, arr.length - 1, arr, temp);
//        redixSort(arr);
        heapSort(arr);
        System.out.println(String.format("排序结果: %s", Arrays.toString(arr)));
    }

    //冒泡排序
    public static void bubbleSort(int[] arr) {
        int helper;
        boolean flag;
        for (int i = 0; i < arr.length - 1; i++) {        //99和后面比一轮，88和后面比一轮，到11后面已经没了所以不用比了
            flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {   //每一轮都有一个大的冒泡到最后面，那么每一轮都可以少比一个
                if (arr[j] > arr[j + 1]) {
                    helper = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = helper;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    //选择排序 {99,88,77,66,55,44,33,22,11}
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                int helper = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = helper;
            }
        }
    }

    //插入排序 {99,88,77,66,55,44,33,22,11}
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int index = i - 1;
            while (index >= 0 && insertVal < arr[index]) {
                arr[index + 1] = arr[index];
                index--;
            }
            arr[index + 1] = insertVal;
        }
    }

    //希尔排序 {99,88,77,66,55,44,33,22,11}
    public static void shellSort(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int insertVal = arr[i];
                int index = i - gap;
                while (index >= 0 && arr[index] > insertVal) {
                    arr[index + gap] = arr[index];
                    index -= gap;
                }
                arr[index + gap] = insertVal;
            }
        }
    }

    //快速排序 {4,2,6,3,1,5} 双指针
    public static void quickSort(int[] arr, int left, int right) {
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
            if (arr[i] <= arr[j]) {
                continue;
            }
            temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
        arr[left] = arr[i];
        arr[i] = base;

        quickSort(arr, j + 1, right);
        quickSort(arr, left, i - 1);

    }

    //归并排序 {4,2,6,3,1,5} {1,2,3}{4,5,6} 先分后合
    public static void mergeSort(int left, int right, int[] arr, int[] helper) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(left, mid, arr, helper);
            mergeSort(mid + 1, right, arr, helper);
            merge(left, mid, right, arr, helper);
        }
    }

    private static void merge(int left, int mid, int right, int[] arr, int[] helper) {
        int i = left;
        int j = mid + 1;
        int helperIndex = 0;

        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                helper[helperIndex++] = arr[i++];
            } else {
                helper[helperIndex++] = arr[j++];
            }
        }

        while (i <= mid) {
            helper[helperIndex++] = arr[i++];
        }
        while (j <= right) {
            helper[helperIndex++] = arr[j++];
        }

        int t = 0;
        while (left <= right) {
            arr[left++] = helper[t++];
        }

    }

    //基数排序{4,2,6,3,1,5} 十个 每个计数
    public static void redixSort(int arr[]) {
        //定义10个桶子 每个桶子能装下所有数据
        int[][] buckets = new int[10][arr.length];
        //数每个桶子里有几个数
        int[] bcount = new int[10];
        //找最大数是几位
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) max = arr[i];
        }
        max = String.valueOf(max).length();
        //遍历次数得到
        for (int i = 1; i < Math.pow(10, max); i *= 10) {
            //从个位开始排序
            for (int j = 0; j < arr.length; j++) {
                int digit = arr[j] / i % 10; //为下标
                //放入桶子
                buckets[digit][bcount[digit]] = arr[j];
                bcount[digit]++;
            }

            //放入完毕，开始取数字
            int index = 0;
            for (int k = 0; k < bcount.length; k++) { //遍历每个桶
                if (bcount[k] != 0) {
                    for (int l = 0; l < bcount[k]; l++) { //遍历桶每个元素
                        arr[index++] = buckets[k][l];
                    }
                }
                bcount[k] = 0;
            }
        }
    }

    //堆排序{4,2,6,3,1,5}
    public static void heapSort(int arr[]) {
        int temp;
        //先从最终非叶子节点开始构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        //换位, 排序剩下的堆
        for (int j = arr.length - 1; j > 0; j--) {
            //换位
            temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;
            adjustHeap(arr, 0, j);
        }
    }

    private static void adjustHeap(int[] arr, int i, int length) {
        //父节点
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k++) {
            //右节点比左大就指向右
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            //右大于父就换位
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }


}
