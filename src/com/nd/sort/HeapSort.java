package com.nd.sort;

import java.util.Arrays;

/**
 * 堆排序(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/11/24 18:20
 */
public class HeapSort {
    public static void main(String[] args) {
        //升序排序 大顶堆
        int arr[] = {4,6,8,5,9};
        heapSort(arr);
    }


    public static void heapSort(int arr[]){
        int temp = 0;
        System.out.println("堆排序");
        //分步完成
//        adjustHeap(arr,1,arr.length);
//        System.out.println(Arrays.toString(arr));
//
//        adjustHeap(arr,0, arr.length);
//        System.out.println(Arrays.toString(arr));
        //最终代码
        for(int i = arr.length/2-1; i>=0; i--){
            adjustHeap(arr,i,arr.length);
        }
        System.out.println(Arrays.toString(arr));

        for (int j = arr.length-1; j > 0 ; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
        }
        System.out.println(Arrays.toString(arr));
    }

    //将一个数组调整成一个大顶堆
    /**
     * @description: 完成将以i对应的非叶子节点的树调整成大顶堆
     * @param arr 待调整数组
     * @param i 非叶子节点在数组中的索引
     * @param length 对多少个元素进行调整,长度在逐渐减少
     * @return void
     * @author: NANDI_GUO
     * @date: 2022/11/24 18:24
     */
    public static void adjustHeap(int arr[], int i, int length){

        int temp = arr[i]; //先保存当前元素的值
        //开始调整
        //1. k = i * 2 + 1是i节点的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k+1<length && arr[k] < arr[k + 1]) { //说明左子节点小于右子节点的值
                k++;
            }
            if (arr[k] > temp){ //如果子节点大于父节点
                arr[i] = arr[k]; //把较大的值赋给当前节点
                i = k; //把i指向k，继续循环比较
            } else {
                break;
            }
        }
        //当for循环结束后，我们已经将以i为父节点的数的最大值，放在了这个树最顶部
        arr[i] = temp;
    }
}
