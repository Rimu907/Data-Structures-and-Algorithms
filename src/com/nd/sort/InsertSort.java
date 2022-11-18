package com.nd.sort;

import java.util.Arrays;

/**
 * 插入排序(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/11/17 19:12
 */
public class InsertSort {
    public static void main(String[] args) {
        int arr[] = {101,34,119,1};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++){
            int insertVal = arr[i];  //定义待插入的数
            int insertIndex = i-1; //往arr[i]的前面插

            //给insert value找到插入的位置
            while (insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertVal;
        }
    }
}
