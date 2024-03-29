package com.nd.search;

/**
 * 插值搜索(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/11/20 17:51
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }
        int i = insertValueSearch(array, 0, array.length - 1, 100);
        System.out.println(i);
    }

    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]){
            return -1;
        }

        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];

        if (findVal > midVal){
            return insertValueSearch(arr,mid+1,right,findVal);
        } else if (findVal < midVal){
            return insertValueSearch(arr,left,mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
