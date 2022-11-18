package com.nd.sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 选择排序(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/11/17 17:58
 */
public class SelectSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[4];
        System.out.println("请输入数组值：");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.println("未排序前：" + Arrays.toString(arr));
        long s = System.currentTimeMillis();
        int[] res = selectSort(arr);
        long e = System.currentTimeMillis();
        long r = e-s;
        System.out.println("未排序前：" + Arrays.toString(res));
        System.out.println("时间=" + r);
    }

    public static int[] selectSort(int[] arr){

        for (int i = 0; i < arr.length-1; i++){
            //假定最小为第一个数
            int minIndex = i;
            int min = arr[i];
            //循环找出后面最小的数
            for (int j = i+1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            //找到了minIndex的值肯定就变了不是i
            //已经是最小就不变了，否则交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
        return arr;
    }
}
