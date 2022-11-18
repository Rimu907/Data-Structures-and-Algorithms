package com.nd.sort;

import java.util.Arrays;

/**
 * 快速排序(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/11/18 11:26
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {1,0,2,-1,0,6,53,27};
        quickSort2(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) { //left最左，right最右
        int l = left;
        int r = right;
        //pivot中轴
        int pivot = arr[(left+right)/2];
        int temp = 0;
        while (l < r){
            //在pivot左边一直找，找到大于等于pivot值才退出 78
            while(arr[l] < pivot){
                l+=1;
            }
            //在pivot右边一直找，找到小于等于pivot值才退出
            while(arr[r] > pivot){
                r-=1;
            }

            //走到这一步，相当于左边都是比pivot小，右边都是比pivot大
            if (l>=r){
                break;
            }

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换结束，发现arr[l] == pivot，前移一下
            if (arr[l] == pivot){
                r-=1;
            }
            if (arr[r] == pivot){
                l+=1;
            }
        }

        //如果 l==r, 必须l++, r--, 否则栈溢出
        if (l==r){
            l++;
            r--;
        }
        if (left < r){
            quickSort(arr, left , r);
        }
        if (right > l){
            quickSort(arr, l , right);
        }
    }

    //自行理解清晰快速排序
    public static void quickSort2(int[] arr, int left, int right){
        //下标错误
        if (left > right){
            return;
        }

        int base = arr[left]; //定义基准数
        int i = left;  //左指针
        int j = right; //右指针

        //遍历检索左右
        while(i != j){
            //从右往左开始检索, 找右边小于base的
            while(arr[j] >= base && i < j){
                j--;
            }

            //从左往右开始检索，找左边大于base
            while(arr[i] <= base && i < j){
                i++;
            }

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        //交换基准数到中间
        arr[left] = arr[i];
        arr[i] = base;

        quickSort2(arr, left, i - 1);
        quickSort2(arr, j + 1, right);
    }
}
