package com.nd.sort;

import java.util.Arrays;

/**
 * 冒泡排序(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/11/13 17:25
 */
public class Bubble {
    public static void main(String[] args) {
        int arr[] = {3,9,-1,10,-2};

        int temp ;
        boolean flag = false;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j] > arr[j+1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if (!flag) {
                break;
            }else{
                flag=false;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
