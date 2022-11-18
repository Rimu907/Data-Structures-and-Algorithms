package com.nd.sort;

import java.util.Arrays;

/**
 * 希尔排序(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/11/18 9:42
 */
public class ShellSort {
    public static void main(String[] args) {
        int arr[] = {8,9,1,7,2,3,5,4,6,0};
        shellsort3(arr);
        System.out.println(Arrays.toString(arr));
    }

    //希尔交换排序
    public static void shellsort(int[] arr){

        for (int gap = arr.length/2; gap > 0; gap/=2) { //按步长进行分组
            //按数据量进行分组
            for (int i = gap ; i < arr.length; i++) { //按每组进行遍历，注意从中间开始, 不会越界
                //遍历各组中所有的元素
                for (int j = i - gap ; j >= 0; j -= gap) { //每组元素进行比较，注意第一个元素是 i-gap也就是中间减去gap
                    //如果当前元素大于加上步长的元素，交换
                    if (arr[j] > arr[j+gap]){
                        int temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
        }
    }

    //希尔插入排序
    public static void shellsort2(int[] arr){
        for (int gap = arr.length/2; gap > 0; gap/=2) { //按步长进行分组, 逐步缩小分组
            //按数据量进行分组
            for (int i = gap ; i < arr.length; i++) { //按每组进行遍历，注意从中间开始, 不会越界
                //遍历各组中所有的元素
                int j = i;
                int temp = arr[j];

                while(j-gap >= 0 && temp < arr[j-gap]){
                    arr[j] = arr[j-gap];
                    j-=gap;
                }

                arr[j] = temp;
            }
        }
    }

    //自行理解默写
    public static void shellsort3(int arr[]){
        //指定gap步长
        for (int gap = arr.length/2; gap > 0 ; gap/=2) {
            //指定按组进行遍历
            for (int i = gap; i < arr.length ; i++) {
                //按元素进行插入排序
                int j = i; //下标
                int temp = arr[j];
                //开始插入排序
                while (j-gap>=0 && temp<arr[j-gap]){
                    arr[j] = arr[j-gap];
                    j-=gap;
                }
                arr[j] = temp;
            }
        }
    }
}
