package com.resort;

import java.util.Arrays;

/**
 * 希尔排序(业务实现)
 * gap+插入
 *
 * @author NANDI_GUO
 * @date 2023/3/30 12:39
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 1, 5, 3, 2};
        System.out.println(Arrays.toString(arr));
        shellSort05(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void shellSort(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i += gap) {
                int insertVal = arr[i];
                int sortedP = i - gap;
                while (sortedP >= 0 && arr[sortedP] > insertVal) {
                    arr[sortedP + gap] = arr[sortedP];
                    sortedP -= gap;
                }
                arr[sortedP + gap] = insertVal;
            }
        }
    }

    private static void shellSort02(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i += gap) {
                int insertVal = arr[i];
                int sp = i - gap;
                while (sp >= 0 && arr[sp] > insertVal) {
                    arr[sp + gap] = arr[sp];
                    sp -= gap;
                }
                arr[sp + gap] = insertVal;
            }
        }
    }

    private static void shellSort03(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i += gap) {
                int insertVal = arr[i];
                int sp = i - gap;
                while (sp >= 0 && arr[sp] > insertVal) {
                    arr[sp + gap] = arr[sp];
                    sp -= gap;
                }
                arr[sp + gap] = insertVal;
            }
        }
    }

    private static void shellSort04(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int insertVal = arr[i];
                int sp = i - gap;
                while (sp >= 0 && arr[sp] > insertVal) {
                    arr[sp + gap] = arr[sp];
                    sp -= gap;
                }
                arr[sp + gap] = insertVal;
            }
        }
    }

    private static void shellSort05(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int insertVal = arr[i];
                int sp = i - gap;
                while (sp >= 0 && arr[sp] > insertVal) {
                    arr[sp + gap] = arr[sp];
                    sp -= gap;
                }
                arr[sp + gap] = insertVal;
            }
        }
    }
}
