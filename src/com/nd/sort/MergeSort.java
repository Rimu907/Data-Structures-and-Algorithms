package com.nd.sort;

import java.util.Arrays;

/**
 * 归并排序(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/11/18 14:11
 */
public class MergeSort {
    public static void main(String[] args) {
        int arr[] = {8,4,6,7,1,3,6,2};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length-1,temp);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int left, int right, int temp[]) {
        if (left >= right){
            return;
        }
        int mid = (left + right)/2;
        //向左递归进行分解
        mergeSort(arr, left, mid, temp);
        //向右
        mergeSort(arr, mid+1, right, temp);

        merge(arr,left,mid,right,temp);
    }

    /**
     * @description:
     * @param arr 原始数组
     * @param left 左边有序列表初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp 中专数组
     * @return void
     * @author: NANDI_GUO
     * @date: 2022/11/18 14:12
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp){
        int i = left;    //左边初始索引
        int j = mid + 1; //右边初始索引
        int t = 0;  //temp辅助数组的索引

        while (i <= mid && j <= right){
//            if (arr[i] < arr[j]){           //左边比右边小，左边放进temp数组
//                temp[t++] = arr[i++];       //放进去之后都向右移位
//            } else {                        //右比左边小，右边放进temp数组
//                temp[t++] = arr[j++];       //放进去之后都向右移位
//            }
            temp[t++] = arr[i] < arr[j]? arr[i++]:arr[j++];
        }
        //填充左边
        while (i <= mid){
            temp[t++] = arr[i++];
        }

        while (j <= right){
            temp[t++] = arr[j++];
        }

        t = 0;
        int tempLeft = left;
        while(tempLeft <= right){
            arr[tempLeft++] = temp[t++];
        }
    }
}
