package com.nd.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 线性查找(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/11/20 13:49
 */
public class SeqSearch {
    public static void main(String[] args) {
        int arr[] = {1,9,11,-1,34,89,11};
        //Integer[] res = seqSearch(arr, 11);
    }

    /**
     * @description: 线性查找，找到一个就返回
     * @param arr
     * @param value
     * @return java.lang.Integer[]
     * @author: NANDI_GUO
     * @date: 2022/11/20 13:56
     */
    public static Integer[] seqSearch(int[] arr, int value){
        List<Integer> list = new ArrayList();
        //线性查找是逐一比对，发现相同就返回下标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value){
                list.add(i);
            }
        }
        return list.toArray(new Integer[list.size()]);
    }
}
