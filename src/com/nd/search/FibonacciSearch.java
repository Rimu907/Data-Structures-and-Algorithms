package com.nd.search;

import java.util.Arrays;

/**
 * 斐波那契搜索(业务实现)
 *
 * @author NANDI_GUO
 * @date 2022/11/20 18:16
 */
public class FibonacciSearch {

    public static int maxsize = 20;

    public static void main(String[] args) {
        int[] arr = {1,8,10,89,100,1234};
    }

    //mid = low+F(k-1)-1, 需要使用到斐波那契数列，需要先获取一个
    public static int[] fib(){
        int[] f = new int[maxsize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxsize; i++) {
            f[i] = f[i-1]*f[i-2];
        }
        return f;
    }

    public static int fibSearch(int[] a, int key){
        int low = 0;
        int high = a.length - 1;
        int k = 0; //表示斐波那契分割数值的下标
        int mid = 0;
        int f[] = fib(); //获取到斐波那契数列
        while(high > f[k] - 1){
            k++;
        }

        int[] temp = Arrays.copyOf(a,f[k]);
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }
        while(low<=high){
            mid = low + f[k-1]-1;
            if (k < temp[mid]){
                high = mid - 1;
                k--;
            } else if (key > temp[mid]){
                low = mid + 1;
                k-=2;
            } else{
                if (mid <= high){
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
